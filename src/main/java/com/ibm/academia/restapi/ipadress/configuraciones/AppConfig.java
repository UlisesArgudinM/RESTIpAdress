package com.ibm.academia.restapi.ipadress.configuraciones;



import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AppConfig 
{
	/*@Bean("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() 
	{
		return new RestTemplate();
	}*/
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer()
	{
		return factory ->factory.configureDefault(id->{
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(CircuitBreakerConfig.custom()
							.slidingWindowSize(10)
							.failureRateThreshold(50)
							.waitDurationInOpenState(Duration.ofSeconds(10L))
							.permittedNumberOfCallsInHalfOpenState(5)
							.build())
					.timeLimiterConfig(TimeLimiterConfig.ofDefaults())
					.build();
					
					
		});
	}
}