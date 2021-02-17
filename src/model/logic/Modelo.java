package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
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
	private IListaTad videos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
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
