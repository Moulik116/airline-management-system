# Airline-Management-System
Airline Management System
This is a Spring Boot application designed to manage airline flight data. It provides RESTful endpoints to retrieve flight information, uses an in-memory H2 database for storage, and allows manual data manipulation via the H2 Console. The project is built with Maven and includes basic CRUD operations for flights.

# Running steps
1) Install all the dependencies.
2) Run 'mvn clean install' to check for any build errors.
3) Run the main application.
4) Access the H2 console by running 'http://localhost:8080/h2-console' in browser and then connect to it.
5) Add data to the tables present in H2 console by running sql queries and give api calls from postman.
## Inserting into flight table
![alt text](image.png)
![alt text](image-3.png)
## Inserting into flightschedule table
![alt text](image-1.png)
## Inserting into ticket table
![alt text](image-2.png)
![alt text](image-4.png)

# Deploying the app on aws ec2 instance
1) Setup EC2 Instance
![image](https://github.com/user-attachments/assets/6c9d72bc-dd72-42c6-86dd-95649bc61838)
2) Create a docker container
![image](https://github.com/user-attachments/assets/7fe2c4ab-1bd7-43e7-a1b4-74f235b946e9)
3) Setup Jenkins pipeline
![image](https://github.com/user-attachments/assets/83d29340-ddc5-4da8-a1a7-577a3d556c83)
4) Insert data via h2 console
![image](https://github.com/user-attachments/assets/a2836b01-240f-4a12-8dba-1a82f2c63e53)
5) Fetch data via postman
![image](https://github.com/user-attachments/assets/b00a61d5-fd39-4ec1-82ec-df071c42161e)


# Author
Moulik Anand Paliwal

