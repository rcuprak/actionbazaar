Instructions
============
Run the unit tests using JUnit + Arquillian 
   
   To run in remote WildFly server (tests run very fast)
   1. Install WildFly server if not installed.
   2. Run WildFly server in standalone mode if not running.
   3. Execute the tests
		mvn -P wildfly-remote clean test
		
   To run in remote Glassfish server (tests run slow for some reason)
   1. Install Glassfish server if not installed.
   2. Run Glassfish server if not running.
   3. Execute the tests
		mvn -P glassfish-remote clean test
		   		
   To run in embedded Glassfish server (tests run at ok speed)
   1. Execute the tests
		mvn -P glassfish-embedded clean test		   		
 
To Deploy in server
	1. use 'glassfish-remote' profile to generate the war file.
		mvn -P glassfish-remote clean package
	2. Now deploy to glassfish server
