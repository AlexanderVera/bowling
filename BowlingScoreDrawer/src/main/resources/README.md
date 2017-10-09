# Project Title

BowlingScoreDrawer
This take all pinfall in a bowling get by a text file and return a console print representation of the final result

## Getting Started

Unzip the zip file in a empty folder.
The zip content will be:
	-	pom.xml: Maven config file
	-	/src/: Source files
	-	/test-files/bowling-game.txt: Happy path 
	-	/test-files/test-frame-all-strike.txt: File with all strike shoots
	-	/test-files/test-frame-all-fail.txt: File with all fail shoots

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
	- Excecute the command: bowling-install.sh on Unix OS
	- Excecute the command: bowling-install.bar on Windows OS
	- Maven build a binary jar in the folder BowlingScoreDrawer/target

## Running the app

To Run this app use the command:
### Windows OS
	run-bowling.bat /abosolute/path/bowling-game.txt
	e.g. run-bowling.bat test-files\bowling-game.txt
	
### Unix OS
	run-bowling.sh /abosolute/path/bowling-game.txt 
	e.g. run-bowling.sh test-files/bowling-game.txt

	
*Note* /absolute/path/ is path where is located the example file include in the zip
	   If the .txt file is in the same app folder you can run the app this way:
	   java -jar bowlingScore.jar bowling-game.txt 	

The bowlingScore.jar must be nearest to the 'lib' folder

### Test examples

The automatic unit test contemplates this scenarios:
	- Happy path. The file with format and content ideal.
	- Incomplete game case. At least one of the player doesn't finished the game.
	- Empty file case.  The file to test is empty
	- Extra shoots case. At least one player has a extra bonus shoots. 
	- 4 players case. A game with four player
	- No file game. Invalid or non existent file as input
	- All strike case. A game with a player with all strike shoots.
	- All fail case. A game with a player who fail all his shoots


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning


## Authors
**Jorge Alexander Vera Meneses ** [LinkedIn] https://www.linkedin.com/in/jorge-alexander-vera-meneses-5ab159118/
							   ** [email] jalexanderv.meneses@gmail.com

