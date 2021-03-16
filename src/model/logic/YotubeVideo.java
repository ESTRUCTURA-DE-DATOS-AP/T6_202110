package model.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;

public class YotubeVideo implements Comparable<YotubeVideo>
{
	//----------------------
	//Atributos
	//----------------------

	private String video_id;
	private String trending_date;
	private String tittle;
	private String channel_tittle;
	private int category_id;
	private String publish_time;
	private String tags;
	private String views;
	private int likes;
	private String dislikes;
	private int comment_count;
	private String thumbnail_link;
	private boolean comment_disabled;
	private boolean rating_disabled;
	private boolean video_error_or_removed;
	private String description;
	private String country;
	private String categoryName;
	
	//----------------------
	//Constructor
	//----------------------
	
	public YotubeVideo(String id, String date,String titulo, String channel, int categoryId, String publish, String tag, String vistas, String meGustas, String nomeGustas, int comentarios, String link, boolean deshabilitarCoemntarios, boolean deshabilitarRating, boolean error, String descripcion, String pais, String category)
	{
		video_id  =id;
		trending_date = date;
		tittle = titulo;
		channel_tittle = channel;
		category_id=categoryId;
		publish_time=publish;
		tags=tag;
		views=vistas;
		likes= Integer.parseInt(meGustas);
		dislikes = nomeGustas;
		comment_count=comentarios;
		thumbnail_link=link;
		comment_disabled=deshabilitarCoemntarios;
		rating_disabled=deshabilitarRating;
		video_error_or_removed=error;
		description=descripcion;
		country=pais;
		categoryName = category;
		
	}

	//----------------------
	//Metodos
	//----------------------
	
	public int darCategoyId() 
	{
		// TODO Auto-generated method stub
		return category_id;
	}
	
	public int darLikes()
	{
		return likes;
	}
	
	public LocalDate tiempo()
	{
		String hora = publish_time.substring(0, publish_time.length()-5);
		
		DateTimeFormatter temp = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDate ld = LocalDate.parse(hora, temp);
		return ld;
	}

	
	
	@Override
	public int compareTo(YotubeVideo o)
	{
		LocalDate thisData = this.tiempo();
		LocalDate ese = o.tiempo();
		
		return (thisData.compareTo(ese));
	}
	
	
	public static class ComparadorXLikes implements Comparator<YotubeVideo>
	{

		private int crierioInteger;
		private int criterioFecha;
		public ComparadorXLikes() 
		{
			
		}
		
		@Override
		public int compare(YotubeVideo o1, YotubeVideo o2) 
		{			
			int comparador = o1.darLikes() == o2.darLikes()? 0: o1.darLikes() > o2.darLikes()? 1:-1;
			return comparador;
		}
		
	}
	
	
	public String toString()
	{
		//return "Titulo: "+tittle + ";Likes: "+likes+ ";Dislikes: "+dislikes+ ";CategoriaID: "+category_id;
		return ""+likes+ ":    "+ tittle;
	}

	public String darTitulo()
	{
		return tittle;
	}
	
}
