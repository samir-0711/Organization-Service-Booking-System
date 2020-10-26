# Organization-Service-Booking-System

Steps to Run Project

step 1: Java JDK Installation (prefered jdk-12.0.2)

step 2: NetBeans Installation (preferred netbeans-11.0)

step 3:  Project Setup
1. Make a folder in D drive and rename it to “Samir”
2. Paste the folder named as “project” in it from unzip folder

Step 4: Open Project
1. Open netbeans64
2. Click on file in menu bar
3. Select open project
4. Go to location D:\Samir\project\Hotel_Salon\Project1 and then click open
5. Pop up will appear “Add Dependencies”
6. Dependencies can be removed by providing their location.
7. Click on dependency and provide location” D:\Samir\project\Hotel_Salon”

Step 5: Create Database in MySQL
1. Open MySQL Workbench and enter password
2. Create a new Schema named as “project_dbms”
3. Select the newly created Schema “project_dbms”
4. Run the sql script provide in unzip folder named as “sql_file_schema_project”
![MySQL_Connection_with_Netbeans](https://user-images.githubusercontent.com/68433936/97161500-eee49d00-17a3-11eb-9fc8-b2ba54b38d05.PNG)


Step 6: Connect MySQL with NetBeans
1. In project1 folder, right click on Libraries and then select Add JAR/Folder
2. Add mysql-connector-java-8.0.18.jar (location:- D:\Samir\project\Hotel_Salon\mysql-connectorjava-8.0.18)
3. Now select Services (left side)
4. Right click on Databases and select New Connection (password :- mysql)
5. Click next ,next, next and finish

Step 7: Enter Email Address and Password for OTP
1. Open JavaMailUtil.java
2. Change xxxxxx in user and password with your Email Address and password
final String user="xxxxxx@gmail.com";//sender mail id
final String password="xxxxxx";//sender mail id password
3. Save file

Step 8: Set up Email for sending OTP automatically
1. Open any browser
2. Go to “https://myaccount.google.com/lesssecureapps”
3. Sign in with above entered Email Address
4. Turn On “Allow less secure apps” and close the browser
5. Google will send you mail to confirm your activity. Open Gmail and click on mail send by google
and press check activity
6. Confirm that it was done by you.

Step 9: Run Application
1. Go to LoginJFrame.java
2. Right click on it and press “Run File"
