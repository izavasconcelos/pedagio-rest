package com.izavasconcelos.cloud.tema05.rest;

import com.izavasconcelos.cloud.tema05.annotation.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.ws.rs.core.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})

public class TollServiceTest {

    @Autowired
    private TollService tollService;
    private Response response;
    private static final String URL = "http://localhost:8080";


    @Test
    public void doBikePaymentTest() {
        response = tollService.calculateChange("bike", 10, 0);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: bike  | Price: $0.49  | Payment: $10.00  | Change: $9.51", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/bike/10")).
                then().
                statusCode(200).body(containsString("Change: $9.51"));
    }

    @Test
    public void doBikeInvalidPaymentTest() {
        response = tollService.calculateChange("bike", .20, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid Value! Give more money!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/bike/.20")).
                then().
                statusCode(400).body(containsString("Invalid Value! Give more money!"));
    }

    @Test
    public void doBusPaymentTest() {
        response = tollService.calculateChange("bus", 8, 0);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: bus  | Price: $1.59  | Payment: $8.00  | Change: $6.41", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/bus/8")).
                then().
                statusCode(200).body(containsString("Change: $6.41"));
    }

    @Test
    public void doBusInvalidPaymentTest() {
        response = tollService.calculateChange("bus", 1, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid Value! Give more money!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/bus/1")).
                then().
                statusCode(400).body(containsString("Invalid Value! Give more money!"));
    }

    @Test
    public void doMotorcyclePaymentTest() {
        response = tollService.calculateChange("motorcycle", 4.13, 0);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: motorcycle  | Price: $1.00  | Payment: $4.13  | Change: $3.13", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/motorcycle/4.13")).
                then().
                statusCode(200).body(containsString("Change: $3.13"));
    }

    @Test
    public void doMotorcycleInvalidPaymentTest() {
        response = tollService.calculateChange("motorcycle", .50, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid Value! Give more money!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/motorcycle/.50")).
                then().
                statusCode(400).body(containsString("Invalid Value! Give more money!"));
    }

    @Test
    public void doBeetlePaymentTest() {
        response = tollService.calculateChange("beetle", 3.25, 0);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: beetle  | Price: $2.11  | Payment: $3.25  | Change: $1.14", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/beetle/3.25")).
                then().
                statusCode(200).body(containsString("Change: $1.14"));
    }

    @Test
    public void doBeetleInvalidPaymentTest() {
        response = tollService.calculateChange("beetle", 2.10, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid Value! Give more money!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/beetle/2.10")).
                then().
                statusCode(400).body(containsString("Invalid Value! Give more money!"));
    }

    @Test
    public void doTruckWithoutAxlesExtraPaymentTest() {
        response = tollService.calculateChange("truck", 5.67, 0);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: truck  | Price: $3.95  | Payment: $5.67  | Change: $1.72", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/truck/5.67/0")).
                then().
                statusCode(200).body(containsString("Change: $1.72"));
    }

    @Test
    public void doInvalidPaymentTruckWithoutAxlesExtraTest() {
        response = tollService.calculateChange("truck", 2.66, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid Value! Give more money!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/truck/2.66/0")).
                then().
                statusCode(400).body(containsString("Invalid Value! Give more money!"));
    }

    @Test
    public void doTruckWithAxlesExtraPaymentTest() {
        response = tollService.calculateChange("truck", 17.92, 3);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: truck  | Price: $15.80  | Payment: $17.92  | Change: $2.12", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/truck/17.92/3")).
                then().
                statusCode(200).body(containsString("Change: $2.12"));
    }

    @Test
    public void doInvalidPaymentTruckWithAxlesExtraTest() {
        response = tollService.calculateChange("truck", 15.48, 4);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid Value! Give more money!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/truck/15.48/4")).
                then().
                statusCode(400).body(containsString("Invalid Value! Give more money!"));
    }

    @Test
    public void doNullVehiclePaymentTest() {
        response = tollService.calculateChange(null, 12.79, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Vehicle is null or invalid!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/null/15.48/0")).
                then().
                statusCode(400).body(containsString("Invalid Vehicle or Parameters!"));
    }

    @Test
    public void doInvalidVehiclePaymentTest() {
        response = tollService.calculateChange("car", 1.79, 0);
        assertEquals(400, response.getStatus());
        assertEquals("Vehicle is null or invalid!", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/car/1.79")).
                then().
                statusCode(400).body(containsString("Vehicle is null or invalid!"));
    }

    @Test
    public void doNotANumberPaymentTest() {
        given().when().
                get(URL.concat("/tema05/toll/bike/1.a77b9")).
                then().
                statusCode(404);
    }

    @Test
    public void doBigPaymentTest() {
        response = tollService.calculateChange("beetle", 234345564588985.66, 0);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: beetle  | Price: $2.11  | Payment: $234345564588985.66  | Change: $234345564588983.53", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/beetle/234345564588985.66")).
                then().
                statusCode(200).body(containsString("Change: $234345564588983.53"));
    }

    @Test
    public void doBigPaymentWithAxlesExtraTest() {
        response = tollService.calculateChange("truck", 92734501275.47, 545434535);
        assertEquals(200, response.getStatus());
        assertEquals("VehicleType: truck  | Price: $2154466417.20  | Payment: $92734501275.47  | Change: $90580034858.27", response.getEntity());
        given().when().
                get(URL.concat("/tema05/toll/truck/92734501275.47/545434535")).
                then().
                statusCode(200).body(containsString("Change: $90580034858.27"));
    }

    @Test
    public void getTollPricesListTest() {
        response = tollService.getTollPricesList();
        assertEquals(200, response.getStatus());
        assertEquals("{motorcycle=1.0, bus=1.59, beetle=2.11, truck=3.95, bike=0.49}", response.getEntity());

        given().when().
                get(URL.concat("/tema05/toll/prices")).
                then().
                statusCode(200).body(containsString("motorcycle=1.0, bus=1.59, beetle=2.11, truck=3.95, bike=0.49"));
    }
}
