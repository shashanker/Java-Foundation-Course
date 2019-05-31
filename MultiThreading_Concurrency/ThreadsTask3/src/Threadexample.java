
// Java program to implement solution of producer 
// consumer problem. 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Threadexample {
	public static void main(String[] args) throws InterruptedException {
		// Object of a class that has both produce()
		// and consume() methods
		final PC pc = new PC();

		// Create producer thread
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pc.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Create consumer thread
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Start both threads
		t1.start();
		t2.start();

		// t1 finishes before t2
		t1.join();
		t2.join();
	}

	// This class has a list, producer (adds items to list
	// and consumber (removes items).
	public static class PC {
		// Create a list shared by producer and consumer
		// Size of list is 2.
		LinkedList<String> list = new LinkedList<>();
		// int capacity = 2;
		boolean pDone = false;

		// Function called by producer thread
		public void produce() throws InterruptedException, IOException {
			File f = new File("topics.txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = br.readLine()) != null && !(line.equals(""))) {
				synchronized (this) {
					// producer thread waits while list
					// is full
					/*
					 * while (list.size()==capacity) wait();
					 */
					System.out.println("Producer produced-" + line);

					// to insert the jobs in the list
					list.add(line);

					// notifies the consumer thread that
					// now it can start consuming
					notify();

					// makes the working of program easier
					// to understand
					Thread.sleep(1000);
				}
			}
			System.out.println("Producer completed");
			pDone = true;
		}

		// Function called by consumer thread
		public void consume() throws InterruptedException {
			String[] topics = { "Google", "football", "Politics" };
			while (true) {
				synchronized (this) {
					// consumer thread waits while list
					// is empty
					while (list.size() == 0)
						wait();
					boolean found = false;
					// to retrive the ifrst job in the list
					String val = list.removeFirst();
					for (String topic : topics) {
						if (val.contains(topic)) {
							System.out.println("Consumer consumed-" + val);
							// list.remove(line);
							found = true;
						}
					}

					found = false;

					// Wake up producer thread
					notify();

					// and sleep
					Thread.sleep(1000);
				}

				if (pDone && list.size() == 0) {
					break;
				}
			}
			System.out.println("Consumer completed..");
		}
	}
}
