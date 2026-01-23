package uce.edu.web.api.matricula.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Materia")
@SequenceGenerator(name = "materia_Seq", sequenceName = "materia_sequencia", allocationSize = 1)
public class Materia extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materia_Seq")
    public Integer idMateria;
    public String nombre;
    public String codigo;
    public Integer creditos;
    public Integer horasSemanales;
    public String semestre;
    public String descripcion;

}
