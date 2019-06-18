Steps .

1 . Required software to run the program.
	a. Java 1.8
        b. maven
        c. Tomcat 8 or above
2 . Run the below mvn command from the project directory
      mvn clean package install

3 . The above command will generate war file . 
4 . Copy the war to the tomcat webapps direcory .
5 . Access the application by url http://localhost:8080/TaskWeb1/

(or)

If you want to run from eclipse 

1 . Import the maven project .
2 . Run as Maven Build (clean package install) .
3 . setup the Tomcat server .
4 . Right-click on the project and select Run As -> Run on Server

