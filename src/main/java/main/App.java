package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import menus.LibreriaMenu;
import menus.MenuApp;

public class App {
	
	private static Logger logger = LogManager.getLogger(LibreriaMenu.class);
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Cargamos el menu
        MenuApp.cargarMenus();
        Scanner entrada = new Scanner(System.in);
        int opcion;
        String respuesta;
		
		try {
	        do {
	            //MOSTRAMOS LAS OPCIONES AL USUARIO
	            LibreriaMenu.mostrarOpciones(MenuApp.menuInicial);
	            //RECOGEMOS LA OPCION QUE HAYA ESCOGIDO DE LA LISTA
	            opcion = LibreriaMenu.pedirOpcion(MenuApp.menuInicial);
	            //PASAMOS LOS DATOS A LA FUNCION QUE VA A CONTROLAR LA OPCION ELEGIDA
	            LibreriaMenu.controlarMenu(opcion);
	            //CONTROLAMOS QUE EL USUARIO QUIERA O NO QUIERA REALIZAR MAS ACCIONES
	            System.out.println("Menú Principal. Desea realizar mas acciones? Si(s) Volver al menu principal, Salir (Cualquier tecla");
	            respuesta = entrada.nextLine();
	
	        } while (respuesta.equalsIgnoreCase("s"));
	        
		} catch (SQLException e) {
			
				logger.error(String.format("%1$s: Error al cargar el menu inicial."), e);
        }	
	}
}

/*
String methodName = App.class.getSimpleName() + ".main()";
logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));
//La SessionFactory se instancia 1 vez por ejecución del programa.
//Todas las sesiones de hibernate se obtienen de esa SessionFactory;	
Session session = HibernateUtil.getSessionFactory().openSession();
Transaction tx = null;
try {
	// Las opereaciones save/update/delete
	tx = session.beginTransaction();
	
	//VARIABLES
	int id;
	String nombre;
	int cod_responsable;	
	Scanner valor1i = new Scanner (System.in); 
	Scanner valor2i = new Scanner (System.in); 
	Scanner valor3i = new Scanner (System.in); 
	
	//Pedimos los datos al usuario
	System.out.println("Introduzca el id:");
	id = valor1i.nextInt();			
	System.out.println("Introduzca el nombre:");
	nombre = valor2i.nextLine();
	System.out.println("Introduzca el codigo de responsable:");
	cod_responsable = valor3i.nextInt();
	
	Departamento depar1 = new Departamento(id, nombre, cod_responsable);
	
	//Insertamos un departamento
	DepartamentoDAO.insertarDepartamento(session, depar1);
	
	tx.commit();
		
} catch (Exception e) {
	  if (tx != null) {
	    tx.rollback();
	  }
	  logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
}
finally {
	if (session != null) {
			session.close();
	}
}*/
