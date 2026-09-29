/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restauranteentities.entity.Menu;
import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restauranteentities.entity.Sucursal;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.EmpleadoDAO;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Clase que implementa la funcionalidad del CRUD de empleados.
 *
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {
	
	static {
		try {
			ConnectionFactory.conectar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int guardar(Empleado empleado) throws SQLException {
		String sql = "INSERT INTO empleado (nombre, primerApellido, segundoApellido, idRol, idSucursal, usuario, password, email, superadmin, superadmingeneral, fechaCreacion, estatus) "
				   + "VALUES ('" + empleado.getNombre() + "', '" + empleado.getPrimerApellido() + "', '" + empleado.getSegundoApellido() + "', " + empleado.getRol().getIdRol() + ", "
				   + "" + empleado.getSucursal().getIdSucursal() + ", '" + empleado.getUsuario() + "', '" + empleado.getPassword() + "', '" + empleado.getEmail() + "', "
				   + "" + empleado.isSuperadmin() + ", " + empleado.isSuperadmingeneral() + ", '" + empleado.getFechaCreacion() + "', " + empleado.isEstatus() + ")";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int actualizar(Empleado empleado) throws SQLException {
		String sql = "UPDATE empleado SET nombre = '" + empleado.getNombre() + "', primerApellido = '" + empleado.getPrimerApellido() + "', segundoApellido = '" + empleado.getSegundoApellido() + "', "
				   + "idRol = " + empleado.getRol().getIdRol() + ", usuario = '" + empleado.getUsuario() + "', password = '" + empleado.getPassword() + "', email = '" + empleado.getEmail() + "', "
				   + "superadmin = " + empleado.isSuperadmin() + ", fechaModificacion = '" + empleado.getFechaModificacion() + "', estatus = " + empleado.isEstatus() + " "
				   + "WHERE idEmpleado = " + empleado.getIdEmpleado() + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int eliminar(int idEmpleado) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Empleado> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado consultarPorId(int idEmpleado) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que permite consultar un empleado desde el login con su informacion y perfil en el sistema.
	 * @param usuario parametro capturado por el usuario.
	 * @param password parametro capturado por el usuario.
	 * @param esSuperAdminGeneral verifica si el usuario es administrador general.
	 * @return objeto con el empleado loggeado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public Empleado consultarPorUsuarioYPassword(String usuario, String password, boolean esSuperAdminGeneral) throws SQLException {
		
		Empleado empleado = null;
		
		String sql = "";
		
		if (esSuperAdminGeneral) {
			sql = "SELECT e.*, r.nombre AS nombrePerfil " + 
					"FROM empleado e, rol r " + 
					"WHERE e.idRol = r.idRol " + 
					"AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') " + 
					"AND e.password = '" + password + "' " + 
					"AND e.idSucursal IS NULL;";
		} else {
			sql = "SELECT e.*, r.nombre as nombrePerfil, s.nombre as nombreSucursal, r.nombre as nombreRestaurante, res.idRestaurante, res.imagen, res.idMenu " + 
					"FROM empleado e, rol r, sucursal s, restaurante res " + 
					"WHERE e.idRol = r.idRol " + 
					"AND e.idSucursal = s.idSucursal " + 
					"AND s.idRestaurante = res.idRestaurante " + 
					"AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') " + 
					"AND password = '" + password + "';";
		}
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			if (rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("idEmpleado"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setPrimerApellido(rs.getString("primerApellido"));
				empleado.setSegundoApellido(rs.getString("segundoApellido"));
				empleado.setUsuario(rs.getString("usuario"));
				empleado.setPassword(rs.getString("password"));
				empleado.setEmail(rs.getString("email"));
				empleado.setEstatus(rs.getBoolean("estatus"));
				empleado.setSuperadmin(rs.getBoolean("superadmin"));
				empleado.setSuperadmingeneral(rs.getBoolean("superadmingeneral"));
				
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombrePerfil"));
				empleado.setRol(rol);
				
				// AÑADIR FUNCIONALIDAD PARA EL CASO DE ADMINISTRADOR DE SUCURSAL Y EMPLEADO.
				if (!empleado.isSuperadmingeneral()) {
					Sucursal sucursal = new Sucursal();
					sucursal.setIdSucursal(rs.getInt("idSucursal"));
					sucursal.setNombre(rs.getString("nombreSucursal"));
					empleado.setSucursal(sucursal);
					
					Restaurante restaurante = new Restaurante();
					restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
					restaurante.setNombre(rs.getString("nombreRestaurante"));
					restaurante.setImagen(rs.getString("imagen"));
					sucursal.setRestaurante(restaurante);
					
					Menu menu = new Menu();
					menu.setIdMenu(rs.getInt("idMenu"));
					restaurante.setMenu(menu);
	
				}
			}
		}
		
		return empleado;
	}
	
	/**
	 * Metodo que permite consultar la lista de empleados del restaurante del administrador de la sucursal.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de empleados.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Empleado> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		List<Empleado> empleadosDB = new ArrayList<Empleado>();
		
		String sql = "SELECT e.*, r.nombre as nombreRol FROM restaurante res, sucursal s, empleado e, rol r " + 
					"WHERE e.idSucursal = s.idSucursal " + 
					"AND s.idRestaurante = res.idRestaurante " + 
					"AND r.idRol = e.idRol " +
					"AND s.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("idEmpleado"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setPrimerApellido(rs.getString("primerApellido"));
				empleado.setSegundoApellido(rs.getString("segundoApellido"));
				empleado.setUsuario(rs.getString("usuario"));
				empleado.setPassword(rs.getString("password"));
				empleado.setEmail(rs.getString("email"));
				empleado.setEstatus(rs.getBoolean("estatus"));
				empleado.setSuperadmin(rs.getBoolean("superadmin"));
				empleado.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				empleado.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaCreacion").toLocalDateTime() : null);
				
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombreRol"));
				
				empleado.setRol(rol);
				
				empleadosDB.add(empleado);
			}
		}
		
		return empleadosDB;
	}
}
