/**
 * 
 */
package com.di3go.restaurantesdata.dao;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Interface que realiza el CRUD de transacciones para la tabla de rol.
 */
public interface RolDAO {
	/**
	 * Metodo que permite guardar registros de roles.
	 * @param rol objeto a guardar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Rol rol) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de rol.
	 * @param rol objeto a actualizar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Rol rol) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de roles.
	 * @param idRol identificador del rol a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idRol) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de roles.
	 * @return lista de roles o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Rol> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de rol por identificador.
	 * @param idRol identificador del tipo.
	 * @return Rol consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excepcion personalizada que genera un mensaje mas especifico
	 */
	Rol consultarPorId(int idRol) throws SQLException, RestauranteException;
}
