package dao;

import exception.DBException;
import model.FundRequest;
//import model.Users;
import util.ConnectionUtil;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Admin;

public class FundRequestDAOImpl implements FundRequestDAO {
	int adminId;
	int adminPassword;
	FundRequest request = new FundRequest();
	Admin admin = new Admin();

	public void charityRequest() throws DBException {
		Scanner scan = new Scanner(System.in);
		Admin admin = new Admin();
		FundRequest req = new FundRequest();
		FundRequestDAOImpl request = new FundRequestDAOImpl();
		Logger.info("FUND REQUEST PROCESS");
		Logger.info("Enter your fund Id");
		int fundId = scan.nextInt();
		Logger.info("Enter your admin Id");
		int adminId = scan.nextInt();
		Logger.info("Enter the product to be donated");
		String reqType = scan.next();
		Logger.info("Enter the amount");
		int amount = scan.nextInt();
		req.setFundId(fundId);
		admin.setAdminId(adminId);
		req.setReqType(reqType);
		req.setAmount(amount);
		request.insert(req, admin);
		request.updateAmount(req, admin);

	}

	public void insert(FundRequest request, Admin admin) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into fund_request (id,admin_id,request_type,target_amount) values ( ?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, request.getFundId());
			pst.setInt(2, admin.getAdminId());
			pst.setString(3, request.getReqType());
			pst.setInt(4, request.getAmount());
			int rows = pst.executeUpdate();
			Logger.info("No of rows inserted :" + rows);
			//System.out.println("Account created successfully");
		} catch (SQLException e) {
			throw new DBException("unable to insert rows");
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

	public void updateAmount(FundRequest request, Admin admin) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update fund_request set target_amount = ? where request_type = ?";
			pst = con.prepareStatement(sql);
			pst.setString(2, request.getReqType());
			pst.setInt(1, request.getAmount());
			int rows = pst.executeUpdate();
			Logger.info("No of rows updated :" + rows);

		} catch (SQLException e) {
			throw new DBException("unable to update rows");
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}
	public List<FundRequest> findFundReq() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<FundRequest> list = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,admin_id,request_type,target_amount from fund_request";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<FundRequest>();
			while (rs.next()) {
				FundRequest request = toRow(rs);
				list.add(request);
			}
		} catch (SQLException e) {
			throw new DBException("unable to update rows");
		}
		return list;
	}
	private FundRequest toRow(ResultSet rs) throws DBException {
		FundRequest request = null;
		try {
			Integer fundId = rs.getInt("id");
			Integer adminId = rs.getInt("admin_id");
			String reqType = rs.getString("request_type");
			Integer amount = rs.getInt("target_amount");
			request = new FundRequest();
			request.setFundId(fundId);
			request.setAdminId(adminId);
			request.setReqType(reqType);
			request.setAmount(amount);
			}catch(SQLException e) {
				throw new DBException("List not found");
			}
return request;
}
}