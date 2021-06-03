package ufps.edu.co.dao;

import ufps.edu.co.model.Eleccion;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;

public class EleccionesDao extends Conexion<Eleccion> implements GenericDAO<Eleccion>  {

	public EleccionesDao() {
		super(Eleccion.class);
	}

}
