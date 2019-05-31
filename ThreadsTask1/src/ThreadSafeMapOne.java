import java.util.HashMap;

/*
 * custom ThreadSafeMap with synchronization
 */
public class ThreadSafeMapOne<K, V> extends HashMap<K, V> {

	final Object mutex;

	public ThreadSafeMapOne() {
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
		synchronized (mutex) {
			return super.put(key, value);
		}

	}

}
