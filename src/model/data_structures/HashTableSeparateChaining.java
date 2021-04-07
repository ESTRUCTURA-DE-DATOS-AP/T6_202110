package model.data_structures;

public class HashTableSeparateChaining< K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos<K, V>
{
	//Contantes:
	private final static int INITIAL_SIZE = 7;
	private final static int MAAXIMUM_LOADING_FACTOR = 5;
	
	//Atributos:
	private IListaTad<NodoTS<K,V>>[] elements; //Arreglo de estructuras casteados en nodos llave/valor
	private int size; //Duplas
	private int M; // Modulo
	private int loadingFactor;
	private int rehashes;
	
	//Constructores
	
	public HashTableSeparateChaining(int pM)
	{
		M = pM;
		elements =(IListaTad<NodoTS<K,V>>[]) new IListaTad[pM];
		
		for(int i = 0; i<M; i++)
		{
			elements[i] = new ArregloDinamico<NodoTS<K,V>>(pM);
		}
		
	}

	@Override
	public void put(K key, V value) 
	{
		NodoTS<K,V> nodoAgregar = (NodoTS<K, V>) new NodoTS<>(key, value);
		int h = 0;
		boolean find = false;
		
		for(int i =0; i < elements[h].size() && !find; i++)
		{
			NodoTS<K,V> nodoTS = elements[h].getElement(i);
			//Existe: update value
			if(nodoTS.compareTo(nodoAgregar) == 0)
			{
				nodoTS.updateValue(value);
				find = true;
			}
		}
		
		//No existe: Agregar al final && Misma posición: Agregar a la lista de llaves 
		if(!find)
		{
			elements[h].addLast(nodoAgregar);
			size ++;
			updateLoadingFactor();
		}
		
		//Factor de carga: rehash 
		if (loadingFactor > MAAXIMUM_LOADING_FACTOR)
		{
			reHash();
		}
		
	}

	@Override
	public IListaTad<V> get(K key) 
	{
		NodoTS<K,V> nodoTS = null;
		IListaTad<V> values = null;
		int h = hash(key);
		boolean find = false;
		
		for(int i = 0; i < elements[h].size() && !find ; i++)
		{
			nodoTS = elements[h].getElement(i);
			if(nodoTS.getKey().compareTo(key) == 0)
			{
				values = nodoTS.getValues();
				find = true;	
			}
		}
		return values;
	}

	@Override
	public V remove(K key) 
	{
		
		NodoTS<K,V> nodoTS = null;
		V value = null;
		int h = hash(key);
		boolean find = false;
		
		for(int i = 0; i < elements[h].size() && !find ; i++)
		{
			nodoTS = elements[h].getElement(i);
			if(nodoTS.getKey().compareTo(key) == 0)
			{
				value = nodoTS.getValue();
				elements[h].deleteElement(i);
				find = true;	
			}
		}
		
		return value;
	}

	@Override
	public boolean contains(K key) 
	{
		return get(key) != null? true:false;
	}

	@Override
	public IListaTad<K> keySet() 
	{
		IListaTad<K> keys = new ArregloDinamico<>(size);
		for(int i = 0; i< M; i++)
		{
			for(int z = 0; z<elements[i].size(); z++ )
			{
				K keyTemp = elements[i].getElement(z).getKey();
				keys.addLast(keyTemp);
			}
		}
		return keys;
	}

	@Override
	public IListaTad<V> valueSet() 
	{
		IListaTad<V> values = new ArregloDinamico<>(size);
		
		for(int i = 0; i< M; i++)
		{
			for(int z = 0; z<elements[i].size(); z++ )
			{
				int tempValues = elements[i].getElement(z).getValues().size();
				for(int j = 0; j < tempValues; j++ )
				{
					V value = elements[i].getElement(z).getValues().getElement(j);
					values.addLast(value);
				}
			}
		}
		return values;
	}

	@Override
	public int size() 
	{
		return size;
	}
	
	

	@Override
	public int hash(K key) 
	{
		return  (key.hashCode() & 0x7fffffff) % M;
	}

	public void reHash()
	{
		rehashes ++;
		HashTableSeparateChaining<K, V> temp = new HashTableSeparateChaining<K, V>(M*2);
		for(int i=0;i<M; i++)
		{
			for(int z=0; z <elements[i].size(); z++)
			{
				int tempValues = elements[i].getElement(z).getValues().size();
				for(int j = 0; j < tempValues; j++ )
				{
					V value = elements[i].getElement(z).getValues().getElement(j);
					temp.put(elements[i].getElement(z).getKey(), value);
				}
				
			}		
		}
		
		this.M = temp.M;
		this.size = temp.size;
		this.elements = temp.elements;
		updateLoadingFactor();
	}
	
	
	//Metodos auxilares
	public void updateLoadingFactor()
	{
		loadingFactor = size/M;
	}
	
	
	public int module()
	{
		return M;
	}
	
	public int videoCount()
	{
		int sum = 0;
		for(int i = 0; i < M; i++)
		{
			for(int z = 0; z < elements[i].size(); z++)
			{
				sum += elements[i].getElement(z).valuesCount();
			}
		}
		
		return sum;
	}

	@Override
	public String data() {
		// TODO Auto-generated method stub
		return "" + rehashes + " " + loadingFactor;
	}

}
