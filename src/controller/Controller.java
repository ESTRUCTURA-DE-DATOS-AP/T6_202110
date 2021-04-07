package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
	//mostrarme info de primer y ultimo video
	//tiempo
	//Por arrelo
	//por lista
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		int capacidad = 0;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			long startT, endT;

			switch(option){
				case 1:
					modelo.crearListaCategorias();
					//--------------------------------
					//Separate Chaining
					modelo.crearTablaSeparateChaining();
					view.printMessage("El numero de duplas es: "+modelo.sizeSeparateChaining());
					view.printMessage("El numero de videos es: " + modelo.videosSeparateChaining());
					view.printMessage("Tiempo promedio metodod put(): "+modelo.getAveragePutSeparateChaining());
					view.printMessage("Se cargaron los datos en al tabla de hash con manejo de colisiones separate chaning \n");
					view.printMessage(modelo.dataSC());
					
					//Linear probing
					modelo.crearTablaLinearProbing();
					view.printMessage("El numero de duplas es: "+modelo.sizeLinearProbing());
					view.printMessage("El numero de videos es: " + modelo.videosLinearProbing());
					view.printMessage("Tiempo promedio metodod put(): "+modelo.getAveragePutLinearProbing());
					view.printMessage("Se cargaron los datos en al tabla de hash con manejo de colisiones lienar probing \n");
					break;

				case 2:
					
					view.printMessage("Por favor ingrese la categoria que desea filtrar");
					String name1 = lector.next();
					int id1 = modelo.giveIdFromName(name1);
					view.printMessage("Por favor ingrese el país que desea filtrar");
					String country1 = lector.next().toLowerCase();
					view.printMessage(modelo.searchKeyLinearProbing(country1, id1));
					break;
					
				case 3:
					
					view.printMessage("Por favor ingrese la categoria que desea filtrar");
					String name = lector.next();
					int id = modelo.giveIdFromName(name);
					view.printMessage("Por favor ingrese el país que desea filtrar");
					String country = lector.next().toLowerCase();
					view.printMessage(modelo.searchKeySeparateChaining(country, id));
					break;
					
				case 4:
					view.printMessage("El promedio del metodo get con separate chaining es de:" + modelo.pruebaGetSeparateCahning());
					//view.printMessage("El promedio del metodo get con linear probing es de:" + modelo.pruebaGetLinearProbing());
					break;

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
