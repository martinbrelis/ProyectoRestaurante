/**
 * 
 */
package com.di3go.restaurantesdata.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

/**
 * @author Diego Paniagua Lopez.
 * Clase que se encarga de realizar la configuracion, la conexion y habilitar las transacciones
 * de una base de datos.
 */
public class ConnectionFactory {
	/**
	 * Objeto encargado de mantener la conexion de la base de datos.
	 */
	private static Connection connection;
	/**
	 * Objeto encargado de habilitar y ejecutar las sentencias SQL.
	 */
	private static Statement statement;
	/**
	 * Metodo que permite conectarse a la base de datos.
	 * @return objeto Connection con la informacion de la base de datos conectada | null.
	 * @throws ClassNotFoundException Excepcion generada al no cargar el driver de conexion.
	 * @throws SQLException Excepcion generada al no conectarse a la base de datos.
	 */
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		//Paso 1: Cargar el driver de conexion.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Paso 2: Establecer los datos de conexion.
		String url = "jdbc:mysql://localhost:3306/restaurante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + TimeZone.getDefault().getID();
		String user = "root";
		String password = "admin";
		
		//Paso 3: Establecer la conexion.
		
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement(); // Habilitando proceso para ejecutar sentencias SQL.
		
		return connection;
	}
	
	/**
	 * Permite ejecutar sentencias INSERT, UPDATE y DELETE.
	 * @param sql parametro con la sentencia a ejecutarse.
	 * @return 1 en caso de ser exitoso, 0 en caso de error.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	public static int ejecutarSQL(String sql) throws SQLException {
		System.out.println("Query: " + sql);
		return statement.executeUpdate(sql);
	}
	
	/**
	 * Permite ejecutar sentencias SELECT.
	 * @param sql parametro con la sentencia a ejecutarse.
	 * @return objeto con la informacion obtenida de la sentencia SELECT:
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	public static ResultSet ejecutarSQLSelect(String sql) throws SQLException {
		System.out.println("Query: " + sql);
		return statement.executeQuery(sql);
	}
		
}
