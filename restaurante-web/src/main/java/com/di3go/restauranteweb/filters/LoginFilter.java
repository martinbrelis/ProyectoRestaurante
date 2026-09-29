/**
 * 
 */
package com.di3go.restauranteweb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.di3go.restauranteentities.entity.Empleado;
import com.di3go.restauranteweb.session.SessionBean;

/**
 * @author Diego Paniagua Lopez.
 * Clase Filtro que permite verificar la sesion del usuario y en caso de no estar en sesion, se le redirige a la pnatalla de login.
 */
@WebFilter
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("sessionBean") == null) {
			response.sendRedirect(request.getContextPath() + "/login.xhtml");
		} else {
			if (session.getAttribute("sessionBean") != null) {
				SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
				Empleado empleado = sessionBean.getEmpleado();
				
				if (empleado == null) {
					response.sendRedirect(request.getContextPath() + "/login.xhtml");
					return;
				}
			}
			
			chain.doFilter(req, res);
		}
	}

}
