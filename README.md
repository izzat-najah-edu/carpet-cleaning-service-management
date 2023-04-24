# About

The Idea is to build management system that can:

### by: [@izzat5233](https://github.com/izzat5233)

- record full information for customers with all CRUD operations available.
- record full information for the products & orders with all CRUD operations available.
- Track the orders and the status [ waiting, complete].
- Notify the customer by sending email when the order is complete.
- [Extra] Authentication system with a login screen (only certain admins can access the system).

### by: [@ayakhammash](https://github.com/ayakhammash)

- generate invoice for customer including delivery information price , address , items he gives to clean
- generate customized reports about business.
- Generate statistics about, for example, total delivered items , total cash , total paid, total debts.
- Give a discount option to customers.

## Technologies:

The system was divided into two modules [core & ui]. And the following was used:

- Hibernate (Core)
- JUnit 5 (Core Testing)
- JavaFX (UI)
- Cucumber (UI Testing)

## MySQL Setup

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

## Email Setup and Environment Variables

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
