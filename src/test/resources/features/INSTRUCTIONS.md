- This directory includes the features of the project
- Each feature has a corresponding -Step Definitions- Class located at src/test/java/features
- The -Step Definitions- Class links every step of every scenario into actual code to be excuted throughout the tests
- But that class alone is not enough, it simply executes all features as code
- When we start the test, only JUnit test classes execute, so we make another test class,
and we configure it to run the cucumber tests [@RunWith & @CucumberOptions]
- In Summary:
1. write a feature in this directory
2. create a step definitions class in src/test/java/features to link every step to an actual code
   (e.g. if the step is: "login screen closes" link it to a code like: "loginScreen.close()")
3. create another test class and configure it to run the cucumber tests
