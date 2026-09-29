/**
 * 
 */
package com.di3go.restauranteentities.entity;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que representa una entidad de menu de la base de datos.
 */
@Data
public class Menu extends CommonEntity {
	/**
	 * Identificador del menu.
	 */
	private Integer idMenu;
	/**
	 * Clave del menu.
	 */
	private String clave;
	/**
	 * Descripcion del menu.
	 */
	private String descripcion;
}
