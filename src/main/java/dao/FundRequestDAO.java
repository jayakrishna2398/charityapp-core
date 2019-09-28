package dao;

import exception.DBException;
import model.Admin;
import model.FundRequest;

public interface FundRequestDAO {
	public void insert(FundRequest request, Admin admin) throws DBException;

	public void charityRequest() throws DBException;

	public void updateAmount(FundRequest request, Admin admin) throws DBException;
}
