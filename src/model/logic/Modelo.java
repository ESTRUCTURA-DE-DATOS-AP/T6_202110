package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.HashTableSeparateChaining;
import model.data_structures.IListaTad;
import model.data_structures.ITablaSimbolos;
import model.data_structures.TablaHashLinearProbing;
import util.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */

	private String listaCategorias[];
	private IListaTad llaves;
	private ITablaSimbolos tableSeparateChaining;
	private ITablaSimbolos tableLinearProbing;
	
	private double averageSeparateChaining;
	private double averageLinearProbing;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @param capacidad 
	 */
	public Modelo()
	{
		llaves = new ArregloDinamico<String>(7);
	}
	
	
	public int sizeSeparateChaining()
	{
		return tableSeparateChaining.size();
	}
	
	public int sizeLinearProbing()
	{
		return tableLinearProbing.size();
	}
	
	
	//Carga los datos del archivo .csv
	public void crearListaCategorias()
	{
		Reader in;
		listaCategorias = new String[32];
		try
		{
			in = new FileReader("data/category-id.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			int contador = 0;
			
			for(CSVRecord record:records)
			{
				String vaina = record.get("id	name");
				listaCategorias[contador] = vaina;
				contador++;
			}
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String giveNameFromID(int id)
	{
		String name = "";
		
		for (int i = 0; i < listaCategorias.length; i++)
		{
			String[] temp = listaCategorias[i].split(" ",2);
			String name_list = temp[1];
			int id_list = Integer.parseInt(temp[0]);
			
		}
		return name;
		
	}
	public int giveIdFromName(String name)
	{

		int id=0;
		String a = "";
		boolean category = false;
		for (int i = 0; i < listaCategorias.length && !category; i++)
		{
			String temp = listaCategorias[i].toLowerCase();
			if (temp.contains(name.toLowerCase()))
			{
				a = temp.substring(0, 2).trim();
				id = Integer.parseInt(a);
				category = true;
			}
			
		}
		return id;
		
	
	}
	
	public void crearTablaSeparateChaining()
	{
		long startT, endT;
		double sumTime =0;
	
		tableSeparateChaining = new HashTableSeparateChaining<String, YotubeVideo>(7);
		
		try
		{
			Reader in;
			in = new FileReader("data/videos-all.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			int contador = 0;
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
				String country = record.get("country").toLowerCase();
				String categoryName = "";
				
				YotubeVideo video = new YotubeVideo(video_id, trending_date, tittle, channel_tittle, category_id, publish_time, tags, views, likes, dislikes, comment_count, thumbnail_link, comment_disabled, rating_disabled, video_error_or_removed, description, country, categoryName);
				
				
				//Control de promedio
				contador ++;
				startT=System.currentTimeMillis();
				tableSeparateChaining.put(country+" - "+ category_id , video);
				endT=System.currentTimeMillis();
				sumTime += (double) (endT -startT);
				averageSeparateChaining = sumTime/contador;
				//-----------------------------------------
				llaves.addLast(country+" - "+ category_id);
			}
				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void crearTablaLinearProbing()
	{
		long startT, endT;
		double sumTime =0;
	
		tableLinearProbing = new TablaHashLinearProbing<String, YotubeVideo>(7);
		
		try
		{
			Reader in;
			in = new FileReader("data/videos-all.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			int contador = 0;
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
				String country = record.get("country").toLowerCase();
				String categoryName = "";
				
				YotubeVideo video = new YotubeVideo(video_id, trending_date, tittle, channel_tittle, category_id, publish_time, tags, views, likes, dislikes, comment_count, thumbnail_link, comment_disabled, rating_disabled, video_error_or_removed, description, country, categoryName);
				
				
				//Control de promedio
				contador ++;
				startT=System.currentTimeMillis(); //
				tableLinearProbing.put(country+" - "+ category_id , video);
				endT=System.currentTimeMillis();
				sumTime += (double) (endT -startT);
				//-----------------------------------------
				averageLinearProbing = sumTime/contador;
				llaves.addLast(country+" - "+ category_id);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public String searchKeySeparateChaining(String country, int id)
	{
		String message = "";
		try
		{
			String concatenate = country + " - " + id;
			IListaTad<YotubeVideo> values =  new ArregloDinamico<YotubeVideo>(7);
			values = tableSeparateChaining.get(concatenate);

			for (int i=0; i<values.size();i++)
			{
				message += "\n Title: " + values.getElement(i).darTitulo() +" Views: " + values.getElement(i).darViews() +" Likes: " + values.getElement(i).darLikes() + " dislikes: " + values.getElement(i).darDislikes();                       
			}	
		}
		catch(Exception e)
		{
			message = "Los parametros no son validos ;), hay un error, rectifica tu camino de vida";
			message += "\n posdata: valiste madre we ;)";
		}

		return message;
		
	}
	
	public String searchKeyLinearProbing(String country, int id)
	{
		String message = "";
		try
		{
			String concatenate = country + " - " + id;
			IListaTad<YotubeVideo> values =  new ArregloDinamico<YotubeVideo>(7);
			values = tableLinearProbing.get(concatenate);

			for (int i=0; i<values.size();i++)
			{
				message += "\n Title: " + values.getElement(i).darTitulo() +" Views: " + values.getElement(i).darViews() +" Likes: " + values.getElement(i).darLikes() + " dislikes: " + values.getElement(i).darDislikes();                       
			}	
		}
		catch(Exception e)
		{
			message = "Los parametros no son validos ;), hay un error, rectifica tu camino de vida";
			message += "\n posdata: valiste madre we ;)";
		}

		return message;
		
	}
	
	
	public double pruebaGetSeparateCahning()
	{
		long startT, endT;
		startT=System.currentTimeMillis();
		for(int i=0; i <699;i++)
		{
			int Random = (int)(Math.random()*129);
			String llave = (String) llaves.getElement(Random);
			tableSeparateChaining.get(llave);
		}
		for(int i=0; i <299;i++)
		{
			int Random = (int)(Math.random()*129);
			tableSeparateChaining.get(Random);
		}
		endT=System.currentTimeMillis();
		
		
		return (endT-startT)/1000;
	}
	
	public double pruebaGetLinearProbing()
	{
		long startT, endT;
		startT=System.currentTimeMillis();
		for(int i=0; i <699;i++)
		{
			int Random = (int)(Math.random()*129);
			String llave = (String) llaves.getElement(Random);
			tableLinearProbing.get(llave);
		}
		for(int i=0; i <299;i++)
		{
			int Random = (int)(Math.random()*129);
			tableLinearProbing.get(Random);
		}
		endT=System.currentTimeMillis();
		
		
		return (endT-startT)/1000;
	}
	
	
	
	
	public double getAveragePutSeparateChaining()
	{
		return averageSeparateChaining;
	}
	
	public double getAveragePutLinearProbing()
	{
		return averageLinearProbing;
	}
	
	
	public int videosSeparateChaining()
	{
		return tableSeparateChaining.videoCount();
	}
	
	
	public int videosLinearProbing()
	{
		return tableLinearProbing.videoCount();
	}
	
	
	public String dataSC()
	{
		return tableSeparateChaining.data();
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	


}
