package com.ibm.academia.restapi.ipadress.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.academia.restapi.ipadress.modelo.entidades.Ipes;


@FeignClient(name = "api-ipfraudes")
public interface IpAdressClienteRest 
{
	@GetMapping("/api/v1/rest-ipfraudes/Ip/listar")
	public List<Ipes> consultarTodosIps(); 
	
	
	@GetMapping("/api/v1/rest-ipfraudes/Ip/ver-detalle/ip/{ident}.{num1}.{num2}.{num3}")
	public Ipes consultarDetalleIpes(@PathVariable Integer ident,@PathVariable Integer num1,@PathVariable Integer num2,@PathVariable Integer num3);
}
