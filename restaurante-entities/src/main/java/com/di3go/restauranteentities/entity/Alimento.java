/**
 * 
 */
package com.di3go.restauranteentities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Diego Paniagua Lopez.
 * Clase que representa una entidad de la tabla alimento.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Alimento extends CommonEntity {
	/**
	 * Identificador del alimento.
	 */
	private Integer idAlimento;
	/**
	 * Nombre del alimento.
	 */
	private String nombre;
	/**
	 * Imagen del alimento.
	 */
	private String imagen;
	/**
	 * Descripcion del alimento.
	 */
	private String descripcion;
	/**
	 * Tipo de alimento al que pertence.
	 */
	private TipoAlimento tipoAlimento;
	/**
	 * Descuento del alimento.
	 */
	private double descuento;
	/**
	 * Precio unitario.
	 */
	private double precio;
	/**
	 * Restaurante donde se encuentra el alimento.
	 */
	private Restaurante restaurante;
	/**
	 * Menu al que se asigna el alimento.
	 */
	private Menu menu;

	
}
