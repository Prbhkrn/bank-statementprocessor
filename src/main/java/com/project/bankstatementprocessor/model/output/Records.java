package com.project.bankstatementprocessor.model.output;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an Records.
 * 
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@XmlRootElement(name = "records")
public class Records {
	/** Represents the List of record reference */
	private List<Record> record;

	/**
	 * Gets the Record.
	 * 
	 * @return A List<Record>
	 */
	public List<Record> getRecord() {
		return record;
	}

	/**
	 * Sets the List of Record
	 * 
	 * @param reference
	 *            A List of Record
	 */
	public void setRecord(List<Record> record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "Records [record=" + record + "]";
	}
}
