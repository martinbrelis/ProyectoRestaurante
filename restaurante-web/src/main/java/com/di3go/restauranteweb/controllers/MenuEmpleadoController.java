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

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restauranteservice.services.AdminSucursalService;
import com.di3go.restauranteservice.services.EmpleadoService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase controller que permite interactuar con la pantalla de menuempleado.xhtml.
 */
@Data
@ManagedBean
@ViewScoped
public class MenuEmpleadoController {
	/**
	 * Lista de tipos de alimentos a mostrar en el menu con sus respectivos alimentos. 
	 */
	private List<TipoAlimento> tiposAlimentos;
	/**
	 * Objeto que realiza la logica de negocio para los empleados.
	 */
	private EmpleadoService empleadoService = new EmpleadoService();
	/**
	 * Objeto que contiene la informacion en sesion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	/**
	 * Metodo que permite inicializar la pantalla de menu del empleado.
	 */
	@PostConstruct
	public void init() {
		this.consultar();
	}
	
	/**
	 * Metodo que permite consultar los alimentos del menu.
	 */
	public void consultar() {
		
		int idMenu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu().getIdMenu();
		
		try {
			this.tiposAlimentos = this.empleadoService.consultarAlimentosPorMenu(idMenu);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al mostrar los tipos de alimentos del menu del restaurante.");
			e.printStackTrace();
		}
	}
}
