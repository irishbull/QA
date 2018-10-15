# LM-TestAutomation

Java project for test automation


## Prerequisites
In order to utilise this project you need to have installed locally: 

1. Java 8 
1. Gradle 4.7


## Usage

### Run tests 
...
`gradle clean refreshTestEnvironment -PEnv={environment} -PBrowser={browser} test`
...

### Generate report
...
`gradle allureReport -PEnv={environment} -PBrowser={browser}`
...

where `{environment}` is one of:
 - `development` *
 - `integration`
 - `production`
 - `catalogo`


where `{browser}` is one of:
 - `chrome` *
 - `firefox`
 - `iexplorer`

*  default value when the property is not specified
