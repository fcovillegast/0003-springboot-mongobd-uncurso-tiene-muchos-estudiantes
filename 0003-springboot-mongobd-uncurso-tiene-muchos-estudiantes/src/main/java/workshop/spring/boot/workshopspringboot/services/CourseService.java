package workshop.spring.boot.workshopspringboot.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Course;

public interface CourseService {
	
	public Flux<Course> findAll();
	
	Course findBy(Integer idCourse);
	
	Course create(Course course);
	
	Course update(Integer id, Course course); 
	
	Mono<Void> delete(Integer id);

	Mono<Course> monoFindBy(Integer idCourse);
	
	
	
//	Course addStudent(Integer idCourse, Integer idStudent);
	

}
