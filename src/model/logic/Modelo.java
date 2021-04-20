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
			in = new FileReader("data/videos-all.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			int contador = 0;
			for(CSVRecord record:records)
			{
				String key = record.get("danceability");
				int value = 1;
				
				totReprod+=value;
				
				redblackTree.put(key, value);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	

}
