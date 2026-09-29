/**
 * 
 */
package com.di3go.restauranteweb.session;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.di3go.restauranteweb.utils.ControllersUtil;

/**
 * @author Diego Paniagua Lopez.
 * Clase que permite cerrar la sesion del usuario.
 */
@ManagedBean
@ViewScoped
public class SessionClosedBean {
	/**
	 * Metodo que permite cerrar la sesion del usuario.
	 */
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		try {
			ControllersUtil.redireccionar("/login.xhtml");
		} catch (IOException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al redigirte a la pantalla de login, favor de volver la pantalla de inicio.");
			e.printStackTrace();
		}
	}
}
