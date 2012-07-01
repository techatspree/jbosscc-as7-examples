Modular Paint Example

Build:
mvn install

Start:
cd msc-paint-example/msc-paint-dist/target/msc-paint-example/
java -Djboss.modules.system.pkgs="com.apple.laf,com.apple.laf.resources" -jar jboss-modules.jar -mp modules de.akquinet.jbosscc.msc-paint-main