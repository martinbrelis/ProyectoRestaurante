/**
 * 
 */
package com.di3go.restauranteweb.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restauranteservice.services.LoginService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase Bean de JSF que se comunica con la pantalla login.xhtml.
 * NOTA: Siempre agregar @ManagedBean a una clase para realizar este proceso.
 */
@Data
@ManagedBean
@ViewScoped
public class LoginController {
	/**
	 * Usuario capturado por el usuario final
	 */
	private String username;
	/**
	 * Contraseña capturada por el usuario final.
	 */
	private String password;
	/**
	 * Indicador para saber si el usuario a ingresar es un administrador general.
	 */
	private boolean esSuperAdminGeneral;
	/**
	 * Objeto que mantiene la informacion en sesion del aplicativo.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	/**
	 * Objeto para obtener la informacion de la logica de negocio del login.
	 */
	private LoginService loginService = new LoginService();
	
	
	public void entrar() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar tu solicitud");
			e1.printStackTrace();
		}
		
		try {
			Empleado empleadoConsultado = this.loginService.consultarPorUsuarioYPassword(this.username, this.password, esSuperAdminGeneral);
			
			if (empleadoConsultado != null) {
				if (empleadoConsultado.isEstatus()) {
					
					if (empleadoConsultado.isSuperadmingeneral()) {
						ControllersUtil.redireccionar("/pages/admin/adminrestaurantes.xhtml");
					} else if (empleadoConsultado.isSuperadmin()) {
						ControllersUtil.redireccionar("/pages/admin/adminmenu.xhtml");
					} else {
						ControllersUtil.redireccionar("/pages/empleado/menuempleado.xhtml");
					}
					
					this.sessionBean.setEmpleado(empleadoConsultado);
					
				} else {
					ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El usuario esta deshabilitado del sistema.");
				}
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El usuario y/o contraseña son incorrectos");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar tu solicitud, favor de intentarlo más tarde");
			e.printStackTrace();
		} catch (IOException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un problema al acceder a tu pantalla de inicio, favor de intentarlo más tarde");
			e.printStackTrace();
		}
	}
}
