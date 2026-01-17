package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos() {
        return estudianteService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer iden) {
        return estudianteService.consultarPoId(iden);
    }

    @POST
    @Path("/crear")
    public void guardar(Estudiante estu) {
        this.estudianteService.crear(estu);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer iden, Estudiante estu) {
        this.estudianteService.actualizar(iden, estu);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer iden, Estudiante estu) {
        this.estudianteService.actualizarParcial(iden, estu);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void eliminar(@PathParam("id") Integer iden) {
        this.estudianteService.eliminar(iden);
    }

}
