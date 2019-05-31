import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.task.model.Currency;
import com.task.model.UserAccount;
import com.task.service.ExchangeService;

public class Application {

	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		log.info("Application started ..");
		ExchangeService exchangeService = new ExchangeService();
		List<UserAccount> accounts = exchangeService.getAllAccount();

		UserAccount account = accounts.get(0); // taking first account for simulation
		double[] amts = new double[] { 2000, 1000, 4500, 35000, 100 };
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (double amt : amts) {
			executorService.submit(() -> {

				exchangeService.exchange(account, new Double(amt), Currency.INR, Currency.AUD);

			});
		}

		executorService.shutdown();
	}
}
