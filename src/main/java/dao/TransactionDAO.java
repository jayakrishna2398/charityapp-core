package dao;

import java.util.List;

import exception.DBException;
import model.FundRequest;
import model.UsersTransaction;

public interface TransactionDAO {
	public void insert(UsersTransaction transaction) throws DBException;

	public void transaction() throws DBException;

	public List<FundRequest> donorFundRequest(String reqType) throws DBException;
}
