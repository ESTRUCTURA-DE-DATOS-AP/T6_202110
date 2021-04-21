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
import model.data_structures.ITablaSimbolosOrdenada;
import model.data_structures.RBT;
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

	private ITablaSimbolosOrdenada redblackTree;
	private double totReprod;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @param capacidad 
	 */
	public Modelo()
	{
		llaves = new ArregloDinamico<String>(7);
	}
	
	public void crearRBT()
	{
		long startT, endT;
		double sumTime =0;
		
		redblackTree = new RBT<>();
		
		try
		{
			Reader in;
			in = new FileReader("context_content_features-small.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			int contador = 0;
			for(CSVRecord record:records)
			{
				double instrumentalness = Double.parseDouble(record.get("instrumentalness"));
				double liveness = Double.parseDouble(record.get("liveness"));
				double speechiness = Double.parseDouble(record.get("speechiness"));
				double danceability = Double.parseDouble(record.get("danceability"));
				double valence = Double.parseDouble(record.get("valence"));
				double loudness = Double.parseDouble(record.get("loudness"));
				double tempo = Double.parseDouble(record.get("tempo"));
				double acousticness = Double.parseDouble(record.get("acousticness"));
				double energy = Double.parseDouble(record.get("energy"));
				double mode = Double.parseDouble(record.get("mode"));
				double key = Double.parseDouble(record.get("key"));
				String artist_id = record.get("artist_id");
				String tweet_lang = record.get("tweet_lang");
				String track_id = record.get("track_id");
				String created_at = record.get("created_at");
				String lang = record.get("lang");
				String time_zone = record.get("time_zone");
				String user_id = record.get("user_id");
				String id =record.get("id");
				
				Reproduccion rep = new Reproduccion(instrumentalness,liveness,speechiness,danceability,
						valence,loudness,tempo,acousticness,energy,mode,key,artist_id,tweet_lang,track_id,
						created_at,lang,time_zone,user_id,id);
				
				redblackTree.put(danceability, (Comparable) rep);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public String data()
	{
		String message ="";
		
		message +="El número total de reproducciones "+ redblackTree.valueSet().size();
		message +="\n"+" El número de llaves presentes en el árbol RBT "+ redblackTree.size();
		message +="\n"+ "La altura del árbol RBT "+ redblackTree.height();
		message += "\n"+"La llave menor "+ redblackTree.keyMin();
		message += "\n"+"La llave mayor "+ redblackTree.keyMax();
		message += "\n" + "El número de hojas en el árbol "+redblackTree.leafs();
		
		return message;
	}
	
	
	
	

}
