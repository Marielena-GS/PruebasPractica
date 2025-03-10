package ec.edu.uce.Practica_Examen_Final.Controller;

import ec.edu.uce.Practica_Examen_Final.Jpa.Task;
import ec.edu.uce.Practica_Examen_Final.Service.TaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTask() {
        return ResponseEntity.ok(taskService.listTask());
    }

    @GetMapping("/status/{status}")
    public List<Task> listarTareasPorStatus(@PathVariable Task.Estado status) {
        return taskService.listarTareasPorStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> obtenerTaskById(@PathVariable Long id) {
        return taskService.obtenerTaskPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> taskActualized(@PathVariable Long id, @RequestBody Task taskActualized) {
        return ResponseEntity.ok(taskService.taskActualized(id, taskActualized));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    //http://localhost:8080/task/status/EJECUTADA
    //http://localhost:8080/task/1
    //Postman
    //crear
    //curl -X POST "http://localhost:8080/task" -H "Content-Type: application/json" -d '{"description":"Nueva tarea","status":"PENDENTE"}'
    //actualizar
    //curl -X PUT "http://localhost:8080/task/1" -H "Content-Type: application/json" -d '{"description":"Actualizada","status":"FINALIZADA"}'
    //eliminar
    // http://localhost:8080/task/1
    //curl -X DELETE "http://localhost:8080/task/1"
}
