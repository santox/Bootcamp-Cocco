package com.cocco.bootcamp.ws;

import com.cocco.bootcamp.weatherAdapter.JsonResponseWeather;

import javax.ws.rs.*;

/**
 * Created by santi on 3/2/2017.
 */
@Path("/v1/public")
@Produces("application/json")
public interface WeatherFromClient {

    @GET
    @Produces("application/json")
    @Path("/yql")
    public JsonResponseWeather getWeather(@QueryParam(value = "q") String q, @QueryParam(value = "format") String format, @QueryParam(value = "env") String env);
}
