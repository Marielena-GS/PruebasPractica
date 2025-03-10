package ec.edu.uce.Practica_Examen_Final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticaExamenFinalApplication {

	public static void main(String[] args) {

		SpringApplication.run(PracticaExamenFinalApplication.class, args);
	}
}
//conexion a postgres desde docker
//docker run -d --name postgreSQL -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 -v postgres-data:/var/lib/postgresql/data postgres:latest
