/**
 * 
 */
package com.di3go.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restaurantesdata.connection.ConnectionFactory;
import com.di3go.restaurantesdata.dao.AlimentoDAO;
import com.di3go.restaurantesdata.myexceptions.RestauranteException;

/**
 * @author Diego Paniagua Lopez.
 * Clase que implementa el CRUD de transacciones para la tabla de alimento.
 */
public class AlimentoDAOImpl implements AlimentoDAO {

	@Override
	public int guardar(Alimento alimento) throws SQLException {
		String sql = "INSERT INTO alimento (nombre, imagen, descripcion, idTipoAlimento, descuento, precio, fechaCreacion, estatus, idRestaurante) "
				   + "VALUES ('" + alimento.getNombre() + "', '" + alimento.getImagen() + "', '"+alimento.getDescripcion()+"', " + alimento.getTipoAlimento().getIdTipoAlimento() + ", "
				   + "" + alimento.getDescuento() + ", " + alimento.getPrecio() + ", '" + alimento.getFechaCreacion() + "', " + alimento.isEstatus() + ", " + alimento.getRestaurante().getIdRestaurante() + ");";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int actualizar(Alimento alimento) throws SQLException {
		String sql = "UPDATE alimento SET nombre = '" + alimento.getNombre() + "', imagen = '" + alimento.getImagen() + "', descripcion = '" + alimento.getDescripcion() + "', idTipoAlimento = " + alimento.getTipoAlimento().getIdTipoAlimento() + ", descuento = " + alimento.getDescuento() + ", precio = " + alimento.getPrecio() + ", fechaModificacion = '" + alimento.getFechaModificacion() + "', estatus = " + alimento.isEstatus() + " " + 
					 "WHERE idAlimento = " + alimento.getIdAlimento() + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int eliminar(int idAlimento) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Alimento> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alimento consultarPorId(int idAlimento) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que permnite consultar los alimentos del restaurante del usuario en sesion.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de alimentos.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Alimento> consultarPorIdRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		String sql = "SELECT a.*, ta.descripcion as descripcionTipo FROM alimento a, restaurante r, tipo_alimento ta, restaurante_has_tipo_alimento rha " + 
					"WHERE a.idRestaurante = r.idRestaurante " + 
					"AND a.idTipoAlimento = ta.idTipoAlimento " + 
					"AND r.idRestaurante = rha.idRestaurante " + 
					"AND ta.idTipoAlimento = rha.idTipoAlimento " + 
					"AND r.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		List<Alimento> alimentos = new ArrayList<Alimento>();
		
		if (rs != null) {
			while (rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
				
				alimento.setTipoAlimento(tipoAlimento);
				
				alimentos.add(alimento);
				
			}
		}
		
		return alimentos;
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos disponibles del restaurante no asignados al menu.
	 * @param idRestauranteUsuarioSesion identificador del restaurante.
	 * @return lista de restaurantes disponibles.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQl.
	 */
	public List<Alimento> consultarAlimentosDisponibles(int idRestauranteUsuarioSesion) throws SQLException {
		List<Alimento> alimentosDB = new ArrayList<Alimento>();
		
		String sql = "SELECT a.*, ta.descripcion as descripcionTipo " + 
					"FROM alimento a, tipo_alimento ta, restaurante res " + 
					"WHERE a.idTipoAlimento = ta.idTipoAlimento " + 
					"AND a.idRestaurante = res.idRestaurante " + 
					"AND a.idMenu IS NULL " + 
					"AND a.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
				
				alimento.setTipoAlimento(tipoAlimento);
				
				alimentosDB.add(alimento);
				
			}
		}
		
		return alimentosDB;
	}

	/**
	 * Metodo que permite consultar la lista de alimentos asignados al menu del restaurante.
	 * @param idMenu identificador del menu del restaurante.
	 * @return lista de restaurantes asignados.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQl.
	 */
	public List<Alimento> consultarAlimentosAsignados(Integer idMenu) throws SQLException {
		List<Alimento> alimentosDB = new ArrayList<Alimento>();
		
		String sql = "SELECT a.*, ta.descripcion as descripcionTipo " + 
					"FROM alimento a, tipo_alimento ta, restaurante res " + 
					"WHERE a.idTipoAlimento = ta.idTipoAlimento " + 
					"AND a.idRestaurante = res.idRestaurante " + 
					"AND a.idMenu = " + idMenu + " ";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
				
				alimento.setTipoAlimento(tipoAlimento);
				
				alimentosDB.add(alimento);
				
			}
		}
		
		return alimentosDB;
	}
	
	/**
	 * Metodo que permite asignar un alimento al menu del restaurante.
	 * @param idAlimentoSeleccionado identificador del alimento a  asignar al menu
	 * @param idMenu identificador del menu.
	 * @return 1 en caso de asignado, 0 en caso de no asignado.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int asignarAlimentoMenu(int idAlimentoSeleccionado, int idMenu) throws SQLException {
		String sql = "UPDATE alimento SET idMenu = " + idMenu + " "
				   + "WHERE idAlimento = " + idAlimentoSeleccionado + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}
	
	/**
	 * Metodo que permite quitar un alimento del menu del restaurante.
	 * @param idAlimentoSeleccionado identificador del alimento seleccionado.
	 * @return 1 en caso de removido, 0 en caso de no removido.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public int quitarAlimentoMenu(int idAlimentoSeleccionado) throws SQLException {
		String sql = "UPDATE alimento SET idMenu = NULL "
				   + "WHERE idAlimento = " + idAlimentoSeleccionado + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;		
	}
	
	/**
	 * Metodo que permite consultar los alimentos por tipo de alimento y su menu correspondiente.
	 * @param idTipoAlimento identificador del tipo de alimento.
	 * @param idMenu identificador del menu del restaurante.
	 * @return lista de alimentos.
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
	 */
	public List<Alimento> consultarAlimentosPorTipoAndMenu(int idTipoAlimento, int idMenu) throws SQLException {
		String sql = "SELECT a.* FROM alimento a, tipo_alimento ta " + 
					"WHERE a.idTipoAlimento = ta.idTipoAlimento " + 
					"AND a.idTipoAlimento = " + idTipoAlimento + " " + 
					"AND a.idMenu = " + idMenu + "";
		
		List<Alimento> alimentosDB = new ArrayList<Alimento>();
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if (rs != null) {
			while (rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setImagen(rs.getString("imagen"));
				
				alimentosDB.add(alimento);
			}
		}
		
		return alimentosDB;
	}
}
