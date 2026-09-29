/**
 * 
 */
package com.di3go.restauranteweb.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restauranteservice.services.AdminGeneralService;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase controller que permite la comunicacion con la pantalla adminperfiles.xhtml
 */
@Data
@ManagedBean
@ViewScoped
public class AdminPerfilesController {
	/**
	 * Lista de roles a mostrar en el datatable de la pantalla.
	 */
	private List<Rol> roles;
	/**
	 * Objeto a guardar o actualizar.
	 */
	private Rol rol;
	/**
	 * Objeto que permite realizar la logica de negocio para el usuario de administrador general.
	 */
	private AdminGeneralService adminGeneralService = new AdminGeneralService();
	/**
	 * Metodo que permite inicializar la pantalla del administrador de rol.
	 */
	@PostConstruct
	public void init() {
		this.consultar();
		this.inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		this.rol = new Rol();
	}
	/**
	 * Metodo que permite consultar la lista de roles.
	 */
	public void consultar() {
		try {
			this.roles = this.adminGeneralService.consultarRoles();
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información para consultar los roles, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar un rol.
	 */
	public void guardar() {
		
		try {
			int guardado = this.adminGeneralService.guardarRol(this.rol);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "El rol " + this.rol.getNombre() + " guardado exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El rol " + this.rol.getNombre() + " no se guardó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información para guardar el rol, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite precargar la informacion del rol a actualizar en la ventana modal.
	 * @param rol
	 */
	public void cargarInformacionModal(Rol rol) {
		this.rol = rol;
	}
	
	/**
	 * Metodo que permite actualizar el perfil seleccionado.
	 */
	public void actualizar() {
		try {
			int actualizado = this.adminGeneralService.actualizarRol(this.rol);
			
			if (actualizado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "El rol " + this.rol.getNombre() + " actualizado exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El rol " + this.rol.getNombre() + " no se actualizó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información para actualizar el rol, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}

}
