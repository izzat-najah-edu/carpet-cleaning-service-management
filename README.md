# CarpetCleaningServiceManagement

The Idea is to build management system that can:
  1. record full information for customers (e.g. name , phone, address, etc..).
  2. record full information for the product (e.g. name, picture, description[dimension, material) , is required special treatment, etc..)
  3. generate invoice for customer including delivery information price , address , items he give to clean
  4. generate customized reports about business.
  5. Track the orders and the status [ waiting, in treatment, complete]
  6. Give a discount option to customers based on your design option, for example if the 
     customer frequently uses the service with an amount of money >400 NIS, he can get a
     10% discount. Please feel free to set an acceptable discount rate.
  7. Generate statistics about, for example, total delivered items , total cash , total paid, total debts.
  8. Distribute the orders on the available workers.
  9. Notify the customer by sending email when the order is complete.
  
Entities of Project:
  ● Customer: id, name,phone,address
  ● Product: Category, name,picture,description….
  ● Worker: id, name,phone,address, a
  ● Admin
  
The System should include the CRUD operations :
  ● Create Customer/Product
  ● Add Customer/Product
  ● Delete Customer/Product
  ● Update Customer/Product
  ● List< Customer/Product> findByName(String name)
