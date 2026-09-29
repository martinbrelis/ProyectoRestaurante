/**
 * 
 */
package com.di3go.restauranteservice.services;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.di3go.restauranteentities.entity.Menu;
import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restaurantesdata.dao.impl.RestauranteDAOImpl;
import com.di3go.restaurantesdata.dao.impl.RolDAOImpl;
import com.di3go.restaurantesdata.dao.impl.TipoRestauranteDAOImpl;

/**
 * @author Diego Paniagua Lopez.
 * Clase de servicios que se encarga de realizar la logica de negocio para la administracion
 * de restaurantes para el administrador general.
 */
public class AdminGeneralService {
	/**
	 * Objeto que permite realizar las transacciones para la tabla de tipo de restaurante.
	 */
	private TipoRestauranteDAOImpl tipoRestauranteDAOImpl = new TipoRestauranteDAOImpl();
	/**
	 * Objeto que permite realizar las transacciones para la tabla de restaurante.
	 */
	private RestauranteDAOImpl restauranteDAOImpl = new RestauranteDAOImpl();
	/**
	 * Objeto que permite realizar las tranasacciones para la tabla de rol.
	 */
	private RolDAOImpl rolDAOImpl = new RolDAOImpl();
	
	//:::::::::::::::: TIPOS DE RESTAURANTES :::::::::::::::::::::
	
	public List<TipoRestaurante> consultarTiposRestaurantes() throws SQLException {
		return this.tipoRestauranteDAOImpl.consultar();
	}
	
	/**
	 * Metodo que permite guardar un registro de tipo de restaurante.
	 * @param tipoRestaurante tipo de restaurante a guardar.
	 * @return 1 en caso de guardado, 0 en caso de error.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
		tipoRestaurante.setFechaCreacion(LocalDateTime.now());
		tipoRestaurante.setEstatus(true);
		
		return this.tipoRestauranteDAOImpl.guardar(tipoRestaurante);
	}
	
	/**
	 * Metodo que permite actualizar un registro de tipo de restaurante.
	 * @param tipoRestaurante tipo de restaurante a actualizar.
	 * @return 1 en caso de actualizado, 0 en caso de error.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int actualizarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
		tipoRestaurante.setFechaModificacion(LocalDateTime.now());
		
		return this.tipoRestauranteDAOImpl.actualizar(tipoRestaurante);
	}

	/**
	 * Metodo que permite eliminar un registro de tipo de restaurante.
	 * @param idTipoRestaurante identificador del tipo de restaurante a eliminar.
	 * @return 1 en caso de eliminar, 0 en caso de error.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int eliminarTipoRestaurante(int idTipoRestaurante) throws SQLException {
		return this.tipoRestauranteDAOImpl.eliminar(idTipoRestaurante);
	}
	
	//:::::::::::::::::: RESTAURANTES :::::::::::::::::::::::::::::::
	
	/**
	 * Metodo que permite consultar los restaurantes.
	 * @return lista de restaurantes.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Restaurante> consultarRestaurantes() throws SQLException {
		return this.restauranteDAOImpl.consultar();
	}
	
	/**
	 * Metodo que permite guardar un restaurante.
	 * @param restaurante objeto a guardar.
	 * @return 1 en caso de guardado, 0 en caso de error.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarRestaurante(Restaurante restaurante) throws SQLException {
		restaurante.setFechaCreacion(LocalDateTime.now());
		restaurante.setEstatus(true);
		return this.restauranteDAOImpl.guardar(restaurante);
	}
	
	/**
	 * Metodo que permite actualizar un restaurante.
	 * @param restaurante objeto a actualizar.
	 * @return 1 en caso de actualizado, 0 en caso de error.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int actualizarRestaurante(Restaurante restaurante) throws SQLException {
		restaurante.setFechaModificacion(LocalDateTime.now());
		restaurante.setMenu(new Menu());
		return this.restauranteDAOImpl.actualizar(restaurante);
	}
	
	// :::::::::::::::::::::::: ROL :::::::::::::::::::::::::::::::
	
	/**
	 * Metodo que permite consultar la lista de roles de la aplicacion.
	 * @return lista de roles.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Rol> consultarRoles() throws SQLException {
		return this.rolDAOImpl.consultar();
	}
	
	/**
	 * Metodo que permite guardar un rol.
	 * @param rol objeto a guardar.
	 * @return 1 en caso de guardado, 0 en caso de no guardar.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarRol(Rol rol) throws SQLException {
		rol.setFechaCreacion(LocalDateTime.now());
		rol.setEstatus(true);
		
		return this.rolDAOImpl.guardar(rol);
	}
	
	/**
	 * Metodo que permite actualizar un rol.
	 * @param rol objeto a actualizar.
	 * @return 1 en caso de actualizado, 0 en caso de no actualizado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */	
	public int actualizarRol(Rol rol) throws SQLException {
		rol.setFechaModificacion(LocalDateTime.now());
		
		return this.rolDAOImpl.actualizar(rol);
	}
}
