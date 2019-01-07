package com.project.bankstatementprocessor.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.bankstatementprocessor.constants.ApplicationConstants;
import com.project.bankstatementprocessor.exception.BusinessException;
import com.project.bankstatementprocessor.model.input.Records;

/**
 * The XMLParser program for XML files
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@Service
public class XMLParser implements StatementInterface {
	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);

	/**
	 * Read the XML file and return Records Object
	 * 
	 * @param null
	 * @return Records
	 */
	@Override
	public Records readFile(String inputFileName) {
		logger.info(String.format(ApplicationConstants.INPUT_MESSAGE, inputFileName));
		File xmlFile = new File(inputFileName);
		JAXBContext jaxbContext;
		Records records = null;
		try {
			jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			records = (Records) jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			throw new BusinessException(ApplicationConstants.JAXB_ERROR);
		}
		return records;
	}

	/**
	 * Write Records Object to XML file
	 * 
	 * @param Records
	 * @return null
	 */
	@Override
	public String writeFile(String outputFileName, com.project.bankstatementprocessor.model.output.Records records) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(com.project.bankstatementprocessor.model.output.Records.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File(outputFileName);
			jaxbMarshaller.marshal(records, file);
			logger.info(String.format(ApplicationConstants.OUTPUT_MESSAGE, outputFileName));
			return String.format(ApplicationConstants.SUCESS_MESSAGE_XML, outputFileName);
		} catch (JAXBException e) {
			throw new BusinessException(ApplicationConstants.JAXB_ERROR);
		}
	}

}
