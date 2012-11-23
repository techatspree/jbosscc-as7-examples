#!/bin/sh

CLIENT_JAR="jms-cluster-example-client/target/jms-cluster-example-client-1.0-SNAPSHOT.jar"

if [ ! -f $CLIENT_JAR ]
then
	echo The client has not been built yet. Building now...
	mvn package

	if [ $? != 0 ]
	then
		echo Maven built was not successful.
		exit 1
	fi
fi

if [ "x$JBOSS_HOME" = "x" ]
then
	echo Please set variable JBOSS_HOME
	exit 1
fi

CLASSPATH=$CLIENT_JAR:$JBOSS_HOME/modules/javax/jms/apu/main/jboss-jms-api_1.1_spec-1.0.1.Final.jar:$JBOSS_HOME/bin/client/jboss-client.jar
java -cp $CLASSPATH de.akquinet.jbosscc.client.Producer 127.0.0.1 4447 admin 123123
