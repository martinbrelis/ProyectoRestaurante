/**
 * 
 */
package com.di3go.restauranteentities.entity;

import java.util.List;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que representa una entidad de la tabla tipo de alimento.
 */
@Data
public class TipoAlimento extends CommonEntity {
	/**
	 * Identificador del tipo de alimento.
	 */
	private Integer idTipoAlimento;
	/**
	 * descripcion del tipo de alimento.
	 */
	private String descripcion;
	/**
	 * Lista de alimentos que contiene el tipo.
	 */
	private List<Alimento> alimentos;

}
