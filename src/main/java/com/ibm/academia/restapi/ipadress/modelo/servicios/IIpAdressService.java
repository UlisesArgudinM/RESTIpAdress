package com.ibm.academia.restapi.ipadress.modelo.servicios;

import java.util.List;

import com.ibm.academia.restapi.ipadress.modelo.entidades.IpAdress;

public interface IIpAdressService
{
	public List<IpAdress>buscarTodos();
	public IpAdress buscarPorIp(Integer ident);
}
