package ufps.edu.co.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.EleccionesDao;
import ufps.edu.co.dao.EstamentoDao;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.Estamento;

/**
 * Servlet implementation class EstamentoServlet
 */
@WebServlet("/EstamentoServlet/*")
public class EstamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EstamentoDao estaDAO;
    private EleccionesDao eleDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstamentoServlet() {
        super();
        estaDAO = new EstamentoDao();
        eleDAO = new EleccionesDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch (action) {
		case "eliminar":
			this.eliminar(request, response);
			break;
		case "mostrar":
			this.showForm(request, response);
			break;
			/*	case "/buscar":
			this.buscar(request, response);
			break;	 */
		default:
			break;
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		switch (action) { 
		case "buscar":
			this.buscar(request, response);
			break; 	
		case "registrar":
			this.registrar(request, response);
			break;
		case "actualizar":
			this.actualizar(request, response);
			break;
		default:
			break;
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Estamento esta = new Estamento();
		esta.setId(id);
		Estamento estaaux = new Estamento();
		request.getSession().setAttribute("estamento", estaaux);
		request.getRequestDispatcher("RegistrarEstamento.jsp").forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		String descripcion = request.getParameter("descripcion");
		Integer eleccion = Integer.parseInt(request.getParameter("eleccion"));
		Eleccion ele = eleDAO.find(eleccion);
		Estamento esta = new Estamento();
		esta.setId(id);
		esta.setDescripcion(descripcion);
		esta.setEleccion(ele);
		estaDAO.update(esta);
		request.getRequestDispatcher("RegistrarEstamento.jsp").forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String descripcion = request.getParameter("descripcion");
		Integer eleccion = Integer.parseInt(request.getParameter("eleccion"));
		Eleccion ele = eleDAO.find(eleccion);
		Estamento esta = new Estamento();
		esta.setDescripcion(descripcion);
		esta.setEleccion(ele);
		estaDAO.insert(esta);
		request.getRequestDispatcher("RegistrarEstamento.jsp").forward(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Estamento esta = new Estamento();
		Estamento estaaux = estaDAO.find(id);
		esta.setId(id);
		estaDAO.delete(estaaux);
		request.getRequestDispatcher("RegistrarEstamento.jsp").forward(request, response);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Estamento esta = new Estamento();
		esta.setId(id);
		Estamento estaaux = estaDAO.find(esta.getId());
		request.getSession().setAttribute("estamento", estaaux);
		request.getRequestDispatcher("RegistrarEstamento.jsp").forward(request, response);
	}


}
