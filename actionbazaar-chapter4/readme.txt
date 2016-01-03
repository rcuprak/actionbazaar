his module deploys and runs in GlassFish 4.1.1.

You may need to add a maven profile in your $HOME/.m2/settings.xml for
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
	    
	     
Instructions
============
1. compile
	mvn clean install 
2. deploy
	mvn glassfish:deploy
3. test
	go to http://localhost:8080/actionbazaar-chapter4 and follow instructions.
	
	