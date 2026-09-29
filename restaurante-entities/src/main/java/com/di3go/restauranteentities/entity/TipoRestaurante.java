/**
 * 
 */
package com.di3go.restauranteentities.entity;

import java.util.List;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que representa una entidad de tipo restaurante en la base de datos.
 */
@Data
public class TipoRestaurante extends CommonEntity {
	/**
	 * Identificador del tipo de restaurante.
	 */
	private Integer idTipoRestaurante;
	/**
	 * Descripcion del tipo de restaurante.
	 */
	private String descripcion;
	/**
	 * Restaurantes que pertenecen a un tipo de restaurante.
	 */
	private List<Restaurante> restaurantes;

}
