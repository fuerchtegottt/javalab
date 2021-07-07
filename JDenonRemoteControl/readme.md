# JDenon
![LOGO](img/JDenonLogo.jpg)

# Description 
java based access layer to DENON AVR 2113 (and compatible) using denon web api

## purpose
control your DENON AVR via http network commands
	
## features
* api class for simple avr operations (focus on zone 2)
* command line interface (CLI) for console access or os scripting
	
## technology
* J2SE
	* http/s communication
	* wraps denon web api (facade)
	* config as properties (e.g. device ip)

## task backlog
- [x] base implementation of api (zone2 on/off)
- [ ] use URI-Builder class
- [ ] add command class
- [ ] add cli param parser
- [ ] add cli interactive mode
- [ ] add javadoc

**Christian Gellert**

- [Profile](https://github.com/fuerchtegottt "Christian Gellert")
- [Email](mailto:christian.gellert@web.de?subject=Hi% "Hi!")
- [Website](http://www.g3ll3rt.de "Welcome")