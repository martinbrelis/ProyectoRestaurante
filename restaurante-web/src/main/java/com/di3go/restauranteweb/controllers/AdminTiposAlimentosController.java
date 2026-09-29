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

import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restauranteservice.services.AdminGeneralService;
import com.di3go.restauranteservice.services.AdminSucursalService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que permite controlar el flujo de la pantalla de administracion de tipos de alimentos.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminTiposAlimentosController {
	/**
	 * Lista de tipos de aliemntos que muestra la informacion en el datatable.
	 */
	private List<TipoAlimento> tiposAlimentos;
	/**
	 * Objeto para guardar o actualizar tipo de alimento.
	 */
	private TipoAlimento tipoAlimento;
	/**
	 * Objeto que contiene la informacion en sesion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de tipos de alimentos.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	
	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.tipoAlimento = new TipoAlimento();
	}
	/**
	 * Permite consultar la lista de tipos de alimentos.
	 */
	public void consultar() {
		try {
			
			int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
			
			this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentosPorRestaurante(idRestauranteUsuarioSesion);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de restaurantes, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar un tipo de alimento.
	 */
	public void guardar() {
		System.out.println("Ejecutando accion para guardar....");
		
		try {
			
			int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
			
			int guardado = this.adminSucursalService.guardarTipoAlimentoRestaurante(this.tipoAlimento, idRestauranteUsuarioSesion);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de alimento " + this.tipoAlimento.getDescripcion() + " guardado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de alimento " + this.tipoAlimento.getDescripcion() + " no se guardó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de alimentos, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite precargar la informacion del tipo de alimento a editar.
	 * @param tipoAlimento objeto a editar.
	 */
	public void cargarInformacionModal(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}
	
	/**
	 * Metodo que permite actualizar un tipo de restaurante.
	 */
	public void actualizar() {
		System.out.println("Ejecutando accion para actualizar....");
		
		try {
			int actualizado = this.adminSucursalService.actualizarTipoAlimento(this.tipoAlimento);
			
			if (actualizado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de alimento " + this.tipoAlimento.getDescripcion() + " actualizado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de alimento " + this.tipoAlimento.getDescripcion() + " no se actualizó");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de alimentos, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
}
