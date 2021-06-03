package ufps.edu.co.dao;

import ufps.edu.co.model.Votante;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;

public class VotanteDao extends Conexion<Votante> implements GenericDAO<Votante>  {

	public VotanteDao(){
		super(Votante.class);
	}

}
