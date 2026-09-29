/**
 * 
 */
package com.di3go.restauranteentities.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que contiene campos que se reutilizan en todas las clases que mappean a las tablas.
 */
@Data
public class CommonEntity {

	/**
	 * Fecha de creacion del tipo de restaurante.
	 */
	private LocalDateTime fechaCreacion;
	/**
	 * Fecha de modificacion del tipo de restaurante.
	 */
	private LocalDateTime fechaModificacion;
	/**
	 * Estatus habilitado o deshabilitado del tipo de restaurante.
	 */
	private boolean estatus;

}
