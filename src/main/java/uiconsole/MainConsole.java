package uiconsole;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Logger;

public class MainConsole {

	public static void main(String[] args) throws Exception {
		MainCharity charity = new MainCharity();
			charity.userAccess();
	}
	/**********************
	 * THIS METHOD IS USED TO VALIDATE THE EMAIL ID USING REGEX OPERATION
	 *******************/

	public static void mail(String donorEmailId) {
		String regex = "^(.+)@(.+)$";

		String status = null;
		boolean loop = true;
		Pattern pattern = Pattern.compile(regex);
		Scanner scan = new Scanner(System.in);
		String email = donorEmailId;
		do {
			if (status != null && !status.equals("")) {
				Logger.info("Enter your email:");
				email = scan.next();
			}
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				Logger.info("Email ID is not specified in its correct way...please enter any key to continue");

				status = scan.next();

			} else {
				loop = false;
			}

			pattern = Pattern.compile(regex);
		} while (loop);

	}

	/*****************************
	 * THIS METHOD WILL VALIDATE THE NAME USING REGEX OPERATION
	 *************************/
	public static void name(String donorName) {
		String regex = "^[a-zA-Z]+$";
		String status = null;
		boolean loop = true;
		Pattern pattern = Pattern.compile(regex);
		Scanner scan2 = new Scanner(System.in);
		String name = donorName;
		do {
			if (status != null && !status.equals("")) {
				Logger.info("Enter your Name:");
				name = scan2.next();
			}
			Matcher matcher = pattern.matcher(name);
			if (!matcher.matches()) {
			Logger.info("Name is not specified in its correct way...please enter any key to continue ");

				status = scan2.next();

			} else {
				loop = false;
			}

			pattern = Pattern.compile(regex);
		} while (loop);

	}

}