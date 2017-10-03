# Project Title

BowlingScoreDrawer
This take all pinfall in a bowling get by a text file and return a console print representation of the final result

## Getting Started

Unzip the zip folder in a empty folder.
The content of the zip is:
	-	/sources/pom.xml: Maven config file
	-	/sources/src/: Source files 
	-	/binary/bowlingScore.jar: binary to run directly
	-	/binary/lib: External packaged libs to run the app
	-	/binary/bowling-game.txt: Example file to run the with 0 error ;)
	-	/binary/test-frame-all-strike.txt: File with all strike shoots
	-	/binary/test-frame-all-fail.txt: File with all fail shoots
	-	/binary/test-frame-large-game.txt: File with 3000 players

### Prerequisites
#### Run Binary
	- java 1.8 

##### Compile the source
	- java 1.8 and maven 2.6 to up

*Note* Windows SO you must configure the M2_HOME and MAVEN_HOME variables in order to compile. See also [Install Maven](http://www.baeldung.com/install-maven-on-windows-linux-mac)

### Installing
####
In order to compile this app:
	- Open a terminal in a BowlingScoreDrawer folder
	- Excecute the command: mvn clean install, then wait to the excecute of a unit test
	- Excecute the command: mvn package, then wait to the excecute of a unit test
	- Maven build a binary jar in the folder BowlingScoreDrawer/target

## Running the app

To run this app:
	- Run the command: java -jar bowlingScore.jar '/abosolute/path/bowling-game.txt'

*Note* /absolute/path/ is path where is located the example file include in the zip
	   If the .txt file is in the same app folder you can run the app this way:
	   java -jar bowlingScore.jar bowling-game.txt 	

The bowlingScore.jar must be nearest to the 'lib' folder

### Break down into end to end tests

The automatic unit test contemplates this scenarios:
	- Nice case. The file with format and content ideal.
	- Incomplete game case. At least one of the player doesn't finished the game.
	- Empty file case.  The file to test is empty
	- Extra shoots case. At least one player has a extra bonus shoots. 
	- 4 players case. A game with four player
	- No file game. Invalid or nonexistent file as input
	- All strike case. A game with a player with all strike shoots.
	- All fail case. A game with a player who fail all his shoots

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning


## Authors

**Alexander Vera**

## License

This project is licensed under the CCO - see the [LICENSE.md](LICENSE.md) file for details

