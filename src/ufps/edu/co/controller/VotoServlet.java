package ufps.edu.co.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.CandidatoDao;
import ufps.edu.co.dao.EstamentoDao;
import ufps.edu.co.dao.VotanteDao;
import ufps.edu.co.dao.VotoDao;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Estamento;
import ufps.edu.co.model.Votante;
import ufps.edu.co.model.Voto;
import ufps.edu.co.util.ValidarCaptcha;

/**
 * Servlet implementation class VotoServlet
 */
@WebServlet("/Voto/*")
public class VotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CandidatoDao canDAO;
       EstamentoDao estaDAO;
       VotanteDao votanDAO;
       VotoDao votaDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotoServlet() {
        super();
        canDAO = new CandidatoDao();
        estaDAO = new EstamentoDao();
        votanDAO = new VotanteDao();
        votaDAO = new VotoDao();
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
		case "validar":
			this.validar(request, response);
			break;
		case "votar":
			votar(request, response);
			break;
				/*	case "/buscar":
			this.buscar(request, response);
			break;	 */
		default:
			break;
		}
	}

	private void efectuarVoto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);
        
        boolean verificado = ValidarCaptcha.verificar(gRecaptchaResponse);
		
		Voto voto = votaDAO.find(Integer.parseInt(request.getParameter("id")));
		String candidatoId = request.getParameter("candidatoId");	
		if(verificado && !candidatoId.equals(null)) {
			Candidato candidato = canDAO.find(Integer.parseInt(candidatoId));
			voto.setCandidato(candidato);
			voto.setFechaVoto(new Timestamp(System.currentTimeMillis()));
			voto.setEnlace("");
			request.setAttribute("candidato", candidato);
			response.sendRedirect("../../votacionExitosa.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/Voto?action=votar&var="+voto.getEnlace());
		}
	}

	private void votar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String var = request.getParameter("var");
		Voto voto = new Voto();
		for (Voto v : votaDAO.list()){
			if(v.getEnlace().equals(var)){
				voto = v;
			}
		}
		if(voto.getEstamento()==null){
			response.sendRedirect("../Voto?action=validar&var="+var);
		}else {
		List<Candidato> candidatos = new LinkedList();
		for(Candidato c: canDAO.list()){
			if(c.getEleccion().equals(voto.getEstamento().getEleccion())){
				candidatos.add(c);
			}
		}
		request.setAttribute("candidatos", candidatos);
		request.setAttribute("voto", voto);
		request.getRequestDispatcher("Votar.jsp").forward(request, response);
		}
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String var = request.getParameter("var");
		Voto voto = new Voto();
		for (Voto v : votaDAO.list()){
			if(v.getEnlace().equals(var)){
				voto = v;
			}
		}
		votaDAO.update(voto);
		request.setAttribute("voto", voto);
		String documento = request.getParameter("documento");
		String clave = request.getParameter("clave");
		if(!voto.getVotante().getDocumento().equals(documento)){
			response.sendRedirect("../Voto?action=validar&var="+var);
		} else if(!voto.getUuid().equals(clave)){
			response.sendRedirect("../Voto?action=validar&var="+var);
		}else {
			response.sendRedirect("../Voto?action=votar&var="+var);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] opt =  request.getRequestURI().split("/");
		String action =  request.getContextPath();
		if (opt.length>3){
		 action = opt[3];
		}	
		if(action.equals("votar")){
			efectuarVoto(request, response);
			return;
		}
		action = request.getParameter("action");
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
		case "check":
			this.check(request, response);
			break;
		default:
			break;
		}
		
	}

	private void validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String var = request.getParameter("var");
		Voto voto = new Voto();
		for (Voto v : votaDAO.list()){
			if(v.getEnlace().equals(var)){
				voto = v;
			}
		}
		request.setAttribute("estamento", voto.getEstamento());
		request.setAttribute("voto", voto);
		request.setAttribute("votante", voto.getVotante());
		if(voto.getVotante().getEleccion().getFechaFin().before(new Timestamp(System.currentTimeMillis()))){
			voto.setEnlace("");
			response.sendRedirect("Index");
			return;
		}
		request.getRequestDispatcher("confirmarDatos.jsp").forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String enlace = request.getParameter("enlace");
		String [] fechaCreacionS = request.getParameter("fechainicio").split("-");
		String [] fechaVotoS = request.getParameter("fechafin").split("-");
		Timestamp fechacreacion = new Timestamp(Integer.parseInt(fechaCreacionS[0])-1900,Integer.parseInt(fechaCreacionS[1]),Integer.parseInt(fechaCreacionS[2]),0,0,0,0);
		Timestamp fechavoto = new Timestamp(Integer.parseInt(fechaVotoS[0])-1900,Integer.parseInt(fechaVotoS[1]),Integer.parseInt(fechaVotoS[2]),0,0,0,0);
		String uuid = request.getParameter("uuid");
		Integer candidato = Integer.parseInt(request.getParameter("candidato"));
		Candidato cadi = canDAO.find(candidato);
		Integer estamento = Integer.parseInt(request.getParameter("candidato"));
		Estamento esta = estaDAO.find(estamento);
		Integer votante = Integer.parseInt(request.getParameter("candidato"));
		Votante vota = votanDAO.find(votante);
		Voto vot = new Voto();
		vot.setEnlace(enlace);
		vot.setFechaCreacion(fechacreacion);
		vot.setFechaVoto(fechavoto);
		vot.setUuid(uuid);
		vot.setCandidato(cadi);
		vot.setEstamento(esta);
		vot.setVotante(vota);
		votaDAO.insert(vot);
		request.getRequestDispatcher("Voto.jsp").forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		String enlace = request.getParameter("enlace");
		String [] fechaCreacionS = request.getParameter("fechainicio").split("-");
		String [] fechaVotoS = request.getParameter("fechafin").split("-");
		Timestamp fechacreacion = new Timestamp(Integer.parseInt(fechaCreacionS[0])-1900,Integer.parseInt(fechaCreacionS[1]),Integer.parseInt(fechaCreacionS[2]),0,0,0,0);
		Timestamp fechavoto = new Timestamp(Integer.parseInt(fechaVotoS[0])-1900,Integer.parseInt(fechaVotoS[1]),Integer.parseInt(fechaVotoS[2]),0,0,0,0);
		String uuid = request.getParameter("uuid");
		Integer candidato = Integer.parseInt(request.getParameter("candidato"));
		Candidato cadi = canDAO.find(candidato);
		Integer estamento = Integer.parseInt(request.getParameter("candidato"));
		Estamento esta = estaDAO.find(estamento);
		Integer votante = Integer.parseInt(request.getParameter("candidato"));
		Votante vota = votanDAO.find(votante);
		Voto vot = new Voto();
		vot.setId(id);
		vot.setEnlace(enlace);
		vot.setFechaCreacion(fechacreacion);
		vot.setFechaVoto(fechavoto);
		vot.setUuid(uuid);
		vot.setCandidato(cadi);
		vot.setEstamento(esta);
		vot.setVotante(vota);
		votaDAO.update(vot);
		
	}
	

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Voto vot = new Voto();
		vot.setId(id);
		Voto votaux = new Voto();
		request.getSession().setAttribute("voto", votaux);
		request.getRequestDispatcher("Voto.jsp").forward(request, response);
		
	}
	
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Voto vot = new Voto();
		vot.setId(id);
		Voto votaux = votaDAO.find(vot.getId());
		request.getSession().setAttribute("voto", votaux);
		request.getRequestDispatcher("Voto.jsp").forward(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Voto vot = new Voto();
		Voto votaux = votaDAO.find(id);
		vot.setId(id);
		votaDAO.delete(votaux);
		request.getRequestDispatcher("Voto.jsp").forward(request, response);
	}

}
