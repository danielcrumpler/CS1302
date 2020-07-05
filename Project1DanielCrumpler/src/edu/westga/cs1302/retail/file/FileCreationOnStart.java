package edu.westga.cs1302.retail.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.westga.cs1302.retail.model.Department;
import edu.westga.cs1302.retail.model.Store;
import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class FileCreationOnStart.
 * 
 * @author CS1302
 */
public class FileCreationOnStart {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm");
	private File storeFile;
	private String fileStringName;
	
	/**
	 * Creates file on launch of program
	 * 
	 * @throws IOException if file cannot be created
	 */
	public FileCreationOnStart() throws IOException {
		LocalDateTime localDateTime = LocalDateTime.now();
		String fileName = localDateTime.format(this.formatter);
		
		File file = new File(fileName + ".txt");
		file.createNewFile();
		this.storeFile = file;
	}
	
	/**
	 * Writes the department to the store file
	 * 
	 * @precondition store != null
	 * @postcondition none
	 * 
	 * @param store store
	 * 
	 * @throws FileNotFoundException exception to be thrown if file not found
	 */
	public void write(Store store) throws FileNotFoundException {
		if (store == null) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_NULL);
		}
		this.fileStringName = this.storeFile.getName();
		this.storeFile.delete();
		File file = new File(this.fileStringName);
		this.storeFile = file;
		PrintWriter writer = new PrintWriter(this.storeFile);
		for (Department department : store.getDepartments()) {
			writer.println(department.getName());
		}
		writer.close();
	}	
}
