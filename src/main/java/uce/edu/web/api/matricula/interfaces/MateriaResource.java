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
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodos() {
        return materiaService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer iden) {
        return materiaService.consultarPorId(iden);
    }

    @POST
    @Path("/crear")
    public void guardar(Materia mat) {
        this.materiaService.crear(mat);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer iden, Materia mat) {
        this.materiaService.actualizar(iden, mat);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer iden, Materia mat) {
        this.materiaService.actualizarParcial(iden, mat);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void eliminar(@PathParam("id") Integer iden) {
        this.materiaService.eliminar(iden);
    }

    @GET
    @Path("/buscarPorCredito/{creditos}")
    public List<Materia> buscarPorNombre(@PathParam("creditos") Integer creditos) {
        return this.materiaService.buscarPorCreditos(creditos);
    }

    @GET
    @Path("/buscarPorSemestre/{semestre}")
    public List<Materia> buscarPorSemestre(@PathParam("semestre") Integer semestre) {
        return this.materiaService.buscarPorSemestre(semestre);
    }

}
