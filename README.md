# About

The Idea is to build management system that can:

1. record full information for customers (e.g. name , phone, address, etc..).
2. record full information for the product (e.g. name, picture, description[dimension, material) , is required special
   treatment, etc..)
3. generate invoice for customer including delivery information price , address , items he gives to clean
4. generate customized reports about business.
5. Track the orders and the status [ waiting, in treatment, complete]
6. Give a discount option to customers based on your design option, for example if the
   customer frequently uses the service with an amount of money >400 NIS, he can get a
   10% discount. Please feel free to set an acceptable discount rate.
7. Generate statistics about, for example, total delivered items , total cash , total paid, total debts.
8. Distribute the orders on the available workers.
9. Notify the customer by sending email when the order is complete.

Entities of Project:

1. Customer: id, name,phone,address
2. Product: Category, name,picture,description….
3. Worker: id, name,phone,address, a
4. Admin

The System should include the CRUD operations :

1. Create Customer/Product
2. Add Customer/Product
3. Delete Customer/Product
4. Update Customer/Product
5. List< Customer/Product> findByName(String name)

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

# Adding Features

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
