package com.project.bankstatementprocessor.service;

import com.project.bankstatementprocessor.model.input.Records;

/**
 * The StatementInterface interface for XMLParser and CSVParser classes
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
public interface StatementInterface {

	public Records readFile(String fileName);

	public String writeFile(String fileName, com.project.bankstatementprocessor.model.output.Records record);
}
