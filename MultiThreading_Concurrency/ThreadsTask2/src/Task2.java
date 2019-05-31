import java.util.LinkedList;

public class Task2 {

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
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

		t3 = new Thread(() -> {
			try {
				tasks.thread3();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		// Start all threads
		t1.start();
		t2.start();
		t3.start();

		// t1 finishes before t2
		t1.join();
		t2.join();
		t3.join();
	}

	public static class ThreadTasks {
		LinkedList<Integer> list = new LinkedList<>();

		// Function called by thread1
		public void thread1() throws InterruptedException {
			int value = 0;
			while (true) {
				synchronized (this) {

					System.out.println("List Value added -" + value);

					list.add(value++);

					// notifies the other threads that
					// now it can start
					notify();

					// makes the working of program easier
					// to understand
					Thread.sleep(1000);
				}
			}
		}

		// Function called by thread2
		public void thread2() throws InterruptedException {
			while (true) {
				synchronized (this) {
					while (list.size() == 0)
						wait();

					long sum = 0;
					for (int i : list) {
						sum = sum + i;
					}

					System.out.println("sum -" + sum);

					// Wake up other threads
					notify();

					Thread.sleep(1000);
				}
			}
		}

		// Function called by thread3
		public void thread3() throws InterruptedException {
			while (true) {
				synchronized (this) {
					while (list.size() == 0)
						wait();

					long sumOfSquares = 0;
					for (int i : list) {
						sumOfSquares = sumOfSquares + (i * i);

					}

					System.out.println("Square root of sumOfSquares - " + Math.sqrt(sumOfSquares));

					// Wake up other threads
					notify();

					Thread.sleep(1000);
				}
			}
		}
	}

}
