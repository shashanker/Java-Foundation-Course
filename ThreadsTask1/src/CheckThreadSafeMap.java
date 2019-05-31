
public class CheckThreadSafeMap {

	public static void main(String[] args) {
		ThreadSafeMapOne<Integer, Integer> mapOne = new ThreadSafeMapOne<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			mapOne.put(i, i);
		}
		long end = System.currentTimeMillis();
		System.out.println("MapOne : " + mapOne);
		System.out.println("Time taken for MapOne " + (end - start) + "ms");

		start = System.currentTimeMillis();
		ThreadSafeMapTwo<Integer, Integer> mapTwo = new ThreadSafeMapTwo<>();
		for (int i = 0; i < 1000; i++) {
			mapTwo.put(i, i);
		}
		end = System.currentTimeMillis();
		System.out.println("MapTwo :" + mapTwo);
		System.out.println("Time taken for MapTwo " + (end - start) + "ms");

	}
}
