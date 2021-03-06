package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.EleccionesDao;
import ufps.edu.co.dao.EstamentoDao;
import ufps.edu.co.dao.TipoDocumentoDao;
import ufps.edu.co.dao.VotanteDao;
import ufps.edu.co.dao.VotoDao;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.model.Votante;
import ufps.edu.co.model.Voto;
import ufps.edu.co.util.EnviarEmail;

/**
 * Servlet implementation class VotanteServlet
 */
@WebServlet({"/RegistrarVotante","/RegistrarVotante/*"})
public class VotanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TipoDocumentoDao tipoDocumentoDao;
	EleccionesDao eleccionesDao; 
	VotanteDao votanteDao;
	VotoDao votoDao;
	EstamentoDao estamentoDao;
	EnviarEmail email;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotanteServlet() {
        super();
        tipoDocumentoDao = new TipoDocumentoDao();
		eleccionesDao = new EleccionesDao();
		votanteDao = new VotanteDao();
		votoDao = new VotoDao();
		estamentoDao= new EstamentoDao();
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
		
		String enlace = UUID.randomUUID().toString();
		String uuid = UUID.randomUUID().toString().split("-")[0];
		
		Voto voto = new Voto();
		voto.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		voto.setVotante(v);
		voto.setEnlace(enlace);
		voto.setUuid(uuid);
		voto.setEstamento(estamentoDao.find(Integer.parseInt(request.getParameter("estamento"))));
		try {
		votoDao.insert(voto);
		}catch(Exception e){ return; }
		email.enviarCorreo(v.getEmail(), uuid, enlace);
		
		response.sendRedirect("../Index");
	}

	private void RegistarVotante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Eleccion> elecciones = this.eleccionesDao.list();
		List<TipoDocumento> tipoDocumento = this.tipoDocumentoDao.list();
		
		request.setAttribute("elecciones", elecciones);
		request.setAttribute("tipoDocumento", tipoDocumento);
		request.setAttribute("estamentos", estamentoDao.list());
		request.setAttribute("fechaActual", new Timestamp(System.currentTimeMillis()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RegistrarVotante.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Eleccion> elecciones = this.eleccionesDao.list();
		List<TipoDocumento> tipoDocumento = this.tipoDocumentoDao.list();
		
		request.setAttribute("elecciones", elecciones);
		request.setAttribute("tipoDocumento", tipoDocumento);
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		Votante vt = new Votante();
		vt.setId(id);
		Votante vtaux = votanteDao.find(vt.getId());
		request.getSession().setAttribute("votante", vtaux);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ActualizarVotante.jsp");
		dispatcher.forward(request, response);
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
		response.sendRedirect("../Admin/lista?id="+eleccion.getId());
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Votante vtaux = votanteDao.find(id);
		votanteDao.delete(vtaux);
		response.sendRedirect("../Admin/lista?id="+vtaux.getEleccion().getId());
	}
}
