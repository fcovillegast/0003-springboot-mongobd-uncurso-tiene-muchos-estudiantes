package workshop.spring.boot.workshopspringboot.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import workshop.spring.boot.workshopspringboot.models.Course;
import workshop.spring.boot.workshopspringboot.models.Student;

@Repository
public interface StudentsRepository extends ReactiveMongoRepository<Student, Integer>{

	Flux<Student> findAll();

	Flux<Student> findAllByCourse(Course course);
	
	
}