# MRE for micronaut-core issue 7887

This repository represents a minimal, reproducible example, showing Micronaut failing to start up in versions 3.6.0 and 3.6.1, with default configurations.

This MRE relates to the following GitHub issues:

* <https://github.com/micronaut-projects/micronaut-core/issues/7887>
* <https://github.com/micronaut-projects/micronaut-core/issues/7869>

## Hints about the MRE

Reproducing the error should be as simple as running `./gradlew run`, using Java 11 or higher (8 would probably work too).

Following the expected failing output:

```console
$ ./gradlew run
> Task :run
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_ 
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_ 
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
  Micronaut (v3.6.1)
[main] INFO  org.hibernate.Version - HHH000412: Hibernate ORM core version 5.6.10.Final
[main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
[main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
[main] INFO  o.h.annotations.common.Version - HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
[main] INFO  org.hibernate.dialect.Dialect - HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
[main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: Bean definition [io.micronaut.transaction.async.AsyncTransactionOperations] could not be loaded: Failed to inject value for parameter [coroutineTxHelper] of method [buildPrimaryAsyncTransactionOperations] of class: io.micronaut.transaction.async.AsyncTransactionOperations

Message: No bean of type [io.micronaut.transaction.interceptor.CoroutineTxHelper] exists. Make sure the bean is not disabled by bean requirements (enable trace logging for 'io.micronaut.context.condition' to check) and if the bean is enabled then ensure the class is declared a bean and annotation processing is enabled (for Java and Kotlin the 'micronaut-inject-java' dependency should be configured as an annotation processor).
Path Taken: AsyncTransactionOperations.buildPrimaryAsyncTransactionOperations(SynchronousTransactionManager synchronousTransactionManager,CoroutineTxHelper coroutineTxHelper) --> AsyncTransactionOperations.buildPrimaryAsyncTransactionOperations(SynchronousTransactionManager synchronousTransactionManager,[CoroutineTxHelper coroutineTxHelper])
io.micronaut.context.exceptions.BeanInstantiationException: Bean definition [io.micronaut.transaction.async.AsyncTransactionOperations] could not be loaded: Failed to inject value for parameter [coroutineTxHelper] of method [buildPrimaryAsyncTransactionOperations] of class: io.micronaut.transaction.async.AsyncTransactionOperations

Message: No bean of type [io.micronaut.transaction.interceptor.CoroutineTxHelper] exists. Make sure the bean is not disabled by bean requirements (enable trace logging for 'io.micronaut.context.condition' to check) and if the bean is enabled then ensure the class is declared a bean and annotation processing is enabled (for Java and Kotlin the 'micronaut-inject-java' dependency should be configured as an annotation processor).
Path Taken: AsyncTransactionOperations.buildPrimaryAsyncTransactionOperations(SynchronousTransactionManager synchronousTransactionManager,CoroutineTxHelper coroutineTxHelper) --> AsyncTransactionOperations.buildPrimaryAsyncTransactionOperations(SynchronousTransactionManager synchronousTransactionManager,[CoroutineTxHelper coroutineTxHelper])
        at io.micronaut.context.DefaultBeanContext.initializeContext(DefaultBeanContext.java:1921)
        at io.micronaut.context.DefaultApplicationContext.initializeContext(DefaultApplicationContext.java:245)
        at io.micronaut.context.DefaultBeanContext.readAllBeanDefinitionClasses(DefaultBeanContext.java:3326)
        at io.micronaut.context.DefaultBeanContext.finalizeConfiguration(DefaultBeanContext.java:3684)
        at io.micronaut.context.DefaultBeanContext.start(DefaultBeanContext.java:341)
        at io.micronaut.context.DefaultApplicationContext.start(DefaultApplicationContext.java:190)
        at io.micronaut.runtime.Micronaut.start(Micronaut.java:75)
        at com.example.Application.main(Application.java:10)
Caused by: io.micronaut.context.exceptions.DependencyInjectionException: Failed to inject value for parameter [coroutineTxHelper] of method [buildPrimaryAsyncTransactionOperations] of class: io.micronaut.transaction.async.AsyncTransactionOperations

Message: No bean of type [io.micronaut.transaction.interceptor.CoroutineTxHelper] exists. Make sure the bean is not disabled by bean requirements (enable trace logging for 'io.micronaut.context.condition' to check) and if the bean is enabled then ensure the class is declared a bean and annotation processing is enabled (for Java and Kotlin the 'micronaut-inject-java' dependency should be configured as an annotation processor).
Path Taken: AsyncTransactionOperations.buildPrimaryAsyncTransactionOperations(SynchronousTransactionManager synchronousTransactionManager,CoroutineTxHelper coroutineTxHelper) --> AsyncTransactionOperations.buildPrimaryAsyncTransactionOperations(SynchronousTransactionManager synchronousTransactionManager,[CoroutineTxHelper coroutineTxHelper])
        at io.micronaut.context.AbstractInitializableBeanDefinition.resolveBean(AbstractInitializableBeanDefinition.java:2087)
        at io.micronaut.context.AbstractInitializableBeanDefinition.getBeanForConstructorArgument(AbstractInitializableBeanDefinition.java:1297)
        at io.micronaut.data.hibernate.transaction.$HibernateTransactionManagersFactory$BuildPrimaryAsyncTransactionOperations0$Definition.doBuild(Unknown Source)
        at io.micronaut.context.AbstractInitializableBeanDefinition.build(AbstractInitializableBeanDefinition.java:769)
        at io.micronaut.context.BeanDefinitionDelegate.build(BeanDefinitionDelegate.java:158)
        at io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2354)
        at io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2305)
        at io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2251)
        at io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3016)
        at io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
        at io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:2918)
        at io.micronaut.context.DefaultBeanContext.loadContextScopeBean(DefaultBeanContext.java:2737)
        at io.micronaut.context.DefaultBeanContext.initializeContext(DefaultBeanContext.java:1915)
        ... 7 common frames omitted
Caused by: io.micronaut.context.exceptions.NoSuchBeanException: No bean of type [io.micronaut.transaction.interceptor.CoroutineTxHelper] exists. Make sure the bean is not disabled by bean requirements (enable trace logging for 'io.micronaut.context.condition' to check) and if the bean is enabled then ensure the class is declared a bean and annotation processing is enabled (for Java and Kotlin the 'micronaut-inject-java' dependency should be configured as an annotation processor).
        at io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2805)
        at io.micronaut.context.DefaultBeanContext.getBean(DefaultBeanContext.java:1617)
        at io.micronaut.context.AbstractBeanResolutionContext.getBean(AbstractBeanResolutionContext.java:66)
        at io.micronaut.context.AbstractInitializableBeanDefinition.resolveBean(AbstractInitializableBeanDefinition.java:2065)
        ... 19 common frames omitted

> Task :run FAILED
```

Some notes about what was required to reproduce this error:

* Eager singleton initialization has to be enabled. See `com.example.Application` line 8.
* A data-source has to be configured for the `EntityManager` bean to exist, see `application.yml`.
* At least one bean has to depend on the `EntityManager` bean. See `com.example.ErrorTrigger` line 11.

We are unsure whether injecting `EntityManager` is the only way of triggering the issue.

## License

There is barely any code in this repository that was not generated by <https://micronaut.io/launch>.
Still, for completeness:

This project is licensed under the MIT license ([LICENSE](LICENSE)).
