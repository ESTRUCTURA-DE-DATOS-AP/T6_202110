package util;

import java.util.Comparator;

import model.data_structures.IListaTad;
import model.logic.YotubeVideo.ComparadorXLikes;

public final class Ordenamiento <T extends Comparable<T>>
{
	
	//--------------
	//Atributos
	//---------------
	
	private String criterio;
	private boolean ascendente;
	private IListaTad<T> lista;
	
	public Ordenamiento()
	{
		
	}
	
	public void ordenarInserccion(IListaTad<T> lista, Comparator<T> criterio , boolean ascendente )
	{
		
		
		for (int i = 1; i < lista.size(); i ++)
		{
			int posMayorMenor = i;
			for (int j = i+1; j <= lista.size(); j++)
			{
				//----------------------------------------------------
				T elemento_1 = lista.getElement(j);
				T elemento_2 = lista.getElement(posMayorMenor);
				int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(posMayorMenor));
				if (factorComparacion < 0)
				{
					posMayorMenor = j;
				}	
			}		
			lista.exchange(posMayorMenor, i);
		}
	

	}
	
	public void ordenarShell(IListaTad<T> lista, Comparator<T> criterio , boolean ascendente )
	{
		int n = lista.size();
		int h = 1;
		while (h < n/3)
			h = 3 * h + 1;
		
		while (h >=1)
		{
			// generalizacion del alg. Insertion sort con un valor h >= 1
			for (int i = h+1; i <= n; i++)
			{
				boolean enPosicion = false;
				for (int j = i; j > h && !enPosicion; j -= h)
				{
					int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(j-h));
					if (factorComparacion < 0)
						lista.exchange(j, j-h);
					else
						enPosicion = true;
				}
			}
			h /= 3;
		}
	}

	public final void ordenarMergeSort(IListaTad<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		int size = lista.size();
		if(size > 1)
		{
			int mid = size/2;
			//Se divide la lista original en dos partes, izquierda y derecha, desde el punto mid.
			IListaTad<T> leftList = lista.subLista(1, mid);
			IListaTad<T> rightList = lista.subLista(mid+1, size - mid);
			
			//Se hace el llamado recursivo con la lista izquierda y derecha.
			ordenarMergeSort(leftList, criterio, ascendente);
			ordenarMergeSort(rightList, criterio, ascendente);
			
			//i recorre la lista de la izquierda, j la derecha y k la lista original.
			int i,j,k;
			i=j=k= 1;
			
			int leftelements = leftList.size();
			int rightelements = rightList.size();
			
			while(i <= leftelements && j <= rightelements)
			{
				T elemi = leftList.getElement(i);
				T elemj = rightList.getElement(j);
				//Compara y ordena los elementos
				int factorComparacion = (ascendente?1:-1) * criterio.compare(elemi, elemj);
				
				if(factorComparacion <= 0) 
				{
					lista.changeInfo(k, elemi);
					i++;
				}
				else
				{
					lista.changeInfo(k, elemj);
					j++;
				}
				k++;
			}
			
			//Agrega los elementos que no se compararon y están ordenados
			while(i <= leftelements)
			{
				lista.changeInfo(k, leftList.getElement(i));
				i++;
				k++;
			}
			
			while(j <= rightelements)
			{
				lista.changeInfo(k, rightList.getElement(j));
				j++;
				k++;
			}
		}
	}	
	
	public void ordenarQuick(IListaTad<T> lista, Comparator<T> criterio , boolean ascendente )
	{
		sort(lista, criterio, ascendente, 1, lista.size());

	}
	
	private final int partition(IListaTad<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		int follower, leader;
		follower = leader = lo;
		while (leader < hi)
		{
			int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(leader), lista.getElement(hi));
			if(factorComparacion < 0)
			{
				lista.exchange(follower, leader);
				follower ++;
			}
			leader ++;
		}
		lista.exchange(follower, hi);
		return follower;
	}
	
	/**
	 * Se localiza el pivot, utilizando el método de partición.
	 * Luego se hace la recursión con los elementos a la izquierda del pivot
	 * y los elementos a la derecha del pivot.
	 */
	private final void sort(IListaTad<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		if(lo >= hi)
			return;
		int pivot = partition(lista, criterio, ascendente, lo, hi);
		sort(lista, criterio, ascendente, lo, pivot - 1);
		sort(lista, criterio, ascendente, pivot +1, hi);
	}


	
	
}
