Clustering and High Availability of the Messaging subsystem HornetQ in EAP6 and JBoss AS 7
=========================================================

This example shows the cluster capabilities of HornetQ and how to configure the EAP 6 / JBoss AS 7 messaging subsystem.

See our blog for more information about this example and the clustering capabilities of HornetQ: 
	http://blog.akquinet.de

Server configuration
-----------------

The directory server-configuration contains the configuration files for the application server. These configurations are for the EAP 6. Maybe some version numbers need to be modified to make it work on a JBoss AS 7.1 (at least version 7.1.2 recommended).

There are already some port-offsets configured into the servers:
- live1   has 0
- backup1 has 25
- live2   has 100
- backup1 has 125

Also on every server there already is a user "admin" with password "123123" as both: ApplicationUser and ManagementUser.

Copy the standalone/configuration/ directories into you servers and start them as follows:
* live1  : ./standalone.sh -c standalone-full-ha.xml -Djboss.node.name=jl1
* backup1: ./standalone.sh -c standalone-full-ha.xml -Djboss.node.name=jbu1
* live2  : ./standalone.sh -c standalone-full-ha.xml -Djboss.node.name=jl2
* backup2: ./standalone.sh -c standalone-full-ha.xml -Djboss.node.name=jbu2


Build and deploy the server application
---------------------------------
* To build the application you need maven.
* Navigate to the jms-cluster-example-server directory and execute
	mvn package
* Deploy the application by copying the jms-cluster-example/jms-cluster-example-server/target/jms-cluster-example-server-1.0-SNAPSHOT.jar into the deploy directory of the JBoss AS live1 and live2 servers.
	

Build and run the client application
-----------------------------
* Navigate to the jms-cluster-example-client directory and execute
	mvn package
* Start the client application by execute the maven command:
	mvn exec:exec	





