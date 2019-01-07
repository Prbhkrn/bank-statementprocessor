package com.project.bankstatementprocessor.model.output;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an Record.
 * 
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@XmlRootElement
public class Record {
	/** Represents the record reference */
	private int reference;
	/** Represents the description */
	private String description;

	/**
	 * Creates an Record
	 * 
	 * @param reference
	 * @param description
	 */
	public Record(int reference, String description) {
		this.reference = reference;
		this.description = description;
	}

	/** Creates an Record */
	public Record() {
	}

	/**
	 * Gets the reference.
	 * 
	 * @return A int representing the reference
	 */
	@XmlAttribute
	public int getReference() {
		return reference;
	}

	/**
	 * Sets the reference
	 * 
	 * @param reference
	 *            A int containing the reference
	 */
	public void setReference(int reference) {
		this.reference = reference;
	}

	/**
	 * Gets the Description.
	 * 
	 * @return A String representing the Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * 
	 * @param reference
	 *            A String containing the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Record [reference=" + reference + ", description=" + description + "]";
	}

}
