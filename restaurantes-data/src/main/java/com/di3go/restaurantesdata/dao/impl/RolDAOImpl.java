/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.RolDAO;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Clase que implementa el CRUD de transaccion de la interface de RolDAO
 */
public class RolDAOImpl implements RolDAO {

	@Override
	public int guardar(Rol rol) throws SQLException {
		String sql = "INSERT INTO rol (nombre, fechaCreacion, estatus) VALUES ('" + rol.getNombre() + "', '" + rol.getFechaCreacion() + "', " + rol.isEstatus() + ");";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int actualizar(Rol rol) throws SQLException {
		String sql = "UPDATE rol SET nombre = '" + rol.getNombre() + "', fechaModificacion = '" + rol.getFechaModificacion() + "', estatus = " + rol.isEstatus() + " "
				   + "WHERE idRol = " + rol.getIdRol() + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int eliminar(int idRol) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Rol> consultar() throws SQLException {
		List<Rol> rolesDB = new ArrayList<Rol>();
		
		String sql = "SELECT * FROM rol ORDER BY nombre";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombre"));
				rol.setEstatus(rs.getBoolean("estatus"));
				rol.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				rol.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				
				rolesDB.add(rol);
			}
		}
		
		return rolesDB;
	}

	@Override
	public Rol consultarPorId(int idRol) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que permite consultar los roles para el administrador de sucursal que desee
	 * guardar un nuevo empleado.
	 * @return lista de roles disponibles.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Rol> consultarRolesSinAdminGeneral() throws SQLException {
		List<Rol> rolesDB = new ArrayList<Rol>();
		
		String sql = "SELECT * FROM rol WHERE idRol != 5;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombre"));
				rol.setEstatus(rs.getBoolean("estatus"));
				
				rolesDB.add(rol);
			}
		}
		
		return rolesDB;
	}
}
