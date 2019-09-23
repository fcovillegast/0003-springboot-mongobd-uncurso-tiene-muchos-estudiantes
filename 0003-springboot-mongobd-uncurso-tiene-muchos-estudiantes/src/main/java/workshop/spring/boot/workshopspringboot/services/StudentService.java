package workshop.spring.boot.workshopspringboot.services;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Student;

public interface StudentService {

	public Flux<Student> findAll();

	public Mono<Student> findBy(Integer idStudent);

	public Mono<Student> create(Student course);

	public Student update(Integer id, Student student);

	Mono<Void> delete(Integer id);

	public Mono<Void> deleteM2(Integer id);
	
//	public Flux<Student> findByCourse(Integer idCourse);
//	
//	Mono<Student> addCourse(Integer idCourse, Integer idStudent);  

}