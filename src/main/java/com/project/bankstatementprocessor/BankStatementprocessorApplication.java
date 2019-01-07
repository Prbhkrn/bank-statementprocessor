package com.project.bankstatementprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Bank Statement Processor Application for CSV and XML
 *
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@ComponentScan(basePackages = { "com.project.bankstatementprocessor" })
@SpringBootApplication
@EnableSwagger2
public class BankStatementprocessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankStatementprocessorApplication.class, args);
	}

}
