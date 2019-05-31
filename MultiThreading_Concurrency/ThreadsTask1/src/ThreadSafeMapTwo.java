import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeMapTwo<K, V> extends HashMap<K, V> {

	final Object mutex;

	private static ReentrantLock lock = new ReentrantLock();

	public ThreadSafeMapTwo() {
		// TODO Auto-generated constructor stub
		mutex = this;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		try {
			lock.lock();
			return super.put(key, value);
		} finally {
			lock.unlock();
		}

	}

}
