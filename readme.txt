This is a code snapshot from https://code.google.com/p/action-bazaar/

Kiran: Updating project structure and dependencies - Oct 2015.
	Done:
		actionbazaar-chapter1
	Ongoing:
	ToDo: 
		ActionBazaar/
		actionbazaar-chapter10/
		actionbazaar-chapter10b7/
		actionbazaar-chapter12/
		actionbazaar-chapter14/
		actionbazaar-chapter15/
		actionbazaar-chapter2/
		actionbazaar-chapter3/
		actionbazaar-chapter4/
		actionbazaar-chapter5/
		actionbazaar-chapter6/
		actionbazaar-chapter7/
		actionbazaar-chapter8/
		actionbazaar-chapter9/
	
Instructions
============
All of the chapters are Maven based. The code samples were tested on the 
   GlassFish 4.1 and the WildFly 9.0.1 application servers. The code may be 
   imported in IDEs supporting Maven. Eclipse was used for updating project 
   structure and dependencies in Oct 2015.

General instructions to run the unit tests (using JUnit + Arquillian) unless 
   otherwise specified in the respective modules
   
   To run in remote WildFly server (tests run very fast)
   1. Install WildFly server if not installed.
   2. Run WildFly server in standalone mode if not running.
   3. Execute the tests
   		cd [module]
		mvn -P glassfish_remote test
		
   To run in remote Glassfish server (tests run slow for some reason)
   1. Install Glassfish server if not installed.
   2. Run Glassfish server if not running.
   3. Execute the tests
   		cd [module]
		mvn -P glassfish_remote test
		   		
   To run in embedded Glassfish server (tests run at ok speed)
   1. Execute the tests
   		cd [module]
		mvn -P glassfish_remote test		   		
 