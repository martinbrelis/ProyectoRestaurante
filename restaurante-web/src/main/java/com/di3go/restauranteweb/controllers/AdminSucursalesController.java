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

import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.Sucursal;
import com.di3go.restauranteservice.services.AdminSucursalService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase controller que permite la comunicacion con la pantalla de adminsucursales.xhtml.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminSucursalesController {
	/**
	 * Lista de sucursales a mostrar en el datatable de la pantalla.
	 */
	private List<Sucursal> sucursales;
	/**
	 * Objeto que contiene la informacion en sesion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Objeto para guardar o editar una sucursal.
	 */
	private Sucursal sucursal;
	/*
	 * Objeto que permite realizar la lofica de negocio para el administrador de sucursal o restaurante.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	/**
	 * Metodo que permite inicializar la informacion de la pantalla de adminsucursales.xhtml.
	 */
	@PostConstruct
	public void init() {
		this.consultar();
		this.inicializarComponentes();
	}
	
	/**
	 * Metodo que permite consultar la lista de sucursales del restaurante del usuario en sesion.
	 */
	public void consultar() {
		
		int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
		
		try {
			this.sucursales = this.adminSucursalService.consultarSucursalesPorRestaurante(idRestauranteUsuarioSesion);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información para consultar las sucursales, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar una sucursal.
	 */
	public void guardar() {
		Restaurante restaurante = this.sessionBean.getEmpleado().getSucursal().getRestaurante();
		
		this.sucursal.setRestaurante(restaurante);

		try {
			int guardado = this.adminSucursalService.guardarSucursal(sucursal);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "La sucursal " + sucursal.getNombre() + " guardada exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "La sucursal " + sucursal.getNombre() + " no fué guardada");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información para guardar la sucursal, favor de intentar más tarde.");
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que permite precargar la informacion de la sucursal seleccionada en la ventana modal para actualizar.
	 * @param sucursal
	 */
	public void cargarInformacionModal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	/**
	 * Metodo que permite guardar una sucursal.
	 */
	public void actualizar() {
		try {
			int actualizado = this.adminSucursalService.actualizarSucursal(sucursal);
			
			if (actualizado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "La sucursal " + sucursal.getNombre() + " actualizada exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "La sucursal " + sucursal.getNombre() + " no fué actualizada");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información para actualizar la sucursal, favor de intentar más tarde.");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Permite inicializar los componentes de la pantalla de sucursales.
	 */
	public void inicializarComponentes() {
		this.sucursal = new Sucursal();
		this.sucursal.setRestaurante(new Restaurante());
	}

}
