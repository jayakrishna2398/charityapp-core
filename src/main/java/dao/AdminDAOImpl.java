package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Users;
import exception.DBException;
import model.Admin;
import model.FundRequest;
import util.ConnectionUtil;
import util.Logger;


public class AdminDAOImpl implements AdminDAO {
	Scanner scan;
	int adminId;
	String adminPassword;
	UserDAOImpl dao = new UserDAOImpl();
	TransactionDaoImpl transaction = new TransactionDaoImpl();
	final String KRISH = "krish";
	/**** THIS METHOD WILL ACCESS THE LOGIN FOR ADMIN *****/
	public void loginAccess() throws DBException {
		Logger.info("================WELCOME ADMIN=================");
		scan = new Scanner(System.in);
		Logger.info("Enter your Admin Id: ");
		adminId = scan.nextInt();
		Logger.info("Enter the password: ");
		adminPassword = scan.next();
		Admin a = new Admin();
		final String KRISH = "krish";
		if (adminId == 90 && adminPassword == KRISH) {
			try {
				a = findByAdmin(adminId, adminPassword);
				Logger.info("/////////////////////////////////////////////////");
				Logger.info("WELCOME ADMIN !!!!");
				Logger.info("YOUR ADMIN NAME IS :" + a.getAdminName());
				Logger.info("YOUR EMAIL ID IS :" + a.getAdminEmailId());
				Logger.info("/////////////////////////////////////////////////");
			} catch (Exception e) {
				throw new DBException("Admin does not exist");
			}
		}
	}

	/********
	 * THIS METHOD WILL DO THE SELECT OPETRATON FOR THE PARTICULAR ADMIN
	 ***********/
	public Admin findByAdmin(int adminId, String adminPassword) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,email_id,password from admin where id = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, adminId);
			pst.setString(2, adminPassword);
			rs = pst.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin = toRow(rs);
			}
		} catch (SQLException e) {
			throw new DBException("unable to login");
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return admin;
	}

	public void donorRegister() throws DBException {
		if (adminId == 90 && adminPassword == "krish") {
			try {
				Logger.info("USERS WHO HAVE REGISTERED");
				List<Users> list = dao.findAll();
				for (Users users : list) {
					Logger.info(users);
				}
			} catch (SQLException e) {
				Logger.error("List not returned");
			}
		}
	}

	public void donorFundRequest(String reqType) throws DBException {
		if (adminId == 90 && adminPassword == "krish") {
			try {
				Logger.info("USERS FUNDED DONATION RECORD");
				List<FundRequest> list = transaction.donorFundRequest(reqType);
				for (FundRequest request : list) {
					Logger.info(request);
				}
			} catch (SQLException e) {
				Logger.error("Record has not been found");;
			}
		}
	}

	private Admin toRow(ResultSet rs) throws DBException {
		Admin admin = null;
		try {
			admin = new Admin();

			String adminName = rs.getString("name");
			String adminPassword = rs.getString("password");
			Integer aId = rs.getInt("id");
			String adminEmailId = rs.getString("email_id");
			admin.setAdminName(adminName);
			admin.setAdminPassword(adminPassword);
			admin.setAdminId(aId);
			admin.setAdminEmailId(adminEmailId);
		} catch (SQLException e) {
			throw new DBException("impossible");
		}
		return admin;
	}

	public Admin findByAdmin(int adminId, int adminPassword) throws DBException {
		return null;
	}


}
