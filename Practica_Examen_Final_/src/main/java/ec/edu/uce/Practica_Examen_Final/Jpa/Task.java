package ec.edu.uce.Practica_Examen_Final.Jpa;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "taks")

public class Task{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "desciption")
    private String description;

    @Column (name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Estado status;

    public Task() {
        this.date = LocalDate.now();
        this.status = Estado.PENDIENTE;
    }

    /*método de enumeración para ver el estado de la tarea*/
    public enum Estado{EJECUTADA,PENDIENTE,FINALIZADA }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Estado getStatus() {
        return status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }
}