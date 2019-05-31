import java.util.HashMap;
import java.util.Map;

public class Task1WithNoConcurrency {

	public static void main(String[] args) throws Exception {
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
		Map<Integer, Integer> map = new HashMap<>();

		public void thread1() {
			int i = 1;
			while (i < 10) {
				map.put(i, i);
				i++;
				System.out.println(map);
			}
		}

		public void thread2() {
			long sum = 0;
			for (Integer key : map.keySet()) {
				sum = sum + map.get(key);
				System.out.println(sum);
			}
		}
	}
}
