package model.data_structures;

public class TablaHashLinearProbing < K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos<K, V>
{
	//Contantes:
	private final static int INITIAL_SIZE = 7;
	private final static double MAAXIMUM_LOADING_FACTOR = 0.75;

	//Atributos:
	private IListaTad<NodoTS<K,V>> elements;
	private int size; //Duplas
	private int M; // Modulo
	private double loadingFactor;
	private int rehashes;

	public TablaHashLinearProbing(int pM)
	{
		M=pM;
		elements = new ArregloDinamico<NodoTS<K,V>>(pM);
	}

	@Override
	public void put(K key, V value)
	{
		NodoTS<K,V> nodoAgregar = (NodoTS<K, V>) new NodoTS<>(key, value);
		int h = hash(key);
		boolean find = false;
		
		if(elements.getElement(h) == null)
		{
			elements.insertElement(nodoAgregar, h);
			size ++;
		}
		else
		{
			while(find == false )
			{
				if(elements.getElement(h).getKey().compareTo(key) ==0)
				{
					elements.getElement(h).updateValue(value);
					find = true;
				}
				else if(elements.getElement(h) == null)
				{
					elements.insertElement(nodoAgregar, h);
					size ++;
					find = true;
				}
				
				if(h+1 > M)
				{
					h = 0;
				}
				else
				{
					h++;
				}
				
			}
		}
		updateLoadingFactor();
		
		
		//Factor de carga: rehash 
		if (loadingFactor > MAAXIMUM_LOADING_FACTOR)
		{
			reHash();
		}
	}

	@Override
	public IListaTad<V> get(K key) 
	{
		int h = hash(key);
		int contador =  0;
		IListaTad<V> values =  null;
		boolean find = false;
		while(find == false && contador < M)
		{
			
			if(elements.getElement(h).getKey().compareTo(key) ==0)
			{
				values = elements.getElement(h).getValues();
				find = true;
			}
			
			if(h+1 > M)
			{
				h = 0;
			}
			else
			{
				h++;
			}
			contador ++;
		}
		
		
		return values;
	}

	@Override
	public V remove(K key)
	{
		int h = hash(key);
		int contador =  0;
		V value =  null;
		boolean find = false;
		while(find == false && contador < M)
		{
			
			if(elements.getElement(h).getKey().compareTo(key) ==0)
			{
				value = elements.getElement(h).getValue();
				elements.deleteElement(h);
				find = true;
			}
			
			if(h+1 > M)
			{
				h = 0;
			}
			else
			{
				h++;
			}
			contador ++;
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
		for(int i =0; i < M; i++)
		{
			keys.addLast(elements.getElement(i).getKey());
		}
		return keys;
	}

	@Override
	public IListaTad<V> valueSet() 
	{
		IListaTad<V> values = new ArregloDinamico<>(size);
		for(int i =0; i < M; i++)
		{
			int tempValues = elements.getElement(i).getValues().size();
			
			for(int j = 0; j < tempValues; j++ )
			{
				V value = elements.getElement(i).getValues().getElement(j);
				values.addLast(value);
			}
		}
		return null;
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int hash(K key)
	{
		// TODO Auto-generated method stub
		return  (key.hashCode() & 0x7fffffff) % M;
	}
	
	public void reHash()
	{
		rehashes ++;
		TablaHashLinearProbing<K, V> temp = new TablaHashLinearProbing<K, V>(M*2);
		for(int i = 0;  i < M; i++)
		{
			int sizeTemp = elements.getElement(i).valuesCount();
			for(int z =0; z <sizeTemp; z++)
			{
				V value =  elements.getElement(i).getValues().getElement(z);
				temp.put(elements.getElement(i).getKey(), value);
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
	
	public int videoCount()
	{
		int sum = 0;
		for(int i = 0; i < M; i++)
		{

			sum += elements.getElement(i).valuesCount();

		}

		return sum;
	}

	@Override
	public String data() {
		// TODO Auto-generated method stub
		return "" + rehashes + " " + loadingFactor;
	}

}
