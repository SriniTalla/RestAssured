package org.example;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class myFirstRestAssuredClass {

    final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    public static void main(String args[]) {
        getResponseBody();
        getResponseStatus();
        getResponseHeaders();
        getResponseTime();
        getResponseContentType();
        getSpecificPartOfResponseBody();

    }

    //This will fetch the response body as is and log it. given and when are optional here
    public static void getResponseBody(){
        given().when().get(url).then().log()
                .all();

        given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log().body();
    }

    public static void getResponseStatus(){
        int statusCode= given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();

        System.out.println("The response status is "+statusCode);

        given().when().get(url).then().assertThat().statusCode(200);
    }

    public static void getResponseHeaders(){
        System.out.println("The headers in the response "+
                get(url).then().extract()
                        .headers());
    }

    public static void getResponseTime(){
        System.out.println("The time taken to fetch the response "+get(url)
                .timeIn(MILLISECONDS) + " milliseconds");
    }

    public static void getResponseContentType(){
        System.out.println("The content type of response "+
                get(url).then().extract()
                        .contentType());
    }

    public static void getSpecificPartOfResponseBody(){

        ArrayList<Integer> amounts = when().get(url).then().extract().path("result.statements.AMOUNT") ;
        int sumOfAll=0;

        for(Integer a:amounts){
            System.out.println("The amount value fetched is "+a);
            sumOfAll = sumOfAll+a;
        }

        System.out.println("The total amount is "+sumOfAll);
    }

}
