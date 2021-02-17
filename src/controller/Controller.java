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

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nCargar lista encdenada \nDar capacidad inicial del arreglo: ");
				    int capacidad = lector.nextInt();
				    modelo = new Modelo(capacidad); 
				    view.printMessage("Arreglo Dinamico creado");
				    view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 2:
					view.printMessage("--------- \nCaegar arreglo dinamico ");
					dato = lector.next();
					modelo.agregar(dato);
					view.printMessage("Dato agregado");
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
