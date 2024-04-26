package com.bank.bankcredits;

import java.io.IOException;

import com.bank.bankcredits.exceptions.IncorrectUserName;
import com.bank.bankcredits.exceptions.IncorrectUserSecondName;

public class BankCreditsApplication {

	public static void main(String[] args) throws IOException, IncorrectUserName, IncorrectUserSecondName {
		new Executor().start();
	}
}
