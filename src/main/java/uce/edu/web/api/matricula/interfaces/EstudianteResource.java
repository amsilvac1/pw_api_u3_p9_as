package uce.edu.web.api.matricula.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin") // solo el administrador
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("Listar Todos XXXXXXXXXXXXXXXXXXXXXXXXX");
        List<EstudianteRepresentation> estudiantes = new ArrayList<>();
        for (EstudianteRepresentation er : this.estudianteService.listarTodos()) {
            estudiantes.add(this.construirLinks(er));

        }
        return estudiantes;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    // @PermitAll // que sea publico
    @RolesAllowed("admin") // que sea admin
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer iden) {
        return construirLinks(estudianteService.consultarPoId(iden));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estu) {
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer iden, EstudianteRepresentation estu) {
        this.estudianteService.actualizar(iden, estu);
        return Response.status(209).entity(estu).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizarParcial(@PathParam("id") Integer iden, EstudianteRepresentation estu) {
        this.estudianteService.actualizarParcial(iden, estu);
        return Response.status(210).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response eliminar(@PathParam("id") Integer iden) {
        this.estudianteService.eliminar(iden);
        return Response.status(Response.Status.NO_CONTENT).entity(null).build();
    }

    @GET
    @Path("/provincia/genero") // para la consulta con query param es
    // http://localhost:8080/estudiantes/buscarPorProvincia?provincia=xxx
    // http://localhost:8080/estudiantes/buscarPorProvincia?provincia=xxx&&genero=xxx
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("Listar Por Provincia y Genero XXXXXXXXXXXXXXXXXXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HijoRepresentation> buscarHijosPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
                .build().toString();
        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
                .path("hijos").build().toString();
        er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));

        return er;
    }
}
