package com.project.bankstatementprocessor.factory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.bankstatementprocessor.constants.ApplicationConstants;
import com.project.bankstatementprocessor.model.input.Record;
import com.project.bankstatementprocessor.model.input.Records;
import com.project.bankstatementprocessor.service.CSVParser;
import com.project.bankstatementprocessor.service.XMLParser;

/**
 * The ParserFactory program id factory class for CSV and XML
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@Service
public class ParserFactory {
	@Value("${input.csvFileName}")
	private String inputCSVFileName;
	@Value("${output.csvFileName}")
	private String outputCSVFileName;
	@Value("${input.xmlFileName}")
	private String inputXMLFileName;
	@Value("${output.xmlFileName}")
	private String outputXMLFileName;

	@Autowired
	private XMLParser xmlParser;

	@Autowired
	private CSVParser csvParser;

	/**
	 * util method for parsing the XML and CSV File
	 * 
	 * @param String
	 *            for type
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> parseFile(String file) {
		if (file.equalsIgnoreCase(ApplicationConstants.XML)) {
			return new ResponseEntity<>(processXMLFile(), HttpStatus.OK);
		} else if (file.equalsIgnoreCase(ApplicationConstants.CSV)) {
			return new ResponseEntity<>(processCSVFile(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(String.format(ApplicationConstants.ERROR_MESSAGE, file),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * util method for processing the XML
	 * 
	 * @param none
	 * @return String
	 */
	public String processXMLFile() {
		Records records = xmlParser.readFile(inputXMLFileName);
		com.project.bankstatementprocessor.model.output.Records recordsFinal = processRecordData(records);
		return xmlParser.writeFile(outputXMLFileName, recordsFinal);
	}

	/**
	 * util method for processing the CSV
	 * 
	 * @param none
	 * @return String
	 */
	public String processCSVFile() {
		Records records = csvParser.readFile(inputCSVFileName);
		com.project.bankstatementprocessor.model.output.Records recordsFinal = processRecordData(records);
		return csvParser.writeFile(outputCSVFileName, recordsFinal);
	}

	/**
	 * util method for distinctByKey - unique object based on condition given.
	 * 
	 * @param keyExtractor
	 * @return T
	 */
	public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	/**
	 * processRecordData method for processing the input Records object and
	 * filter if based on condition.
	 * 
	 * @param Records
	 * @return Records
	 */
	public com.project.bankstatementprocessor.model.output.Records processRecordData(Records records) {
		com.project.bankstatementprocessor.model.output.Records recordsFinal = new com.project.bankstatementprocessor.model.output.Records();
		List<Record> recordList = records.getRecord().stream().filter(distinctByKey(Record::getReference))
				.filter(record -> record.getEndBalance() == (record.getStartBalance() + record.getMutation()))
				.collect(Collectors.toList());
		List<com.project.bankstatementprocessor.model.output.Record> newRecord = recordList.stream()
				.map(responseRecord -> new com.project.bankstatementprocessor.model.output.Record(
						responseRecord.getReference(), responseRecord.getDescription()))
				.collect(Collectors.toList());
		recordsFinal.setRecord(newRecord);
		return recordsFinal;
	}

}
