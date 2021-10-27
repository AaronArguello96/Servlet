package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.App;

/**
 * Servlet implementation class MostrarDatosServ
 */
@WebServlet(name = "MostrarDepartamentosServ", urlPatterns = "/MostrarDatos")
public class MostrarDatosServ extends HttpServlet {
	private static Logger logger = LogManager.getLogger(MostrarDatosServ.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDatosServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String methodName = App.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Comienza la ejecución del servlet de mostrar datos.", methodName));
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter(); 
		String table = request.getParameter("table");
		
		if(table.equals("departamento")){  
			logger.info("Se llama al servlet que muestra los departamentos");
	        RequestDispatcher rd=request.getRequestDispatcher("MostrarDepartamentosServ");  
	        rd.forward(request, response); 
		}
		if(table.equals("empleado")){  
			logger.info("Se llama al servlet que muestra los empleados");
			RequestDispatcher rd=request.getRequestDispatcher("MostrarEmpleadosServ");  
	        rd.forward(request, response); 
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
