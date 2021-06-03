package ufps.edu.co.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.TipoDocumentoDao;
import ufps.edu.co.model.TipoDocumento;

/**
 * Servlet implementation class TipodocumentoServlet
 */
@WebServlet("/TipodocumentoServlet/*")
public class TipodocumentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TipoDocumentoDao tpdtDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipodocumentoServlet() {
        super();
        tpdtDao = new TipoDocumentoDao();
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
		TipoDocumento tp = new TipoDocumento();
		tp.setId(id);
		TipoDocumento tpaux = new TipoDocumento();
		request.getSession().setAttribute("tipodocumento", tpaux);
		request.getRequestDispatcher("TipodocumentoRegistro.jsp").forward(request, response);
	}
	
	protected void  registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String descripcion = request.getParameter("descripcion");
		TipoDocumento tpdt = new TipoDocumento();
		tpdt.setDescripcion(descripcion);
		tpdtDao.insert(tpdt);
		request.getRequestDispatcher("TipodocumentoRegistro.jsp").forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		String descripcion = request.getParameter("descripcion");
		TipoDocumento tp = new TipoDocumento();
		tp.setId(id);
		tp.setDescripcion(descripcion);
		tpdtDao.update(tp);
		request.getRequestDispatcher("TipodocumentoLista.jsp").forward(request, response);
	}


	
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		TipoDocumento tp = new TipoDocumento();
		tp.setId(id);
		TipoDocumento tpaux = tpdtDao.find(tp.getId());
		request.getSession().setAttribute("tipodocumento", tpaux);
		request.getRequestDispatcher("TipodocumentoRegistro.jsp").forward(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		TipoDocumento tp = new TipoDocumento();
		TipoDocumento tpaux = tpdtDao.find(id);
		tp.setId(id);
		tpdtDao.delete(tpaux);
		request.getRequestDispatcher("TipodocumentoLista.jsp").forward(request, response);
		
	}


}
