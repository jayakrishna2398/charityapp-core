package model;

/****************************THIS MODEL WILL BE USED TO IMPLEMENT THE USER TRANSACTION DAO*******************************/
public class UsersTransaction {
	private int donorId;
	private int fundRequestId;
	private double targetAmount;
	private int transactionId;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public int getFundRequestId() {
		return fundRequestId;
	}

	public void setFundRequestId(int fundRequestId) {
		this.fundRequestId = fundRequestId;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	@Override
	public String toString() {
		return "UsersTransaction [donorId=" + donorId + ", fundRequestId=" + fundRequestId + ", targetAmount="
				+ targetAmount + ", transactionId=" + transactionId + "]";
	}

}
