package org.jobsity.run.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
* IFileManager
* 
* @author alexander.vera
* @since 30/09/2017
*/
public interface IFileManager {
	
	/**
     * Method that take the input file and make a list of file.
     * Lines with the player and his pinfalls
     *
     * @return A list of String, each line is a player info
     */
	List<String> buildListPlayerFromFile();
	
	/**
     * Read a file and check if the file has content or exists
     *
     * @param Object file
     * @return a BufferedReader with the content file.
     */
	BufferedReader validateBoard(File file) throws IOException;
}

/** 
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		add validateBoard method
*/