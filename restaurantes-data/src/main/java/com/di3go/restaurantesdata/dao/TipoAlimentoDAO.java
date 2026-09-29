/**
 * 
 */
package com.di3go.restaurantesdata.dao;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniaggua Lopez.
 * Interface que realiza el CRUD de transacciones para la tabla de tipo de alimento.
 */
public interface TipoAlimentoDAO {
	/**
	 * Metodo que permite guardar registros de tipos de alimentos.
	 * @param tipoAlimento objeto a guardar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(TipoAlimento tipoAlimento) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de tipos de alimentos.
	 * @param tipoAlimento objeto a actualizar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(TipoAlimento tipoAlimento) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de tipos de alimentos.
	 * @param idTipoAlimento identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idTipoAlimento) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de tipos de alimentos.
	 * @return lista de tipos de alimentos o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<TipoAlimento> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo de alimento por identificador.
	 * @param idTipoAlimento identificador del tipo.
	 * @return tipo de alimento consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excepcion personalizada que genera un mensaje mas especifico
	 */
	TipoAlimento consultarPorId(int idTipoAlimento) throws SQLException, RestauranteException;
}
