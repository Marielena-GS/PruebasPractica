package ec.edu.uce.Practica_Examen_Final.Service;

import ec.edu.uce.Practica_Examen_Final.Jpa.Task;
import ec.edu.uce.Practica_Examen_Final.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    //maneja las operaciones de la base de datos
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> listTask(){
        return taskRepository.findAll().stream().collect(Collectors.toList());
    }

    public List<Task> listarTareasPorStatus(Task.Estado status) {
        return taskRepository.findByStatus(status).stream().collect(Collectors.toList());
    }

    public Optional<Task> obtenerTaskPorId(Long id){
        return taskRepository.findById(id);
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task taskActualized(Long id, Task taskActualized) {
        return taskRepository.findById(id)
                .map(task-> {
                    task.setDescription(taskActualized.getDescription());
                    task.setStatus(taskActualized.getStatus());
                    task.setDate(taskActualized.getDate());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
