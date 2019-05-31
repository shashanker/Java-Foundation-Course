import java.util.LinkedList;

public class BlockingObjectPool {

	private int size;

	private LinkedList<Object> pool;

	public BlockingObjectPool(int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		pool = new LinkedList<>();
	}

	public Object get() {

		synchronized (this) {
			if (pool.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Object object = pool.removeFirst();
			notifyAll();
			return object;
		}

	}

	public void take(Object object) {
		synchronized (this) {
			if (pool.size() == this.size) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pool.add(object);
			notifyAll();
		}
	}

	public int size() {
		return pool.size();
	}

}
