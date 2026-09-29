/**
 * 
 */
package com.di3go.restauranteweb.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restauranteentities.entity.Rol;
import com.di3go.restauranteentities.entity.Sucursal;
import com.di3go.restauranteservice.services.AdminSucursalService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase controller que interactua con la pantalla de adminempleados.xhtml.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminEmpleadosController {
	/**
	 * Lista de empleados a mostrarse en la administracion.
	 */
	private List<Empleado> empleados;
	/**
	 * Objeto a guardar o actualizar.
	 */
	private Empleado empleado;
	/**
	 * Lista de roles a mostrarse en combo de la ventana modal.
	 */
	private List<Rol> roles;
	/**
	 * Objeto de logica de negocio para el usuario administrador de sucursal o restaurante.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	/**
	 * Objeto que contiene la informacion en sesion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Inicializa la informacion de la pantalla de adminempleados.
	 */
	@PostConstruct
	public void init() {
		this.consultar();
		this.consultarRoles();
		this.inicializarComponentes();
	}
	/**
	 * Metodo que permite inicializar el empleado a guardar o actualizar.
	 */
	public void inicializarComponentes() {
		this.empleado = new Empleado();
		this.empleado.setRol(new Rol());
		this.empleado.setSucursal(new Sucursal());
	}
	/**
	 * Metodo que permite consultar la lista de roles para el combo de la ventana modal.
	 */
	public void consultarRoles() {
		try {
			this.roles = this.adminSucursalService.consultarRolesSinAdminGeneral();
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para consultar roles, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite consultar los empleadosm de la sucursal.
	 */
	public void consultar() {
		
		int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
		
		try {
			this.empleados = this.adminSucursalService.consultarEmpleadosPorRestaurante(idRestauranteUsuarioSesion);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para consultar empleados, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar un empleado.
	 */
	public void guardar() {
		
		Sucursal sucursalSesion = this.sessionBean.getEmpleado().getSucursal();
		this.empleado.setSucursal(sucursalSesion);
		
		try {
			int guardado = this.adminSucursalService.guardarEmpleado(this.empleado);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Empleado " + this.empleado.getNombre() + " guardado exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Empleado " + this.empleado.getNombre() + " no guardado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para guardar un empleado, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite actualizar un empleado.
	 */
	public void actualizar() {
		try {
			int actualizar = this.adminSucursalService.actualizarEmpleado(this.empleado);
			
			if (actualizar > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Empleado " + this.empleado.getNombre() + " actualizado exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Empleado " + this.empleado.getNombre() + " no actualizado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para actualizar un empleado, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que precarga la informacion del empleado seleccionado a actualizar.
	 * @param empleado objeto seleccionado a actualizar.
	 */
	public void cargarInformacionModal(Empleado empleado) {
		this.empleado = empleado;
	}

}
