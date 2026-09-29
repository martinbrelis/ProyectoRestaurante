/**
 * 
 */
package com.di3go.restaurantesdata.dao;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Interface que realiza el CRUD de transacciones para la tabla de alimento.
 */
public interface AlimentoDAO {
	/**
	 * Metodo que permite guardar registros de alimentos.
	 * @param alimentos objeto a guardar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Alimento alimento) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de alimentos.
	 * @param alimento objeto a actualizar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Alimento alimento) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de alimentos.
	 * @param idAlimento identificador del alimento a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idAlimento) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de alimentos.
	 * @return lista de alimentos o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Alimento> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de alimento por identificador.
	 * @param idAlimento identificador del tipo.
	 * @return Alimento consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excepcion personalizada que genera un mensaje mas especifico
	 */
	Alimento consultarPorId(int idAlimento) throws SQLException, RestauranteException;
}
