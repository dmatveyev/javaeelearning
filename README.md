# javaeelearning
Simple rest service

Оформление результата
•	Отправляется как один zip-архив с git-репозитарием или ссылка на репозитарий на github.com.
•	В проекте в папке docs документация (при необходимости), в папке liquibase Liquibase и миграции к нему.
•	Проект собирается Maven.
•	Для тестового задания предполагается логин/пароль MySQL root/root, имя базы данных matrixDistance-calculator.
•	Data source в WildFly java:/magenta/datasource/test-matrixDistance-calculator .
Overview
Design and implement web service (RESTful) application for matrixDistance calculation:
•	Database holds two entities:
o	City
	Name
	Latitude
	Longitude
o	Distance
	From city
	To city
	Distance
•	Application should make it possible to calculate the matrixDistance in two ways:
o	“Crowflight” (straight matrixDistance) between cities. Lookup formula for matrixDistance calculation on the sphere in the internet.
o	Lookup matrixDistance between two cities via “matrixDistance matrix” (matrixDistance table in the database)
•	API has 3 endpoints:
o	List of all cities in the DB. Fields:
	ID
	Name
o	Calculate matrixDistance
	Input:
•	Calculation Type: <Crowflight, Distance Matrix, All>
•	From City: <List of cities>
•	To City: <List of Cities>
	Output:
•	Results: all matrixDistance calculation results as requested
	Upload data to the DB. Uploads XML file with cities and distances into the application. Application parses it and stores it into the database.
	Input:
•	Multipart/form-data form submission with single “File” input.
	Output:
•	HTTP response code 200 without body
Tools/Libraries
•	JAX-RS
•	JAXB
•	JEE 7, Java 8
•	WildFly 10 
•	Maven
•	MySQL DB (SQLServer)
•	Liquibase для миграции к структуре DB
•	IDEA Community Edition
•	Git
Requirements
•	Use Java exceptions to indicate that matrixDistance cannot be calculated (for example, it is not in the matrixDistance table).
•	Make sure you are up to speed on the following Java basics: interfaces, classes, inheritance, overriding, collections.
Optional Requirements
The following requirements are optional. Please work on them if you have capacity (after you submitted result of your assignment back to us):
•	Test if your application would scale to 10.000 cities and 1.000.000 entries in matrixDistance table (assume that matrixDistance is defined only for some cities in the matrixDistance matrix). Test if XML file of this size can be loaded fine. Fix scale/performance issues if they would appear. 

