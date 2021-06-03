package ufps.edu.co.dao;

import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;


public class TipoDocumentoDao extends Conexion<TipoDocumento> implements GenericDAO<TipoDocumento>  {

	public TipoDocumentoDao(){
		super(TipoDocumento.class);
	}

}
