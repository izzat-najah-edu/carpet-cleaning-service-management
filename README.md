# About

The Idea is to build management system that can:

### Developed by: [@izzat5233](https://github.com/izzat5233)

- Record full information for customers with all CRUD operations available.
- Record full information for the products & orders with all CRUD operations available.
- Track the orders and the status [waiting, complete].
- Notify customers via email when their order is complete.
- [Extra] Authentication system with a login screen (restrict access to specific admins).

### Developed by: [@ayakhammash](https://github.com/ayakhammash)

- Generate invoice for customer including delivery information price , address , items he gives to clean
- Generate customized reports about business.
- Generate statistics about, for example, total delivered items , total cash , total paid, total debts.
- Give a discount option to customers.

# Table of Contents

- [Summary](#summary)
  - [Technologies](#technologies)
  - [Static Analysis](#static-analysis)
  - [Coverage](#coverage)
- [Setup](#setup)
  - [MySQL](#mysql)
  - [Email Service](#email-service)
- [Latest Look](#latest-look)
- [More](#more)
  - [Adding Features](#adding-features)

# Summary

## Technologies

The system is divided into two modules, Core and UI. And the following technologies are used:

- Core Module:
  - Hibernate
  - JUnit5 (for testing)
  - Mockito (for testing)
  - Jakarta Mail
- UI Module:
  - JavaFX
  - Cucumber (for testing)
  - TestFX (for testing)
- CI Analysis:
  - SonarCloud (for static analysis)
  - Jenkins (for building automation)
  - Jacoco (for coverage report)

## Static Analysis

- Full analysis available at
  [project link](https://sonarcloud.io/project/overview?id=izzat-najah-edu_carpet-cleaning-service-management)

![SonartCloud Analysis](https://github.com/izzat-najah-edu/carpet-cleaning-service-management/assets/92182269/aa0a491d-06a2-4506-b0d6-63c9f99518d1)

## Coverage

- Most of the non-covered lines are either additional methods to handle extra functionalities which haven't been tested
  yet or getters & setters of the POJO entity objects (AdminEntity, CustomerEntity, etc.) which are auto-generated and
  do not require testing.

- The Cucumber tests focused mainly on the UI. Therefore, almost all UI functionalities are tested.

![Jacoco Coverage Report](https://github.com/izzat-najah-edu/carpet-cleaning-service-management/assets/92182269/b7ef1423-5430-4411-9132-53f1b1392471)

# Setup

## MySQL

This project requires a MySQL server to be installed and running. Please follow the official MySQL installation guide
for your platform:

- https://dev.mysql.com/downloads/installer/

After creating the database and user, you need to set up the database schema and populate it with some initial data.
This project includes two SQL files for this purpose, located in `/assets/sql`:

1. `CREATE.sql`: This file contains the SQL commands to create the necessary tables and relationships.
2. `INSERT.sql`: This file contains the SQL commands to insert initial data into the tables.

To execute these files, follow these steps:

1. Open your MySQL shell or client, and connect to the database you created earlier using the user credentials you set
   up.
2. Execute the contents of the `CREATE.sql` file in your MySQL shell or client. You can either copy and paste the
   commands into the shell or client, or you can use the `SOURCE` command followed by the path to the file.
3. Similarly, execute the contents of the `INSERT.sql` file in your MySQL shell or client. Use the same method as you
   did for the `CREATE.sql` file.

## Email Service

This project uses an email service to send notifications to customers. To protect sensitive information, the email
credentials should be stored as environment variables. Please follow these steps to set up the environment variables:

- Create two environment variables `JAVAMAIL_USER` and `JAVAMAIL_PASS`, where `JAVAMAIL_USER` stores your email
  address,
  and `JAVAMAIL_PASS` stores your email password.

**For Windows:**

- Open the Command Prompt as an administrator.
- Run the following commands, replacing "your_email@example.com" and "your_password" with your actual email and
  password:

```cmd
setx JAVAMAIL_USER "your_email@example.com"
setx JAVAMAIL_PASS "your_password"
```

**For Linux/macOS:**

- Open a terminal.
- Run the following commands, replacing "your_email@example.com" and "your_password" with your actual email and
  password:

```cmd
export JAVAMAIL_USER="your_email@example.com"
export JAVAMAIL_PASS="your_password"
```

# Latest Look

Screenshots of the latest interface are provided below:

- Login Panel

![Login Panel](https://github.com/izzat-najah-edu/carpet-cleaning-service-management/assets/92182269/10957c64-bf3a-43cd-8a49-cde3615a885b)

- Customers Tab

![Customers Tab](https://github.com/izzat-najah-edu/carpet-cleaning-service-management/assets/92182269/993473fb-2f00-4455-8365-1086ebdbd653)

- Products Tab

![Products Tab](https://github.com/izzat-najah-edu/carpet-cleaning-service-management/assets/92182269/49036d2b-d693-440e-84a1-72333013dd3b)

- Orders Tab

![Orders Tab](https://github.com/izzat-najah-edu/carpet-cleaning-service-management/assets/92182269/c9ce93e3-5946-4492-a182-5cfd18a6627a)

# More

## Adding Features

- The directory `/src/test/java/resources/features` includes the features of the project
- Each feature has a corresponding -Step Definitions- Class located at src/test/java/features
- The -Step Definitions- Class links every step of every scenario into actual code to be executed throughout the tests
- But that class alone is not enough, it simply executes all features as code
- When we start the test, only JUnit test classes execute, so we make another test class,
  and we configure it to run the cucumber tests [@RunWith & @CucumberOptions]
- In Summary:

1. write a feature in this directory
2. create a step definitions class in src/test/java/features to link every step to an actual code
   (e.g. if the step is: "login screen closes" link it to a code like: "loginScreen.close()")
3. create another test class and configure it to run the cucumber tests
