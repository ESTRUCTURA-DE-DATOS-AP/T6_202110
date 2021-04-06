package model.data_structures;

public interface ITablaSimbolos< K extends Comparable <K>, V extends Comparable <V>>
{
	public void put(K key, V value);
	public  IListaTad<V>  get(K key);
	public V remove(K key);
	boolean contains(K key);
	public IListaTad<K> keySet(); 
	public IListaTad<V> valueSet(); 
	public int size();
	public int hash(K key);
	
}
