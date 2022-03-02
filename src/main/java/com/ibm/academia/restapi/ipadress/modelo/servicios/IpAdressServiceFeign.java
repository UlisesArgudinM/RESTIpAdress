package com.ibm.academia.restapi.ipadress.modelo.servicios;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ipadress.clientes.IpAdressClienteRest;
import com.ibm.academia.restapi.ipadress.modelo.entidades.IpAdress;

@Service("serviceFeign")
public class IpAdressServiceFeign implements IIpAdressService
{
	@Autowired
	private IpAdressClienteRest clienteFeign;

	@Override
	public List<IpAdress> buscarTodos() {
		
		return clienteFeign.consultarTodosIps().stream().map(p -> new IpAdress(p)).collect(Collectors.toList());
	}

	@Override
	public IpAdress buscarPorIp(Integer ident) {
		
		return new IpAdress (clienteFeign.consultarDetalleIpes(ident, 0, 0, 0));
	}
	
	
}
