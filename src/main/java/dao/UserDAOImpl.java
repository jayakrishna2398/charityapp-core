package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exception.DBException;
import model.Users;
import uiconsole.MainConsole;
import util.ConnectionUtil;
import util.Logger;
import validator.UserValidator;

public class UserDAOImpl implements UserDAO {
	int donorId;
	String donorName;
	String donorEmailId;
	String donorPassword;
	String gender;
	Scanner scan = new Scanner(System.in);
	TransactionDaoImpl transaction = new TransactionDaoImpl();

	public void loginDetails(Users users) throws Exception {
		try {
			scan = new Scanner(System.in);
			Logger.info("Enter the donor name: ");
			String donorName = scan.next();
			MainConsole.name(donorName);
			Logger.info("Enter the password: ");
			int donorPassword = scan.nextInt();
			users = new Users();
			users = findByLogin(donorName, donorPassword);
			Logger.info("/////////////////////////////////////////////////");
			Logger.info("WELCOME USER !!!!! THE DETAILS ARE MENTIONED BELOW");
			Logger.info("YOUR DONATION ID IS :" + users.getDonorId());
			Logger.info("YOUR EMAIL ID IS :" + users.getDonorEmailId());
			Logger.info("YOUR GENDER IS :" + users.getGender());
			Logger.info("/////////////////////////////////////////////////");
		} catch (SQLException e) {
			throw new NullPointerException("does not exist");
		}
	}

	public void donorRegistration(Users users) throws Exception, InputMismatchException {
		UserDAOImpl userdao = new UserDAOImpl();
		UserValidator validator = new UserValidator();
		try {
			users = new Users();
			int choice = 0;
			Logger.info("=========WELCOME TO DONATION FOR REGISTRATION==============");
			Logger.info("Please select your choice \n 1.Registration \n 2.Login user");
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				Logger.error("Invalid choice");
			}
			while (true) {
				switch (choice) {
				case 1:
					try {
						Logger.info("create new donor ID");
						donorId = scan.nextInt();
						Logger.info("Enter your name");
						donorName = scan.next();
						MainConsole.name(donorName);
						Logger.info("Enter your email ID");
						donorEmailId = scan.next();
						MainConsole.mail(donorEmailId);
						Logger.info("Enter your password");
						donorPassword = scan.next();
						Logger.info("Enter your gender");
						gender = scan.next();
						users.setDonorId(donorId);
						users.setDonorName(donorName);
						users.setDonorEmailId(donorEmailId);
						users.setDonorPassword(donorPassword);
						users.setGender(gender);
						validator.validateRegistrationForDonor(users);
						userdao.insert(users);
						donorRegistration(users);
						break;
					} catch (InputMismatchException e) {
						Logger.error(e.getMessage());
					} finally {
						Logger.error("unable to login");
					}
				case 2:
					try {
						Logger.info("Enter the login details");
						users.setDonorName(donorName);
						users.setDonorPassword(donorPassword);
						loginDetails(users);
						validator.validateLoginForDonor(users);
						userdao.findByLogin(donorName, donorPassword);
						transaction.transaction();
						donorRegistration(users);
						break;
					} catch (InputMismatchException e) {
						Logger.error(e.getMessage());
					}
				}
			}
		} finally {
			Logger.info("unable to login");
		}
	}

	public void insert(Users users) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into donor (id,name,email_id,password,gender) values ( ?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, users.getDonorId());
			pst.setString(2, users.getDonorName());
			pst.setString(3, users.getDonorEmailId());
			pst.setString(4, users.getDonorPassword());
			pst.setString(5, users.getGender());
			int rows = pst.executeUpdate();
			Logger.info("No of rows inserted :" + rows);
			Logger.info("Account created successfully");
		} catch (SQLException e) {
			throw new DBException("unable to insert rows", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

	public Users findByLogin(String email, String donorPassword) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Users users = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,email_id,password,gender from donor where email_id = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, donorPassword);
			rs = pst.executeQuery();
			if (rs.next()) {
				users = new Users();
				users = toRow(rs);
			}
		} catch (SQLException e) {
			Logger.error("User account does not exist");
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return users;
	}

	public List<Users> findAll() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Users> list = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,email_id,password,gender from donor";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<Users>();
			while (rs.next()) {
				Users users = toRow(rs);
				list.add(users);
			}
		} catch (SQLException e) {
			throw new DBException("unable to List users", e);
		}
		return list;
	}

	private Users toRow(ResultSet rs) throws DBException {
		Users users = null;
		try {
			String name = rs.getString("name");
			String pswd = rs.getString("password");
			Integer id = rs.getInt("id");
			String emailId = rs.getString("email_id");
			String gender = rs.getString("gender");
			users = new Users();
			users.setDonorName(name);
			users.setDonorPassword(pswd);
			users.setDonorId(id);
			users.setDonorEmailId(emailId);
			users.setGender(gender);

		} catch (SQLException e) {
			throw new DBException("rows not been generated", e);
		}
		return users;
	}

	public Users findByLogin(String donorName, int donorPassword) throws DBException {
		return null;
	}

}
