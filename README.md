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
```diff
+ A) Must create hibernate property and set hibernate.dialect = org.hibernate.dialect.xx {xx:-> MySQLDialect}
```
```diff
+ B) There must be a DataSource in IOC, for connection management {LocalSessionFactoryBean}
```
```diff
+ C) Must create a session factory
  example: LocalSessionFactoryBean {Managing connection, provides CRUD and extra fun}
```
* Must set LocalSessionFactoryBean.setPackagesToScan("package: Model classes to map with tables")
```diff
+ D) Must use @EnableTransactionManagement with Hibernate configuration class
```
```diff
+ E) Must creat HibernateTransactionManager bean in IOC {Spring Internal use}
```
* Must use @Transactional, preferably with dao classes/Methods {bind sessions}
* Spring Hibernate provides: 1) Lazy loading[when needed], 2) Eager fetching[Grab entire object], 3) Cascading{change in one table riflect in other table as well} 

**###5.  Questions on Spring- Hibernate**
> How to listen on multiple end points, when mapping with controllers
* {"/", "/person"} or "*"

> Why we need to set hibernate.dialect = org.hibernate.dialect.MySQLDialect
* Must Needed for converting hibernate query to mysql query
```java
	hibernate.dialect = org.hibernate.dialect.MySQLDialect
	hibernate.show_sql = true
	hibernate.format_sql = true
```

> What is DataSource
* Have details of the connetions, responsible for managing connections.
```java
    	@Bean
    	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		return dataSource;
    	}
```

> Differences between DriverManagerDataSource and BasicDataSource API, for creating DataSource
* This class is not an actual connection pool; it does not actually pool Connections. It just serves as simple replacement for a full-blown connection pool, implementing the same standard interface, but creating new Connections on every call.
* In other words, it may be OK for tests but in real application use Apache DBCP

> Why we need to LocalSessionFactoryBean.setPackagesToScan("Model classes to map with tables")?
* if we do not set this, it will not map model classes with database tables. {later we must use @Entity annotation as well}
```java
	LocalSessionFactoryBean.setPackagesToScan(new String[]{"model package location"});
```

> Why we need to use @EnableTransactionManagement with Hibernate configuration file?
* Session will not be created/managed by Session manager {HibernateTransactionManager}
* Must use @EnableTransactionManagement with hibernate configuration file
* Error: org.hibernate.HibernateException: No Session found for current thread
```java
	@Configuration
	@EnableTransactionManagement
	@ComponentScan(basePackages="spring.project")
	@PropertySource(value = { "classpath:application.properties" })
	public class HibernateConfiguration {}
```

> Why we need to create HibernateTransactionManager bean in IOC?
* org.springframework.transaction.PlatformTransactionManager iterface requires any implementations in IOC to Perform Transaction Manager
* Need to create a bean in IOC for this interface, for example using HibernateTransactionManager and setSessionFactory(SessionFactory)
* It is a internal need of Spring to read PlatformTransactionManager implementation from IOC to manage commit, rollback and Transaction Management work to be handlled
```java
    	@Bean
    	@Autowired
    	public HibernateTransactionManager transactionManager(SessionFactory s) {
       		HibernateTransactionManager txManager = new HibernateTransactionManager();
       		txManager.setSessionFactory(s);
       		return txManager;
    	}
```

> Why we need to use @Transactional with serviceImpl, dao, daoImpl classe/Interface?
* We must require to add @Transactional with service or dao classes or interfaces to build a session for transection
* Not with Dao interface. Service interface and classes are okay, dao class is also okay, even on dao method is also okay
* Thumb rule, use @Transactional with dao classes or methods
* Wherever there is a chance of session creation must use @Transactional with those dao classes
* Error: org.hibernate.HibernateException: No Session found for current thread
```java
	@Repository
	@Transactional
	public class PersonDaoImpl implements PersonDao {}
```

> First level(Session Level) and Second level(Session Factory level) cache, use of EhCache in Hibernate
* Reference : https://javarevisited.blogspot.com/2017/03/difference-between-first-and-second-level-cache-in-Hibernate.html
* Important Questions: http://www.java67.com/2016/02/top-20-hibernate-interview-questions.html

> Disable Lazy Loading in Hibernate
* https://stackoverflow.com/questions/5479140/disable-lazy-loading-in-hibernate

