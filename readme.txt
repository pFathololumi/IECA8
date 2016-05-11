1) Install HSQL (hsqldb.org)
2) Start HSQL Server
	cd lib/hsqldb
	java -cp ./hsqldb.jar org.hsqldb.server.Server
3) Start HSQL Database Manager
	java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
4) Connect to the server
	Set 'type' to 'HSQL Database Engine Server'
5) Create the tables
	Open init-db.sql and execute SQL commands
6) Build and Deploy the project
	./build&deploy.sh
7) Start Tomcat
	./startTomcat.sh

