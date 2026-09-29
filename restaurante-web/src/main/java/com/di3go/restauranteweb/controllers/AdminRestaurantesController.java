/**
 * 
 */
package com.di3go.restauranteweb.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import com.di3go.restauranteentities.entity.Menu;
import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restauranteservice.services.AdminGeneralService;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que permite controlar el flujo de la pantalla de administracion de restaurantes.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminRestaurantesController {
	/**
	 * Lista de restaurantes a mostrar en la tabla de administracion.
	 */
	private List<Restaurante> restaurantes;
	/**
	 * Objeto para guardar o actualizar un registro de restaurante.
	 */
	private Restaurante restaurante;
	/**
	 * Archivo seleccionado con la imagen del restaurante.
	 */
	private Part archivoSeleccionado;
	/**
	 * Direccion destino a guardar la imagen.
	 */
	private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\alien\\Documents\\CursoJavaOnline\\restaurante-web\\src\\main\\webapp\\resources\\images\\restaurantes";
 	
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de  restaurantes.
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
		this.restaurante = new Restaurante();
		this.restaurante.setTipoRestaurante(new TipoRestaurante());
		this.restaurante.setMenu(new Menu());
	}
	/**
	 * Permite consultar la lista de restaurantes.
	 */
	public void consultar() {
		try {
			this.restaurantes = this.adminGeneralService.consultarRestaurantes();
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de restaurantes, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que permite guardar la imagen en un directorio.
	 */
	public void guardarImagenDirectorio() {
		if (this.archivoSeleccionado != null) {
			try {
				InputStream inputStream = this.archivoSeleccionado.getInputStream();
				String nombreArchivo = this.archivoSeleccionado.getSubmittedFileName();
				Files.copy(inputStream, new File(PATH_DESTINO_ARCHIVO, nombreArchivo).toPath());
				this.restaurante.setImagen(nombreArchivo);
			} catch (IOException e) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al copiar la imagen en el directorio, favor de intentarlo más tarde.");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que permite guardar un restaurante.
	 */
	public void guardar() {
		try {
			this.guardarImagenDirectorio();
			int guardado = this.adminGeneralService.guardarRestaurante(this.restaurante);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Restaurante " + this.restaurante.getNombre() + " guardado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Restaurante " + this.restaurante.getNombre() + " no guardado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información del restaurante, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite actualizar un restaurante.
	 */
	public void actualizar() {
		try {
			this.guardarImagenDirectorio();
			int actualizado = this.adminGeneralService.actualizarRestaurante(this.restaurante);
			
			if (actualizado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Restaurante " + this.restaurante.getNombre() + " actualizado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Restaurante " + this.restaurante.getNombre() + " no actualizado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información del restaurante, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite precargar la informacion del restaurante a editar.
	 * @param restaurante objeto a editar.
	 */
	public void cargarInformacionModal(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

}
