package com.project.bankstatementprocessor.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.bankstatementprocessor.constants.ApplicationConstants;
import com.project.bankstatementprocessor.model.input.Record;
import com.project.bankstatementprocessor.model.input.Records;
import com.project.bankstatementprocessor.service.CSVParser;
import com.project.bankstatementprocessor.service.XMLParser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ParserFactoryTest.class)
@ContextConfiguration(classes = { ParserFactory.class })
public class ParserFactoryTest {

	@MockBean
	private XMLParser xmlParser;
	@MockBean
	private CSVParser csvParser;
	@Autowired
	private ParserFactory parserFactory;

	@Test
	public void testParseFileForXML() {
		Records records = new Records();
		Record record1 = new Record(123, "A123", 1.0, 2.0, "Test", 3.0);
		Record record2 = new Record(456, "A98", 100.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		records.setRecord(recordList);
		when(xmlParser.readFile(any())).thenReturn(records);
		when(xmlParser.writeFile(any(), any())).thenReturn(ApplicationConstants.SUCESS_MESSAGE_XML);
		ResponseEntity<String> responseFinal = parserFactory.parseFile("XML");
		assertNotNull(responseFinal);
		assertEquals(HttpStatus.OK, responseFinal.getStatusCode());
		assertEquals("Report generation is sucessfull for XML. Please check the output folder.",
				responseFinal.getBody());
	}

	@Test
	public void testParseFileForCSV() {
		Records records = new Records();
		Record record1 = new Record(123, "A123", 1.0, 2.0, "Test", 3.0);
		Record record2 = new Record(456, "A98", 100.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		records.setRecord(recordList);

		when(csvParser.readFile(any())).thenReturn(records);
		when(csvParser.writeFile(any(), any())).thenReturn(ApplicationConstants.SUCESS_MESSAGE_CSV);

		ResponseEntity<String> responseFinal = parserFactory.parseFile("CSV");
		assertNotNull(responseFinal);
		assertEquals(HttpStatus.OK, responseFinal.getStatusCode());
		assertEquals("Report generation is sucessfull for CSV. Please check the output folder.",
				responseFinal.getBody());
	}

	@Test
	public void testParseFileForInvalidFormat() {
		ResponseEntity<String> responseFinal = parserFactory.parseFile("123");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseFinal.getStatusCode());
		assertEquals("Invalid format 123.Please try the valid file format.", responseFinal.getBody());
	}

	@Test
	public void testProcessRecordData() {
		Records inputRecords = new Records();
		Record record1 = new Record(123, "A123", 1.0, 2.0, "Test", 3.0);
		Record record2 = new Record(666, "A98", 100.0, 12.0, "Test desc", 112.0);
		Record record3 = new Record(456, "A98", 100.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		recordList.add(record3);
		inputRecords.setRecord(recordList);

		com.project.bankstatementprocessor.model.output.Records recordsFinal = parserFactory
				.processRecordData(inputRecords);

		assertNotNull(recordsFinal);
		assertEquals(3, recordsFinal.getRecord().size());
		assertEquals(123, recordsFinal.getRecord().get(0).getReference());
		assertEquals(666, recordsFinal.getRecord().get(1).getReference());
		assertEquals(456, recordsFinal.getRecord().get(2).getReference());
	}

	@Test
	public void testProcessRecordIncorrectEndBalance() {
		Records inputRecords = new Records();
		Record record1 = new Record(123, "A123", 5.0, 2.0, "Test", 3.0);
		Record record2 = new Record(456, "A98", 22.0, 12.0, "Test desc", 112.0);
		Record record3 = new Record(456, "A98", 5.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		recordList.add(record3);
		inputRecords.setRecord(recordList);
		com.project.bankstatementprocessor.model.output.Records recordsFinal = parserFactory
				.processRecordData(inputRecords);
		assertNotNull(recordsFinal);
		assertEquals(0, recordsFinal.getRecord().size());
	}

	@Test
	public void testProcessRecordDistinctReferenceData() {
		Records inputRecords = new Records();
		Record record1 = new Record(123, "A123", 1.0, 2.0, "Test", 3.0);
		Record record2 = new Record(666, "A98", 100.0, 12.0, "Test desc", 112.0);
		Record record3 = new Record(666, "A98", 100.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		recordList.add(record3);
		inputRecords.setRecord(recordList);

		com.project.bankstatementprocessor.model.output.Records recordsFinal = parserFactory
				.processRecordData(inputRecords);

		assertNotNull(recordsFinal);
		assertEquals(2, recordsFinal.getRecord().size());
	}

	@Test
	public void testProcessXMLFile() {
		Records inputRecords = new Records();
		Record record1 = new Record(123, "A123", 1.0, 2.0, "Test", 3.0);
		Record record2 = new Record(666, "A98", 100.0, 12.0, "Test desc", 112.0);
		Record record3 = new Record(456, "A98", 100.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		recordList.add(record3);
		inputRecords.setRecord(recordList);
		when(xmlParser.readFile(any())).thenReturn(inputRecords);
		when(xmlParser.writeFile(any(), any())).thenReturn(ApplicationConstants.SUCESS_MESSAGE_XML);

		String responseFinal = parserFactory.processXMLFile();
		assertEquals("Report generation is sucessfull for XML. Please check the output folder.", responseFinal);
	}

	@Test
	public void testProcessCSVFile() {
		Records inputRecords = new Records();
		Record record1 = new Record(123, "A123", 1.0, 2.0, "Test", 3.0);
		Record record2 = new Record(666, "A98", 100.0, 12.0, "Test desc", 112.0);
		Record record3 = new Record(456, "A98", 100.0, 12.0, "Test desc", 112.0);
		List<Record> recordList = new ArrayList<>();
		recordList.add(record1);
		recordList.add(record2);
		recordList.add(record3);
		inputRecords.setRecord(recordList);
		when(csvParser.readFile(any())).thenReturn(inputRecords);
		when(csvParser.writeFile(any(), any())).thenReturn(ApplicationConstants.SUCESS_MESSAGE_CSV);

		String responseFinal = parserFactory.processCSVFile();
		assertEquals("Report generation is sucessfull for CSV. Please check the output folder.", responseFinal);
	}

}
