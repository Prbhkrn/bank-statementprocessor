package com.project.bankstatementprocessor.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

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
import com.project.bankstatementprocessor.factory.ParserFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatementControllerTest.class)
@ContextConfiguration(classes = { StatementController.class })
public class StatementControllerTest {

	@MockBean
	private ParserFactory parserFactory;
	@Autowired
	private StatementController statementController;

	@Test
	public void testGetStatementForXML() {
		ResponseEntity<String> response = new ResponseEntity<>(ApplicationConstants.SUCESS_MESSAGE_XML, HttpStatus.OK);
		when(parserFactory.parseFile("XML")).thenReturn(response);
		ResponseEntity<String> responseFinal = statementController.getStatement("XML");
		assertNotNull(responseFinal);
		assertEquals(HttpStatus.OK, responseFinal.getStatusCode());
		assertEquals("Report generation is sucessfull for XML. Please check the output folder.",
				responseFinal.getBody());
	}

	@Test
	public void testGetStatementForCSV() {
		ResponseEntity<String> response = new ResponseEntity<>(ApplicationConstants.SUCESS_MESSAGE_CSV, HttpStatus.OK);
		when(parserFactory.parseFile("CSV")).thenReturn(response);
		ResponseEntity<String> responseFinal = statementController.getStatement("CSV");
		assertNotNull(responseFinal);
		assertEquals(HttpStatus.OK, responseFinal.getStatusCode());
		assertEquals("Report generation is sucessfull for CSV. Please check the output folder.",
				responseFinal.getBody());
	}

	@Test
	public void testGetStatementForInvalidType() {
		ResponseEntity<String> response = new ResponseEntity<>(String.format(ApplicationConstants.ERROR_MESSAGE, "123"),
				HttpStatus.INTERNAL_SERVER_ERROR);
		when(parserFactory.parseFile("123")).thenReturn(response);
		ResponseEntity<String> responseFinal = statementController.getStatement("123");
		assertNotNull(responseFinal);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseFinal.getStatusCode());
		assertEquals("Invalid format 123.Please try the valid file format.", responseFinal.getBody());
	}

}
