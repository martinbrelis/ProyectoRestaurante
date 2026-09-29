/**
 * 
 */
package com.di3go.restauranteentities.entity;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 *
 */
@Data
public class Empleado extends CommonEntity {
	/**
	 * Identificador del empleado.
	 */
	private Integer idEmpleado;
	/**
	 * Nombre del empleado
	 */
	private String nombre;
	/**
	 * Primer apellido del empleado.
	 */
	private String primerApellido;
	/**
	 * Segundo apellido del empleado.
	 */
	private String segundoApellido;
	/**
	 * Rol del empleado.
	 */
	private Rol rol;
	/**
	 * Sucursal del empleado
	 */
	private Sucursal sucursal;
	/**
	 * Usuario del empleado
	 */
	private String usuario;
	/**
	 * Contraseña del empleado
	 */
	private String password;
	/**
	 * Email del empleado.
	 */
	private String email;
	/**
	 * rol superadmin
	 */
	private boolean superadmin;
	/**
	 * rol superadmingeneral.
	 */
	private boolean superadmingeneral;
	
}
