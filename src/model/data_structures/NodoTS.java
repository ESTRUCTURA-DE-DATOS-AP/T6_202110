package model.data_structures;

public class NodoTS <K extends Comparable<K>,  V extends Comparable<V> > implements  Comparable<NodoTS<K,V>>
{
	private K key;
	private V value;

	
	public NodoTS(K pKey, V pValue)
	{
		key = pKey;
		value = pValue;
	}
	
	
	public void updateValue(V pValue)
	{
		value = pValue;
	}
	
	
	@Override
	public int compareTo(NodoTS<K, V> other) 
	{
		return this.key.compareTo(other.key);
	}

}
