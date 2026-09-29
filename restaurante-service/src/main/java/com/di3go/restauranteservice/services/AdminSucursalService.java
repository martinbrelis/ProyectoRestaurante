/**
 * 
 */
package com.di3go.restauranteservice.services;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restauranteentities.entity.Sucursal;
import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restaurantesdata.dao.impl.AlimentoDAOImpl;
import com.di3go.restaurantesdata.dao.impl.EmpleadoDAOImpl;
import com.di3go.restaurantesdata.dao.impl.RolDAOImpl;
import com.di3go.restaurantesdata.dao.impl.SucursalDAOImpl;
import com.di3go.restaurantesdata.dao.impl.TipoAlimentoDAOImpl;

/**
 * @author Diego Paniagua Lopez.
 * Clase que realiza la logica de negocio para la pantalla del administrador de sucursal.
 */
public class AdminSucursalService {
	/**
	 * Objeto que permite realizar las transacciones a la base de datos de la tabla tipo alimento.
	 */
	private TipoAlimentoDAOImpl tipoAlimentoDAOImpl = new TipoAlimentoDAOImpl();
	/**
	 * Objeto que permite realizar las transacciones a la base de datos de la tabla de alimento.
	 */
	private AlimentoDAOImpl alimentoDAOImpl = new AlimentoDAOImpl();
	/**
	 * Objeto que permite realizar las transacciones a la base de datos de la tabla de empleado.
	 */	
	private EmpleadoDAOImpl empleadoDAOImpl = new EmpleadoDAOImpl();
	/**
	 * Objeto que permite realizar las transacciones a la base de datos de la tabla de rol.
	 */	
	private RolDAOImpl rolDAOImpl = new RolDAOImpl();
	/**
	 * Objeto que permite realizar las transacciones a la base de datos de la tabla de sucursal.
	 */
	private SucursalDAOImpl sucursalDAOImpl = new SucursalDAOImpl();

	
	//::::::::::::::::::::: TIPOS DE ALIMENTOS ::::::::::::::::::::::::::::
	
