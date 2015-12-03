Instructions
============
1. compile and deploy
	mvn -pl actionbazaar-chapter3-ear -am clean install glassfish:deploy
2. run client
	mvn -pl actionbazaar-chapter3-client exec:exec
