
import java.util.concurrent.ConcurrentHashMap;

public class Task1WithConcurrency {
	public static void main(String[] args) {
		Thread t1 = null, t2 = null;
		final ThreadTasks tasks = new ThreadTasks();
		t1 = new Thread(() -> {
			try {
				tasks.thread1();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t2 = new Thread(() -> {
			try {
				tasks.thread2();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		t1.start();
		t2.start();
	}

	public static class ThreadTasks {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

		public void thread1() throws InterruptedException {
			int i = 1;
			while (true) {
				map.put(i, i);
				i++;
				System.out.println(map);
				Thread.sleep(1000);
			}
		}

		public void thread2() throws InterruptedException {

			while (true) {
				long sum = 0;
				for (Integer key : map.keySet()) {
					sum = sum + map.get(key);

				}
				System.out.println(sum);
				Thread.sleep(1000);
			}

		}
	}

}
