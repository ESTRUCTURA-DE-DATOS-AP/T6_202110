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
		
		NodoTS<K,V> nodoAgregar = new NodoTS<>(key, value);
		for (int i = 0; i<elements.size(); i++)
		{
			System.out.println(elements.getElement(i).getKey());
			
		}
		
		//Agregar el primer elemento a la lista
		if (elements.isEmpty())
		{
			elements.addLast(nodoAgregar);
			size++;
		}
		//Cualquier otro caso
		else
		{
			//Condicionador
			boolean posicionador = false;
			//Empezar en la mitad, y tener un nodo de referencia
			int origin = size/2;
			NodoTS<K,V> nodoMitad = elements.getElement(origin);
			
			//Preguntar recorrido
			if(nodoMitad.compareTo(nodoAgregar)==0)
			{
				//Es igual
				nodoMitad.updateValue(value);
			}
			else if(nodoMitad.compareTo(nodoAgregar)>0)
			{
				//Recorrido en contra
				while(origin >0 && !posicionador)
				{
					origin --;
					NodoTS<K,V> nodo = elements.getElement(origin);
					if(nodo.compareTo(nodoAgregar)<0)
					{
						elements.insertElement(nodoAgregar, origin+1);
						posicionador = true;
						size ++;
					}
					else if(nodo.compareTo(nodoAgregar)==0)
					{
						//Si es igual actualiza
						nodo.updateValue(value);
						posicionador = true;
						
					}
				}
				
			}
			else if (nodoMitad.compareTo(nodoAgregar)<0)
			{
				//Recorrido a favor
				while(origin < size && !posicionador)
				{
					
					NodoTS<K,V> nodo = elements.getElement(origin);
					if(nodo.compareTo(nodoAgregar)>0)
					{
						elements.insertElement(nodoAgregar, origin-1);
						posicionador = true;
						size ++;
					}
					else if(nodo.compareTo(nodoAgregar)==0)
					{
						//Si es igual actualiza
						nodo.updateValue(value);
						posicionador = true;
						
					}
					origin ++;
				}
				
				if(posicionador == false )
				{
					NodoTS<K,V> nodo = elements.getElement(origin);
					elements.addLast(nodoAgregar);
					size ++;
					
				}
				
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
		System.out.println("----------------------------------------------------------");
		for (int i = 0; i < size + 1 && !contain; i++)
		{	
			NodoTS<K,V> nodo = elements.getElement(i);
			
			System.out.println(nodo.getKey() + "En la posicion " +i);
			
			if(nodo.getKey().compareTo(key) == 0)
			{
				
				contain = true;
			}
		}
		System.out.println("----------------------------------------------------------");
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
		int end = elements.size();
		boolean find = false;
		NodoTS<K,V> nodo = null;
		NodoTS<K,V> temp = null;
		NodoTS<K,V> nodoSearch = new NodoTS<>(key, null);
		
		
		while (origin < end  && !find  && !elements.isEmpty())
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
			
		}
		return nodo;
	}
	
	public int size()
	{
		return size;
	}
	

}
