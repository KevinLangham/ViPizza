# ViPizza

Web application written using Spark web framework, Angular JS, JQuery and MongoDB.

All pizza orders will be written to the Mongo No-SQL database

To run:

1.  Download and save source code

2.  Open command window and start MongoDB (if not already installed, download and install from https://www.mongodb.org/. Navigate to MongoDBInstallDirectory/bin and run 'mongod --dbpath MongoDBInstallDirectory\bin\data\db')

3.  Open a separate command window and navigate to directory containing POM.xml
  
4.  Build the app using 'mvn clean install'
  
5.  Run the app using 'java -jar target/vipizza-1.0-SNAPSHOT.jar'
