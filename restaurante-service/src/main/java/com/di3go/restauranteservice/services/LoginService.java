/**
 * 
 */
package com.di3go.restauranteservice.services;

import java.sql.SQLException;

import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restaurantesdata.dao.impl.EmpleadoDAOImpl;

/**
 * @author Diego Paniagua Lopez.
 * Clase que realiza la logica de negocio para la funcionalidad de la pantalla de login.
 */
public class LoginService {

	/**
	 * Objeto para obtener los resultados de las transacciones a la base de datos en la tabla de empleados.
	 */
	private EmpleadoDAOImpl empleadoDAOImpl = new EmpleadoDAOImpl();
	
	/**
	 * Metodo que permite consultar un empleado desde el login con su informacion y perfil en el sistema.
	 * @param usuario parametro capturado por el usuario.
	 * @param password parametro capturado por el usuario.
	 * @param esSuperAdminGeneral verifica si el usuario es administrador general.
	 * @return objeto con el empleado loggeado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public Empleado consultarPorUsuarioYPassword(String usuario, String password, boolean esSuperAdminGeneral) throws SQLException {
		return this.empleadoDAOImpl.consultarPorUsuarioYPassword(usuario, password, esSuperAdminGeneral);
	}
}
