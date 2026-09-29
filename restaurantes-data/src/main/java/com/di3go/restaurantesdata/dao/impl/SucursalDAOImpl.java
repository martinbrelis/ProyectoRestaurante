/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.Sucursal;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.SucursalDAO;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Clase que implementa el CRUD de la interface de SucursalDAO.
 */
public class SucursalDAOImpl implements SucursalDAO {

	@Override
	public int guardar(Sucursal sucursal) throws SQLException {
		String sql = "INSERT INTO sucursal (nombre, idRestaurante, fechaCreacion, estatus) "
				   + "VALUES ('" + sucursal.getNombre() + "', " + sucursal.getRestaurante().getIdRestaurante() + ", '" + sucursal.getFechaCreacion() + "', " + sucursal.isEstatus() + ");";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int actualizar(Sucursal sucursal) throws SQLException {
		String sql = "UPDATE sucursal SET nombre = '" + sucursal.getNombre() + "', fechaModificacion = '" + sucursal.getFechaModificacion() + "', estatus = " + sucursal.isEstatus() + " "
				   + "WHERE idSucursal = " + sucursal.getIdSucursal() + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int eliminar(int idSucursal) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sucursal> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sucursal consultarPorId(int idSucursal) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que permite consultar la lista de sucursales de un restaurante.
	 * @param idRestauranteUsuarioSesion identificador del restaraurante.
	 * @return lista de sucursales.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL:
	 */
	public List<Sucursal> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		List<Sucursal> sucursalesDB = new ArrayList<Sucursal>();
		
		String sql = "SELECT s.*, res.nombre as nombreRestaurante "
				   + "FROM sucursal s, restaurante res "
				   + "WHERE s.idRestaurante = res.idRestaurante "
				   + "AND s.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Sucursal sucursal = new Sucursal();
				sucursal.setIdSucursal(rs.getInt("idSucursal"));
				sucursal.setNombre(rs.getString("nombre"));
				sucursal.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				sucursal.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				sucursal.setEstatus(rs.getBoolean("estatus"));
				
				Restaurante restaurante = new Restaurante();
				restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				restaurante.setNombre(rs.getString("nombreRestaurante"));
				
				sucursal.setRestaurante(restaurante);
				
				sucursalesDB.add(sucursal);
			}
		}
		
		return sucursalesDB;
	}
}
