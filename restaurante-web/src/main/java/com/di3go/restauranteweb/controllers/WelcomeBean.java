/**
 * 
 */
package com.di3go.restauranteweb.controllers;

import javax.faces.bean.ManagedBean;

/**
 * @author alien
 *
 */
@ManagedBean
public class WelcomeBean {
	
	public WelcomeBean() {
		System.out.println("WelcomeBean instantiated");
	}
    
	public String getMessage() {
        return "I'm alive!";
    }
}
