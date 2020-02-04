package com.redhat.demo.dm.ccfraud.domain;

import java.math.BigDecimal;

/**
 * Represents a credit-card transaction.
 * <p/>
 * This class is immutable.
 * 
 * @author <a href="mailto:ddoyle@redhat.com">Duncan Doyle</a>
 */
@org.kie.api.definition.type.Role(org.kie.api.definition.type.Role.Type.EVENT)
@org.kie.api.definition.type.Expires("2m")
public class CreditCardTransaction {

	private long transactionNumber;
	private long creditCardNumber;
	private BigDecimal amount;
	private long timestamp;
	private Terminal terminal;
	
	public CreditCardTransaction(long transactionNumber, long creditCardNumber, BigDecimal amount, long timestamp, Terminal terminal) {
		this.transactionNumber = transactionNumber;
		this.creditCardNumber = creditCardNumber;
		this.amount = amount;
		this.timestamp = timestamp;
		this.terminal = terminal;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}
	
	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	@Override
	public String toString() {
		return "CreditCardTransaction{" +
				"transactionNumber=" + transactionNumber +
				", creditCardNumber=" + creditCardNumber +
				", amount=" + amount +
				", timestamp=" + timestamp +
				", terminal=" + terminal +
				'}';
	}
}
