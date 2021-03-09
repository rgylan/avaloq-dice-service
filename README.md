# avaloq-dice-service

####Propose further steps for application development and address areas missing in this assessment.

Development
1)  Externalize into file Exception Messages

    In any Spring Boot, it is ideal to have a configurable exception messages.
    
    a)  Create a messages properties file and put it under "resources" folder.  Properties are key value pair.
    
    b)  Configure a MessageResource bean referencing the classpath of above.
    
    c)  Reference the said Bean into your Exception Handler class.
    
2)  Add Log

    Spring Framework added support for logging so any Spring Boot should have proper logging.
    
    a)  Configure "logback" by creating a "logback-spring.xml" putting under "resources" folder.
    
3)  Add Unit Test

    At least for Service Classes should add JUnit and Mocking tests.
    
4)  Create API Blueprint

    a)  The idea is to document the API first before development.  Any text editor will do.
    
    b)  You can use Swagger, add "springfox-swagger-ui" to build.gradle.
    
    
Deployment

1)  Add Trace ID
    
    In a multi cluster environment is it ideal a Request is traceable in the logs.
    
    a)  Add "spring-cloud-starter-sleuth" in your build.gradle if you are using Gradle.
    
2)  Spring Profile

    If you need to switch environment when deploying Spring Boot Apps, spring.profiles.active=test from prop file


####Solution and the important decisions that has been made.

1)  Use of ResponseWrapper class

    The idea is to build the response into a generic/reusable way.  One of the benefits is from Controller class,
    
    you have one view/class of returning different responses.
    
2)  Use of Exception Handler

    The idea is to have an "error/exception" landing component/class where you can alter/tweak error/exception
    
    messages into more friendly/meaningful instead of stack trace.
    
3)  Use of ThreadLocalRandom less overhead and contention reasons were choose against the ability to change
    
    "seed"(ThreadLocalRandom uses internal seed ).  There is no need to change seed in this assignment.
    
4)  The outcome of throwing 3 dice(six sided) simultaneously is 216 combinations, ie, 6 x 6 x 6.

    Now the probability of getting three ones(1, 1, 1) is 1/216.  Developing/coding such condition is hard.
    
    You have to construct on the fly the number of combinations and weight each combination properly.
    
    With probability, throwing 3 dice simultaneously is the same as throwing a single "die" three times,
    
    ie, the probability of getting three ones from 3 throws is 1/6(1st) x 1/6(2nd) x 1/6(3rd) is 1/216, hence,
    
    the chosen logic is to iterate from the number of rolls with each roll get random number three times.   

5)  Use of DB(h2 in mem DB) to store simulations in JSON format chosen over writing into a file.

    File reading/writing has much overhead.  Unfortunately h2's support for JSON data type isn't easy,
    
    so, I have to write a converter class to serialize/deserialize a Map(content saved in the DB) object.   
        
6)  API Chosen Must be Noun

    a) /simulation - This is POST Http Method since the result is non-idempotent, with Query Parameters.  
    b) /simulations - This is GET Http Method to get all simulations.
    c) /simulations/{dice}/{side} - This is GET Http Method to get all simulations from a given resource path
    
    of dice and side. 

####How to Run
1)  To run locally check out from Git 

    git clone https://github.com/rgylan/avaloq-dice-service.git
    
2)  From a local machine, open a terminal/command window and go to project root 

    gradlew bootRun
    
3)  Runs on port 8080, if conflict from your local, pls amend from yml file.

4) Sample API Call

    a)  curl -X POST "http://localhost:8080/v1/dice/simulation?roll=10&dice=1&side=6"
    
    b)  curl -X GET "http://localhost:8080/v1/dice/simulations"
    
    c)  curl -X GET "http://localhost:8080/v1/dice/simulations/1/6"
    
    d)  Note:  curl -X POST "http://localhost:8080/v1/dice/simulations, ie, with "s" is 405 as
    
    "simulations" is GET and vice versa. 
    