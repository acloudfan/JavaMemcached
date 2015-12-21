# Java Hello World Web Starter Overview

The Java Hello World Web Starter demonstrates a simple, reusable Java web application.

## Run the app locally

1. Download and extract [Apache Ant][]
2. Add the extracted `bin` directory to your PATH environment variable
3. Download and extract [WAS Liberty with Java EE 7 Web Profile with Java 8][]
4. cd into the extracted directory and run `wlp/bin/server start` to create and start a server
5. Download and extract the starter code from the Bluemix UI
6. cd into the app directory
7. Build the app by running `ant`
8. Copy the resulting WAR file to `wlp/usr/servers/defaultServer/dropins`
9. Access the running app in a browser at http://localhost:9080/JavaHelloWorldApp

[Apache Ant]: https://ant.apache.org/bindownload.cgi
[WAS Liberty with Java EE 7 Web Profile with Java 8]: https://developer.ibm.com/wasdev/downloads/liberty-profile-using-non-eclipse-environments/
