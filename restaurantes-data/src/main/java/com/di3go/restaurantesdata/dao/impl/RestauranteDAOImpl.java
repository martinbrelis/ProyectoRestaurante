/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.Menu;
import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.RestauranteDAO;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez. Clase que implementa los metodos deL CRUD de la
 *         interface RestauranteDAO.
 */
public class RestauranteDAOImpl implements RestauranteDAO {

	static {
		try {
			ConnectionFactory.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.err.println("Error de Base de datos: " + e.getMessage());
		}
	}

	@Override
	public int guardar(Restaurante restaurante) throws SQLException {
		String sql = "insert into restaurante (nombre, imagen, slogan, idTipoRestaurante, fechaCreacion, estatus, idMenu) "
				+ "VALUES ('" + restaurante.getNombre() + "', '" + restaurante.getImagen() + "', '', "
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + ", " + "'" + restaurante.getFechaCreacion()
				+ "', " + restaurante.isEstatus() + ", " + restaurante.getMenu().getIdMenu() + ");";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int actualizar(Restaurante restaurante) throws SQLException {
		String sql = "UPDATE restaurante SET nombre = '" + restaurante.getNombre() + "', imagen = '"
				+ restaurante.getImagen() + "', slogan = '" + restaurante.getSlogan() + "', idTipoRestaurante = "
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + ", fechaModificacion = '"
				+ restaurante.getFechaModificacion() + "', estatus = " + restaurante.isEstatus() + ", idMenu = "
				+ restaurante.getMenu().getIdMenu() + " WHERE idRestaurante = " + restaurante.getIdRestaurante() + "";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int eliminar(int idRestaurante) throws SQLException {
		String sql = "DELETE FROM restaurante WHERE idRestaurante = " + idRestaurante + " ";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public List<Restaurante> consultar() throws SQLException {
		String sql = "SELECT r.*, tr.descripcion " + 
					 "FROM restaurante r, tipo_restaurante tr " + 
					 "WHERE r.idTipoRestaurante = tr.idTipoRestaurante " + 
					 "ORDER BY nombre;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		
		if (rs != null) {
			while (rs.next()) {
				Restaurante restaurante = new Restaurante();
				restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setImagen(rs.getString("imagen"));
				restaurante.setEstatus(rs.getBoolean("estatus"));
				restaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				restaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				restaurante.setSlogan(rs.getString("slogan"));
				
				TipoRestaurante tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				restaurante.setTipoRestaurante(tipoRestaurante);
				
				restaurantes.add(restaurante);
			}
		}
		
		return restaurantes;
	}

	@Override
	public Restaurante consultarPorId(int idRestaurante) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}

}
