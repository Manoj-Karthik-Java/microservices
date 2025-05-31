
Run Springboot application using maven
Navigate to the project folder in command prompt where the pom.xml file is available.
1. Run _**mvn spring-boot:run**_ when you want to:
2. Quickly test and run your Spring Boot application.
3. See the changes you've made to your code without packaging the application.
4. Make changes to your code.
5. Run _mvn spring-boot:run_ to test and run your application.
6. Once you're satisfied with the changes, run _**mvn install**_ to build and package your project.

_**mvn install**_ will create a target folder which contains jar file.
open cmd and run _**java -jar application-name.jar**_


************************************************************************************************************************

How to resolve lombok not working error in intellij ?


settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> select "Obtain processors from class path"

if the above doesn't work try

settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors 

click on current project then select "Obtain processors from class path". Refer below screen shot for clear understanding.

![image](https://github.com/user-attachments/assets/fd30bced-0f2d-470e-8bcc-9d8eb4736c43)

