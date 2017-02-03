package com.cocco.bootcamp.ws;

import com.cocco.bootcamp.weatherAdapter.JsonResponseWeather;

import javax.ws.rs.*;

/**
 * Created by santi on 3/2/2017.
 */
@Path("/")
@Produces("application/json")
public interface WeatherFromClient {

    @GET
    @Produces("application/json")
    @Path("{url}")
    public JsonResponseWeather getWeather(@PathParam(value = "url") String url);
}
