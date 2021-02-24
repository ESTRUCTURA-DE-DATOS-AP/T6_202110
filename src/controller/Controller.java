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
					view.printMessage("--------- \nSe cargaron los datos como lista encadenada ");
					
					startT=System.currentTimeMillis();
					modelo.agregarLista(1);
					endT=System.currentTimeMillis();
					view.printMessage(" \nPara cargar tomo el tiempo: " + (endT-startT)+ " en milisegundos");
					view.printMessage(" El primer elemento es: "+modelo.darPrimero());
					view.printMessage(" El ultimo elemento es: "+modelo.darUltimo());
					view.printMessage(" El numero de elementos es: "+modelo.size());
					break;

				case 2:
					view.printMessage("--------- \nSe cargaron los datos como arreglo dinamico ");
					startT=System.currentTimeMillis();
					modelo.agregarLista(2);
					endT=System.currentTimeMillis();
					view.printMessage(" Para cargar tomo el tiempo: " + (endT-startT) + " en milisegundos");	
					view.printMessage(" El primer elemento es: "+modelo.darPrimero());
					view.printMessage(" El ultimo elemento es: "+modelo.darUltimo());
					view.printMessage(" El numero de elementos es: "+modelo.size());
					break;
				case 3:
					view.printMessage("--------- \nSe Inserte tamaño de la muestra de videos: ");
					capacidad = lector.nextInt(); 
					view.printMessage(" El numero de elementos es: "+ modelo.subLista(capacidad));
					break;
				
				case 4:
					view.printMessage("--------- \nEscoja algoritmo con el cual quiere ordenar");
					view.printMessage("Insertion Sort: 1");
					view.printMessage("Shell Sort: 2");
					view.printMessage("Merge Sort: 3");
					view.printMessage("Quick Sort: 4");
					int tipoAlgoritmo = lector.nextInt();
					startT=System.currentTimeMillis();
					modelo.ordenarSubLista(tipoAlgoritmo);
					endT=System.currentTimeMillis();
					view.printMessage(modelo.textOrdenamiento());
					view.printMessage(" Para ordenar tomo el tiempo: " + (endT-startT) + " en milisegundos");
				
					break;

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
