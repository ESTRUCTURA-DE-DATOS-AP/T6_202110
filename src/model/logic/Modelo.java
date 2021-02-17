package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.IListaTad;
import model.data_structures.ListaEncadenada;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private IArregloDinamico datos;
	private IListaTad videos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ArregloDinamico(7);
		videos = new ListaEncadenada<YotubeVideo>(); 
	}
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico(capacidad);
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return (String) datos.buscar(dato);
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return (String) datos.eliminar(dato);
	}
	
	public void agregarLista()
	{
		Reader in;
		try
		{
			in = new FileReader("data/videos-small.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("ID", "CustomerNo", "Name").parse(in);
			
			for(CSVRecord record:records)
			{
				String video_id = record.get("video_id");
				String trending_date = record.get("trending_date");
				String tittle = record.get("tittle");
				String channel_tittle = record.get("channel_tittle");
				int category_id= Integer.parseInt(record.get("category_id"));
				String publish_time= record.get("publish_time");
				String tags= record.get("tags");
				String views= record.get("views");
				String likes= record.get("likes");
				int comment_count= Integer.parseInt(record.get("comment_count"));
				String thumbnail_link= record.get("thumbnail_link");
				boolean comment_disabled = record.get("comment_disabled").toLowerCase().equals("false")? false:true;
				boolean rating_disabled = record.get("rating_disabled").equals("False")? false:true;
				boolean video_error_or_removed = record.get("rating_disabled").equals("False")? false:true;
				String description = record.get("description");
				String country = record.get("country");
				
				YotubeVideo video = new YotubeVideo(video_id, trending_date, tittle, channel_tittle, category_id, publish_time, tags, views, likes, comment_count, thumbnail_link, comment_disabled, rating_disabled, video_error_or_removed, description, country);
				videos.addFirst(video);
				
			}
			
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
