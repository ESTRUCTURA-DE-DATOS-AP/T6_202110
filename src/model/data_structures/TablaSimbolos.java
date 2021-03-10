package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>, V extends Comparable<V> > implements ITablaSimbolos<K, V>
{
	private IListaTad<NodoTS<K,V>> elementos;
	
	public TablaSimbolos()
	{
		elementos = new ArregloDinamico<NodoTS<K,V>>(7);
		
	}
	
	@Override
	public void put(K key, V value) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K key) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IListaTad<K> keySet() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IListaTad<V> valueSet() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
