package workshop.spring.boot.workshopspringboot.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import workshop.spring.boot.workshopspringboot.models.Course;
 
public interface CourseRepository extends ReactiveMongoRepository<Course, Integer>{

	Flux<Course> findAll();
	

}
