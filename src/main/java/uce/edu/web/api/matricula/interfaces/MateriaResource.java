package uce.edu.web.api.matricula.interfaces;

import java.util.List;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listarTodos() {
        return materiaService.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML) // Es para cuando devuelva el objeto en XML
    public Materia consultarPorId(@PathParam("id") Integer iden) {
        return materiaService.consultarPorId(iden);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON) // Es para cuando envie el objeto en JSON
    public Response guardar(Materia mat) {
        this.materiaService.crear(mat);
        return Response.status(Response.Status.CREATED).entity(mat).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer iden, Materia mat) {
        this.materiaService.actualizar(iden, mat);
        return Response.status(209).entity(mat).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response actualizarParcial(@PathParam("id") Integer iden, Materia mat) {
        this.materiaService.actualizarParcial(iden, mat);
        return Response.status(210).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer iden) {
        this.materiaService.eliminar(iden);
        return Response.status(Response.Status.NO_CONTENT).entity(null).build();
    }

    @GET
    @Path("/credito/{creditos}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> buscarPorNombre(@PathParam("creditos") Integer creditos) {
        return this.materiaService.buscarPorCreditos(creditos);
    }

    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> buscarPorSemestre(@PathParam("semestre") Integer semestre) {
        return this.materiaService.buscarPorSemestre(semestre);
    }

}
