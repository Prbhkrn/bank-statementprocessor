package com.project.bankstatementprocessor.constants;

/**
 * The Application Constants
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
public class ApplicationConstants {

	private ApplicationConstants() {
	}

	public static final String COMMA_DELIMITER = ",";
	public static final String NEW_LINE_SEPARATOR = "\n";
	public static final String FILE_HEADER = "Reference,Description";
	public static final String INPUT_MESSAGE = "File processing Started: %s";
	public static final String OUTPUT_MESSAGE = "File processing Completed. Report %s generated.";
	public static final String FILE_NOT_FOUND = "File Not found. Please enter valid file name";
	public static final String ERROR_PROCESSING = "Error occured when processing the file.Please check the content of file";
	public static final String ERROR_FILE = "Error occured when Closing the file.";
	public static final String FILE_EMPTY = "Record is empty and could not be processed.";
	public static final String JAXB_ERROR = "Error happened while processing the XML file.";
	public static final String MESSAGE_STARTED = "File processing started in controller for %s type";
	public static final String ERROR_MESSAGE = "Invalid format %s.Please try the valid file format.";
	public static final String SUCESS_MESSAGE_XML = "Report generation is sucessfull for XML. Please check the output folder.";
	public static final String SUCESS_MESSAGE_CSV = "Report generation is sucessfull for CSV. Please check the output folder.";
	public static final String XML = "XML";
	public static final String CSV = "CSV";
}
