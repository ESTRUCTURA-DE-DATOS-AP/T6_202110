package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>, V extends Comparable<V> > implements ITablaSimbolos<K, V>
{
	private IListaTad<NodoTS<K,V>> elements;
	private int size;
	
	
	public TablaSimbolos()
	{
		elements = new ArregloDinamico<NodoTS<K,V>>(7);
		
	}
	
	@Override
	public void put(K key, V value) 
	{
		boolean find=false;
		NodoTS<K,V> nodoAgregar = (NodoTS<K, V>) new NodoTS<>(key, value);
		
		for(int i=0; i<elements.size()&& !find;i++)
		{
			NodoTS<K,V> nodo = elements.getElement(i);
			if(nodo.compareTo(nodoAgregar)==0)
			{
				nodo.updateValue(value);
				find = true;
			}
		}
		if (find == false)
		{
			elements.addLast(nodoAgregar);
			size ++;
		}
	}



	@Override
	public V remove(K key) 
	{
		V value = null; 
		boolean finish = false;
		int position = 0;
		for (int i = 0; i< size && !finish; i++)
		{
			NodoTS<K,V> nodo = elements.getElement(i);
			if(nodo.getKey().compareTo(key) == 0)
			{
				position = i;
				finish = true;
			}
		}
		
		value = finish == true ? elements.deleteElement(position).getValue():null; 
		return value;
	}
	

	@Override
	public boolean contains(K key)
	{
		boolean contain = false;
		for (int i = 0; i < size && !contain; i++)
		{	
			NodoTS<K,V> nodo = elements.getElement(i);
			
			System.out.println(nodo.getKey() + "En la posicion " +i);
			if(nodo.getKey().compareTo(key) == 0)
			{
				contain = true;
			}
		}
		return contain;
	}

	@Override
	public IListaTad<K> keySet() 
	{
		IListaTad<K> keys = new ArregloDinamico<>(size);
		
		for (int i =0; i<size; i++)
		{
			K keyTemp = elements.getElement(i).getKey();
			keys.addLast(keyTemp);
		}
		return keys;
	}

	@Override
	public IListaTad<V> valueSet() 
	{
		IListaTad<V> values = new ArregloDinamico<>(size);

		for (int i =0; i<size; i++)
		{
			V valueTemp = elements.getElement(i).getValue();
			values.addLast(valueTemp);
		}
	
		return values;
	}

	private NodoTS<K,V> binarySearch( K key)
	{
		NodoTS<K,V> nodo = null;
		NodoTS<K,V> nodoDevolver = null;
		boolean contain = false;
		for (int i = 0; i < size && !contain; i++)
		{	
			nodo = elements.getElement(i);
			if(nodo.getKey().compareTo(key) == 0)
			{
				nodoDevolver = nodo;
				contain = true;
			}
		}
		return nodoDevolver;
	}
	
	public int size()
	{
		return size;
	}

	@Override
	public IListaTad<V> get(K key)
	{
		IListaTad<V> values = binarySearch(key).getValues();
		return values;
	}
	

}
