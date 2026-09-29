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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.DragDropEvent;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.Menu;
import com.di3go.restauranteservice.services.AdminSucursalService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase controller que permite la comunicacion con la pantalla de adminmenu.xhtml.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminMenuController {
	
	 private static final Logger logger = LogManager.getLogger(AdminMenuController.class);
	/**
	 * Lista de alimentos disponibles a realizar con draggable.
	 */
	private List<Alimento> alimentosDisponibles;
	/**
	 * Lista de alimentos asignados al menu a realizar con dropable.
	 */
	private List<Alimento> alimentosAsignados;
	/**
	 * Menu del restaurante del usuario.
	 */
	private Menu menu;
	/**
	 * Objeto que permite realizar la logica de negocio para las pantallas del administrador de sucursal o restaurante.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	/**
	 * Objeto que contiene la informacion del usuario en sesion.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Metodo que inicializa la ventana de administracion de menu.
	 */
	@PostConstruct
	public void init() {
		this.menu = null;
		this.consultar();
	}
	
	/**
	 * Metodo que permite consultar la informacion de los alimentos dispobibles y asignados.
	 */
	public void consultar() {
		
		logger.info("Consultando los alimentos disponibles y asignados al menú del restaurante...");
		
		int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
		
		// ::::::::::::: ALIMENTOS DISPONIBLES:::::::::
		
		try {
			this.alimentosDisponibles = this.adminSucursalService.consultarAlimentosDisponiblesRestaurante(idRestauranteUsuarioSesion);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un error al procesar la informacion de alimentos disponibles, favor de intentar más tarde");
			e.printStackTrace();
		}
		
		// ::::::::::::: ALIMENTOS ASIGNADOS:::::::::
		
		try {
			
			this.menu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu();
			
			this.alimentosAsignados = this.adminSucursalService.consultarAlimentosAsignadosMenu(this.menu.getIdMenu());
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un error al procesar la informacion de alimentos disponibles, favor de intentar más tarde");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite asignar el alimento al droppable.
	 * @param alimentoEvent evento con el alimento a asignar al menu.
	 */
	public void asignarAlimentosMenu(DragDropEvent<Alimento> alimentoEvent) {
		Alimento alimentoSeleccionado = alimentoEvent.getData();
		
		this.alimentosDisponibles.remove(alimentoSeleccionado);
		this.alimentosAsignados.add(alimentoSeleccionado);
		
		//:::::::::::::::::::::: GUARDAR ALIMENTO ASIGNADO AL MENU ::::::::::::::::::::::
		
		try {
			int asignado = this.adminSucursalService.asignarAlimentoMenu(alimentoSeleccionado.getIdAlimento(), this.menu.getIdMenu());
			
			if (asignado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento " + alimentoSeleccionado.getNombre() + " asignado al menú exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Alimento " + alimentoSeleccionado.getNombre() + " no asignado al menú.");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la asignación del alimento al menú, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo que permite quitar un alimento del menu.
	 * @param alimento objeto a quitar del menu.
	 */
	public void quitarAlimentoMenu(Alimento alimento) {
		try {
			int removido = this.adminSucursalService.quitarAlimentoMenu(alimento.getIdAlimento());
			
			if (removido > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento " + alimento.getNombre() + " ha sido removido del menú exitósamente");
				this.consultar();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Alimento " + alimento.getNombre() + " no se removió del menú.");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al tratar de remover el alimento del menú, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}

}
