/**
 * 
 */
package com.di3go.restauranteentities.entity;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que representa una entidad de la tabla de sucursal.
 */
@Data
public class Sucursal extends CommonEntity {
	/**
	 * Identificador de la sucursal.
	 */
	private Integer idSucursal;
	/**
	 * Nombre de la sucursal.
	 */
	private String nombre;
	/**
	 * Restaurante del cual pertenece.
	 */
	private Restaurante restaurante;

}
