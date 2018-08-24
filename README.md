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

> **###5.  Questions on Hibernate**
```diff
+ Hello
- World
```

- ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) 'Hello'
- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) 'World'
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) 'SampleTest'





