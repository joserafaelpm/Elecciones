package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.CandidatoDao;
import ufps.edu.co.dao.EleccionesDao;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;

/**
 * Servlet implementation class ControllerCandidato
 */
@WebServlet({ "/Admin", "/Admin/*" })
public class EleccionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EleccionesDao elecciones;
	CandidatoDao candidatos;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EleccionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		elecciones = new EleccionesDao();
		candidatos = new CandidatoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] opt =  request.getRequestURI().split("/");
		String action =  request.getContextPath();
		if (opt.length>3){
		 action = opt[3];
		}		
		try {
			switch (action) {
			case "eliminarEleccion":
				//showNewForm(request, response);
				break;
			case "editarEleccion":
				//showNewForm(request, response);
				break;
			case "insert":
				insertarEleccion(request, response);
				break;
			case "insertCandidato":
				insertarCandidato(request, response);
				break;
			case "eliminarCandidato":
				//eliminarUsuario(request, response);
				break;
			case "editarCandidato":
				//showEditForm(request, response);
				break;
			default:
				Elecciones(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertarCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String documento = request.getParameter("documento");
		Eleccion eleccion = elecciones.find(Integer.parseInt(request.getParameter("eleccion")));
		Integer numero = Integer.parseInt(request.getParameter("numero"));
		
		Candidato c = new Candidato(documento, nombre, apellido, eleccion, numero);
		candidatos.insert(c);
		
		response.sendRedirect("/Elecciones/Admin");
	}

	private void insertarEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		String [] fechaInicioS = request.getParameter("fechainicio").split("-");
		String [] fechaFinS = request.getParameter("fechafin").split("-");
		Timestamp fechainicio = new Timestamp(Integer.parseInt(fechaInicioS[0])-1900,Integer.parseInt(fechaInicioS[1]),Integer.parseInt(fechaInicioS[2]),0,0,0,0);
		Timestamp fechafin = new Timestamp(Integer.parseInt(fechaFinS[0])-1900,Integer.parseInt(fechaFinS[1]),Integer.parseInt(fechaFinS[2]),0,0,0,0);
		String cargo = request.getParameter("cargo");		
		Eleccion eleccion = new Eleccion(nombre,fechainicio,fechafin,cargo);
		elecciones.insert(eleccion);
		response.sendRedirect("/Elecciones/Admin");
	}

	private void Elecciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Eleccion> e = elecciones.list();
		List<Candidato> c = candidatos.list();
		
		request.setAttribute("elecciones", e);
		request.setAttribute("candidatos", c);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Elecciones.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
