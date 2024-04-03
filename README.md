DataBase changes 
 On your application go to application.properties and change the url,username,password according to your database
and then run the application 

After creating the tables on your db you need to create roles for that run the below query on workbench or your db application 

use db_name; --your database name

insert into role values(1,"ADMIN"),(2,"CUSTOMER");


 
