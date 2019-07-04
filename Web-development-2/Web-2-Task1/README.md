Maven steps :
	1. $ mvn compile ( to compile src files)
	$ mvn test-compile (to compile src and test files)
	
	2. $ mvn package
	
	3. $ mvn test
	  -- it will run the test
	4. $ mvn surefire-report:report
	It will generate html test report at location 
	<project-dir>/target/site/surefire-report.html
	
	
	5. $ mvn checkstyle:check
	It will run Checkstyle and display the results accordingly
	6. $ mvn package (creates are war).
	7. $mvn  tomcat7:run


Gradle Steps :
	1. $ gradle compileJava
	2. $ gradle build
	3. $ gradle test
	  -- it will run the test
	 -- it will also generate html test report
	4. Add checkStyle plugin

	apply plugin: 'checkstyle'
	checkstyle {
	    toolVersion '7.8.1'
	    configFile file("config/checkstyle/checkstyle.xml")
	}
	checkstyleMain {
	    source ='src/main/java'
	}
	checkstyleTest {
	    source ='src/test/java'
	}
	
	Run cmd :
	$ gradle build or $ gradle check

	5. Create  War file
	apply plugin : 'war'
	 
	Run cmd :
	$ gradle war

	6. Deploy to Tomcat using Gradle tomcat plugin
	
	buildscript {
	    repositories {
	        jcenter()
	    }
	 
	    dependencies {
	        classpath 'com.bmuschko:gradle-tomcat-plugin:2.0'
	    }
	}
	apply plugin: 'com.bmuschko.tomcat'
	// War file name
	war.baseName = 'Web-2-Task1'
	// Web directory, this overrides the default value "webapp"
	project.webAppDirName = 'WebContent'
	
	sourceSets {
		main {
			java.srcDirs = ['src/main/java']
		}
	
	}
	dependencies {
	
		def tomcatVersion = '8.0.42'
	    tomcat "org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}",
	           "org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}",
	           "org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}"
	}
	
	tomcatRun.contextPath = '/WebTask1'
	tomcatRunWar.contextPath = '/WebTask1'
	
	Run cmd :
	$ gradle tomcatRunWar
	
	7. Create custom tasks in Gradle .
	Created task  showDate - to display current Date 
	class ShowDate extends DefaultTask {
		String dateMessage = "Current Date: "
		
		@TaskAction
		void doTask(){
			println dateMessage + new Date()
		}
		
		
	}
	
	task showDate(type: ShowDate){
		dependsOn build
}