package model;

/****************************THIS MODEL IS USED TO ACCESS THE USER IMPLEMENTATIONS OF USER DAO**********************/
public class Users {
	private int donorId;
	private String donorName;
	private String donorEmailId;
	private String donorPassword;
	private String gender;

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDonorEmailId() {
		return donorEmailId;
	}

	public void setDonorEmailId(String donorEmailId) {
		this.donorEmailId = donorEmailId;
	}

	public String getDonorPassword() {
		return donorPassword;
	}

	public void setDonorPassword(String donorPassword) {
		this.donorPassword = donorPassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Users [donorId=" + donorId + ",donorName=" + donorName + ", donorEmailId=" + donorEmailId
				+ ", donorPassword=" + donorPassword + ", gender=" + gender + "]";
	}

}
