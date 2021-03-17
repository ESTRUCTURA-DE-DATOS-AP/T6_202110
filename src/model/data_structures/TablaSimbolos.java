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
		System.out.println("----------------------------------------------");
		for(int i=0; i<elements.size()&& !find;i++)
		{
			NodoTS<K,V> nodo = elements.getElement(i);
			System.out.println(nodo.getKey());
		}
		System.out.println("----------------------------------------------");
		
		
	}

	@Override
	public V get(K key) 
	{
		NodoTS<K,V> nodo = binarySearch(key);
		return nodo.getValue();
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
		int origin = 0;
		int end = elements.size()-1;
		boolean find = false;
		NodoTS<K,V> nodo = null;
		NodoTS<K,V> temp = null;
		NodoTS<K,V> nodoSearch = new NodoTS<>(key, null);
		
		
		while (origin <= end  && !find  && !elements.isEmpty())
		{
			int middle = (origin + end)/2;
			temp  = elements.getElement(middle);
			if(temp.compareTo(nodoSearch) > 0)
			{	
				end = middle-1;

			}
			else if(temp.compareTo(nodoSearch) < 0)
			{
				origin = middle+1;	
			}
			else 
			{
				nodo = temp;
				find = true;
			}
			
			System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''");
			System.out.println("Mitad: "+middle);
			System.out.println("Origen: "+origin);
			System.out.println("Fin: "+end);
			System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''");

			
		}
		return nodo;
	}
	
	public int size()
	{
		return size;
	}
	

}
