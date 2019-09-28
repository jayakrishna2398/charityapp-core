package validator;

import exception.ValidatorException;
import model.Users;

public class UserValidator {
	public void validateRegistrationForDonor(Users users) throws ValidatorException {
		String donorName = users.getDonorName();
		if (donorName == null || "".equals(donorName.trim())) {
			throw new ValidatorException("Invalid Name");
		}
		String donorPassword = users.getDonorPassword();
		if (donorPassword == null || "".equals(donorName.trim())) {
			throw new ValidatorException("Invalid password");
		}
		String donorEmailId = users.getDonorEmailId();
		if (donorEmailId == null || "".equals(donorName.trim())) {
			throw new ValidatorException("Invalid Email ID");
		}
		String gender = users.getGender();
		if (gender == null || "".equals(donorName.trim())) {
			throw new ValidatorException("Invalid gender");
		}
		int donorId = users.getDonorId();
		if (donorId == 0) {
			throw new ValidatorException("Invalid Id");
		}
	}

	public void validateLoginForDonor(Users users) throws ValidatorException {
		String donorName = users.getDonorName();
		if (donorName == null || "".equals(donorName.trim())) {
			throw new ValidatorException("Invalid Name");
		}
		String donorPassword = users.getDonorPassword();
		if (donorPassword == null || "".equals(donorName.trim())) {
			throw new ValidatorException("Invalid password");
		}
	}

}
