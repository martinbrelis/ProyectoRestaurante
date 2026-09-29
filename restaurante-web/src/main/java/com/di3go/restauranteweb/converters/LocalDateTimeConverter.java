/**
 * 
 */
package com.di3go.restauranteweb.converters;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author Diego Paniagua Lopez.
 * Converter de fechas de tipo Localdatetime.
 */
@ManagedBean
@ViewScoped
public class LocalDateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return LocalDateTime.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		LocalDateTime localDateTime = (LocalDateTime) value;
		
		Date fecha = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String fechaConverter = sdf.format(fecha);
		return fechaConverter;
	}

}
