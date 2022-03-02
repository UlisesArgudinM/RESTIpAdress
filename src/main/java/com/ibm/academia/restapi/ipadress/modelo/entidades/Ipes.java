package com.ibm.academia.restapi.ipadress.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Ipes implements Serializable
{

	
	private Long id;
	private Integer ident;
	private String nombre;
	private String iso;
	private String moneda;
	private String cambio;
	private Date fechaCreacion; 
	
	
	private static final long serialVersionUID = -1689225630935057738L;

}
