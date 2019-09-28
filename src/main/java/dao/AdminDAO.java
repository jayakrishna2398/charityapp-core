package dao;

import exception.DBException;
import model.Admin;

/**** THIS INTERFACE WILL IMPLEMENT THE CLASS ADMIN OPERATIONS OF DAO ****/
public interface AdminDAO {
	public Admin findByAdmin(int adminId, int adminPassword) throws DBException;

	public void loginAccess() throws Exception;

	public void donorRegister() throws DBException;

	public void donorFundRequest(String reqType) throws DBException;
}
