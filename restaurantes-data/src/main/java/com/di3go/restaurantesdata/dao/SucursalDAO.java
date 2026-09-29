/**
 * 
 */
package com.di3go.restaurantesdata.dao;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.Sucursal;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Interface que realiza el CRUD de transacciones para la tabla de sucursal.
 */
public interface SucursalDAO {
	/**
	 * Metodo que permite guardar registros de sucursales.
	 * @param sucursal objeto a guardar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Sucursal sucursal) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de sucursales.
	 * @param sucursal objeto a actualizar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Sucursal sucursal) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de sucursales.
	 * @param idSucursal identificador de la sucursal a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idSucursal) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de sucursales.
	 * @return lista de sucursales o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Sucursal> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de sucursal por identificador.
	 * @param idSucursal identificador del tipo.
	 * @return Sucursal consultada o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excepcion personalizada que genera un mensaje mas especifico
	 */
	Sucursal consultarPorId(int idSucursal) throws SQLException, RestauranteException;
}
