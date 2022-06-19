# Hepsiburada-Sample-Automation-Project
This is a sample mobile automation project for testing the UI functionalities of Hepsiburada Shopping Android app.

Developed using [Appium](https://appium.io/) and [Gauge](https://gauge.org) framework.


[![Java Badge](https://forthebadge.com/images/badges/made-with-java.svg)](#)

[![Gauge Badge](https://gauge.org/Gauge_Badge.svg)](https://gauge.org)

This project is based on Page Object Model (POM) architecture.

## Setting up this project
* Real [Android](https://www.android.com/) devices are used when running the tests.

### Prerequisites

This project requires the following software to run.
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or above
    * **Note:** Gauge works with Java 1.6 and above. This project uses Java 1.8
* [Gauge](https://docs.gauge.org/getting_started/installing-gauge.html)
* Gauge Plugins
    * Java plugin
        * Can be installed using `gauge install java`
    * HTML Report plugin
        * Can be installed using `gauge install html-report`
    * XML Report plugin
        * Can be installed using `gauge install xml-report`
* [Appium](https://appium.io/)

### Setting up Appium

* Download and install the latest [Appium](https://github.com/appium/appium-desktop/releases/latest) client
* Start Appium server by running this command
```
appium
```
* Appium server should now be available at [http://127.0.0.1:4723/wd/hub](http://127.0.0.1:4723/wd/hub)

### Setting up Maven

* [Maven installation instructions](http://maven.apache.org/install.html)

## Running this project

If you already have [Maven](http://maven.apache.org/index.html) installed, you can execute specs as:
```
mvn gauge:execute -DspecsDir=specs -Denv=default
```

You can find these environment variables in the ```env/``` folder.

```
# This is a sample environment variable file

APPIUM_PLATFORM_VER=10

DEVICE_NAME= 
DEVICE_UDID= 
APP_PACKAGE=com.pozitron.hepsiburada
APP_ACTIVITY=com.hepsiburada.ui.startup.SplashActivity
PLATFORM_NAME=android

```
For testing with different phones other than the defined phone in properties files, change the ```DEVICE_NAME``` and 
```DEVICE_UDID``` to the corresponding values.
