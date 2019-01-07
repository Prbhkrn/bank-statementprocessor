package com.project.bankstatementprocessor.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.bankstatementprocessor.constants.ApplicationConstants;
import com.project.bankstatementprocessor.exception.BusinessException;
import com.project.bankstatementprocessor.model.input.Record;
import com.project.bankstatementprocessor.model.input.Records;

/**
 * The CSVParser class for CSV files
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@Service
public class CSVParser implements StatementInterface {

	private static final Logger logger = LoggerFactory.getLogger(CSVParser.class);

	/**
	 * Read the CSV file and return Records Object
	 * 
	 * @param null
	 * @return Records
	 */
	@Override
	public Records readFile(String inputFileName) {
		logger.info(String.format(ApplicationConstants.INPUT_MESSAGE, inputFileName));
		Records records = new Records();
		List<Record> recordList = new ArrayList<>();
		String line = "";

		try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
				String[] value = line.split(ApplicationConstants.COMMA_DELIMITER);
				if (value.length > 0) {
					Record record = new Record();
					record.setReference(Integer.parseInt(value[0]));
					record.setAccountNumber(value[1]);
					record.setDescription(value[2]);
					record.setStartBalance(Double.parseDouble(value[3]));
					record.setMutation(Double.parseDouble(value[4]));
					record.setEndBalance(Double.parseDouble(value[5]));
					recordList.add(record);
				}
				records.setRecord(recordList);
			}
		} catch (FileNotFoundException e) {
			throw new BusinessException(ApplicationConstants.FILE_NOT_FOUND);
		} catch (IOException e) {
			throw new BusinessException(ApplicationConstants.ERROR_PROCESSING);
		}
		return records;
	}

	/**
	 * Write Records Object to CSV file
	 * 
	 * @param Records
	 * @return null
	 */
	@Override
	public String writeFile(String outputFileName, com.project.bankstatementprocessor.model.output.Records records) {
		try (FileWriter fileWriter = new FileWriter(outputFileName)) {
			fileWriter.append(ApplicationConstants.FILE_HEADER);
			fileWriter.append(ApplicationConstants.NEW_LINE_SEPARATOR);
			for (com.project.bankstatementprocessor.model.output.Record rec : records.getRecord()) {
				fileWriter.append(String.valueOf(rec.getReference()));
				fileWriter.append(ApplicationConstants.COMMA_DELIMITER);
				fileWriter.append(String.valueOf(rec.getDescription()));
				fileWriter.append(ApplicationConstants.NEW_LINE_SEPARATOR);
			}
			logger.info(String.format(ApplicationConstants.OUTPUT_MESSAGE, outputFileName));
		} catch (IOException e) {
			throw new BusinessException(ApplicationConstants.ERROR_PROCESSING);
		}
		return String.format(ApplicationConstants.SUCESS_MESSAGE_CSV, outputFileName);
	}
}
