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
		if (value != null && key != null)
		{
			System.out.println("hay un caso " + size);
			boolean existe = contains(key);
			if(existe == false)
			{
				NodoTS<K,V> nodoAgregar = (NodoTS<K, V>) new NodoTS<>(key, value);
				
				if(elements.isEmpty())
				{
					elements.addFirst(nodoAgregar);
					size++;
					;
				}
				else if(elements.getElement(2) == null)
				{	
					
					if(elements.firstElement().compareTo(nodoAgregar) <0)
					{
						elements.addLast(nodoAgregar);
					}
					else
					{
						elements.addFirst(nodoAgregar);	
					}
					
					size++;
					
				}
				else
				{			
					for(int i=2; i< elements.size()+1; i++)
					{
						NodoTS<K,V> nodo = elements.getElement(i-1);
						NodoTS<K,V> nodo2 = elements.getElement(i);
	
						if(nodo.compareTo(nodoAgregar) < 0 && nodoAgregar.compareTo(nodo2) <= 0 )
						{
							System.out.println("Entro");
							elements.insertElement(nodoAgregar, i);
							size ++;
						}
					}
				}
				
			}
			else 
			{
				NodoTS<K,V> nodo = binarySearch(key);
				nodo.updateValue(value);
			}	
		}
		
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
		
		for (int i = 1; i < size && !contain; i++)
		{
			NodoTS<K,V> nodo = elements.getElement(i);
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
			keys.addFirst(keyTemp);
		}
		return keys;
	}

	@Override
	public IListaTad<V> valueSet() 
	{
		IListaTad<V> values = new ArregloDinamico<>(size);

		for (int i =0; i<size; i++)
		{
			
		}
	
		return null;
	}

	private NodoTS<K,V> binarySearch( K key)
	{
		int origin = 1;
		int end = elements.size() - 1;
		boolean find = false;
		NodoTS<K,V> nodo = null;
		NodoTS<K,V> temp = null;
		NodoTS<K,V> nodoSearch = new NodoTS<>(key, null);
		
		
		while (origin <= end && !find  && !elements.isEmpty())
		{
			int middle = (origin + end)/2;
			temp  = elements.getElement(middle);
			
			if(temp.compareTo(nodoSearch) < 0)
			{
				end = middle-1;
				
			}
			else if(temp.compareTo(nodoSearch) > 0)
			{
				origin = middle+1;	
			}
			else 
			{
				nodo = temp;
			}
		}
		
		return nodo;
	}
	
	public int size()
	{
		return size;
	}
	

}
