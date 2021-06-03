package ufps.edu.co.dao;

import ufps.edu.co.model.Voto;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;

public class VotoDao extends Conexion<Voto> implements GenericDAO<Voto>  {

	public VotoDao(){
		super(Voto.class);
	}

}
