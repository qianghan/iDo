## uServices Platform [Play 2.3 - Scala]

This is a multiproject domain that includes the following subprojects:

* `uShip.machool.com`: Shipping service
* `uMan.machool.com`: User Management service
* `uCommon.machool.com` : Domain model & common library

These services will be isolated in one domain and to be managed seperately (with different PIDs, different ports, different resources…).

We have the following objectives:

* Development should be simple. `activator run` should be enough to run all services at the same time.
* Common code, dependencies and modules should be easily shared.
* We should be able to compile, test and run each service separately in development and production.
* We should distribute each service separately.
* It should be a template ready to use with the following features:
  * [Webjars](http://www.webjars.org).
  * [CoffeeScript](http://coffeescript.org) and [LESS](http://lesscss.org) Assets.
  * [Assets with RequireJS, Digest, Etag, Gzip, Fingerprint](http://www.playframework.com/documentation/2.3.x/Assets).
* It shoud explain:
  * How to share every common code to avoid duplications (models, controllers, views, CoffeeScript, LESS, ...).
  * How to use it for development, test and production.

### Multiproject

This is the basic structure of the whole project:

```
uServices
 └ build.sbt
 └ app
   └ Global.scala
 └ conf
   └ application.conf
   └ routes
 └ project
   └ build.properties
   └ plugins.sbt
   └ Common.scala
 └ modules
   └ uShip
     └ build.sbt
     └ app
	   └ GlobalUShip.scala
       └ assets
         └ javascripts
           └ main-uShip.coffee
           └ uShip.coffee
           └ uShip
             └ otherLib.coffee
         └ stylesheets
           └ uShip
             └ uShip.less
       └ controllers
         └ uShip
           └ Application.scala
           └ Assets.scala
       └ models
         └ uShip
           └ Models.scala
       └ views
	       └ uShip
             └ index.scala.html
             └ main.scala.html
     └ conf
       └ application.conf
       └ prod.conf
       └ uShip.routes
     └ public
       └ images
         └ uShip
     └ test
   └ uMan
     └ ...
   └ uCommon
     └ ...
```

Let's try to explain briefly how it is configured. For running the whole project we have the following configuration files:

* `build.sbt`: configures root project and declares every subproject.
* `conf/application.conf` _(used when whole project is running)_: the default one. In the next section it is explained in detail.
* `conf/routes` _(used when whole project is running)_: routes file for the whole project. It simply imports the routes file of every subproject.
* `app/Global.scala` _(used when whole project is running)_: the GlobalSettings object for the whole project. It determines the subdomain for each request (admin or web) and delegates its behaviour to the corresponding subproject.

And for running each subproject independently:

* `modules/[subproject]/build.sbt`: configures the [subproject].
* `modules/[subproject]/conf/application.conf` _(used when only this subproject is running)_: the default one, it declares the routes file and GlobalSettings object for the subproject while running, testing or distributing only this subproject.
* `modules/[subproject]/conf/[subproject].routes` _(used when only this subproject is running)_: routes file for this subproject.
* `modules/[subproject]/app/Global[Subproject].scala` _(used when only this subproject is running)_: the GlobalSettings object for this subproject.

The common code for every  `build.sbt` file is defined at:

* `project/Common.scala`:  contains all the shared common variables and code for sbt files.

And the rest of relevant folders and files are:

* `modules/[subproject]/app/assets/javascripts/`: folder for CoffeeScript files of this subproject. Take care with the last folder for avoid namespace problems while running the whole project.
* `modules/[subproject]/app/assets/stylesheets/[subproject]/`: folder for LESS files of this subproject. Take care with the last folder for avoid namespace problems while running the whole project.
* `modules/[subproject]/app/controllers/[subproject]/`: folder for the controllers of this subproject. Take care with the last folder for avoid namespace problems while running the whole project.
* `modules/[subproject]/app/controllers/[subproject]/Assets.scala`: it is necessary to implement an `object Assets extends controllers.AssetsBuilder` for every subproject.
* `modules/[subproject]/app/views/[subproject]/`: folder for the views of this subproject. Take care with the last folder for avoid namespace problems while running the whole project.
* `modules/[subproject]/public/`: folder for every public file of this subproject. Take care with possible namespace problems while running the whole project.
* `modules/[subproject]/test/`: folder for every test file for this subproject.

Please check the _Splitting the route file_ section within the documentation page about [SBT Sub-projects](http://www.playframework.com/documentation/2.3.x/SBTSubProjects).

### Configuration files

As we want to run or test the whole project and also run, test or dist _admin_ and _web_ subprojects, we have several configuration files. Each one has its own particular purpose:

* `conf/application.conf`: the configuration file that is called by default when the whole project is running. It simply includes the `shared.dev.conf` file.
* `conf/shared.dev.conf`: declares all the development configuration shared for the whole project and every subproject.
* `conf/shared.prod.conf`: includes the `shared.dev.conf` file and overrides every configuration that is specific for production and it is shared for the whole project and every subproject.

* `modules/[subproject]/conf/application.conf`: the configuration file that is called by default when the [subproject] is running. It simply includes the `shared.dev.conf` and `[subproject].conf` files.
* `modules/[subproject]/conf/[subproject].conf`: declares the specific configuration for this subproject for development or production. It must declare the Global object and route file for this subproject.
* `modules/[subproject]/conf/prod.conf`: declares the specific configuration for this subproject for production. It includes the `shared.prod.conf` and `[subproject].conf` files.
* `modules/[subproject]/conf/shared.dev.conf`: it is simply a copy of `conf/shared.dev.conf`. It must be copied here to be available for production distribution.
* `modules/[subproject]/conf/shared.prod.conf`: it is simply a copy of `conf/shared.prod.conf`. It must be copied here to be available for production distribution.

It has been added a key called `this.file` in many of the configuration files and it is shown in the index web page when you run it. Please, play with it to see how it is overridden by each configuration file depending the project and mode (dev or prod) you are running.

__Tip:__ as files `shared.dev.conf` and `shared.prod.conf` for every subproject are the same as the general ones, you can use _aliases_ or _symbolic links_ for them in order to avoid to maintain all of them.

### Development

First of all, to get access to `uShip` subdomain you will need modify your `/etc/hosts` files (or the equivalent in your S.O.) to map the next URLs to `localhost` or (`127.0.0.1`). For example, add the following lines:

    127.0.0.1	uShip.machool.com
    127.0.0.1	uMan.machool.com

Then, simply execute:

    $ activator run

or

    [play-multidomain-seed] $ run

And that's all! The whole project will run using the `conf/application.conf` file enabling all the services at once. You can go with your browser and check the URLs:

* `uShip.machool.com:9000`: uShip service
* `uMan.machool.com:9000`: uMan service

As you can see, you must add the default `9000` port, but you can use the port you want with the parameter with `activator run -Dhttp.port=9001`.

If you want to run only one subproject separately, you have to get into the subproject and run:

    [play-multidomain-seed] $ project uShip
	[admin] $ run


### Test

Each subproject has its own test files within the folder `modules/[subproject]/test`.

To run the tests for every subproject at once, simply execute:

    [play-multidomain-seed] $ test

And for a unique subproject, get into it and test it:

    [play-multidomain-seed] $ project uShip
	[admin] $ test

### Production

Simply execute:

    $ activator dist

or

    [play-multidomain-seed] $ dist

Now you have a zip file for each module.

    /play-multidomain-seed/modules/uShip/target/universal/uShip-1.0-SNAPSHOT.zip
    /play-multidomain-seed/modules/uMan/target/universal/uMan-1.0-SNAPSHOT.zip

So you can extract wherever you want and execute them separately. For example with:

    ./admin-1.0-SNAPSHOT/bin/admin -Dconfig.resource=prod.conf -Dhttp.port=9001 -Dapplication.secret=abcdefghijk &

Note it is added the `&` at the end to run the app in the background. The PID will be stored in `RUNNING_PID` file, so when you want to stop the app, just execute:

    kill $(cat path/to/RUNNING_PID)

If you would like to test the whole project in production mode, you should be able to execute the start command as:

    [play-multidomain-seed] $ start

Please, check the documentation about [Production Configuration](http://www.playframework.com/documentation/2.3.x/ProductionConfiguration) for more parameters. And also check about [Application Secret](http://www.playframework.com/documentation/2.3.x/ApplicationSecret).

### Thanks to

http://www.playframework.com/documentation/2.3.x/SBTSubProjects

http://eng.kifi.com/multi-project-deployment-in-play-framework/ -> https://github.com/kifi/multiproject

http://parleys.com/play/527f7a92e4b084eb60ac7732/chapter17/about
