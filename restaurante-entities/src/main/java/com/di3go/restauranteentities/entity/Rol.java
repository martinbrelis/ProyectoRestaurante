/**
 * 
 */
package com.di3go.restauranteentities.entity;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez
 * CLase que representa una entidad de rol.
 */
@Data
public class Rol extends CommonEntity {
	/**
	 * Identificador del rol.
	 */
	private Integer idRol;
	/**
	 * Nombre del rol.
	 */
	private String nombre;

}
