package org.acme.customer;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(CustomerCustomResource.class)
public class CustomerCustomResourceTest {

    private static List<Customer> customers = new ArrayList<>();
    private static int CUSTOMERS_COUNT = 10;
    protected static int PAGE_SIZE = 5;
    protected static int PAGE_INDEX = 0;
    protected static int PAGE_COUNT = CUSTOMERS_COUNT / PAGE_SIZE;

    @BeforeAll
    public static void setupTest(){
        PanacheMock.mock(Customer.class);
        Mockito.when(Customer.findAll(PAGE_INDEX, PAGE_SIZE)).thenReturn(customers);
        Mockito.when(Customer.getPageCount()).thenReturn(PAGE_COUNT);
    }

    @Test
    public void testTotalCountHeaderOnGet() {
        given()
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .header("X-Total-Count", String.valueOf(PAGE_COUNT));
    }
}
