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

import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restauranteservice.services.AdminGeneralService;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que permite controlar el flujo de la pantalla de administracion de tipos de restaurantes.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminTiposRestaurantesController {
	/**
	 * Lista de tipos de restaurantes que muestra la informacion en el datatable.
	 */
	private List<TipoRestaurante> tiposRestaurantes;
	/**
	 * Objeto para guardar, actualizar, eliminar.
	 */
	private TipoRestaurante tipoRestaurante;
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de tipos de restaurantes.
	 */
	private AdminGeneralService adminGeneralService = new AdminGeneralService();
	
	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.tipoRestaurante = new TipoRestaurante();
	}
	/**
	 * Permite consultar la lista de tipos de restaurantes.
	 */
	public void consultar() {
		try {
			this.tiposRestaurantes = this.adminGeneralService.consultarTiposRestaurantes();
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de restaurantes, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar un tipo de restaurante.
	 */
	public void guardar() {
		System.out.println("Ejecutando accion para guardar....");
		
		try {
			int guardado = this.adminGeneralService.guardarTipoRestaurante(this.tipoRestaurante);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " guardado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " no se guardó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de restaurantes, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite precargar la informacion del tipo de restaurante a editar.
	 * @param tipoRestaurante objeto a editar.
	 */
	public void cargarInformacionModal(TipoRestaurante tipoRestaurante) {
		this.tipoRestaurante = tipoRestaurante;
	}
	
	/**
	 * Metodo que permite actualizar un tipo de restaurante.
	 */
	public void actualizar() {
		System.out.println("Ejecutando accion para actualizar....");
		
		try {
			int actualizado = this.adminGeneralService.actualizarTipoRestaurante(this.tipoRestaurante);
			
			if (actualizado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " actualizado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " no se actualizó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de restaurantes, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite eliminar un tipo de restaurante.
	 */
	public void eliminar() {
		System.out.println("Ejecutando accion para eliminar....");
		
		try {
			int eliminado = this.adminGeneralService.eliminarTipoRestaurante(this.tipoRestaurante.getIdTipoRestaurante());
			
			if (eliminado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " eliminado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " no se eliminó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de restaurantes, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}

}
