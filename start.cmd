@echo off
cd event-management-svc
call mvnw clean package
cd ..
docker-compose up -d
