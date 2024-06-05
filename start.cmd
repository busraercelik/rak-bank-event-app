@echo off

cd event-management-svc
call mvnw clean package
cd ..

cd event-svc
call mvnw clean package
cd ..

cd notification-svc
call mvnw clean package
cd ..

cd payment-svc
call mvnw clean package
cd ..

cd ticket-svc
call mvnw clean package
cd ..

cd user-svc
call mvnw clean package
cd ..

docker-compose up -d