	/**
	 * Metodo que permite consultar la lista de tipos de alimentos.
	 * @return lista de tipos de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<TipoAlimento> consultarTiposAlimentos() throws SQLException {
		return this.tipoAlimentoDAOImpl.consultar();
	}
	
	/**
	 * Metodo que permite guardar un tipo de alimento.
	 * @param tipoAlimento objeto a guardar.
	 * @return 1 en caso de guardar, 0 en caso de no guardar.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarTipoAlimento(TipoAlimento tipoAlimento) throws SQLException {
		tipoAlimento.setFechaCreacion(LocalDateTime.now());
		tipoAlimento.setEstatus(true);
		
		return this.tipoAlimentoDAOImpl.guardar(tipoAlimento);
	}
	
	/**
	 * Metodo que permite guardar un tipo de alimento en un restaurante.
	 * @param tipoAlimento objeto a guardar.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return 1 en caso de guardar, 0 en caso de no guardar.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarTipoAlimentoRestaurante(TipoAlimento tipoAlimento, int idRestauranteUsuarioSesion) throws SQLException {
		tipoAlimento.setFechaCreacion(LocalDateTime.now());
		tipoAlimento.setEstatus(true);
		
		return this.tipoAlimentoDAOImpl.guardar(tipoAlimento, idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite actualizar un tipo de alimento.
	 * @param tipoAlimento objeto a editar
	 * @return 1 en caso de actualizar, 0 en caso de no actualizar.
	 * @throws SQLException SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int actualizarTipoAlimento(TipoAlimento tipoAlimento) throws SQLException {
		tipoAlimento.setFechaModificacion(LocalDateTime.now());
		
		return this.tipoAlimentoDAOImpl.actualizar(tipoAlimento);
	}
	
	/**
	 * Metodo que permite consultar los tipos de alimentos que corresponden al restaurante del usuario en sesion.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de tipos de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<TipoAlimento> consultarTiposAlimentosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		return this.tipoAlimentoDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
	}
	
	//:::::::::::::::::::::: ALIMENTOS ::::::::::::::::::::::::::::::::
	
	/**
	 * Metodo que permite consultar la lista de alimentos de un restaurante.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Alimento> consultarAlimentosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		return this.alimentoDAOImpl.consultarPorIdRestaurante(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite guardar un registro de alimento.
	 * @param alimento objeto a guardar
	 * @return 1 en caso de guardar, 0 en caso de no guardar.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarAlimento(Alimento alimento) throws SQLException {
		alimento.setFechaCreacion(LocalDateTime.now());
		alimento.setEstatus(true);
		
		return this.alimentoDAOImpl.guardar(alimento);
	}

	/**
	 * Metodo que permite actualizar un registro de alimento.
	 * @param alimento objeto a actualizar
	 * @return 1 en caso de actualizar, 0 en caso de no actualizar.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int actualizarAlimento(Alimento alimento) throws SQLException {
		alimento.setFechaModificacion(LocalDateTime.now());
		return this.alimentoDAOImpl.actualizar(alimento);
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos disponibles del restaurante no asignados al menu.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de restaurantes disponibles.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQl.
	 */
	public List<Alimento> consultarAlimentosDisponiblesRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		return this.alimentoDAOImpl.consultarAlimentosDisponibles(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos asignados al menu del restaurante.
	 * @param idMenu identificador del menu.
	 * @return lista de restaurantes asignados.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQl.
	 */
	public List<Alimento> consultarAlimentosAsignadosMenu(Integer idMenu) throws SQLException {
		return this.alimentoDAOImpl.consultarAlimentosAsignados(idMenu);
	}
	
	/**
	 * Metodo que permite asignar un alimento al menu del restaurante.
	 * @param idAlimentoSeleccionado identificador del alimento a  asignar al menu
	 * @param idMenu identificador del menu.
	 * @return 1 en caso de asignado, 0 en caso de no asignado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int asignarAlimentoMenu(int idAlimentoSeleccionado, int idMenu) throws SQLException {
		return this.alimentoDAOImpl.asignarAlimentoMenu(idAlimentoSeleccionado, idMenu);
	}

	/**
	 * Metodo que permite quitar un alimento del menu del restaurante.
	 * @param idAlimentoSeleccionado identificador del alimento seleccionado.
	 * @return 1 en caso de removido, 0 en caso de no removido.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int quitarAlimentoMenu(int idAlimentoSeleccionado) throws SQLException {
		return this.alimentoDAOImpl.quitarAlimentoMenu(idAlimentoSeleccionado);
	}
	
	// :::::::::::::::::::: EMPLEADOS ::::::::::::::::::::::::::::::::::
	
	/**
	 * Metodo que permite consultar la lista de empleados del restaurante del administrador de la sucursal.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de empleados.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Empleado> consultarEmpleadosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		return this.empleadoDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite guardar un empleado.
	 * @param empleado objeto a guardar.
	 * @return 1 en caso de guardado, 0 en caso de no guardado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarEmpleado(Empleado empleado) throws SQLException {
		
		//Si el rol a asignar al empleado es administrador de sucursal.
		if (empleado.getRol().getIdRol() == 6) {
			empleado.setSuperadmin(true);
		}
		
		empleado.setFechaCreacion(LocalDateTime.now());
		empleado.setEstatus(true);
		
		return this.empleadoDAOImpl.guardar(empleado);
	}
	
	/**
	 * Metodo que permite actualizar un empleado.
	 * @param empleado objeto a actualizar.
	 * @return 1 en caso de actualizado, 0 en caso de no actualizado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int actualizarEmpleado(Empleado empleado) throws SQLException {
		
		//Si el rol a asignar al empleado no es administrador de sucursal.
		if (empleado.getRol().getIdRol() != 6) {
			empleado.setSuperadmin(false);
		}
		
		empleado.setFechaModificacion(LocalDateTime.now());
		
		return this.empleadoDAOImpl.actualizar(empleado);
	}
	
	// :::::::::::::::::::: ROLES ::::::::::::::::::::::::::::::::::::::::
	
	/**
	 * Metodo que permite consultar los roles para el administrador de sucursal que desee
	 * guardar un nuevo empleado.
	 * @return lista de roles disponibles.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Rol> consultarRolesSinAdminGeneral() throws SQLException {
		return this.rolDAOImpl.consultarRolesSinAdminGeneral();
	}
	
	// ::::::::::::::::::::: SUCURSALES :::::::::::::::::::::::::::::::::

	/**
	 * Metodo que permite consultar la lista de sucursales de un restaurante.
	 * @param idRestauranteUsuarioSesion identificador del restaraurante.
	 * @return lista de sucursales.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL:
	 */
	public List<Sucursal> consultarSucursalesPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		return this.sucursalDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite guardar una sucursal.
	 * @param sucursal objeto a guardar.
	 * @return 1 en caso de guardado, 0 en caso de no guardado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardarSucursal(Sucursal sucursal) throws SQLException {
		sucursal.setFechaCreacion(LocalDateTime.now());
		sucursal.setEstatus(true);
		return this.sucursalDAOImpl.guardar(sucursal);
	}
	
	/**
	 * Metodo que permite actualizar una sucursal.
	 * @param sucursal objeto a actualizar.
	 * @return 1 en caso de actualizado, 0 en caso de no actualizado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */	
	public int actualizarSucursal(Sucursal sucursal) throws SQLException {
		sucursal.setFechaModificacion(LocalDateTime.now());
		
		return this.sucursalDAOImpl.actualizar(sucursal);
	}
	
}
