/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
import empresaHibernate.Departamento;
import empresaHibernate.Empleado;
import main.App;
import utils.HibernateUtil;

public class LibreriaMenu {
	
	static SessionFactory sessionFactory;
	private static Logger logger = LogManager.getLogger(LibreriaMenu.class);
    // EN CLASES DE ESTE TIPO O BIEN TENEMOS VARIABLES ESTATICAS TENIENDO CLARO LO QUE IMPLICA O LAS TENDREMOS QUE DECLARAR COMO MIEMBROS LOCALES EN LOS METODOS
    //VAMOS A ADAPTAR NUESTRA LIBRER�?A PARA QUE PUEDA RECIBIR UNA LISTA DINAMICA Y SIGA COMPORTANDOSE IGUAL QUE ANTES
    //VAMOS A APROVECHAR ESTA CLASE PARA HACER SOLO METODOS ESTATICOS Y QUE NO HAYA NECESIDAD DE INSTANCIAR ESTA CLASE
    public static void mostrarOpciones(ArrayList<String> listaOpciones) {
        listaOpciones.forEach((opcionMenu) -> {
            System.out.println(opcionMenu);
        });
    }

    //FUNCION QUE NOS PERMITE DE FORMA SEGURA GARANTIZAR QUE SE HA ESCOGIDO UNA DE LAS OPCIONES DE LA LISTA 
    //QUE LE LLEGA
    public static int pedirOpcion(ArrayList<String> listaOpciones) {
        int opcion = 0;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("Introduzca una de las opciones mostradas");
            opcion = entrada.nextInt();
        } while (opcion < 0 || opcion > listaOpciones.size());

