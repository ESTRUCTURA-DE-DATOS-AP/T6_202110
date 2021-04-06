package model.data_structures;

public class NodoTS <K extends Comparable<K>,  V extends Comparable<V> > implements  Comparable<NodoTS<K,V>>
{
	private K key;
	private V value;
	private IListaTad<V> values;
	private int valuesCount;

	
	public NodoTS(K pKey, V pValue)
	{
		key = pKey;
		value = pValue;
		values = new ArregloDinamico<V>(7);
		values.addLast(value);
	}
	
	
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return values.lastElement();
	}
	
	public IListaTad<V> getValues()
	{
		return values;
	}
	
	public void updateValue(V pValue)
	{
		values.addLast(pValue);
	}
	
	
	
	
	@Override
	public int compareTo(NodoTS<K, V> other) 
	{
		return this.key.compareTo(other.key);
	}
	
	
	
	
	
	
	
	
	

}
