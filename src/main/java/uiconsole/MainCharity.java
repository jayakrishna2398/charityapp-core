package uiconsole;

import java.util.Scanner;

import dao.AdminDAOImpl;
import dao.FundRequestDAOImpl;
import dao.TransactionDaoImpl;
import dao.UserDAOImpl;
import model.Users;
import util.Logger;

public class MainCharity {
	Scanner scan = new Scanner(System.in);
	UserDAOImpl userdao = new UserDAOImpl();
	AdminDAOImpl admindao = new AdminDAOImpl();
	FundRequestDAOImpl fundreq = new FundRequestDAOImpl();
	TransactionDaoImpl transaction = new TransactionDaoImpl();
	Users users = new Users();
	static String donorName;
	static int donorPassword;
	String reqType;

	/******************
	 * THIS METHOD WILL BE USED TO CONSOLE THE PARTICULAR STATEMENTS
	 *****************/
	public void userAccess() throws Exception {
			Logger.info("===============WELCOME TO REVATURE CHARITY TRUST====================");
			Logger.info("Please select your choice");
			Logger.info("\n 1.create an donor registration \n 2.Admin login \n 3. Exit");
			int choice = 0;
				choice = scan.nextInt();
				Logger.info("invalid input");
			Users u = new Users();
			switch(choice) {
			case 1:
					Logger.info("create new User registration and Login");
					userdao.donorRegistration(u);
					Logger.info("Invalid input");
				userAccess();
				break;
			case 2:
					Logger.info("Admin Login");
					admindao.loginAccess();
					admindao.donorRegister();
					transaction.donorFundRequest(reqType);
					admindao.donorFundRequest(reqType);
					Logger.info("Invalid input");
				userAccess();
				break;
			default:
				Logger.info("=================THANK YOU FOR THE SERVICES AND CONTRIBUTION=============");
				break;
			}
		}
	}

