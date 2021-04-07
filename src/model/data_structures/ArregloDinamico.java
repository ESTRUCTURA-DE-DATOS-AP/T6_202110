package model.data_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T>>  implements IListaTad<T>
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T darElemento(int i) 
	{
		// TODO implementar
		T elemento = i <= tamanoAct? (T) elementos[i]:null; 
		return elemento;
	}

	public int compareTo(T o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public void addFirst(T elemento) {
		
		if(tamanoAct==tamanoMax)
		{
			tamanoMax = tamanoMax*2;
		}
		
		T temp[] = (T[]) new Comparable[tamanoMax];
		temp[0]=elemento;
		
		for(int i=0; i<tamanoAct;i++)
		{
			temp[i+1]=elementos[i];
		}
		elementos=temp;
		tamanoAct++;
	}

	public void addLast(T elemento) {
		
		if(tamanoAct==tamanoMax || tamanoAct==0)
		{
			tamanoMax = tamanoMax*2;
			elementos = java.util.Arrays.copyOf(elementos, tamanoMax);
		}

		elementos[tamanoAct] = elemento;
		tamanoAct ++;
	}

	public void insertElement(T elemento, int pPosicion) {
		
		if(tamanoAct==tamanoMax || tamanoAct==0)
		{
			tamanoMax = tamanoMax*2;
			elementos = java.util.Arrays.copyOf(elementos, tamanoMax);
		}
		
		if(elementos[pPosicion] == null)
		{
			elementos[pPosicion] = elemento;
		}
		else
		{
			for(int i = tamanoMax-2; i>=pPosicion; i--)
			{
				
				elementos[i+1]= elementos[i];
			}
			elementos[pPosicion] = elemento;
			tamanoAct++;
		}
		
		
	}

	public T removeFirst() {
		
		T temp = elementos[0];
		elementos[0]=null;
		
		if (tamanoAct!=0 &&tamanoAct!=1)
		{	
			actualizarLista(0);
		}
		return temp;
	}

		
	public T removeLast() {
		
		if(tamanoAct>0)
		{
			T temp = elementos[tamanoAct-1];
			elementos[tamanoAct-1] = null;
			tamanoAct--;
			return temp;			
		}
		else
		{
			T temp = elementos[tamanoAct];
			elementos[tamanoAct] = null;
			return temp;
		}
	}

	public T deleteElement(int pPosicion) {
		if(pPosicion<tamanoAct)
		{
			T temp = elementos[pPosicion];
			elementos[pPosicion] = null;
			actualizarLista(pPosicion);
			return temp;
		}
		else
			return null;
	}

	public T firstElement() 
	{
		return elementos[0];
	}

	public T lastElement() {
		
		return tamanoAct>0? elementos[tamanoAct-1]:elementos[tamanoAct];
		
	}

	@Override
	public T getElement(int pPosicion) {
		if (pPosicion < tamanoAct)
		{
			return elementos[pPosicion];
		}
		else
			return null;
	}

	public int size() {
		return tamanoAct;
	}

	public boolean isEmpty() {
		return tamanoAct==0? true: false;
	}

	public int isElement(T elemento) {
		int pos=-1;
		int contador=0;
		
		while(pos==-1 || contador<tamanoAct)
		{
		if (elementos[contador].equals(elemento))
			pos = contador;
		else
			contador++;
		}
		return pos;
	}

	public void exchange(int pPosicion_1, int pPosicion_2) {

		
		if (pPosicion_1<tamanoAct && pPosicion_2<tamanoAct)
		{
			T temp = elementos[pPosicion_1];
			elementos[pPosicion_1]=elementos[pPosicion_2];
			elementos[pPosicion_2] = temp;
		}
	}

	public void changeInfo(int pPosicion, T elemento)
	{
		
		if (pPosicion<tamanoAct)
		{
			elementos[pPosicion]=elemento;
		}
		
	}

	public void actualizarLista(int posInicio)
	{
		if (elementos[posInicio]==null)
		{
			int actual = posInicio;
			while(elementos[actual+1]!=null)
			{
				elementos[actual]=elementos[actual+1];
				actual++;
			}
		}

	}

	@Override
	public IListaTad<T> subLista(int inicio, int numeroElementos) 
	{
		int valor = inicio;
		IListaTad<T> elementitos = (IListaTad<T>) new ArregloDinamico<T>(numeroElementos);
		for(int i = 0; i < numeroElementos; i++)
		{
			elementitos.addLast(elementos[valor + i]);
		}
		return elementitos;
	}

	
	public void addElement(T element, int index)
	{
		elementos[index] =  element;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
