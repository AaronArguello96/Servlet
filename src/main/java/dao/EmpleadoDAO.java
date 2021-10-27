package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.query.Query;

import empresaHibernate.Departamento;
import empresaHibernate.Empleado;

public class EmpleadoDAO {

	public static void insertarEmpleado(Session s, Empleado empleado) {
		
		s.save(empleado);
	}
	
	public static Empleado getEmpleado(Session s, int id) {
		return s.get(Empleado.class, id);
	}

	public static void updateEmpleado(Session s, Empleado empleado) {
		s.update(empleado);
	}
	
	public static void deleteEmpleado(Session s, Empleado empleado) {
		s.delete(empleado);
	}
	
	//Consulta con criteria
	public static Empleado consultarEmpleado(Session s, int cod_departamento) {
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Empleado> query = builder.createQuery(Empleado.class);
		Root<Empleado> root = query.from(Empleado.class);
    	query.select(root).where(builder.equal(root.get("codDepartamento"), cod_departamento));
    	Query<Empleado> q = s.createQuery(query);
    	List<Empleado> result = (List<Empleado>) q.list();
		for(Empleado e : result) {
			System.out.println(e.toString());
		}
    	return null;
	}
	
	//Consulta con HQL
	public static Empleado empleadosMayores(Session s, int edad) {
		
		  String hQuery = " TIMESTAMPDIFF(YEAR,edad,CURDATE()) AS edad\r\n"
		  		+ "	     FROM empleado";
		  Empleado empleado = s.createQuery(hQuery, Empleado.class)
		                   .setParameter("fechaNacimiento", edad)
		                   .setMaxResults(1)
		                   .uniqueResult();
	    return empleado;
	}
	
	public List<Empleado> getEmpleados(Session miSesion) {
		
		Query<Empleado> miConsulta=miSesion.createQuery("from Empleado", Empleado.class);
		List<Empleado> empleados = miConsulta.getResultList();
		
		return empleados;
	}
}
