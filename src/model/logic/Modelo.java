package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.IListaTad;
import model.data_structures.ListaEncadenada;
import util.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private IListaTad videos;
	private IListaTad subVideos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @param capacidad 
	 */
	public Modelo()
	{
			
	}
	
	public String darPrimero()
	{
		return videos.firstElement().toString();
	}
	
	public String darUltimo()
	{
		return videos.lastElement().toString();
	}
	
	public int size()
	{
		return videos.size();
	}
	
	public void agregarLista(int tipoEstructura)
	{
		
		if (tipoEstructura==1)
		{
			videos = new ListaEncadenada<YotubeVideo>(); 
		}
		else
		{
			videos = new ArregloDinamico<YotubeVideo>(4); 
		}
		
		Reader in;
		try
		{
			in = new FileReader("data/videos-small.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			
			for(CSVRecord record:records)
			{
				String video_id = record.get("video_id"); 
				String trending_date = record.get("trending_date");
				String tittle = record.get("title");
				String channel_tittle = record.get("channel_title");
				int category_id= Integer.parseInt(record.get("category_id"));
				String publish_time= record.get("publish_time");
				String tags= record.get("tags");
				String views= record.get("views");
				String likes= record.get("likes");
				String dislikes= record.get("dislikes");
				int comment_count= Integer.parseInt(record.get("comment_count"));
				String thumbnail_link= record.get("thumbnail_link");
				boolean comment_disabled = record.get("comments_disabled").toLowerCase().equals("false")? false:true;
				boolean rating_disabled = record.get("ratings_disabled").equals("False")? false:true;
				boolean video_error_or_removed = record.get("video_error_or_removed").equals("False")? false:true;
				String description = record.get("description");
				String country = record.get("country");
				
				YotubeVideo video = new YotubeVideo(video_id, trending_date, tittle, channel_tittle, category_id, publish_time, tags, views, likes, dislikes, comment_count, thumbnail_link, comment_disabled, rating_disabled, video_error_or_removed, description, country);
				
				if (tipoEstructura==1)
				{
					videos.addFirst(video);
				}
				else
				{
					videos.addLast(video);
				}
				
			}
			
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int subLista(int inicio, int capacidad)
	{
		subVideos = videos.subLista(inicio,capacidad);
		return subVideos.size();
	}

	public void ordenarSubLista(int tipoAlgoritmo) 
	{
		int option = tipoAlgoritmo;
		Ordenamiento<YotubeVideo> ordenador = new Ordenamiento<YotubeVideo>();
		Comparator<YotubeVideo> criterio = new YotubeVideo.ComparadorXLikes();
		
		switch (option) 
		{
		case 1:
			ordenador.ordenarInserccion(subVideos, criterio, true);
			System.out.println("Ordenado por inserción");
			break;
		case 2:
			ordenador.ordenarShell(subVideos, criterio, true);
			System.out.println("Ordenado por Shell");
			break;
		case 3:			
			ordenador.ordenarMergeSort(subVideos, criterio, true);
			System.out.println("Ordenado por Merge");
			break;
		case 4:
			ordenador.ordenarQuick(subVideos, criterio, true);
			System.out.println("Ordenado por Quick");
			break;
		default:
			System.out.println("--- Opición invalida ---");
			break;
		}
		
	}

	public String getElementosPrimeros10(int capacidad)
	{
		String mensaje = "";
		for(int i= 1; i<capacidad && i<10; i ++) 
		{
			YotubeVideo video = (YotubeVideo) subVideos.getElement(i);
			mensaje = video.darLikes() + "\n";
		}
		
		return mensaje;
	}

	public String getElementosUltimos10(int capacidad)
	{
		String mensaje = "";
		for(int i= capacidad; i>=10; i --) 
		{
			YotubeVideo video = (YotubeVideo) subVideos.getElement(i);
			mensaje = video.darTitulo() + video.darLikes() + "\n";
		}
		
		return mensaje;
	}

	public String textOrdenamiento() {
		
		int size = subVideos.size();
		String resp = "";
		int contador = 1;
		YotubeVideo temp;
		boolean seguir = true;
		
		if(size<=20)
		{
			while(contador<=20 && seguir)
			{
				temp = (YotubeVideo) subVideos.getElement(contador);
				if(temp!=null)
				{        
					resp += "El video numero "+ contador+" se llama: "+temp.darTitulo()+" y tiene: "+temp.darLikes()+" likes. \n";
					contador++;
				}
				else
					seguir = false;
			}
		}
		else
		{
			while(contador<=10)
			{
				temp = (YotubeVideo) subVideos.getElement(contador);
				resp += "El video numero "+ contador+" se llama: "+temp.darTitulo()+" y tiene: "+temp.darLikes()+" likes. \n";
				contador++;
			}
			contador = size-10;
			
			while(contador<=size)
			{
				temp = (YotubeVideo) subVideos.getElement(contador);
				resp += "El video numero "+ contador+" se llama: "+temp.darTitulo()+" y tiene: "+temp.darLikes()+" likes. \n";
				contador++;
			}
		}
		
		
		return resp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
