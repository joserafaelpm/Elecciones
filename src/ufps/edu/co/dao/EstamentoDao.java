package ufps.edu.co.dao;

import ufps.edu.co.model.Estamento;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;


public class EstamentoDao extends Conexion<Estamento> implements GenericDAO<Estamento>  {

	public EstamentoDao(){
		super(Estamento.class);
	}

}
