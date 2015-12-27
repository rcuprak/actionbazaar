
This module deploys and runs in GlassFish 4.1.1.

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
	mvn -pl actionbazaar-chapter3-ear -am clean install 
2. deploy
	mvn -pl actionbazaar-chapter3-ear glassfish:deploy
3. run client as an application client
	mvn -pl actionbazaar-chapter3-client -P application exec:exec
	
4. run as standalone client
	-----------------------------------
	cd actionbazaar-chapter3-client; \
	MAVEN_OPTS="-Dorg.omg.CORBA.ORBInitialHost=localhost -Dorg.omg.CORBA.ORBInitialPort=3700"; \
	mvn -P standalone exec:java -Dexec.mainClass="com.actionbazaar.client.StandAloneAccountCreatorClient" 
	-----------------------------------
	
	This is equivalent to
		----------------------------------------------- 
		java -Dorg.omg.CORBA.ORBInitialHost=localhost \
		     -Dorg.omg.CORBA.ORBInitialPort=3700 \
		     -cp gf-client.jar:actionbazaar-chapter3-ejb.jar:actionbazaar-chapter3-client.jar \
		     com.actionbazaar.client.StandAloneAccountCreatorClient
		-----------------------------------------------
	But we use maven to simplify resolving the classpath/dependencies and avoid using java directly
	
	