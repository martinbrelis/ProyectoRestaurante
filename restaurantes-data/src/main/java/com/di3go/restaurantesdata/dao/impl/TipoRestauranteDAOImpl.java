/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.TipoRestauranteDAO;
import com.di3go.restaurantesdata.enums.CodigoEnum;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Clase que implementa el CRUD y otros metodos personalizados de transacciones a la base de datos en la tabla
 * tipo_restaurante.
 */
public class TipoRestauranteDAOImpl implements TipoRestauranteDAO {
	
	@Override
	public int guardar(TipoRestaurante tipoRestaurante) throws SQLException {
	
		String sql = "INSERT INTO tipo_restaurante (descripcion, fechaCreacion, estatus) VALUES('" + tipoRestaurante.getDescripcion() + "', '" + tipoRestaurante.getFechaCreacion()+ "', " + tipoRestaurante.isEstatus() + ");";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int actualizar(TipoRestaurante tipoRestaurante) throws SQLException {
		String sql = "UPDATE tipo_restaurante SET descripcion = '" + tipoRestaurante.getDescripcion() + "', fechaModificacion = '" + tipoRestaurante.getFechaModificacion() + "', estatus = " + tipoRestaurante.isEstatus() + 
				    " WHERE idTipoRestaurante = " + tipoRestaurante.getIdTipoRestaurante() + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int eliminar(int idTipoRestaurante) throws SQLException {
		String sql = "DELETE FROM tipo_restaurante WHERE idTipoRestaurante = " + idTipoRestaurante + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public List<TipoRestaurante> consultar() throws SQLException {
		String sql = "SELECT * FROM tipo_restaurante order by descripcion;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		List<TipoRestaurante> tiposRestaurantes = new ArrayList<TipoRestaurante>();
		
		if (rs != null) {
			while (rs.next()) {
				TipoRestaurante tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				tipoRestaurante.setEstatus(rs.getBoolean("estatus"));
				
				tiposRestaurantes.add(tipoRestaurante);
			}
		}
		
		return tiposRestaurantes;
	}

	@Override
	public TipoRestaurante consultarPorId(int idTipoRestaurante) throws SQLException, RestauranteException {
		String sql = "SELECT * FROM tipo_restaurante WHERE idTipoRestaurante = " + idTipoRestaurante + ";";
		
		ResultSet rs = null;
		
		try {
			rs = ConnectionFactory.ejecutarSQLSelect(sql);	
		} catch (Exception e) {
			throw new RestauranteException("Error de sintaxis en la sentencia " + sql, CodigoEnum.SINTAXIS_SQL_ERROR_CODE);
		}
				
		TipoRestaurante tipoRestaurante = null;
		
		if (rs != null) {
			if (rs.next()) {
				tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				tipoRestaurante.setEstatus(rs.getBoolean("estatus"));	
			}			
		}
		
		return tipoRestaurante;
	}

}
