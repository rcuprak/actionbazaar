This is a code snapshot from https://code.google.com/p/action-bazaar/

Kiran: Updating project structure and dependencies - Oct 2015.
	Done:
		actionbazaar-chapter1
		actionbazaar-chapter2/
		actionbazaar-chapter3/
		actionbazaar-chapter4/
	Ongoing:
		actionbazaar-chapter5/
	ToDo: 
		ActionBazaar/
		actionbazaar-chapter10/
		actionbazaar-chapter10b7/
		actionbazaar-chapter12/
		actionbazaar-chapter14/
		actionbazaar-chapter15/
		actionbazaar-chapter6/
		actionbazaar-chapter7/
		actionbazaar-chapter8/
		actionbazaar-chapter9/
	
Instructions
============
All of the chapters are Maven based. The code samples were tested on the 
   GlassFish 4.1 application servers. The code may be 
   imported in IDEs supporting Maven. Eclipse was used for updating project 
   structure and dependencies in Oct 2015.

See the respective <module>/readme.txt for instructions to run the examples.

Note: 
	1. Run this command to install root pom.xml
		mvn -pl actionbazaar -N install
		
	2. DONT run any  "mvn" command on the root project - actionbazaar - other 
		than the one give above. Go to the individual chapter modules and 
		following the instructions in the respective readme files.
		  
	2. You may need to add a maven profile in your $HOME/.m2/settings.xml for
		your glassfish configurations. Just google for instructions.
		----------------------snippet begin---------------------
		<profiles>
			---
			<profile>
					<id>env-dev</id>
					<properties>
										
						<!-- glassfish configuration begin -->
						<glassfish.home>C:/glassfish4/glassfish</glassfish.home>
						<glassfish.adminUser>admin</glassfish.adminUser> 
						<glassfish.adminPassword>asdfgh</glassfish.adminPassword> 
						<glassfish.echo>false</glassfish.echo> 
						<glassfish.terse>true</glassfish.terse> 
						<glassfish.debug>false</glassfish.debug>						
						<glassfish.appclient>${glassfish.home}/bin/appclient.bat</glassfish.appclient> <!-- windows -->
						<!-- <glassfish.appclient>${glassfish.home}/bin/appclient</glassfish.appclient> --><!-- linux -->
						<database.password>asdfasdf</database.password> 
						<!-- glassfish configuration end -->					
					</properties>
		    </profile>	
	    	---
	    </profiles>
	    ---
	    <activeProfiles>
	     	---
			<activeProfile>env-dev</activeProfile>
			---		    
		</activeProfiles>	    
	    -----------------------snippet end----------------------

