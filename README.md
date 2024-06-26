# rak-bank-event-app

![rakbank.drawio.png](rakbank.drawio.png)

### Run the app
- Run the app in container 
  - Mac/Linux `sh start.sh`
  - Windows `./start.cmd`
- visit http://localhost:8080/swagger-ui/index.html

### Features
  #### User
  - Register a user
  - Fetch users
  
  #### Event
  - Create event
  - Search events
    
  #### Ticket 
  - View all ticket types
  - Book ticket for event
  - Complete payment for booking
  - Cancel booking

  #### Notifications 
  - Email Notification on below events
    - creating booking
    - completing payment
    - cancelling booking

#### Monitoring
   -  http://localhost:8080/actuator/metrics
   -  http://localhost:8080/actuator/health

#### Postman collections
You can download the Postman collection directly from the link below:

[Download Postman Collection](https://github.com/busraercelik/rak-bank-event-app/releases/download/1.0.0/rak-bank-coding-assignment-busra-ercelik.postman_collection.json)
