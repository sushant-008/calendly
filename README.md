# Calendly


##Tech stack 

Application is built on spring-boot 
Usage mysql database 
Java


##Assumptions 

1. Each user will have a single schedule for a week Day
2. Schedules are always within a day boundary.
3. Events can't span across day.



##How to run 

1. A script file is placed under Resource folder. use mysql source command to load schema. this script will seed few data as well.
2. Update mysql user and pwd in Resource/application.properties
3. Use any ide and import project. Run project directly from IDE runner. 
4. To use apis, a client is added `CalendlyServiceClient` with main method and all the other required method. 
   Individual methods can be called in any order.


Few apis can be directly invoked from browser 


Get users schedule -> http://localhost:8082/schedule/1

Get users events for the given date -> http://localhost:8082/events/1?date=1704133800 

Get users availability on given date -> http://localhost:8082/availability/1/1704133800

Overlapping schedule on given day of week -> http://localhost:8082/schedule/overlap/1/10?dayOfWeek=MONDAY

Find conflict between 2 users on the given days ->  http://localhost:8082/events/overlap/1/2?date=1704133800






   