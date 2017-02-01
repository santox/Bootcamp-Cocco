package com.cocco.bootcamp.ws;

import com.cocco.bootcamp.dto.JsonResponseCountry;
import com.cocco.bootcamp.dto.JsonResponseState;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by santi on 1/2/2017.
 */
@Path("/")
@Produces("application/json")
public interface CountriesAndStates {

    @GET
    @Produces("application/json")
    @Path("country/get/all")
    public JsonResponseCountry getCountries();

    @GET
    @Produces("application/json")
    @Path("state/get/{country}/all")
    public JsonResponseState getStates(@PathParam(value = "country") String country);
}
