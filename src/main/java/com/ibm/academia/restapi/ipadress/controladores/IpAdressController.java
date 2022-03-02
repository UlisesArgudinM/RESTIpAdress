package com.ibm.academia.restapi.ipadress.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.ipadress.modelo.entidades.IpAdress;
import com.ibm.academia.restapi.ipadress.modelo.entidades.Ipes;
import com.ibm.academia.restapi.ipadress.modelo.servicios.IIpAdressService;

import feign.FeignException;

@RestController
@RequestMapping("/IpAdress")
public class IpAdressController 
{
	private final static Logger logger = LoggerFactory.getLogger(IpAdressController.class);
	
	@Autowired
	private CircuitBreakerFactory circuitBreaker;
	
	@Autowired
	@Qualifier("serviceFeign")
	private IIpAdressService ipAdressService;
	
	
	/**
	 * Endpoint que lista a todos los datos de la ips
	 * @return regresa una lista de objetos IpAdress
	 * @author Usuario 01/03/2022
	 */
	@GetMapping("/listar")
	public ResponseEntity<?>buscarTodo()
	{
		List<IpAdress> ipadress = ipAdressService.buscarTodos();
		return new ResponseEntity<List<IpAdress>>(ipadress,HttpStatus.OK);
	}
	
	/*@GetMapping("/ver-detalle/ip/{ident}.{num1}.{num2}.{num3}")
	public ResponseEntity<?>consultarDetallesIp(@PathVariable Integer ident,@PathVariable Integer num1,@PathVariable Integer num2,@PathVariable Integer num3)
	{
		Map<String,Object> respuesta = new HashMap<String, Object>();
		IpAdress ipadress = null;
		try
		{
			ipadress = ipAdressService.buscarPorIp(ident);
		}
		catch (FeignException fe) 
		{
			logger.info("mensaje:"+ fe.getMessage() + "Causa:"+fe.getCause());
			respuesta.put("mensaje",fe.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.NOT_FOUND);
		}
		catch (Exception e) 
		{
			logger.info("mensaje:"+ e.getMessage()+ "Causa:"+e.getCause());
			respuesta.put("mensaje",e.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return new ResponseEntity<IpAdress>(ipadress,HttpStatus.OK);
	}*/
	@GetMapping("/ver-detalle/ip/{ident}.{num1}.{num2}.{num3}")
	public IpAdress consultarDetallesIp(@PathVariable Integer ident,@PathVariable Integer num1,@PathVariable Integer num2,@PathVariable Integer num3)
	{
		
		return circuitBreaker.create("ipadressC")
				.run(() -> ipAdressService.buscarPorIp(ident), e->metodoAlternativo(e));
		
	}
	
	
	/**
	 * Metodo alternativo por si falla algo
	 * @param e es la excepcion 
	 * @return retorna una entidad tipo IpAdress
	 */
	public IpAdress metodoAlternativo(Throwable e)
	{
		logger.info(e.getMessage());
		IpAdress ipadress = new IpAdress();
		Ipes ips = new Ipes();
		
		ipadress.setIpes(ips);
		ips.setMoneda("Euro");
		ips.setIso("EUA");
		
		return ipadress;
	}
	
}
