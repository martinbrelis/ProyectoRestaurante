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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import com.di3go.restauranteentities.entity.Alimento;
import com.di3go.restauranteentities.entity.Menu;
import com.di3go.restauranteentities.entity.Restaurante;
import com.di3go.restauranteentities.entity.TipoAlimento;
import com.di3go.restauranteentities.entity.TipoRestaurante;
import com.di3go.restauranteservice.services.AdminGeneralService;
import com.di3go.restauranteservice.services.AdminSucursalService;
import com.di3go.restauranteweb.session.SessionBean;
import com.di3go.restauranteweb.utils.ControllersUtil;

import lombok.Data;

/**
 * @author Diego Paniagua Lopez.
 * Clase que permite controlar el flujo de la pantalla de administracion de alimentos.
 */
@Data
@ManagedBean
@ViewScoped
public class AdminAlimentosController {
	/**
	 * Lista de alimentos a mostrar en la tabla de administracion.
	 */
	private List<Alimento> alimentos;
	/**
	 * Lista de tipos de alimentos.
	 */
	private List<TipoAlimento> tiposAlimentos;
	
	/**
	 * Objeto para guardar o actualizar un registro de alimento.
	 */
	private Alimento alimento;
	/**
	 * Archivo seleccionado con la imagen del alimento.
	 */
	private Part archivoSeleccionado;
	/**
	 * Objeto con la informacion en sesion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Direccion destino a guardar la imagen.
	 */
	private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\alien\\Documents\\CursoJavaOnline\\restaurante-web\\src\\main\\webapp\\resources\\images\\alimentos";
 	
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de alimentos.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	
	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
		this.consultarTiposAlimentosPorRestaurante();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.alimento = new Alimento();
		this.alimento.setTipoAlimento(new TipoAlimento());
		this.alimento.setMenu(new Menu());
		this.alimento.setRestaurante(new Restaurante());
	}
	/**
	 * Permite consultar la lista de alimentos.
	 */
	public void consultar() {
		try {
			this.alimentos = this.adminSucursalService.consultarAlimentosPorRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los alimentos, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite consultar la lista de tipos de alimentos del restaurante del usuario en sesion.
	 */
	public void consultarTiposAlimentosPorRestaurante() {
		try {
			this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentosPorRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al solicitar la información de los tipos de alimentos, favor de intentarlo más tarde.");
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
				this.alimento.setImagen(nombreArchivo);
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
			this.alimento.setRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante());
			this.guardarImagenDirectorio();
			int guardado = this.adminSucursalService.guardarAlimento(this.alimento);
			
			if (guardado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "El alimento " + this.alimento.getNombre() + " guardado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El alimento " + this.alimento.getNombre() + " no guardado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información del alimento a guardar, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite actualizar un restaurante.
	 */
	public void actualizar() {
		try {
			this.alimento.setRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante());
			this.guardarImagenDirectorio();
			
			int actualizado = this.adminSucursalService.actualizarAlimento(alimento);
			
			if (actualizado > 0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento " + this.alimento.getNombre() + " actualizado exitósamente");
				this.consultar();
				this.inicializarComponentes();
			} else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Alimento " + this.alimento.getNombre() + " no actualizado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la información del alimento a actualizar, favor de intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite precargar la informacion del alimento a editar.
	 * @param alimento objeto a editar.
	 */
	public void cargarInformacionModal(Alimento alimento) {
		this.alimento = alimento;
	}	
}
