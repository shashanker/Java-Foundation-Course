package com.task.dao;

import java.util.List;

import com.task.model.Currency;
import com.task.model.ExchangeRate;
import com.task.model.UserAccount;
import com.task.samplesData.Data;

public class ExchangeDAO {

	public void exchangeCurrency(UserAccount account, String fromCurrency, String toCurrency) {

	}

	public List<UserAccount> getAccount() {
		return Data.getAccounts();
	}

	public Double getExchangeRates(Currency fromCurrency, Currency toCurrency) {
		// TODO Auto-generated method stub
		List<ExchangeRate> exchangeRates = Data.getExchangeRates();
		Double fromRate = null;
		Double toRate = null;
		for (ExchangeRate exchangeRate : exchangeRates) {
			if (exchangeRate.getCurrency() == fromCurrency) {
				fromRate = exchangeRate.getRate();
			}
			if (exchangeRate.getCurrency() == toCurrency) {
				toRate = exchangeRate.getRate();
			}
		}

		return toRate / fromRate;
	}

}
