package com.project.bankstatementprocessor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bankstatementprocessor.constants.ApplicationConstants;
import com.project.bankstatementprocessor.factory.ParserFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Statement Controller for Rest Operation /getStatement
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@RestController
@Api(value = "/customer-api")
@RequestMapping("/customer-api")
public class StatementController {

	private static final Logger logger = LoggerFactory.getLogger(StatementController.class);

	@Autowired
	private ParserFactory parserFactory;

	/**
	 * getStatement method for controller to process the XML and CSV File
	 * 
	 * @param String
	 *            for type
	 * @return ResponseEntity<String>
	 */
	@ApiOperation(value = "Get Statement", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customer Statement Report Generated", response = String.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Report not found") })
	@GetMapping(value = "/getStatement")
	public ResponseEntity<String> getStatement(@RequestParam(value = "type", required = true) String type) {
		logger.info(String.format(ApplicationConstants.MESSAGE_STARTED, type));
		return parserFactory.parseFile(type);
	}

}
