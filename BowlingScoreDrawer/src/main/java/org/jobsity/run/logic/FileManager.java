package org.jobsity.run.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IMessages;

public class FileManager implements IFileManager {

	private String fileName;
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	private File file;
	private IMessages messages;

	public FileManager(File file) {
		this.file = file;
		messages = new Messages();
	}

	public List<String> buildListPlayerFromFile() {

		List<String> playerLines = null;

		BufferedReader bufferPlayerLines = null;
		try {
			bufferPlayerLines = validateBoard(getFile());
			String playerLine = null;
			if (bufferPlayerLines != null) {
				playerLines = new ArrayList<String>();
				while ((playerLine = bufferPlayerLines.readLine()) != null) {
					if(playerLine.trim().length()>0){
						playerLines.add(playerLine);
					}
				}
			}
		} catch (IOException exc) {
			LOG.error(exc.getMessage());
		} finally {
			try {
				if (bufferPlayerLines != null) {
					bufferPlayerLines.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}

		return playerLines;
	}

	public BufferedReader validateBoard(File file) throws IOException {
		if (!file.exists()) {
			throw new IOException(messages.getMessage("src.main.messages.file.not.found"));
		} else {
			if (file.length() == 0) {
				throw new IOException(messages.getMessage("src.main.messages.empty.file"));
			} else {
				return new BufferedReader(new FileReader(file));
			}
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
