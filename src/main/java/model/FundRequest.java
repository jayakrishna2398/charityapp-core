package model;
/**************************THIS CLASS IS THE MODEL FOR FUND REQUEST DAO IMPLEMENTATION*****************************/
public class FundRequest {
	private int fundId;
	private int adminId;
	private String reqType;
	private int amount;
	private double fundRaised;
	private double fundPending;

	public double getFundRaised() {
		return fundRaised;
	}

	public void setFundRaised(double fundRaised) {
		this.fundRaised = fundRaised;
	}

	public double getFundPending() {
		return fundPending;
	}

	public void setFundPending(double fundPending) {
		this.fundPending = fundPending;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "FundRequest [fundId=" + fundId + ", adminId=" + adminId + ", reqType=" + reqType + ", amount=" + amount + ", fundRaised=" + fundRaised + ", fundPending="
				+ fundPending + "]";
	}

}
