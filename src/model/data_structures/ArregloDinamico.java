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
public class ArregloDinamico<T extends Comparable<T>>  implements IArregloDinamico<T> 
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
	private Comparable<T> elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[tamanoMax];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			Comparable<T>[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
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

	public T buscar(T dato) 
	{
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.

		T elemento = null;
		boolean encontro = false;
		for (int i=0; i< tamanoAct && elemento == null ; i++)
		{

			if (elementos[i].compareTo(dato)==0)
			{
				
				//elemento = elementos[i].equals(dato)? elementos[i]:null;
	

				elemento = (T) elementos[i];
			}

		}

		return elemento;

	}


	public T eliminar(T dato)
	{
		T elemento = null;
		T temp[] = (T[]) new Comparable[tamanoMax];
		int contador = -1;
		for (int i = 0; i< tamanoAct; i++)
		{
			if (elementos[i].compareTo((T) dato) == 0)	
			{
				//No hace nada
			}
			else
			{
				contador ++;
				temp[contador] = (T) elementos[i];
				
//				if (elementos[i].equals(dato))	
//				{
//					elemento = elementos[i];
//					Collections.addAll(lista, elementos);
//					lista.remove(i);
//					elementos = lista.toArray(new String[lista.size()]);
//					tamanoAct --;
//					encontro = true;
//				} 

			}
		}

		elementos = temp;


		return elemento;
	}


	public void invertir()
	{
		T temp[] = (T[]) new Comparable[tamanoMax];
		int contador = 0;

		for (int i=tamanoAct; i>0; i--)
		{
			temp[contador] = (T) elementos[i];
			contador ++;
		}


		elementos = temp;


	}



	public int compareTo(T o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}







}
