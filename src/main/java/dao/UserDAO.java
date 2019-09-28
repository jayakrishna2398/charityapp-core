package dao;

import exception.DBException;

import model.Users;

/***************
 * THIS INTERFACE WILL IMPLEMENTS THE USER DAO CLASS FOR DONOR REGISTRATION AND
 * LOGIN
 **********************/
public interface UserDAO {
	public void loginDetails(Users users) throws Exception;

	public void donorRegistration(Users users) throws Exception;

	public void insert(Users users) throws DBException;

	public Users findByLogin(String donorName, int donorPassword) throws DBException;

}
