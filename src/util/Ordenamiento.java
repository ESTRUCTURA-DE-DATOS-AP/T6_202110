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
		
	}
	
	public void ordenarQuick(IListaTad<T> lista, Comparator<T> criterio , boolean ascendente )
	{
		
	}
	
	
	
}
