# SpringMvcUsingHibernateMysql

* Spring + Hibernate + Mysql DB + Java Configuration + MVC + Maven , Example
* Hibernate example for Spring 4 MVC + JSP View with pure Java Configuration (no XML), using Maven build tool.
* Spring4 + MVC, Integration without using the web.xml and Spring_Servlet.xml file. 
* By using WebMvcConfigurerAdapter class and WebApplicationInitializer interface to replace above files.
* Ref: https://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial
* Ref: http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-example-using-annotations/

> **###1. Technologies**
* Spring 4.0.3.RELEASE
* Hibernate 4.3.5.Final
* Mysql Connector 8.0.11
* Maven 3.1
* JSTL 1.2

> **###2. To Run this project locally**
* $ git clone https://github.com/AkashChauhanSoftEngi/SpringMvcUsingHibernateMysql
* $ mvn tomcat7:run

> **###3.  Access** 
* http://localhost:8080/SpringMvcUsingHibernate/
* http://localhost:8080/SpringMvcUsingHibernate/person

> **###4.  Important Points to keep in mind**
* Hibernate Jars {hibernate-entitymanager, hibernate-core, for exa. 4.3.5.Final version}
* You migth get an session error if old version of mysql connector is being used
* Must set hibernate.dialect = org.hibernate.dialect.xx {xx:-> MySQLDialect}
* There must be a DataSource in IOC, for connection management {LocalSessionFactoryBean}
* Must set LocalSessionFactoryBean.setPackagesToScan("package: Model classes to map with tables")
* Must use @EnableTransactionManagement with Hibernate configuration class
* Must creat HibernateTransactionManager bean in IOC {Spring Internal use}
* Must use @Transactional, preferably with dao classes/Methods {bind sessions}

**###5.  Questions on Hibernate**
> How to listen on multiple end points, when mapping with controllers
* {"/", "/person"} or "*"

> Why we need to set hibernate.dialect = org.hibernate.dialect.MySQLDialect
* Must Needed for converting hibernate query to mysql query
```java
```

> What is DataSource
* Have details of the connetions, responsible for managing connections.
```java
```

> Differences between DriverManagerDataSource and BasicDataSource API, for creating DataSource
* This class is not an actual connection pool; it does not actually pool Connections. It just serves as simple replacement for a full-blown connection pool, implementing the same standard interface, but creating new Connections on every call.
* In other words, it may be OK for tests but in real application use Apache DBCP
```java
```

> Why we need to LocalSessionFactoryBean.setPackagesToScan("Model classes to map with tables")?
* if we do not set this, it will not map model classes with database tables. {later we must use @Entity annotation as well}
```java
```

> Why we need to use @EnableTransactionManagement with Hibernate configuration file?
* Session will not be created/managed by Session manager {HibernateTransactionManager}
* Must use @EnableTransactionManagement with hibernate configuration file
* Error: org.hibernate.HibernateException: No Session found for current thread
```java
```

> Why we need to create HibernateTransactionManager bean in IOC?
* org.springframework.transaction.PlatformTransactionManager iterface requires any implementations in IOC to Perform Transaction Manager
* Need to create a bean in IOC for this interface, for example using HibernateTransactionManager and setSessionFactory(SessionFactory)
* It is a internal need of Spring to read PlatformTransactionManager implementation from IOC to manage commit, rollback and Transaction Management work to be handlled
```java
```

> Why we need to use @Transactional with serviceImpl, dao, daoImpl classe/Interface?
* We must require to add @Transactional with service or dao classes or interfaces to build a session for transection
* Not with Dao interface, service interface and classes are okay, dao class is also okay, even on dao method is also okay
* Thumb rule, use @Transactional with dao classes or methods
* Wherever there is a chance of session creation must use @Transactional with those dao classes
* Error: org.hibernate.HibernateException: No Session found for current thread
```java
```
