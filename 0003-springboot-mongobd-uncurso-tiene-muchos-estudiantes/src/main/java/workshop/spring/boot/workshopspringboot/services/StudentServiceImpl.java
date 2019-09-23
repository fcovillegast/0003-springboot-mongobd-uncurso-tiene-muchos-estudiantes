package workshop.spring.boot.workshopspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Student;
import workshop.spring.boot.workshopspringboot.repositories.StudentsRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentsRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentsRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public Flux<Student> findAll() {
		Flux<Student> courses = studentRepository.findAll();
		return courses;
	}

	public Mono<Student> findBy(Integer idStudent) {
		Mono<Student> studentSaved = studentRepository.findById(idStudent);

		checkExistStudent(idStudent, studentSaved);

		return studentSaved;
	}

	public Mono<Student> create(Student student) {
		Mono<Student> mono = studentRepository.save(student);
		return mono;
	}

	public Student update(Integer id, Student student) {

		Mono<Student> studentMono = this.findBy(id);

		Student studentFinded = studentMono.block();
		studentFinded.setRut(student.getRut());
		studentFinded.setName(student.getName());
		
 		Mono<Student> studentSaved = studentRepository.save(studentFinded);
 
		
		return studentSaved.block();
	 
	}

	public Mono<Void> delete(Integer id) {
		Mono<Student> studentSaved = this.findBy(id);
		
		Mono<Void> monoVoid = studentRepository.deleteById(id);
		monoVoid.subscribe(); 
		
		return monoVoid;
	}
	
	public Mono<Void> deleteM2(Integer id) {
		Mono<Student> studentSaved = this.findBy(id);
		
		Mono<Void> monoVoid = studentRepository.delete(studentSaved.block());
		monoVoid.subscribe(); 
		
		return monoVoid;
	}

	private void checkExistStudent(Integer id, Mono<Student> studentSaved) {
		if (studentSaved == null || studentSaved.block() == null) {
			throw new CourseNotFoundException("Doesnt not exist the Student with id:" + id);
		}
	}

//	@Override
//	public Flux<Student> findByCourse(Integer idCourse) {
//		
//		Mono<Course> course = courseService.monoFindBy(idCourse);
//		
//		Flux<Student> students = studentRepository.findAllByCourse(course.block());
//		
//		return students;
//	}

//	@Override
//	public Mono<Student> addCourse(Integer idCourse, Integer idStudent) {
//		
//		throw new RuntimeException("No implementado");
//	}
}
