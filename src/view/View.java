package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar la fuente de datos en un lista encadenada");
			System.out.println("2. Cargar la fuente de datos en arreglo dinamico");
			System.out.println("3. Crear nueva sublista de videos");
			System.out.println("4. Ordenar muestra videos");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
