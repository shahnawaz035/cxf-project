package com.shah.webservice.cxf.cxfproject.config;

import com.shah.webservice.cxf.cxfproject.HelloWs;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

    private final Bus bus;

    public WebServiceConfig(Bus bus) {
        this.bus = bus;
    }

    @Bean
    public Endpoint endpoint(){
        Endpoint endpoint = new EndpointImpl(bus, new HelloWs());
        endpoint.publish("/hello");
        return endpoint;
    }
}
