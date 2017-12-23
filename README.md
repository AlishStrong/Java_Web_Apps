# Java_Web_Apps
Examples of some java web apps

Important moments!
1) You have to install Apache Tomcat Web Server and configure it.

2) In the apps a table named Student was used.
Student table was created on MariaDB.
The table has the following columns (id, firstname, lastname, streetaddress, postcode, postoffice)

Student (
id INTEGER NOT NULL,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
streetaddress VARCHAR(50) NOT NULL,
postcode CHAR(5) NOT NULL,
postoffice VARCHAR(50) NOT NULL,
CONSTRAINT pk_student PRIMARY KEY(id)
) ;

3) For MariaDB connectivity with your Java project you need to include JDBC driver

4) There are .java and .jsp files. Java files contain the logical back-bone and linkage to the database data, while .jsp files contain the front-end constitution of the project and interact with servlets and java classes.
