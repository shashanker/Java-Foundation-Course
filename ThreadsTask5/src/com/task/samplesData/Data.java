package com.task.samplesData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.model.Currency;
import com.task.model.ExchangeRate;
import com.task.model.UserAccount;

public class Data {

	public static List<UserAccount> getAccounts() {

		List<UserAccount> accounts = new ArrayList<>();
		UserAccount user1 = new UserAccount();
		user1.setId(10001);
		user1.setName("user1");
		Map<Currency, Double> u1Deposits = new HashMap<>();
		u1Deposits.put(Currency.USD, new Double(5200));
		u1Deposits.put(Currency.INR, new Double(16000));
		u1Deposits.put(Currency.AUD, new Double(7000));
		user1.setDeposits(u1Deposits);

		UserAccount user2 = new UserAccount();
		user2.setId(10002);
		user2.setName("user2");
		Map<Currency, Double> u2Deposits = new HashMap<>();
		u2Deposits.put(Currency.CAD, new Double(1400));
		u2Deposits.put(Currency.INR, new Double(50000));
		user2.setDeposits(u2Deposits);

		UserAccount user3 = new UserAccount();
		user3.setId(10003);
		user3.setName("user3");
		Map<Currency, Double> u3Deposits = new HashMap<>();
		u3Deposits.put(Currency.INR, new Double(20000));
		u3Deposits.put(Currency.USD, new Double(4700));
		u3Deposits.put(Currency.AUD, new Double(3600));
		user3.setDeposits(u3Deposits);

		accounts.add(user1);
		accounts.add(user2);
		accounts.add(user3);
		return accounts;
	}

	public static List<ExchangeRate> getExchangeRates() {
		ExchangeRate euroRate = new ExchangeRate();
		euroRate.setId(1);

		euroRate.setCurrency(Currency.EUR);
		euroRate.setRate(0.896807);

		ExchangeRate usRate = new ExchangeRate();
		usRate.setId(2);

		usRate.setCurrency(Currency.USD);
		usRate.setRate(1);

		ExchangeRate ausRate = new ExchangeRate();
		ausRate.setId(3);

		ausRate.setCurrency(Currency.AUD);
		ausRate.setRate(1.44568);

		ExchangeRate cadRate = new ExchangeRate();
		cadRate.setId(4);

		cadRate.setCurrency(Currency.CAD);
		cadRate.setRate(1.35550);

		ExchangeRate inrRate = new ExchangeRate();
		inrRate.setId(5);

		inrRate.setCurrency(Currency.INR);
		inrRate.setRate(69.8013);

		List<ExchangeRate> exchangeRates = new ArrayList<>();
		exchangeRates.add(euroRate);
		exchangeRates.add(usRate);
		exchangeRates.add(ausRate);
		exchangeRates.add(cadRate);
		exchangeRates.add(inrRate);
		return exchangeRates;
	}

}
