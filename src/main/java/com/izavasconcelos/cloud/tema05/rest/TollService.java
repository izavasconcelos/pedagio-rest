package com.izavasconcelos.cloud.tema05.rest;

import com.izavasconcelos.cloud.tema05.vehicles.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.HashMap;
import java.util.Map;

@Service
@ApplicationPath("toll")
public class TollService extends Application {

    @Autowired
    private ApplicationContext appContext;
    private Vehicle vehicle;


    @GET
    @Path("/{vehicleType}/{payment}")
    @Produces("text/plain")
    public Response doTollPaymentWithoutAxles(
            @PathParam("vehicleType") String vehicleType,
            @PathParam("payment") double payment) {

            vehicleType = vehicleType.toLowerCase();
            if(vehicleType.equals("truck")) {
               return Response.status(400).entity("Invalid Vehicle or Parameters!").build();
            }

            return calculateChange(vehicleType, payment, 0);
    }

    @GET
    @Path("/{vehicleType}/{payment}/{axles}")
    @Produces("text/plain")
    public Response doTollPaymentWithAxles(
            @PathParam("vehicleType") String vehicleType,
            @PathParam("payment") double payment,
            @PathParam("axles") int axles) {

        vehicleType = vehicleType.toLowerCase();
        if(!vehicleType.equals("truck")) {
             return Response.status(400).entity("Invalid Vehicle or Parameters!").build();
        }

        return calculateChange(vehicleType, payment, axles);
    }

    @GET
    @Path("/prices")
    @Produces("text/plain")
    public Response getTollPricesList() {
        String[] vehicles = appContext.getBeanNamesForType(Vehicle.class);
        Map<String, Double> pricesList = new HashMap<>();
        for(String vehicleType : vehicles) {
            vehicle = (Vehicle) appContext.getBean(vehicleType);
            pricesList.put(vehicleType, vehicle.getTollPrice());
        }
        return Response.ok(pricesList.toString()).build();
    }

    public Response calculateChange(String vehicleType, double payment, int axles) {
        if(vehicleType == null || !appContext.containsBeanDefinition(vehicleType) ) {
            return Response.status(400).entity("Vehicle is null or invalid!").build();
        }
        vehicle = (Vehicle) appContext.getBean(vehicleType);
        double price = vehicle.getTollPrice() + (vehicle.getTollPrice() * axles);
        double change =  payment - price;
        ResponseBuilder response;
        if(payment >= price) {
            response = Response.ok(String.format("VehicleType: %s  | Price: $%.2f  | Payment: $%.2f  | Change: $%.2f", vehicleType, price, payment, change));
        } else {
            response = Response.status(400).entity("Invalid Value! Give more money!");
        }

        return response.build();
    }
}