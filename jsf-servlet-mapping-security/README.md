

Usage
-------

    mvn jboss-as:run
Will start WildFly and deploy the war. You can then access the app on

    localhost:8080/secure-jsf


* GET [http://localhost:8080/secure-jsf/index.jsf](http://localhost:8080/secure-jsf/index.jsf) => HTTP 403 Forbidden
* GET [http://localhost:8080/secure-jsf/faces/index.xhtml](http://localhost:8080/secure-jsf/faces/index.xhtml) => HTTP 200 Ok
* GET [http://localhost:8080/secure-jsf/index.faces](http://localhost:8080/secure-jsf/index.faces) => HTTP 200 Ok