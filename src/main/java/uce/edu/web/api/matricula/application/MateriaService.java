package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infrastructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Materia mat) {
        this.materiaRepository.persist(mat);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat) {
        Materia materia = this.consultarPorId(id);
        materia.nombre = mat.nombre;
        materia.codigo = mat.codigo;
        materia.creditos = mat.creditos;
        materia.horasSemanales = mat.horasSemanales;
        materia.semestre = mat.semestre;
        materia.descripcion = mat.descripcion;

    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat) {
        Materia materia = this.consultarPorId(id);
        if (mat.nombre != null) {
            materia.nombre = mat.nombre;
        }
        if (mat.codigo != null) {
            materia.codigo = mat.codigo;
        }
        if (mat.creditos != null) {
            materia.creditos = mat.creditos;
        }
        if (mat.horasSemanales != null) {
            materia.horasSemanales = mat.horasSemanales;
        }
        if (mat.semestre != null) {
            materia.semestre = mat.semestre;
        }
        if (mat.descripcion != null) {
            materia.descripcion = mat.descripcion;
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    public List<Materia> buscarPorCreditos(Integer creditos) {
        return this.materiaRepository.getEntityManager()
                .createQuery("SELECT m FROM Materia m WHERE m.creditos = :creditos", Materia.class)
                .setParameter("creditos", creditos)
                .getResultList();
    }

    public List<Materia> buscarPorSemestre(Integer semestre) {
        return this.materiaRepository.getEntityManager()
                .createQuery("SELECT m FROM Materia m WHERE m.semestre = :semestre", Materia.class)
                .setParameter("semestre", semestre)
                .getResultList();
    }

}
