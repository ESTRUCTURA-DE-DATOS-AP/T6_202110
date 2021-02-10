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
	}
	//-----------------------
	//Atributos 
	//-----------------------
	
	
	
	
	
	
	//--------------------------
	//Métodos
	//--------------------------
	@Override
	public void addFirst(T elemento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(T elemento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertElement(T elemento, int pPosicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T deleteElement(int pPosicion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T firstElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T lastElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getElement(int pPosicion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isElement(T elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void exchange(int pPosicion_1, int pPosicion_2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeInfo(int pPosicion, T elemento) {
		// TODO Auto-generated method stub
		
	}
	
}
