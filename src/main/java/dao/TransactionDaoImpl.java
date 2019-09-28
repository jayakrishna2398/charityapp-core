package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.FundRequest;
import model.UsersTransaction;
import exception.DBException;
import util.ConnectionUtil;
import util.Logger;

public class TransactionDaoImpl implements TransactionDAO {
	int transactionId;
	int donorId;
	int fundRequestId;
	int targetAmount;
	String reqType;


	/***********************
	 * THIS METHOD WILL CONSOLE THE TRANSACTION DETAILS
	 *********************/
	public void transaction() throws DBException {
		UsersTransaction transaction = new UsersTransaction();
		Scanner scan = new Scanner(System.in);
		Logger.info("================TRANSACTION DETAILS TO DONATE======================");
		Logger.info("Enter the transaction ID");
		transactionId = scan.nextInt();
		Logger.info("Enter the donor ID");
		donorId = scan.nextInt();
		Logger.info("Enter the fund Id");
		fundRequestId = scan.nextInt();
		Logger.info("Enter the amount to be funded");
		targetAmount = scan.nextInt();
		transaction.setTransactionId(transactionId);
		transaction.setDonorId(donorId);
		transaction.setFundRequestId(fundRequestId);
		transaction.setTargetAmount(targetAmount);
		insert(transaction);
		donorFundRequest(reqType);
	}

	/**********************************
	 * THIS METHOD WILL DO THE INSERT OPERATION TO DO TRANSACTION MADE BY DONORS
	 ***********************/
	public void insert(UsersTransaction transaction) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into transaction_details (id,donor_id,fund_id,amount_funded) values ( ?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(3, transaction.getFundRequestId());
			pst.setInt(2, transaction.getDonorId());
			pst.setInt(1, transaction.getTransactionId());
			pst.setDouble(4, transaction.getTargetAmount());
			int rows = pst.executeUpdate();
			Logger.info("No of rows inserted :" + rows);
		} catch (SQLException e) {
			throw new DBException("unable to insert rows");
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

	/*********************************
	 * THIS METHOD WILL DO THE SELECT OPERATION BASED ON WHAT THE USERS WILL DONATE
	 ****************/
	public List<FundRequest> donorFundRequest(String reqType) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<FundRequest> list = null;
		try {
			//list = new ArrayList<FundRequest>();
			con = ConnectionUtil.getConnection();
			String sql = "select request_type,target_amount,"
					+ " ifnull((select sum(amount_funded) from transaction_details where fund_id = fr.id),0) as fund_raised,"
					+ " ifnull(target_amount -(select sum(amount_funded) from transaction_details where fund_id = fr.id),0) as fund_p"
					+ " from fund_request fr where request_type = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, reqType);
			rs = pst.executeQuery();
			list = new ArrayList<FundRequest>();
			while (rs.next()) {
				//request.setReqType(rs.getString("request_type"));
				//request.setAmount(rs.getInt("target_amount"));
				//request.setFundRaised(rs.getDouble("fund_raised"));
				//request.setFundPending(rs.getDouble("fund_p"));
				FundRequest request = toRow(rs);
				list.add(request);
			}
		} catch (SQLException e) {
			throw new DBException("Fund has been transfered to trust successfully!!!", e);
		}
		return list;

	}
	
	public List<FundRequest> donorFundRequest() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<FundRequest> list = null;
		try {
			//list = new ArrayList<FundRequest>();
			con = ConnectionUtil.getConnection();
			String sql = "select request_type,target_amount,"
					+ " ifnull((select sum(amount_funded) from transaction_details where fund_id = fr.id),0) as fund_raised,"
					+ " ifnull(target_amount -(select sum(amount_funded) from transaction_details where fund_id = fr.id),0) as fund_p"
					+ " from fund_request fr ";
			pst = con.prepareStatement(sql);
			//pst.setString(1, reqType);
			rs = pst.executeQuery();
			list = new ArrayList<FundRequest>();
			while (rs.next()) {
				//request.setReqType(rs.getString("request_type"));
				//request.setAmount(rs.getInt("target_amount"));
				//request.setFundRaised(rs.getDouble("fund_raised"));
				//request.setFundPending(rs.getDouble("fund_p"));
				FundRequest request = toRow(rs);
				list.add(request);
			}
		} catch (SQLException e) {
			throw new DBException("Fund has been transfered to trust successfully!!!", e);
		}
		return list;

	}
	private FundRequest toRow(ResultSet rs) throws DBException {
		FundRequest request = null;
		try {
			String reqType = rs.getString("request_type");
			Integer amount = rs.getInt("target_amount");
			Double fundRaised = rs.getDouble("fund_raised");
			Double fundPending = rs.getDouble("fund_p");
			request = new FundRequest();
			request.setReqType(reqType);
			request.setAmount(amount);
			request.setFundRaised(fundRaised);
			request.setFundPending(fundPending);

		} catch (SQLException e) {
			throw new DBException("rows not been generated", e);
		}
		return request;
	}
}