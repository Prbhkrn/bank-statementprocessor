package com.project.bankstatementprocessor.model.input;

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
	/** Represents the accountNumber */
	private String accountNumber;
	/** Represents the description */
	private String description;
	/** Represents the startBalance */
	private double startBalance;
	/** Represents the mutation */
	private double mutation;
	/** Represents the endBalance */
	private double endBalance;

	/**
	 * Creates an Record
	 * 
	 * @param reference
	 * @param accountNumber
	 * @param startBalance
	 * @param mutation
	 * @param description
	 * @param endBalance
	 */
	public Record(int reference, String accountNumber, double startBalance, double mutation, String description,
			double endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
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
	 * Gets the AccountNumber.
	 * 
	 * @return A String representing the AccountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the AccountNumber
	 * 
	 * @param reference
	 *            A String containing the AccountNumber
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	/**
	 * Gets the StartBalance.
	 * 
	 * @return A double representing the StartBalance
	 */
	public double getStartBalance() {
		return startBalance;
	}

	/**
	 * Sets the startBalance
	 * 
	 * @param reference
	 *            A double containing the startBalance
	 */
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * Gets the Mutation.
	 * 
	 * @return A double representing the Mutation
	 */
	public double getMutation() {
		return mutation;
	}

	/**
	 * Sets the mutation
	 * 
	 * @param reference
	 *            A double containing the mutation
	 */
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	/**
	 * Gets the EndBalance.
	 * 
	 * @return A double representing the EndBalance
	 */
	public double getEndBalance() {
		return endBalance;
	}

	/**
	 * Sets the endBalance
	 * 
	 * @param reference
	 *            A double containing the endBalance
	 */
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {
		return "Record [reference=" + reference + ", accountNumber=" + accountNumber + ", description=" + description
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", endBalance=" + endBalance + "]";
	}

}
