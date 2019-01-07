package com.project.bankstatementprocessor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.bankstatementprocessor.constants.ApplicationConstants;
import com.project.bankstatementprocessor.exception.BusinessException;
import com.project.bankstatementprocessor.model.output.Record;
import com.project.bankstatementprocessor.model.output.Records;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CSVParserTest.class)
@ContextConfiguration(classes = { CSVParser.class })
public class CSVParserTest {

	@Autowired
	private CSVParser csvParser;
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testParseFileForXML() {
		com.project.bankstatementprocessor.model.input.Records records = csvParser.readFile("records-test.csv");
		assertNotNull(records);
		assertEquals(123, records.getRecord().get(0).getReference());
		assertEquals("A123", records.getRecord().get(0).getAccountNumber());
	}

	@Test
	public void testParseFileForInvalidXML() {
		Records recordsFinal = new Records();
		Record recordFinal1 = new Record(123, "Test");
		List<Record> recordFinalList = new ArrayList<>();
		recordFinalList.add(recordFinal1);
		recordsFinal.setRecord(recordFinalList);
		String response = csvParser.writeFile("records-test_processed.csv", recordsFinal);
		assertNotNull(response);
		assertEquals(ApplicationConstants.SUCESS_MESSAGE_CSV, response);
	}

	@Test(expected = BusinessException.class)
	public void testParseInvalidCSVFile() {
		csvParser.readFile("records-123.csv");
	}
}
