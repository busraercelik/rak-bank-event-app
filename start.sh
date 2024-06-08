docker-compose down --rmi all

cd event-management-svc
./mvnw clean package
cd ..

cd event-svc
./mvnw clean package
cd ..

cd notification-svc
./mvnw clean package
cd ..

cd payment-svc
./mvnw clean package
cd ..

cd ticket-svc
./mvnw clean package
cd ..

cd user-svc
./mvnw clean package
cd ..

docker-compose up -d