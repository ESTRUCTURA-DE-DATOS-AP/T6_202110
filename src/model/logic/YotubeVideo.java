package model.logic;

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
	private String likes;
	private int comment_count;
	private String thumbnail_link;
	private boolean comment_disabled;
	private boolean rating_disabled;
	private boolean video_error_or_removed;
	private String description;
	private String country;
	
	//----------------------
	//Constructor
	//----------------------
	
	public YotubeVideo(String id, String date,String titulo, String channel, int categoryId, String publish, String tag, String vistas, String meGustas, int comentarios, String link, boolean deshabilitarCoemntarios, boolean deshabilitarRating, boolean error, String descripcion, String pais)
	{
		video_id  =id;
		trending_date = date;
		tittle = titulo;
		channel_tittle = channel;
		category_id=categoryId;
		publish_time=publish;
		tags=tag;
		views=vistas;
		likes=meGustas;
		comment_count=comentarios;
		thumbnail_link=link;
		comment_disabled=deshabilitarCoemntarios;
		rating_disabled=deshabilitarRating;
		video_error_or_removed=error;
		description=descripcion;
		country=pais;
	}

	//----------------------
	//Metodos
	//----------------------
	
	private int darCategoyId() {
		// TODO Auto-generated method stub
		return category_id;
	}

	@Override
	public int compareTo(YotubeVideo o)
	{
		// TODO Auto-generated method stub
		int comparador = 0;
		if (category_id > o.darCategoyId())
		{
			comparador = 1;
		}
		else if (category_id < o.darCategoyId())
		{
			comparador = -1;
		}
		return comparador;
	}

	
}
