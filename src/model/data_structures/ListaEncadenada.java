package model.data_structures;

public class ListaEncadenada <T extends Comparable<T>> implements IListaTad<T>
{
	private class Nodo<T>
	{
		//Atribute
		private T elemento;
		private Nodo<T> next;
		
		public Nodo(T pElemento)
		{
			elemento = pElemento;
		}
		
		public void setNext(Nodo<T> siguiente)
		{
			next = siguiente;
		}
		
		public void setChangeInfo(T info_elemento)
		{
			elemento = info_elemento;
		}
		
		public Nodo<T> darSiguiente()
		{
			return next;
		}
		
		public T darElemento()
		{
			return elemento;
		}
	}
	//-----------------------
	//Atributos 
	//-----------------------
	
	private Nodo<T> primero;
	private int size;
	
	//---------------------
	//Constructor
	//---------------------
	public ListaEncadenada()
	{
		primero = null;
	}

	
	
	//--------------------------
	//Métodos
	//--------------------------
	@Override
	public void addFirst(T elemento) 
	{
		Nodo<T> nodoAgregar = new Nodo<T>(elemento);
		
		if(primero == null)
		{
			primero =  nodoAgregar;
		}
		else
		{
			nodoAgregar.setNext(primero);
			primero =  nodoAgregar;
		}
		
		size ++;
		
	}

	@Override
	public void addLast(T elemento) 
	{
		Nodo<T> nodoAgregar = new Nodo<T>(elemento);
		Nodo<T> actual = primero;
		int contador = 0;
		if(actual == null)
		{
			primero = nodoAgregar;
		}
		else
		{

			while(actual.darSiguiente() != null)
			{
				actual = actual.darSiguiente();
			}
			actual.setNext(nodoAgregar);
		}
		
			
		
		size ++;
	}
	
	
	@Override
	public void insertElement(T elemento, int pPosicion) 
	{
		Nodo<T> nodoInsertar = new Nodo<T>(elemento);
		Nodo<T> actual = primero;
		Nodo<T> siguiente = actual.darSiguiente();
		int contador = 0;
		while(siguiente != null && pPosicion <= size)
		{
			contador ++;
			if (pPosicion == contador)
			{
				actual.setNext(nodoInsertar);
				nodoInsertar.setNext(nodoInsertar);
			}
			
			
			actual = actual.darSiguiente();
			
		}
	
	}

	@Override
	public T removeFirst() 
	{
		primero.setNext(null);
		T elemento = primero.darElemento();
		primero = primero.darSiguiente() != null? primero.darSiguiente():null;
		return elemento;
	}

	@Override
	public T removeLast()
	{
		Nodo<T> actual = primero;
		T ultimo = lastElement();
		boolean encontro  = false;

		for (int i = 1; i<=size && !encontro; i++)
		{

			if (actual.darSiguiente().darElemento().compareTo(ultimo) == 0 && actual.darSiguiente() != null)
			{
				actual.setNext(null);
				encontro = true;
				ultimo = actual.darSiguiente().darElemento();
			}
			actual = actual.darSiguiente();


		}
		return ultimo;
	}

	@Override
	public T deleteElement(int pPosicion) 
	{
		Nodo<T> actual = primero;
		Nodo<T> siguiente = actual.darSiguiente();
		T elementoEliminar = null;
		int contador = 0;
		while(siguiente != null && pPosicion < size)
		{
			contador ++;
			if (pPosicion == contador)
			{
				actual.setNext(siguiente.darSiguiente());
				siguiente.setNext(null);
				elementoEliminar = siguiente.darElemento();
			}
			actual = actual.darSiguiente();
			siguiente = actual.darSiguiente();
		}		
		return elementoEliminar;
	}

	@Override
	public T firstElement() 
	{
		T elemento = primero != null? primero.darElemento():null;
		return elemento;
	}

	@Override
	public T lastElement() 
	{
		Nodo<T> actual = primero;
		int cont = 0;
		
		while(actual.darSiguiente()!=null && cont<size)
		{
			actual = actual.darSiguiente();
			cont++;
		}
		
		return actual.darElemento();
	}

	@Override
	public T getElement(int pPosicion) 
	{
		Nodo<T> actual = primero;
		T elemento = null;
		int contador = 1;
		boolean salio = false;
		
		//System.out.println(size);
		if( pPosicion <= size)
		{
			while (actual!= null && salio == false)
			{

				if (contador == pPosicion)
				{
					elemento = actual.darElemento();
					salio = true;
				}
				contador ++;
				actual = actual.darSiguiente();
			}
		}
				
	
		return elemento;
	}
	

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		boolean vacio = primero != null? true : false;		
		return vacio;
	}

	@Override
	public int isElement(T elemento) 
	{
		
		return 0;
	}

	@Override
	public void exchange(int pPosicion_1, int pPosicion_2) 
	{
		Nodo<T> actual = primero;
		T elemento_1 = null;
		T elemento_2 = null;
		int contador = 1;
		while(actual != null)
		{
			if(contador == pPosicion_1)
			{
				elemento_1= actual.darElemento();
			}
			
			if(contador == pPosicion_2)
			{
				elemento_2= actual.darElemento();
			}
			//----------------------------------------------------------------
			contador ++;
			actual = actual.darSiguiente();
		}
		
		//Intercambia-----------------------
		changeInfo(pPosicion_2, elemento_1);
		changeInfo(pPosicion_1, elemento_2);
	}

	@Override
	public void changeInfo(int pPosicion, T elemento) 
	{
		Nodo<T> actual = primero;
		
		int contador = 1;
		while (actual!= null)
		{
			
			if (contador == pPosicion)
			{
				actual.setChangeInfo(elemento);
			}
			contador ++;
			actual = actual.darSiguiente();
		}
	}



	@Override
	public IListaTad<T> subLista(int inicio, int numeroElementos) 
	{
		IListaTad<T> listita = new ListaEncadenada<T>();
		Nodo<T> actual = primero;
		int contador = 1;
		while(contador <= numeroElementos && actual!= null)
		{
			if (contador == inicio)
			{
				listita.addFirst(actual.darElemento());
				inicio ++;
			}
			contador ++;
			actual = actual.darSiguiente();
		}
		return listita;
	}



	@Override
	public IListaTad<T> subList(int tamaño, int inicio) 
	{
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
