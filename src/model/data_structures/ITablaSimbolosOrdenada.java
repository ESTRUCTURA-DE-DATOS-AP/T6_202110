package model.data_structures;

public interface ITablaSimbolosOrdenada <K extends Comparable<K>, V extends Comparable<V>>
{
	public int size();
	public boolean isEmpty();
	public V get(K key);
	public int getHeight(K key);
	public boolean contains(K key);
	public void put(K key, V val);
	public int height();
	public K keyMin();
	public K keyMax();
	public IListaTad<K> keySet();
	public IListaTad<V> valueSet();
	public IListaTad<K> keysInRange(K keyOrigin, K keyEnd);
	public IListaTad<V> valuesInRange(K keyOrigin, K keyEnd);
}
