package ufps.edu.co.dao;

import ufps.edu.co.model.Candidato;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;

public class CandidatoDao extends Conexion<Candidato> implements GenericDAO<Candidato>  {

	public CandidatoDao(){
		super(Candidato.class);
	}
	
}
