package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPoId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());

    }

    @Transactional
    public void crear(Estudiante estu) {
        this.estudianteRepository.persist(estu); // persist: crear
    }

    @Transactional
    public void actualizar(Integer id, Estudiante est) {
        Estudiante estu = this.consultarPoId(id);
        estu.nombre = est.nombre;
        estu.apellido = est.apellido;
        estu.fechaNacimiento = est.fechaNacimiento;
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante est) {
        Estudiante estu = this.consultarPoId(id);
        if (est.nombre != null) {
            estu.nombre = est.nombre;
        }
        if (est.apellido != null) {
            estu.apellido = est.apellido;
        }
        if (est.fechaNacimiento != null) {
            estu.fechaNacimiento = est.fechaNacimiento;
        }
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }
}
