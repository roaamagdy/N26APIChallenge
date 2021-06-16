# N26-API-Challenge

- API Test Automation project to verify functionality of "Pet" entity. 
- In this project I applied the approach of developing each test case as a standalone case 

#  Future work/enhancements:
    1. Adding negative scenarios
    2. Covering remaining entities "Store" & "User"
    3. Setting "description" & "severity" for each test case to get it in the generated report

# Prerequisites:
    1. Java (JDK-11.0.11)
    2. Maven 
    3. IntelliJ IDEA
    4. Setup Swagger Petstore locally 

# To run the Tests
Use one of the following:

    1. Go to project path and run the following command: mvn clean test
    2. Run project as “Maven” test within IntelliJ IDEA
    3. Run testng.xml file directly within IntelliJ IDEA

# Reports

    1. Reports are generated using "Allure Framework"
    2. Reports are generated in “allure-report” folder
    3. Reports can be re-generated to a temp folder by executing the following command after moving to the project path: allure.bat serve allure-results

# Framework used 

java-TestNG framework

# Built With

- Rest assured Java Library
- Maven Repository

