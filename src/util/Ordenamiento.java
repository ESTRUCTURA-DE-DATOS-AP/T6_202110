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

	public void ordenarMerge(IListaTad<T> lista, Comparator<T> criterio , boolean ascendente )
	{
			int size = lista.size();
			if(size > 1)
			{
				int mid = size/2;
				//Se divide la lista original en dos partes, izquierda y derecha, desde el punto mid.			ILista<T> leftList = lista.subList(1, mid);
				
				
				IListaTad<T> leftList = lista.subLista(1, mid);
//				System.out.println("---------------------------------------------------");
//				System.out.println("Izquierda" + "Tamaño: " + leftList.size());
//				for(int i= 1; i<=leftList.size(); i++)
//				{
//					System.out.println(leftList.getElement(i).toString());
//				}
//				System.out.println("---------------------------------------------------");
//				System.out.println("Separador");
				
				IListaTad<T> rightList = lista.subLista(mid+1, size - mid);
//				System.out.println("---------------------------------------------------");
//				System.out.println("Derecha" + "Tamaño: " + rightList.size());
//				for(int i= 1; i<=rightList.size(); i++)
//				{
//					System.out.println(rightList.getElement(i).toString());
//				}
//				System.out.println("---------------------------------------------------");
//				
				
				
				//Se hace el llamado recursivo con la lista izquierda y derecha.
				ordenarMerge(leftList, criterio, ascendente);
				ordenarMerge(rightList, criterio, ascendente);
				
				//i recorre la lista de la izquierda, j la derecha y k la lista original.
				int i,d,k;
				i=d=k= 1;
				
				int leftelements = leftList.size();
				int rightelements = rightList.size();
				
				while(i <= leftelements && d <= rightelements)
				{
					T elemi = leftList.getElement(i);
					T elemd = rightList.getElement(d);
					//Compara y ordena los elementos
					int factorComparacion = (ascendente?1:-1) * criterio.compare(elemi, elemd);
					
					if(factorComparacion <= 0) 
					{
						lista.changeInfo(k, elemi);
						i++;
					}
					else
					{
						lista.changeInfo(k, elemd);
						d++;
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
				
				while(d <= rightelements)
				{
					lista.changeInfo(k, rightList.getElement(d));
					d++;
					k++;
				}
				
				
//				System.out.println("Lista en ordenamiento  ************************");
//				for (int l=1; l<=lista.size();l++)
//				{
//					System.out.println(lista.getElement(i));
//				}
//				System.out.println("********************************************");
//				
			}
			
		}

	
	
	public void ordenarQuick(IListaTad<T> lista, Comparator<T> criterio , boolean ascendente )
	{
		
	}
	
	
	
}
