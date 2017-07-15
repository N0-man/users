# User Repository Service for Trade Away application

## Technology
- Java 8
- Maven 4.0.x
- PostGreSql 9.6 (default postgres database)
- SpringBoot 1.5.4

## Run Application Locally
```sh spring-boot.sh```

## Run Integration Tests (make sure your service is up)
```mvn test```

## Rest Client
POSTMAN App on Chrome Webstore

### Create User
```select POST in Postmant
URL: http://localhost:8080/createUser
Header: Content-Type - application/json
Body (raw) :
{
 "fname" : "Dango",
 "lname" : "Coco",
 "type" : "Buyer"
}
```

### List All Users
```select GET in Postmant
URL: http://localhost:8080/users
```

### Find User By Last Name
```select GET in Postmant
URL: http://localhost:8080/findUser/Coco
```

