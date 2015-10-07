This is a code snapshot from https://code.google.com/p/action-bazaar/

Kiran: Updating project structure and dependencies.
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
All of the chapters are Maven based and can be easily opened in NetBeans, 
   IntelliJ, or Eclipse. The code samples were tested on GlassFish 4.1. 

General instructions to run the unit tests (using JUnit + Arquillian) unless 
   otherwise specified in the respective modules
   
   To run in remote Glassfish server
   1. Install Glassfish server if not installed.
   2. Run Glassfish server
   3. Execute the tests
   		cd [module]
		mvn -P glassfish_remote test
		   		
 