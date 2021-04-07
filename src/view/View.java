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
			System.out.println("1. Cargar la fuente de datos en una tabla de hash con: Linear probing y separate chaining");
			System.out.println("2. Cargar n videos dado una categoria y un pais: Linear probing ");
			System.out.println("3. Cargar n videos dado una categoria y un pais: separate chaining");
			System.out.println("4. Prueba de desemepeño get(): Linear probing y separate chaining");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
