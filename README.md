# E-commerce Automation Testing Framework

## **Project Overview**
This project is a test automation framework designed to verify the end-to-end order creation process on an e-commerce platform. The automated test covers the user journey, from registration and login to adding products to the cart and completing the checkout process.

## **Key Features**
- **Page Object Model (POM)**: A design pattern that helps to organize the project and improve maintainability by separating the test code from UI interactions.
- **Data-Driven Testing**: The framework supports data-driven testing, allowing dynamic test input using Java Faker library and custom data files.
- **TestNG Integration**: Utilizes TestNG for structured test case management, annotations, and data-driven capabilities.
- **RestAssured**: Testing REST APIs by sending HTTP requests and validating responses.
- **Extent Reports**: Provides detailed HTML reports with pass/fail status, logs, and step-by-step test execution results.
- **WebDriver Manager**: Simplifies browser driver setup by automatically managing the required binaries.

## **Tools and Technologies Used**
- **Java**: The core programming language for implementing the test logic.
- **Selenium WebDriver**: Used for interacting with the browser and automating user actions.
- **TestNG**: A testing framework for executing and managing test cases.
- **Maven**: Manages project dependencies and builds the automation project.
- **Faker Library**: Generates realistic random data for user inputs.
- **RestAssured**: Testing REST APIs by sending HTTP requests and validating responses.
- **Extent Reports**: Generates comprehensive HTML reports for test execution.
- **WebDriver Manager**: Automatically handles WebDriver binaries, ensuring compatibility.

## **Project Structure**
src/
|-- main/
|   |-- java/
|       |-- org.example/
|           |-- pages/                # Page classes (LoginPage, HomePage, CheckOutPage)
|           |-- testData/             # Test data classes/Json (UserData.java, BillingData.java, userData.json)
|           |-- utils/                # Utility classes (ExtentManager)
|-- test/
    |-- java/
        |-- org.example.tests/        # Test classes (CheckOutTests,ApiTest)


### **1. Pages Directory**
Contains classes representing individual pages of the application (e.g., `LoginPage.java`, `HomePage.java`). Each class includes methods to interact with UI elements, following the Page Object Model (POM) pattern.
1. UI Tests (End-to-End Flow)
Page Object Model (POM) is implemented for better maintainability. The UI flow includes the following pages:
RegistrationPage: Handles user registration form filling and validation.
LoginPage: Handles login functionality after registration.
HomePage: Manages product interaction and cart addition.
CartPage: Manages the cart and cart assertions.
CheckOutPage: Handles the checkout flow, including the billing address and payment methods.
2. API Test (User Creation)
We have added an API test class using RestAssured to automate the user creation flow using the ReqRes API. This test sends a POST request to the /api/users endpoint and validates the following:

The status code is 201 (Created).
The response body contains the following data:
id: A unique identifier for the newly created user.
name: A name such as "John Doe".
job: A job such as "Software Engineer".
API Test Details:
Create a JSON object with user data.
Send a POST request to the ReqRes API.
Assert the response status code is 201.
Validate that the response body contains the ID, name, and job attributes.

### **2. testData Directory**
Includes data classes like `UserData` and `BillingData`. These classes provide input data for the tests, making the framework flexible and easy to maintain.

### **3. utils Directory**
Holds utility classes like `ExtentManager`, which handles the initialization and management of Extent Reports.

### **4. tests Directory**
Contains test classes 
- CheckOutTests.java that implements end-to-end test scenarios using methods from the page classes.
- ApiTest.java that tests REST APIs by sending HTTP requests and validating responses.

## **How to Run the Tests**

### **Pre-requisites**
- **Java 11** or higher
- **Maven** installed
- **Chrome browser**
- **IDE** (e.g., IntelliJ IDEA, Eclipse)

### **Setup**
1. Clone the repository:
   git clone <repository-url>
2. Navigate to the project directory:
cd e-commerce-automation
3. Install dependencies using Maven:
mvn clean install

### **Execution**
Run tests using Maven:
mvn test

### **Test Reports**
After the test execution, a detailed HTML report is generated in the test-output/ folder.
Open the report file ExtentReport.html in a browser to view detailed results, including pass/fail status, logs, and error details.

### **Approach**
- Page Object Model (POM)
The POM design pattern helps separate test logic from the code that interacts with the UI elements. This approach enhances readability and maintainability. For example, the LoginPage.java class handles all login-related actions, while the test class (CheckOutTests.java) manages the test flow.

- Data-Driven Testing
We utilize Java Faker for generating realistic user data dynamically. Additionally, data classes like BillingData.java help pass structured data inputs to tests, improving flexibility and reusability.

- Extent Reporting
Using Extent Reports, we provide detailed test execution feedback. The ExtentManager class initializes the report, logs test steps, and captures the pass/fail status. The report includes:
Detailed step-by-step logs
Pass/Fail status
Exception details on failures
- WebDriver Manager
WebDriver Manager eliminates the need for manual WebDriver binary setup. It automatically downloads the required browser drivers, ensuring compatibility and reducing setup effort.
