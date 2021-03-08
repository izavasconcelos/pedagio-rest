package com.izavasconcelos.cloud.tema05.annotation;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import com.izavasconcelos.cloud.tema05.rest.TollService;
import com.izavasconcelos.cloud.tema05.vehicles.Truck;
import com.izavasconcelos.cloud.tema05.vehicles.Beetle;
import com.izavasconcelos.cloud.tema05.vehicles.Motorcycle;
import com.izavasconcelos.cloud.tema05.vehicles.Bike;
import com.izavasconcelos.cloud.tema05.vehicles.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


@Configuration
public class AppConfig {

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {
        final JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(tollService(),JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.asList(tollService()));
        factory.setProviders( Arrays.asList( jsonProvider() ) );
        return factory.create();
    }

    @Bean
    public TollService tollService() {
        return new TollService();
    }

    @Bean
    public Beetle beetle() {
        return new Beetle();
    }

    @Bean
    public Bike bike() {
        return new Bike();
    }

    @Bean
    public Bus bus() {
        return new Bus();
    }

    @Bean
    public Motorcycle motorcycle() {
        return new Motorcycle();
    }

    @Bean
    public Truck truck() {
        return new Truck();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
}

