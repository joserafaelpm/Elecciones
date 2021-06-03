package ufps.edu.co.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.EleccionesDao;
import ufps.edu.co.dao.TipoDocumentoDao;
import ufps.edu.co.dao.VotanteDao;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.model.Votante;

/**
 * Servlet implementation class VotanteServlet
 */
@WebServlet({"/RegistrarVotante","/RegistrarVotante/*"})
public class VotanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TipoDocumentoDao tipoDocumentoDao;
	EleccionesDao eleccionesDao; 
	VotanteDao votanteDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotanteServlet() {
        super();
        tipoDocumentoDao = new TipoDocumentoDao();
		eleccionesDao = new EleccionesDao();
		votanteDao = new VotanteDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] opt =  request.getRequestURI().split("/");
		String action =  request.getContextPath();
		if (opt.length>3){
		 action = opt[3];
		}		
		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertarUsuario(request, response);
				break;
			case "delete":
				eliminarUsuario(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				editarUsuario(request, response);
				break;
			default:
				RegistarVotante(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("email");
		String documento = request.getParameter("documento");
		TipoDocumento tipo = tipoDocumentoDao.find(Integer.parseInt(request.getParameter("Tipodocumento")));
		Eleccion eleccion = eleccionesDao.find(Integer.parseInt(request.getParameter("eleccion")));
		Votante v = new Votante(nombre,correo,documento,tipo,eleccion);
		votanteDao.insert(v);
		request.getRequestDispatcher("RegistrarVotante.jsp").forward(request, response);
	}

	private void RegistarVotante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Eleccion> elecciones = this.eleccionesDao.list();
		List<TipoDocumento> tipoDocumento = this.tipoDocumentoDao.list();
		
		request.setAttribute("elecciones", elecciones);
		request.setAttribute("tipoDocumento", tipoDocumento);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RegistrarVotante.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Votante vt = new Votante();
		vt.setId(id);
		Votante vtaux = votanteDao.find(vt.getId());
		request.getSession().setAttribute("tipodocumento", vtaux);
		request.getRequestDispatcher("RegistrarVotante.jsp").forward(request, response);
	}

	private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("email");
		String documento = request.getParameter("documento");
		TipoDocumento tipo = tipoDocumentoDao.find(Integer.parseInt(request.getParameter("Tipodocumento")));
		Eleccion eleccion = eleccionesDao.find(Integer.parseInt(request.getParameter("eleccion")));
		Votante v = new Votante(id,nombre,correo,documento,tipo,eleccion);
		votanteDao.update(v);
		request.getRequestDispatcher("ListarVotante.jsp").forward(request, response);
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Votante vt = new Votante();
		Votante vtaux = votanteDao.find(id);
		vt.setId(id);
		votanteDao.delete(vtaux);
		request.getRequestDispatcher("ListarVotante.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Votante vt = new Votante();
		vt.setId(id);
		Votante vtaux = votanteDao.find(vt.getId());
		request.getSession().setAttribute("tipodocumento", vtaux);
		request.getRequestDispatcher("RegistrarVotante.jsp").forward(request, response);
	}

}
