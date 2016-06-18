
=====APPLICATION FUNCTIONAL SUMMARY======

- Reads in batch data of booking information from file.
  sample input data can be found in src/test/resources/sample-input.txt
  the first line represents company working hours.
  
- Removes any bookings that are not within company working hours

- Maintains a chronological order of bookings according to start date-time

- Prints booking calendar to console


=====IMPORTANT CONFIGURATION NOTES=====

1. JAVA VERSION: 1.8.0_51-b16

2. In 'application-test.properties' and 'application-live.properties'
   the following properties have to be modified to suit as required:
-  data.input.file.location
-  logging.level.org.springframework.web

3   In logback-spring.xml please set value of property "LOG_FILE":
 
4. Create and grant read-write permissions to the above created file and folder location
   e.g on mac OS you would use the following commands:
   sudo touch /var/log/bookit.log
   sudo chmod 777 /var/log/bookit.log
   sudo touch /Users/wamalalawrence/Documents/data.txt
   sudo chmod 777 /Users/wamalalawrence/Documents/data.txt
   
5. All logging goes to the application log file as defined in the properties file

6. BUILD AND RUN
   cd BookIt
   BUILD:- mvn package
   This will also run tests
   RUN:-   java -jar -Dspring.profiles.active=test target/BookIt.jar

7. The application will exit after successfully processing the batch

