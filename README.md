# Simple Mobile Automation Framework using Appium

### Tech Stack: *Selenium, Appium, Log4j, Maven, TestNG*

**Mobile App Used** : Coinswitch Kuber (available on Google play store)

*Use "mvn clean test" from command line to trigger the tests in testng.xml*

### Prerequisites:
1. Appium server must be up and running
2. Coinswitch Kuber app must be installed on device

### Additional details:

**src/main/java** -> com.coinswitch.base -> BaseTest.java : Base class for all test classes and responsible for driver instantiation with relevant capabilities, app lauch, app close and appium session termination

**src/main/java** -> com.coinswitch.helper -> AppiumUtilities.java : Helper methods to handle identification, waiting of mobile elements and notifications

**src/main/resources** -> config.properties : Capabilities to start appium driver session and other configurable properties like phone number, pin etc are stored here. Please configure these properly before running the tests.

**src/main/resources** -> log4j.xml : Log4j configuration is stored here

**src/test/java** -> com.coinswitch.pages : All page objects are placed here

**src/test/java** -> com.coinswitch.tests : All test classes are placed here

**Test Results** are generated in "target/surefire-reports" folder.

**Logs** are generated in logs folder
