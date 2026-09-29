/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.TipoAlimentoDAO;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez. Clase que implementa el CRUD de la interface de
 *         TipoAlimentoDAO.
 */
public class TipoAlimentoDAOImpl implements TipoAlimentoDAO {

	@Override
	public int guardar(TipoAlimento tipoAlimento) throws SQLException {
		String sql = "INSERT INTO tipo_alimento (descripcion, fechaCreacion, estatus) VALUES('"
				+ tipoAlimento.getDescripcion() + "', '" + tipoAlimento.getFechaCreacion() + "', "
				+ tipoAlimento.isEstatus() + ");";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int actualizar(TipoAlimento tipoAlimento) throws SQLException {
		String sql = "UPDATE tipo_alimento SET descripcion = '" + tipoAlimento.getDescripcion()
				+ "', fechaModificacion = '" + tipoAlimento.getFechaModificacion() + "', estatus = "
				+ tipoAlimento.isEstatus() + " WHERE idTipoAlimento = " + tipoAlimento.getIdTipoAlimento()
				+ ";";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int eliminar(int idTipoAlimento) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TipoAlimento> consultar() throws SQLException {
		String sql = "SELECT * FROM tipo_alimento order by descripcion;";

		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

		List<TipoAlimento> tiposAlimentos = new ArrayList<TipoAlimento>();

		if (rs != null) {
			while (rs.next()) {
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcion"));
				tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null
						? rs.getTimestamp("fechaModificacion").toLocalDateTime()
						: null);
				tipoAlimento.setEstatus(rs.getBoolean("estatus"));

				tiposAlimentos.add(tipoAlimento);
			}
		}

		return tiposAlimentos;
	}

	@Override
	public TipoAlimento consultarPorId(int idTipoAlimento) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que permite consultar los tipos de alimentos que corresponden al restaurante del usuario en sesion.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de tipos de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<TipoAlimento> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		String sql = "SELECT ta.* FROM tipo_alimento ta, restaurante_has_tipo_alimento rhta, restaurante res " + 
					"WHERE ta.idTipoAlimento = rhta.idTipoAlimento " + 
					"AND res.idRestaurante = rhta.idRestaurante " + 
					"AND res.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

		List<TipoAlimento> tiposAlimentos = new ArrayList<TipoAlimento>();

		if (rs != null) {
			while (rs.next()) {
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcion"));
				tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null
						? rs.getTimestamp("fechaModificacion").toLocalDateTime()
						: null);
				tipoAlimento.setEstatus(rs.getBoolean("estatus"));

				tiposAlimentos.add(tipoAlimento);
			}
		}

		return tiposAlimentos;
	}
	
	/**
	 * Metodo que permite guardar un tipo de alimento en el restaurante del usuario en sesion.
	 * @param tipoAlimento objeto a guardar.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return 1 en caso de ser guardado, 0 en caso de no guardado
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int guardar(TipoAlimento tipoAlimento, int idRestauranteUsuarioSesion) throws SQLException {
		String sql = "INSERT INTO tipo_alimento (descripcion, fechaCreacion, estatus) VALUES('"
				+ tipoAlimento.getDescripcion() + "', '" + tipoAlimento.getFechaCreacion() + "', "
				+ tipoAlimento.isEstatus() + ");";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		sql = "SELECT LAST_INSERT_ID();";

		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			if (rs.next()) {
				int lastIdTipoAlimento = rs.getInt(1);
				sql = "INSERT INTO restaurante_has_tipo_alimento (idRestaurante, idTipoAlimento) VALUES (" + idRestauranteUsuarioSesion + ", " + lastIdTipoAlimento + ")";
				ConnectionFactory.ejecutarSQL(sql);
			}
		}
		
		return ejecutado;
	}
	
	/**
	 * Metodo que permite consultar los tipos de alimentos del menu del restaruante.
	 * @param idMenu identificador del menu.
	 * @return lista de tipos de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia sql.
	 */
	public List<TipoAlimento> consultarTiposAlimentosPorMenu(int idMenu) throws SQLException {
		String sql = "SELECT DISTINCT ta.* " + 
					"FROM tipo_alimento ta, menu m, alimento a " + 
					"WHERE ta.idTipoAlimento = a.idTipoAlimento " + 
					"AND a.idMenu = m.idMenu " + 
					"AND m.idMenu = " + idMenu + " " + 
					"ORDER BY ta.descripcion;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		List<TipoAlimento> tiposAlimentos = new ArrayList<TipoAlimento>();
		
		if (rs != null) {
			while (rs.next()) {
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcion"));
				tipoAlimento.setEstatus(rs.getBoolean("estatus"));
				
				tiposAlimentos.add(tipoAlimento);
			}
		}
		
		return tiposAlimentos;
	}

}
