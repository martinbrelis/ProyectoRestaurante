/**
 * 
 */
package com.di3go.restaurantesdata.dao;

import java.sql.SQLException;
import java.util.List;

import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Interface que proporciona el CRUD para las transacciones hacia la base de datos
 * en la tabla empleado.
 */
public interface EmpleadoDAO {
	/**
	 * Metodo que permite guardar registros de empleados.
	 * @param empleado objeto a guardar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Empleado empleado) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de empleados.
	 * @param empleado objeto a actualizar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Empleado empleado) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de empleado.
	 * @param idEmpleado identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idEmpleado) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de empleados.
	 * @return lista de empleados o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Empleado> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de empleado por identificador.
	 * @param idEmpleado identificador del tipo.
	 * @return empleado consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excepcion personalizada que genera un mensaje mas especifico
	 */
	Empleado consultarPorId(int idEmpleado) throws SQLException, RestauranteException;
}
