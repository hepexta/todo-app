@ECHO OFF
cd C:\Users\Sergei_Kuznetsov1\Work\Projects\interview\cs\todo-app\frontend
CALL ng build
cd C:\Users\Sergei_Kuznetsov1\Work\Projects\interview\cs\todo-app
CALL mvn clean install
FOR /f "tokens=*" %%i IN ('docker ps -q') DO docker stop %%i
CALL docker-compose build
docker-compose up