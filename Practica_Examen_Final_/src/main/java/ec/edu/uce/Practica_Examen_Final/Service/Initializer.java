package ec.edu.uce.Practica_Examen_Final.Service;

import ec.edu.uce.Practica_Examen_Final.Jpa.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {

        Task task1 = new Task();
        task1.setDescription("Pokedex");
        task1.setStatus(Task.Estado.FINALIZADA);
        task1.setDate(LocalDate.now());

        Task task2 = new Task();
        task2.setDescription("PokedexWeb");
        task2.setStatus(Task.Estado.EJECUTADA);
        task2.setDate(LocalDate.now());

        Task task3 = new Task();
        task3.setDescription("BBD");
        task3.setStatus(Task.Estado.PENDIENTE);
        task3.setDate(LocalDate.now());

        taskService.createTask(task1);
        taskService.createTask(task2);
        taskService.createTask(task3);
    }
}
