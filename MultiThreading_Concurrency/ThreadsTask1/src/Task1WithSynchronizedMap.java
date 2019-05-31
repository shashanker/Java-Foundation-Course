import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Task1WithSynchronizedMap {

	public static void main(String[] args) {
		Thread t1 = null, t2 = null;
		final ThreadTasks tasks = new ThreadTasks();
		t1 = new Thread(() -> {
			tasks.thread1();
		});

		t2 = new Thread(() -> {
			tasks.thread2();

		});
		t1.start();
		t2.start();
	}

	public static class ThreadTasks {
		Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

		public void thread1() {
			int i = 1;

			while (true) {
				synchronized (this) {
					map.put(i, i);
					i++;
					System.out.println(map);
					notify();

					// makes the working of program easier
					// to understand
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		public void thread2() {

			while (true) {
				synchronized (this) {
					while (map.size() == 0)
						try {
							wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					long sum = 0;
					for (Integer key : map.keySet()) {
						sum = sum + map.get(key);

					}
					System.out.println("sum :" + sum);

					// Wake up other threads
					notify();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

}
