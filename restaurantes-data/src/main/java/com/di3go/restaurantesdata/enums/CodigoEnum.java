/**
 * 
 */
package com.di3go.restaurantesdata.enums;

/**
 * @author Diego Paniagua Lopez.
 * Enumeracion que contiene los codigos de error del aplicativo.
 */
public enum CodigoEnum {
	/**
	 * CONSTANTE CON EL CODIGO DE ERROR DE SINTAXIS
	 */
	SINTAXIS_SQL_ERROR_CODE(1064),
	/**
	 * CONSTANTE CON EL CODIGO DE ERROR DE CLASE NO ENCONTRADA.
	 */
	CLASE_NO_ENCONTRADA(1000);
	/**
	 * Codigo de error.
	 */
	private int code;

	/**
	 * Constructor privado default de la enumeracion
	 * @param code codigo del error generado.
	 */
	private CodigoEnum(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
