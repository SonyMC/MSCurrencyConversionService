package com.in28minutes.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.in28minutes.microservices.currencyconversionservice.beans.CurrencyConversionBean;


//@FeignClient(name="currency-exchange-service",url="localhost:8000")  -> If we use ribbon, we do not need to specify port in Feign client
//@FeignClient(name="currency-exchange-service")  -> Once we use ZUUL API, we need to provide the ZUUL service name in Feign
@FeignClient(name="netflix-zuul-api-gateway-server")  
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")  -> When we use ZUUL API, we have to prefix the application service name to the uri
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
