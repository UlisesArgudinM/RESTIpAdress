package com.ibm.academia.restapi.ipadress.modelo.entidades;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IpAdress implements Serializable
{
	private Ipes ipes;
	
	public IpAdress(Ipes ipes)
	{
		this.ipes=ipes;
	}
	
	
	
	
	
	private static final long serialVersionUID = 8606916440020303337L;

}
