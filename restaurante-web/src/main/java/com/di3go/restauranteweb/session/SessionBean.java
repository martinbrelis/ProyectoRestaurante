/**
 * 
 */
package com.di3go.restauranteweb.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.di3go.restauranteentities.entity.Empleado;

/**
 * @author Diego Paniagua Lopez.
 * Clase de JSF que almacena la información en sesión del usuario.
 */
@ManagedBean
@SessionScoped
public class SessionBean {
	/**
	 * Objeto del empleado que inicia la sesion.
	 */
	private Empleado empleado;

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}
