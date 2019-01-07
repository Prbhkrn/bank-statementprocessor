package com.project.bankstatementprocessor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.bankstatementprocessor.exception.BusinessException;
import com.project.bankstatementprocessor.model.output.Record;
import com.project.bankstatementprocessor.model.output.Records;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XMLParserTest.class)
@ContextConfiguration(classes = { XMLParser.class })
public class XMLParserTest {

	@Autowired
	private XMLParser xmlParser;

	@Test
	public void testParseFileForXML() {
		com.project.bankstatementprocessor.model.input.Records records = xmlParser.readFile("records-test.xml");
		assertNotNull(records);
		assertEquals(123, records.getRecord().get(0).getReference());
		assertEquals("A23", records.getRecord().get(0).getAccountNumber());
	}

	@Test
	public void testParseFileForInvalidXML() {
		Records recordsFinal = new Records();
		Record recordFinal1 = new Record(123, "Test");
		List<Record> recordFinalList = new ArrayList<>();
		recordFinalList.add(recordFinal1);
		recordsFinal.setRecord(recordFinalList);
		String response = xmlParser.writeFile("records-test_processed.xml", recordsFinal);
		assertNotNull(response);
		assertEquals("Report generation is sucessfull for XML. Please check the output folder.", response);
	}
	
	@Test(expected = BusinessException.class)
	public void testParseFileForXMLError() {
		xmlParser.readFile("recordsJAXB.xml");		
	}
	
}