        return opcion;
    }

    public static void controlarMenu(int opcion) throws SQLException, IOException {
    	
		String methodName = App.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Comienza la ejecución del main.", methodName));
		//La SessionFactory se instancia 1 vez por ejecución del programa.
		//Todas las sesiones de hibernate se obtienen de esa SessionFactory;	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			// Las opereaciones save/update/delete
			tx = session.beginTransaction();
	        Scanner entrada = new Scanner(System.in);
	        String respuesta;
	
	        switch (opcion) {
	        	
	            case 1:
	            	int opcioninsertar;
	            	Scanner opcioni = new Scanner (System.in); 
	            	System.out.println("Seleccione que quiere insertar");
	            	System.out.println("1. Insertar departamento");
	            	System.out.println("2. Insertar empleado");
	            	opcioninsertar = opcioni.nextInt();	
	            	
	            	if(opcioninsertar == 1) {
	            		
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
		            	DepartamentoDAO departamento = new DepartamentoDAO();
		                departamento.insertarDepartamento(session, depar1);
		                
		                logger.info("Se ha introducido un departamento en la BD.");
		                
		                break;
		                
	            	}else if(opcioninsertar == 2) {
	            		
		    			//VARIABLES
		    			int codigo;
		    			String nombre;
		    			String apellido1;
		    			String apellido2;
		    			String lugar_nacimiento;
		    			String fecha_nacimiento;
		    			String direccion;
		    			String telefono;
		    			String puesto;
		    			int cod_departamento;
		    				
		    			Scanner valor1i = new Scanner (System.in); 
		    			Scanner valor2i = new Scanner (System.in); 
		    			Scanner valor3i = new Scanner (System.in); 
		    			Scanner valor4i = new Scanner (System.in); 
		    			Scanner valor5i = new Scanner (System.in); 
		    			Scanner valor6i = new Scanner (System.in);
		    			Scanner valor7i = new Scanner (System.in); 
		    			Scanner valor8i = new Scanner (System.in); 
		    			Scanner valor9i = new Scanner (System.in);
		    			Scanner valor10i = new Scanner (System.in);
		    			    			
		    			//Pedimos los datos al usuario
		    			System.out.println("Introduzca el código:");
		    			codigo = valor1i.nextInt();			
		    			System.out.println("Introduzca el nombre:");
		    			nombre = valor2i.nextLine();
		    			System.out.println("Introduzca el primer apellido:");
		    			apellido1 = valor3i.nextLine();
		    			System.out.println("Introduzca el segundo apellido:");
		    			apellido2 = valor4i.nextLine();
		    			System.out.println("Introduzca el lugar de nacimiento:");
		    			lugar_nacimiento = valor5i.nextLine();
		    			System.out.println("Introduzca la fecha de nacimiento:");
		    			fecha_nacimiento = valor6i.nextLine();
		    			System.out.println("Introduzca la dirección:");
		    			direccion = valor7i.nextLine();
		    			System.out.println("Introduzca el teléfono:");
		    			telefono = valor8i.nextLine();
		    			System.out.println("Introduzca el puesto:");
		    			puesto = valor9i.nextLine();
		    			System.out.println("Introduzca el código del departamento:");
		    			cod_departamento = valor10i.nextInt();
		    			
		    			Empleado emple1 = new Empleado(codigo, nombre, apellido1, apellido2, 
		    										   lugar_nacimiento, fecha_nacimiento, direccion, 
		    										   telefono, puesto, cod_departamento);
		    			
		            	EmpleadoDAO empleado = new EmpleadoDAO();
		            	
		                empleado.insertarEmpleado(session, emple1);
		                
		                logger.info("Se ha introducido un empleado en la BD.");
		                
		                break;
		                
	            	}else {
	            		logger.warn("La opción introducida para insertar la información no es válida.");
	            		return;
	            	}//IF ELSE
	            	
	            case 2:	
	            	
	            	int opcionborrar;
	            	Scanner opciono = new Scanner (System.in); 
	            	System.out.println("Seleccione que quiere borrar");
	            	System.out.println("1. Borrar departamento");
	            	System.out.println("2. Borrar empleado");
	            	opcionborrar = opciono.nextInt();	
	            	
	            	if(opcionborrar == 1) {
	            		
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
		            	DepartamentoDAO departamento = new DepartamentoDAO();
		                departamento.deleteDepartamento(session, depar1);
		                
		                logger.info("Se ha borrado un departamento en la BD");
		                
	            		break;
	            		
	            	}else if(opcionborrar == 2) {
	            		
		    			//VARIABLES
		    			int codigo;
		    			String nombre;
		    			String apellido1;
		    			String apellido2;
		    			String lugar_nacimiento;
		    			String fecha_nacimiento;
		    			String direccion;
		    			String telefono;
		    			String puesto;
		    			int cod_departamento;
		    				
		    			Scanner valor1i = new Scanner (System.in); 
		    			Scanner valor2i = new Scanner (System.in); 
		    			Scanner valor3i = new Scanner (System.in); 
		    			Scanner valor4i = new Scanner (System.in); 
		    			Scanner valor5i = new Scanner (System.in); 
		    			Scanner valor6i = new Scanner (System.in);
		    			Scanner valor7i = new Scanner (System.in); 
		    			Scanner valor8i = new Scanner (System.in); 
		    			Scanner valor9i = new Scanner (System.in);
		    			Scanner valor10i = new Scanner (System.in);
		    			    			
		    			//Pedimos los datos al usuario
		    			System.out.println("Introduzca el código:");
		    			codigo = valor1i.nextInt();			
		    			System.out.println("Introduzca el nombre:");
		    			nombre = valor2i.nextLine();
		    			System.out.println("Introduzca el primer apellido:");
		    			apellido1 = valor3i.nextLine();
		    			System.out.println("Introduzca el segundo apellido:");
		    			apellido2 = valor4i.nextLine();
		    			System.out.println("Introduzca el lugar de nacimiento:");
		    			lugar_nacimiento = valor5i.nextLine();
		    			System.out.println("Introduzca la fecha de nacimiento:");
		    			fecha_nacimiento = valor6i.nextLine();
		    			System.out.println("Introduzca la dirección:");
		    			direccion = valor7i.nextLine();
		    			System.out.println("Introduzca el teléfono:");
		    			telefono = valor8i.nextLine();
		    			System.out.println("Introduzca el puesto:");
		    			puesto = valor9i.nextLine();
		    			System.out.println("Introduzca el código del departamento:");
		    			cod_departamento = valor10i.nextInt();
		    			
		    			Empleado emple1 = new Empleado(codigo, nombre, apellido1, apellido2, 
		    										   lugar_nacimiento, fecha_nacimiento, direccion, 
		    										   telefono, puesto, cod_departamento);
		    			
		            	EmpleadoDAO empleado = new EmpleadoDAO();
		            	
		                empleado.deleteEmpleado(session, emple1);
		                
		                logger.info("Se ha borrado un empleado en la BD.");
		                
		                break;
	            	}else {
	            		logger.warn("La opción introducida para borrar la información no es válida.");
	            		return;
	            	}//IF ELSE
	            	
	            case 3:
	            	
	            	int opcionactualizar;
	            	Scanner opciona = new Scanner (System.in); 
	            	System.out.println("Seleccione que quiere actualizar");
	            	System.out.println("1. Actualizar departamento");
	            	System.out.println("2. Actualizar empleado");
	            	opcionactualizar = opciona.nextInt();	
	            	
	            	if(opcionactualizar == 1) {
	            		
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
		            	DepartamentoDAO departamento = new DepartamentoDAO();
		                departamento.updateDepartamento(session, depar1);
		                
		                logger.info("Se ha actualizado la información de un departamento en la BD.");
		                
	            		break;
	            		
	            	}else if(opcionactualizar == 2) {
	            		
		    			//VARIABLES
		    			int codigo;
		    			String nombre;
		    			String apellido1;
		    			String apellido2;
		    			String lugar_nacimiento;
		    			String fecha_nacimiento;
		    			String direccion;
		    			String telefono;
		    			String puesto;
		    			int cod_departamento;
		    				
		    			Scanner valor1i = new Scanner (System.in); 
		    			Scanner valor2i = new Scanner (System.in); 
		    			Scanner valor3i = new Scanner (System.in); 
		    			Scanner valor4i = new Scanner (System.in); 
		    			Scanner valor5i = new Scanner (System.in); 
		    			Scanner valor6i = new Scanner (System.in);
		    			Scanner valor7i = new Scanner (System.in); 
		    			Scanner valor8i = new Scanner (System.in); 
		    			Scanner valor9i = new Scanner (System.in);
		    			Scanner valor10i = new Scanner (System.in);
		    			    			
		    			//Pedimos los datos al usuario
		    			System.out.println("Introduzca el código:");
		    			codigo = valor1i.nextInt();			
		    			System.out.println("Introduzca el nombre:");
		    			nombre = valor2i.nextLine();
		    			System.out.println("Introduzca el primer apellido:");
		    			apellido1 = valor3i.nextLine();
		    			System.out.println("Introduzca el segundo apellido:");
		    			apellido2 = valor4i.nextLine();
		    			System.out.println("Introduzca el lugar de nacimiento:");
		    			lugar_nacimiento = valor5i.nextLine();
		    			System.out.println("Introduzca la fecha de nacimiento:");
		    			fecha_nacimiento = valor6i.nextLine();
		    			System.out.println("Introduzca la dirección:");
		    			direccion = valor7i.nextLine();
		    			System.out.println("Introduzca el teléfono:");
		    			telefono = valor8i.nextLine();
		    			System.out.println("Introduzca el puesto:");
		    			puesto = valor9i.nextLine();
		    			System.out.println("Introduzca el código del departamento:");
		    			cod_departamento = valor10i.nextInt();
		    			
		    			Empleado emple1 = new Empleado(codigo, nombre, apellido1, apellido2, 
		    										   lugar_nacimiento, fecha_nacimiento, direccion, 
		    										   telefono, puesto, cod_departamento);
		    			
		            	EmpleadoDAO empleado = new EmpleadoDAO();
		            	
		                empleado.updateEmpleado(session, emple1);
		                
		                logger.info("Se ha actualizado la información de un empleado en la BD.");
		                
		                break;
	            		
	            	}else {
	            		logger.warn("La opción introducida para actualizar la información no es válida.");
	            		return;
	            	}
	            	
	            case 4:
	            	
	            	int opcionleer;
	            	Scanner opcionl = new Scanner (System.in); 
	            	System.out.println("Seleccione que quiere actualizar");
	            	System.out.println("1. Leer departamento");
	            	System.out.println("2. Leer empleado");
	            	opcionleer = opcionl.nextInt();	
	            	
	            	if(opcionleer == 1) {
	            		
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
		            	DepartamentoDAO departamento = new DepartamentoDAO();

		                Departamento resultadodepartamento = departamento.getDepartamento(session, id);
		                
		                logger.info("Se ha leído la información de un departamento de la BD.");
		                
		                System.out.println(resultadodepartamento.getCodigo() + " " + 
		                				   resultadodepartamento.getNombre() + " " + 
		                				   resultadodepartamento.getCodResponsable()); 
		                
	            		break;
	            		
	            	}else if(opcionleer == 2) {
	            		
		    			//VARIABLES
		    			int codigo;
		    			String nombre;
		    			String apellido1;
		    			String apellido2;
		    			String lugar_nacimiento;
		    			String fecha_nacimiento;
		    			String direccion;
		    			String telefono;
		    			String puesto;
		    			int cod_departamento;
		    				
		    			Scanner valor1i = new Scanner (System.in); 
		    			Scanner valor2i = new Scanner (System.in); 
		    			Scanner valor3i = new Scanner (System.in); 
		    			Scanner valor4i = new Scanner (System.in); 
		    			Scanner valor5i = new Scanner (System.in); 
		    			Scanner valor6i = new Scanner (System.in);
		    			Scanner valor7i = new Scanner (System.in); 
		    			Scanner valor8i = new Scanner (System.in); 
		    			Scanner valor9i = new Scanner (System.in);
		    			Scanner valor10i = new Scanner (System.in);
		    			    			
		    			//Pedimos los datos al usuario
		    			System.out.println("Introduzca el código:");
		    			codigo = valor1i.nextInt();			
		    			System.out.println("Introduzca el nombre:");
		    			nombre = valor2i.nextLine();
		    			System.out.println("Introduzca el primer apellido:");
		    			apellido1 = valor3i.nextLine();
		    			System.out.println("Introduzca el segundo apellido:");
		    			apellido2 = valor4i.nextLine();
		    			System.out.println("Introduzca el lugar de nacimiento:");
		    			lugar_nacimiento = valor5i.nextLine();
		    			System.out.println("Introduzca la fecha de nacimiento:");
		    			fecha_nacimiento = valor6i.nextLine();
		    			System.out.println("Introduzca la dirección:");
		    			direccion = valor7i.nextLine();
		    			System.out.println("Introduzca el teléfono:");
		    			telefono = valor8i.nextLine();
		    			System.out.println("Introduzca el puesto:");
		    			puesto = valor9i.nextLine();
		    			System.out.println("Introduzca el código del departamento:");
		    			cod_departamento = valor10i.nextInt();
		    			
		    			Empleado emple1 = new Empleado(codigo, nombre, apellido1, apellido2, 
		    										   lugar_nacimiento, fecha_nacimiento, direccion, 
		    										   telefono, puesto, cod_departamento);
		    			
		            	EmpleadoDAO empleado = new EmpleadoDAO();
		            	
		                Empleado resultadoempleado = empleado.getEmpleado(session, codigo);
		                
		                logger.info("Se ha leído la información de un empleado de la BD.");
		                
		                System.out.println(resultadoempleado.getCodigo() + " " + 
					                	   resultadoempleado.getNombre() + " " + 
					                	   resultadoempleado.getApellido1() + " " +
					                       resultadoempleado.getApellido2() + " " +
					                	   resultadoempleado.getLugarNacimiento() + " " +
					                	   resultadoempleado.getFechaNacimiento() + " " +
					                	   resultadoempleado.getDireccion() + " " +
					                       resultadoempleado.getTelefono() + " " +
					                	   resultadoempleado.getPuesto()+ " " +
					                	   resultadoempleado.getCodDepartamento()); 
		                break;
		                
	            	}else {
	            		logger.warn("La opción introducida para leer la información no es válida.");
	            		return;
	            	}
	            //Consulta criteria
	            case 5:
	            	int cod_departamento;
	    			Scanner valor_cod_departamento = new Scanner (System.in);	
	    			//Pedimos los datos al usuario
	    			System.out.println("Introduzca el código del departamento:");
	    			cod_departamento = valor_cod_departamento.nextInt();	
	    			
	            	EmpleadoDAO empleado = new EmpleadoDAO();
	            	Empleado resultadoempleado = empleado.consultarEmpleado(session, cod_departamento);
	            	
	            case 6:
	            	int edad;
	    			Scanner mayores_edad = new Scanner (System.in);	
	    			//Pedimos los datos al usuario
	    			System.out.println("Introduzca la edad:");
	    			edad = mayores_edad.nextInt();	
	    			
	            	EmpleadoDAO empleado1 = new EmpleadoDAO();
	            	Empleado resultadoempleado1 = empleado1.empleadosMayores(session, edad);
	            	
	                System.out.println(resultadoempleado1.getCodigo() + " " + 
		                	   resultadoempleado1.getNombre() + " " + 
		                	   resultadoempleado1.getApellido1() + " " +
		                       resultadoempleado1.getApellido2() + " " +
		                	   resultadoempleado1.getLugarNacimiento() + " " +
		                	   resultadoempleado1.getFechaNacimiento() + " " +
		                	   resultadoempleado1.getDireccion() + " " +
		                       resultadoempleado1.getTelefono() + " " +
		                	   resultadoempleado1.getPuesto()+ " " +
		                	   resultadoempleado1.getCodDepartamento());
	        }//SWITCH
	        
	        //Hacemos un commit para guardar los cambios hechos y que funcionen las operaciones
	    	tx.commit();
	        
		}catch (Exception e) {
			  if (tx != null) {
			    tx.rollback();
			  }
			  logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
			  
		}finally {
			if (session != null) {
					session.close();
			}
		} 
		
		//Cerramos la sesion
		session.close();
		
		logger.info(String.format("%1$s: >>>>>> Se acaba la ejecución del main.", methodName));
    }
}
