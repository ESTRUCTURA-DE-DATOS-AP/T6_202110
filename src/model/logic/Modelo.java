package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.IListaTad;
import model.data_structures.ITablaSimbolos;
import model.data_structures.ListaEncadenada;
import model.data_structures.TablaSimbolos;
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
	private String listaCategorias[];
	private IListaTad llaves;
	private ITablaSimbolos table;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @param capacidad 
	 */
	public Modelo()
	{
		llaves = new ArregloDinamico<String>(7);
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
		return table.size();
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
	
	public void crearTabla()
	{
		
		table = new TablaSimbolos<String, YotubeVideo>();
		
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
				table.put(country+" - "+ category_id , video);
				contador ++;
				llaves.addLast(country+" - "+ category_id);
			}
				
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void agregarLista(int tipoEstructura)
	{
		
		
	}
	
	public int subLista(int inicio, int capacidad)
	{
		subVideos = videos.subLista(inicio,capacidad);
		return subVideos.size();
	}

	public String searchKey(String country, int id)
	{
		String message = "";
		try
		{
			String concatenate = country + " - " + id;
			IListaTad<YotubeVideo> values =  new ArregloDinamico<YotubeVideo>(7);
			values = table.get(concatenate);

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
	
	public double pruebaGet()
	{
		long startT, endT;
		startT=System.currentTimeMillis();
		for(int i=0; i <699;i++)
		{
			int Random = (int)(Math.random()*129);
			String llave = (String) llaves.getElement(Random);
			table.get(llave);
		}
		for(int i=0; i <299;i++)
		{
			int Random = (int)(Math.random()*129);
			table.get(Random);
		}
		endT=System.currentTimeMillis();
		
		
		return (endT-startT)/1000;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
