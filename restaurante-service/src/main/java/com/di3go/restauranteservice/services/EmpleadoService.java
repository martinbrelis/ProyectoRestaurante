/**
 * 
 */
package com.di3go.restauranteservice.services;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restaurantesdata.dao.impl.AlimentoDAOImpl;
import com.di3go.restaurantesdata.dao.impl.TipoAlimentoDAOImpl;

/**
 * @author Diego Paniagua Lopez.
 * Clase que realiza la logica de negocio para la pantalla del empleado.
 */
public class EmpleadoService {
	/**
	 * Objeto que implementa las transacciones a la tabla de tipo alimento
	 */
	private TipoAlimentoDAOImpl tipoAlimentoDAOImpl = new TipoAlimentoDAOImpl();
	/**
	 * Objeto que implementa las transacciones a la tabla de alimento
	 */
	private AlimentoDAOImpl alimentoDAOImpl = new AlimentoDAOImpl();
	/**
	 * Metodo que permite consultar alimentos del menu del restaurante.
	 * @param idMenu identificador del menu.
	 * @return lista de tipos de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<TipoAlimento> consultarAlimentosPorMenu(int idMenu) throws SQLException {
		List<TipoAlimento> tiposAlimentos = this.tipoAlimentoDAOImpl.consultarTiposAlimentosPorMenu(idMenu);
		
		for (TipoAlimento tipoAlimento : tiposAlimentos) {
			List<Alimento> alimentos = this.alimentoDAOImpl.consultarAlimentosPorTipoAndMenu(tipoAlimento.getIdTipoAlimento(), idMenu);
			tipoAlimento.setAlimentos(alimentos);
		}
		
		return tiposAlimentos;
	}
}
