# Calendly


## Tech stack 

    - spring-boot 
    - mysql
    - java


## Assumptions 

1. Each user will have a single schedule for a week Day, although it can be different for each of those days.
2. Schedules are always within a day boundary. That means we can create a schedule that starts on monday and continues upto tuesday.
3. Events are always within a day boundary.That means we can create a schedule that starts on monday and continues upto tuesday.


## hacks
1. Used mysql for storing user schedule. Ideally, i would have used a document DB (each schedule load is making 5-6 DB calls)
2. Have kept all ids int for quick testing


## How to run locally

1. A sql script file `calendly.sql` is placed under root folder. use mysql source command to load schema. this script will seed few data as well.
2. Update mysql user and pwd in Resource/application.properties
3. Use any IDE and import project. Run project directly from IDE runner. 
4. To use apis, a client is added `CalendlyServiceClient` with main method and all the other required method. 
   Individual methods can be called in any order.


## How to run direct (for Bonus :) 


Few apis can be directly invoked from browser 


Get users schedule -> http://18.61.72.193:8082/schedule/1

Get users events for the given date -> http://18.61.72.193:8082/events/1?date=1704133800 

Get users availability on given date -> http://18.61.72.193:8082/availability/1/1704133800

Overlapping schedule of 2 users on given day of week -> http://18.61.72.193:8082/schedule/overlap/1/11?dayOfWeek=MONDAY

Find conflict between 2 users on the given days ->  http://18.61.72.193:8082/events/overlap/1/2?date=1704133800

All post methods are covered in `CalendlyServiceClient`




   