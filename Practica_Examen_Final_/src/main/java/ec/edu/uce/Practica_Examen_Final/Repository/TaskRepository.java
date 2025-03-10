package ec.edu.uce.Practica_Examen_Final.Repository;

import ec.edu.uce.Practica_Examen_Final.Jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.Estado status);
}


