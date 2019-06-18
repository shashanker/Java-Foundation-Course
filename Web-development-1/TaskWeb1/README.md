Steps .

1 . Required software to run the program.<br/>
<pre>a) Java 1.8
b) maven
c) Tomcat 8 or above</pre>
2 . Run the below mvn command from the project directory<br/>
     <pre> mvn clean package install</pre><br/><br/>

3 . The above command will generate war file . <br/>
4 . Copy the war to the tomcat webapps direcory .<br/>
5 . Access the application by url <pre>http://localhost:8080/TaskWeb1/</pre><br/><br/>

(or)<br/><br/>

If you want to run from eclipse<br/><br/> 

1 . Import the maven project .<br/>
2 . Run as Maven Build (clean package install) .<br/>
3 . setup the Tomcat server .<br/>
4 . Right-click on the project and select Run As -> Run on Server<br/>

