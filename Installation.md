### System Required ###
  1. Java Runtime Environment 1.5.0 +
  1. if package form source code, Maven 3.0 + is required

### From Source Code ###
  1. Download source code from Downloads or check out from svn.
  1. From terminal cd to connect's root directory. then execute `mvn package` command.
  1. After execute `mvn package` command, you will find a connect-web-${version}.war in ${CONNECT\_ROOT}/connect-web/target. this package is the same as which you download from Google Code's Downloads.


### From War Package ###
  1. Download war package from downloads, and put the war under tomcat's _webapp_.
  1. Startup the tomcat, the tomcat will unpackage connect-web.war, then cd to _webapp_/connect-web/WEB-INF/classes, your need to edit application.properties according to your database environment.After finish editing. you need to restart tomcat.