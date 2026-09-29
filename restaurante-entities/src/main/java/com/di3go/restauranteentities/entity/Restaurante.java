/**
 * 
 */
package com.di3go.restauranteentities.entity;

import lombok.Data;

/**
 * @author DIego Paniagua Lopez. Clase que representa una entidad de restaurante
 *         en la base de datos.
 */
@Data
public class Restaurante extends CommonEntity {
	/**
	 * Identificador del restaurante.
	 */
	private Integer idRestaurante;
	/**
	 * Nombre del restaurante.
	 */
	private String nombre;
	/**
	 * Imagen del restaurante,
	 */
	private String imagen;
	/**
	 * Slogan del restaurante.
	 */
	private String slogan;
	/**
	 * Tipo de restaurante.
	 */
	private TipoRestaurante tipoRestaurante;
	/**
	 * Menu del restaurante.
	 */
	private Menu menu;

}
