package ru.ml.pyramid.server.res;

import ru.ml.pyramid.server.App;
import ru.ml.pyramid.server.model.Pyramid;
import ru.ml.pyramid.server.model.PyramidException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weight")
public class WeightRes {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response a(@QueryParam("level") int level, @QueryParam("index") int index) {
        return get(level, index);
    }

    @GET
    @Path("/{level}/{element}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response b(@PathParam("level") int level, @PathParam("element") int element) {
        return get(level, element);
    }

    private Response get(int level, int index) {
        Pyramid pyramid = App.injector.getInstance(Pyramid.class);
        try {
            float weight = pyramid.getHumanEdgeWeight(level, index);
            return Response.status(Response.Status.OK).entity(String.valueOf(weight)).build();
        } catch (PyramidException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
