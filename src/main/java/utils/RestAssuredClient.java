package utils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import common.AppConstants;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RestAssuredClient {

    public Response doGetRequest(){
        //RestAssured.baseURI = ConfigReader.getInstance().getValue(AppConstants.END_POINT);
        String url = ConfigReader.getInstance().getValue(AppConstants.END_POINT);
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification http = RestAssured.given();
        http.headers(AppConstants.CONTENT_TYPE,
                ContentType.JSON, AppConstants.ACCEPT, ContentType.JSON);

        return http.when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public List<LinkedHashMap<String, Map<String, Object>>> parseResponse(Response response){
        return response.jsonPath().getList(AppConstants.NETWORKS);
    }

}
