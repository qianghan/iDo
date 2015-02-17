package neo4j.plugins;

import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import play.Application;
import play.Logger;
import play.Plugin;
import play.api.Play;
import java.lang.annotation.Annotation;

public class Neo4JPlugin extends Plugin {

  private static ThreadLocal<ServiceProvider> neo4jProvider = new ThreadLocal<ServiceProvider>();
  private final Application application;
  private static AnnotationConfigApplicationContext springContext = null;
  private final static String SERVICE_PROVIDER_NAME_CFG = "neo4j.serviceProviderClass";
  private static Class<?> serviceProviderClass = null;

  public Neo4JPlugin(Application application) {
    this.application = application;
  }

  public void onStart() {
    String serviceProviderClassName = ConfigFactory.load().getString(SERVICE_PROVIDER_NAME_CFG);
    if(StringUtils.isEmpty(serviceProviderClassName) == true) {
      if(Logger.isErrorEnabled()) {
        Logger.error("No configuration for the neo4h ServiceProvider found: "+ SERVICE_PROVIDER_NAME_CFG +" must be set for this plugin.");
      }
      return;
    }

    try {
      final ClassLoader classLoader = Play.classloader(Play.current());
      serviceProviderClass = Class.forName(serviceProviderClassName,false,classLoader);
      Annotation annotation = serviceProviderClass.getAnnotation(Component.class);
      if(annotation == null) {
        if(Logger.isErrorEnabled()) {
          Logger.error("Class : "+serviceProviderClassName+" must be annotated with: "+Component.class.getCanonicalName());
        }
        return;
      }
    } catch (ClassNotFoundException e) {
      if(Logger.isErrorEnabled()) {
        Logger.error("Error while getting Neo4J class from configuration: "+ SERVICE_PROVIDER_NAME_CFG +" = "+serviceProviderClassName,e);
      }
      return;
    }

    final String mode = ConfigFactory.load().getString("neo4j.mode");
    if(mode.equals("embedded")) {
      springContext = new AnnotationConfigApplicationContext(EmbbededNeo4jConfig.class);
    }

    if(mode.equals("remote")) {
      springContext = new AnnotationConfigApplicationContext(RestNeo4jConfig.class);
    }

    if(springContext == null) {
      Logger.error("Could not load config must be: embedded or embeddedWithWebServer");
    }

    springContext.start();
    //springContext.scan();
    springContext.getAutowireCapableBeanFactory().autowireBean(serviceProviderClass);
    springContext.registerShutdownHook();
  }

  public void onStop() {
    Neo4JPlugin.get().template.getGraphDatabaseService().shutdown();
    springContext.stop();
  }

  private boolean isPluginDisabled() {
    String status = application.configuration().getString("neo4jplugin");
    return status != null && status.equals("disabled");
  }

  @Override
  public boolean enabled() {
    return isPluginDisabled() == false;
  }

  //STFU INTELLIJ
  public void $init$() {
  }

  public static <E extends ServiceProvider> E get() {
    neo4jProvider.set((ServiceProvider) springContext.getBean(serviceProviderClass));
    return (E) neo4jProvider.get();
  }

  /**
  * Run a block of code in a NEO4J transaction.
  *
  * @param block Block of code to execute.
  */
  public static <T> T withTransaction(play.libs.F.Function0<T> block) throws Throwable {
    try {
      Neo4JPlugin.get().template.getGraphDatabase().getTransactionManager().begin();
      T result = block.apply();
      Neo4JPlugin.get().template.getGraphDatabase().getTransactionManager().commit();
      return result;
    } catch (Throwable t) {
      Neo4JPlugin.get().template.getGraphDatabase().getTransactionManager().rollback();
      throw t;
    } finally {
    }
  }
}
