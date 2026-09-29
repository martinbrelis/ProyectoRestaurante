/**
 * 
 */
package com.di3go.restaurantesdata.dao;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Interface que proporciona el CRUD para las transacciones hacia la base de datos
 * en la tabla restaurante.
 */
public interface RestauranteDAO {
	/**
	 * Metodo que permite guardar registros de tipos de restaurantes.
	 * @param restaurante objeto a guardar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Restaurante restaurante) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de tipos de restaurantes.
	 * @param restaurante objeto a actualizar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Restaurante restaurante) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de tipos de restaurantes.
	 * @param idRestaurante identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idRestaurante) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de tipos de restaurantes.
	 * @return lista de tipos de restaurantes o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Restaurante> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo de restaurante por identificador.
	 * @param restaurante identificador del tipo.
	 * @return tipo de restaurante consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excepcion personalizada que genera un mensaje mas especifico
	 */
	Restaurante consultarPorId(int idRestaurante) throws SQLException, RestauranteException;
}
