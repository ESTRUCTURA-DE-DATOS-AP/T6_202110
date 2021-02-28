package model.data_structures;

public interface IListaTad <T extends Comparable<T>>
{
	
	public void addFirst(T elemento);
	public void addLast(T elemento);
	public void insertElement(T elemento, int pPosicion);
	public T removeFirst();
	public T removeLast();
	public T deleteElement(int pPosicion);
	public T firstElement();
	public T lastElement();
	public T getElement(int pPosicion);
	public int size();
	public boolean isEmpty();
	public int isElement(T elemento);
	public void exchange(int pPosicion_1,  int pPosicion_2);
	public void changeInfo(int pPosicion, T elemento);
	public IListaTad<T> subLista(int inicio, int numeroElementos);
	public IListaTad<T> subList(int tamaño, int inicio);
	
}
