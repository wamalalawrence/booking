
=====IMPORTANT CONFIGURATION NOTES=====
1. JAVA VERSION: 1.8.0_51-b16

2. In 'application-test.properties' and 'application-live.properties'
   the following properties have to be modified to suit as required:
-  csv.file.location
-  logging.level.org.springframework.web

   In logback-spring.xml please set the following properties:
-  the value of LOG_FILE

 
3. Allow permissions to the above created file and folders
4. All logging goes to the application log file as defined in the properties file

5. BUILD AND RUN
   cd GoEuroTest
   RUN:-   java -jar -Dspring.profiles.active=test target/GoEuroTest.jar
   BUILD:- mvn package

6. To stop the appliation please enter command "ctrl C"

