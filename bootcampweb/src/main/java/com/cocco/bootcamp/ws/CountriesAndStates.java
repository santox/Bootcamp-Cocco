package com.cocco.bootcamp.ws;

import com.cocco.bootcamp.domain.Country;
import com.cocco.bootcamp.dto.RestResponse;
import com.cocco.bootcamp.dto.RestResponseCountry;
import com.cocco.bootcamp.dto.RestResponseState;

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
    public RestResponseCountry getCountries();

    @GET
    @Produces("application/json")
    @Path("state/get/{country}/all")
    public RestResponseState getStates(@PathParam(value = "country") String country);
}
