#uLog 0.2.2

uLog is a service that handles the remote uShipApp logging. 

It takes remote logging requests, and log them to the uLog application logs, with logger [uShipApp].

The log level, and related configuration can be modified under /modules/uLog/conf/logger.xml

uLog service access endpoint is 

```
http://uLog.machool.com:9000

```
Only one API is provided currently: 

```
POST  /logger with log json

```
