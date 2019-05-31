import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingPoolExample {
	public static void main(String[] args) throws InterruptedException {
		// ArrayBlockingQueue<Integer> priorityBlockingQueue = new
		// ArrayBlockingQueue<>(5);
		BlockingObjectPool blockingObjectPool = new BlockingObjectPool(5);

		// Producer thread
		new Thread(() -> {
			int i = 0;
			try {
				while (true) {
					Object obj = new Object();
					blockingObjectPool.take(obj);
					System.out.println("Added :" + obj);
					System.out.println("pool size :" + blockingObjectPool.size());
					// System.out.println("Added : " + i);

					Thread.sleep(TimeUnit.SECONDS.toMillis(1));
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}).start();

		// Consumer thread
		new Thread(() -> {
			try {
				while (true) {
					Object poll = blockingObjectPool.get();
					System.out.println("Polled :" + poll);
					System.out.println("pool size :" + blockingObjectPool.size());

					Thread.sleep(TimeUnit.SECONDS.toMillis(2));
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}).start();
	}
}