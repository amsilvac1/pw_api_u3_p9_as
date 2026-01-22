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
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes/")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("")
    public List<Estudiante> listarTodos() {
        System.out.println("Listar Todos XXXXXXXXXXXXXXXXXXXXXXXXX");
        return estudianteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer iden) {
        return estudianteService.consultarPoId(iden);
    }

    @POST
    @Path("")
    public void guardar(Estudiante estu) {
        this.estudianteService.crear(estu);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer iden, Estudiante estu) {
        this.estudianteService.actualizar(iden, estu);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Integer iden, Estudiante estu) {
        this.estudianteService.actualizarParcial(iden, estu);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer iden) {
        this.estudianteService.eliminar(iden);
    }

    @GET
    @Path("/provincia/genero") // para la consulta con query param es
    // http://localhost:8080/estudiantes/buscarPorProvincia?provincia=xxx
    // http://localhost:8080/estudiantes/buscarPorProvincia?provincia=xxx&&genero=xxx
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("Listar Por Provincia y Genero XXXXXXXXXXXXXXXXXXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

}
