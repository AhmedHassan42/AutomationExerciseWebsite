# AutomationExerciseWebsite
# Web Automation for Checkout Process 
## Introduction This project aims to automate the checkout process on the "https://automationexercise.com/" site. The automated test script follows a data-driven approach and covers the entire user flow, including product selection, adding to the cart, proceeding to checkout, entering shipping and billing information, and completing the order. 
## Tools and Technologies Used 
- **Selenium WebDriver**: Used for browser automation.
- **Java**: The programming language used for writing the test scripts.
- **TestNG**: Used for organizing and running the test cases.
- **Page Object Model (POM)**: Design pattern applied to enhance code maintainability and readability.
- **Allure Report**: Used for generating detailed test execution reports.
- **Log4j2**: Used for logging the test execution details.
- **Data-Driven Testing**: Test data is provided using external files.
- **XML Test Runner**: TestNG XML files are used for configuring and running the tests.
## How to Run the Tests 
1. **Clone the Repository**: Clone this repository to your local machine.
2. **Setup Environment**: Ensure you have Java, Maven, and a web browser installed on your machine.
3. **Install Dependencies**: Run `mvn clean install` to install the necessary dependencies.
4. **Run the Tests**: Use the TestNG XML file to run the tests
mvn test -DsuiteXmlFile=TestRunner/RegressionSuite.xml
**View Reports**: Generate and view the Allure report:
allure serve target/TestResults/allure-results
## Test Data The test data for Login and Payment Method information is provided using an external file (Json). Ensure that the data file is correctly referenced in the test script. 
## Logging Log4j2 is used for logging the test execution details. The logs are stored in TestOutputs/Logs. 
## Conclusion This automation project ensures the checkout process on the "https://automationexercise.com/" site is working smoothly. By using Selenium, Java, TestNG, and other tools, the project provides a robust and maintainable solution for automated testing.
