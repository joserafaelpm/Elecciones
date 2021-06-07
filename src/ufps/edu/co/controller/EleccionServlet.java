package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.CandidatoDao;
import ufps.edu.co.dao.EleccionesDao;
import ufps.edu.co.dao.VotanteDao;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.Votante;

/**
 * Servlet implementation class ControllerCandidato
 */
@WebServlet({ "/Admin", "/Admin/*" })
public class EleccionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EleccionesDao elecciones;
	CandidatoDao candidatos;
	VotanteDao votantes;
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
		votantes = new VotanteDao();
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
			case "lista":
				ListarUsuario(request, response);
				break;
			case "updateCandidato":
				updateCandidato(request, response);
				break;
			case "eliminarEleccion":
				eliminarEleccion(request, response);
				break;
			case "editarEleccion":
				editarEleccion(request, response);
				break;
			case "insert":
				insertarEleccion(request, response);
				break;
			case "insertCandidato":
				insertarCandidato(request, response);
				break;
			case "eliminarCandidato":
				eliminarCandidato(request, response);
				break;
			case "updateEleccion":
				updateEleccion(request, response);
				break;
			case "editarCandidato":
				editarCandidato(request, response);
				break;
			default:
				Elecciones(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String documento = request.getParameter("documento");
		Integer numero = Integer.parseInt(request.getParameter("numero"));
		Eleccion eleccion = elecciones.find(Integer.parseInt(request.getParameter("eleccion")));
		
		Candidato c = new Candidato(id,documento,nombre,apellido,eleccion,numero);
		candidatos.update(c);
		
		response.sendRedirect("../Admin");
	}

	private void updateEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		Calendar c = Calendar.getInstance();
		String [] fechaInicioS = request.getParameter("fechainicio").split("-");
		c.set(Integer.parseInt(fechaInicioS[0]),Integer.parseInt(fechaInicioS[1])-1, Integer.parseInt(fechaInicioS[2]));
		Timestamp fechainicio = new Timestamp(c.getTimeInMillis());
		String [] fechaFinS = request.getParameter("fechafin").split("-");
		Calendar x = Calendar.getInstance();
		x.set(Integer.parseInt(fechaFinS[0]),Integer.parseInt(fechaFinS[1])-1, Integer.parseInt(fechaFinS[2]));
		String cargo = request.getParameter("cargo");
		Timestamp fechafin = new Timestamp(x.getTimeInMillis());
		Eleccion eleccion = new Eleccion(id,nombre,fechainicio,fechafin,cargo);
		elecciones.update(eleccion);
		response.sendRedirect("../Admin");
	}

	private void editarEleccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Eleccion eleccion = elecciones.find(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("eleccion", eleccion);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editEleccion.jsp");
		dispatcher.forward(request, response);
	}

	private void eliminarEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		elecciones.delete(elecciones.find(id));
		
		response.sendRedirect("../Admin");
	}

	private void editarCandidato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Eleccion> e = elecciones.list();
		Candidato candidato = candidatos.find(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("elecciones", e);
		request.setAttribute("candidato", candidato);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editCandidato.jsp");
		dispatcher.forward(request, response);
	}

	private void eliminarCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		candidatos.delete(candidatos.find(id));
		
		response.sendRedirect("../Admin");
	}

	private void ListarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Votante> votantes = this.votantes.list();
		Eleccion eleccion = elecciones.find(Integer.parseInt(request.getParameter("id"))); 
		
		request.setAttribute("eleccion", eleccion);
		request.setAttribute("votantes", votantes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarVotante.jsp");
		dispatcher.forward(request, response);
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

	private void insertarEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		String nombre = request.getParameter("nombre");
		Calendar c = Calendar.getInstance();
		String [] fechaInicioS = request.getParameter("fechainicio").split("-");
		c.set(Integer.parseInt(fechaInicioS[0]),Integer.parseInt(fechaInicioS[1])-1, Integer.parseInt(fechaInicioS[2]));
		Timestamp fechainicio = new Timestamp(c.getTimeInMillis());
		String [] fechaFinS = request.getParameter("fechafin").split("-");
		Calendar x = Calendar.getInstance();
		x.set(Integer.parseInt(fechaFinS[0]),Integer.parseInt(fechaFinS[1])-1, Integer.parseInt(fechaFinS[2]));
		String cargo = request.getParameter("cargo");
		Timestamp fechafin = new Timestamp(x.getTimeInMillis());
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
