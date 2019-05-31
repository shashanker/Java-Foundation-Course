package com.task.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.task.dao.ExchangeDAO;
import com.task.exceptions.InsufficientBalanceException;
import com.task.model.Currency;
import com.task.model.UserAccount;

public class ExchangeService {

	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private UserAccount account;

	/*
	 * public ExchangeService(UserAccount account) { // TODO Auto-generated
	 * constructor stub this.account = account; }
	 */

	public void exchange(UserAccount account, Double amt, Currency fromCurrency, Currency toCurrency) {
		log.info("Exchange process started : ");
		synchronized (this) {

			if (checkBalance(account, amt, fromCurrency)) {
				Double rate = getExchangeRate(fromCurrency, toCurrency);
				// Update the Account Deposits
				updateAccount(account, amt, fromCurrency, toCurrency, rate);

			} else {
				try {
					throw new InsufficientBalanceException("Not enough balance to do exchange");
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					// System.out.println("Error :"+e.getMessage());
					log.log(Level.SEVERE, "Error :" + e.getMessage());
				}
			}
		}

		log.info("Exchange process ended : ");
	}

	private UserAccount updateAccount(UserAccount account, Double amt, Currency fromCurrency, Currency toCurrency,
			Double rate) {
		log.log(Level.INFO, "updation of account started :");

		log.info("Deposits before :" + account.getDeposits());
		Double accBalance = account.getDeposits().get(fromCurrency);

		Map<Currency, Double> deposits = account.getDeposits();
		deposits.put(fromCurrency, accBalance - amt);
		Double toAmt = deposits.get(toCurrency);
		if (toAmt == null) {

			toAmt = new Double(0);
		}

		deposits.put(toCurrency, toAmt + (amt * rate));

		account.setDeposits(deposits);
		log.info("Deposits after :" + account.getDeposits());
		log.log(Level.INFO, "updation of account ended :");
		writeTofile(account);
		return account;

	}

	private Double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
		// TODO Auto-generated method stub
		log.info("Getting the Exchange Rate :");
		ExchangeDAO dao = new ExchangeDAO();
		return dao.getExchangeRates(fromCurrency, toCurrency);
	}

	private boolean checkBalance(UserAccount account, Double amt, Currency fromCurrency) {
		// TODO Auto-generated method stub
		log.info("Checking available balance :");
		Double accBalance = account.getDeposits().get(fromCurrency);
		if (accBalance == null || accBalance.doubleValue() < amt.doubleValue()) {
			return false;
		}
		return true;
	}

	public static List<UserAccount> getAllAccount() {
		ExchangeDAO dao = new ExchangeDAO();
		return dao.getAccount();
	}

	public void writeTofile(UserAccount account) {
		log.info("writing account to the file :" + account.getName() + "_" + account.getId() + ".txt");
		File f = new File(account.getName() + "_" + account.getId() + ".txt");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
			for (Entry<Currency, Double> d : account.getDeposits().entrySet()) {
				String s = account.getName() + "," + d.getValue() + "," + d.getKey() + "\n";
				bw.write(s);
			}
			bw.flush();
			log.info("finished writing account to the file :" + account.getName() + "_" + account.getId() + ".txt");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
