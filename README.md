# Crutchfield New Media [Selenium Tests]
This repository contains [Selenium](http://seleniumhq.org/) tests for the [Crutchfield Corporation](https://www.crutchfield.com/), [GitHub](https://github.wsgc.com/ychukhari/WSI_Base)   DEMO-project.

## Dependencies
1. Make sure you have [Java](http://www.java.com/) installed on your system, if not follow the vendor instructions for installing them on your operating system.


## Running Tests
The following steps should get you set up for running Selenium tests locally on your machine:

1. Clone this repository to your local machine.
2. All commands must be run from the `\WebStoreAutomation` directory cloned during setup process above.


### Run all test without of reports
mvn clean test  -Denv=prod  -DdriverType=Chrome   -Dng.suite=testng1
##### OR
mvn clean test  -Denv=prod  -DdriverType=Chrome   -Dng.suite=testng2



### Run all test and get reports
mvn clean site  -Denv=prod  -DdriverType=Chrome  -Dng.suite=testng1.xml 
##### OR
mvn clean site  -Denv=prod  -DdriverType=Chrome  -Dng.suite=testng2.xml 


### Skip all test
mvn -o site -DskipTests=true


### Parameters
The following are valid test parameters:

*	`-Denv` - The environment on which the test(s) should be run in.
*	`-DdriverType` - Which browser to use, for example `Chrome`, `FF`.
*	`-Dng.suite` - Which test scenario to run.



### Environments
The following are valid for use in the `-Denv` parameter (Environment):
*	prod
*   qa - [Not Implement yet : unavailable]



### Browsers
The following are valid for use in the `-DdriverType` parameter:

*	FF
*	CHROME
*	IE - [Not Implement yet]


### Test Suite (for demo)
The following are valid for use in the `-Dng.suite` parameter (TestSuite):

*	testng1.xml - 'startPage' - suite consists base tests (1. a correct title of a page, 2. number of links with null 'href' attribute, 3. number of IMG with null 'src' attribute, 4. provide equals attribute 'src' = 'alt' in element IMG)
*	testng2.xml - 'regression' - E2E scenario (coming to the site, buy something and get order number)


# Reports
In project exist 3 kind of reports:

- [SureFire](http://maven.apache.org/surefire/maven-surefire-plugin/) report. The Surefire Plugin is used during the test phase of the build lifecycle to execute the unit tests of an application.

- [Allure](http://allure.qatools.ru/) report. An open-source framework designed to create test execution reports clear to everyone in the team. 

- [Java Code Coverage](http://www.eclemma.org/jacoco/index.html) report. JaCoCo is a free code coverage library for Java, which has been created by the EclEmma team based on the lessons learned from using and integration existing libraries for many years. 


