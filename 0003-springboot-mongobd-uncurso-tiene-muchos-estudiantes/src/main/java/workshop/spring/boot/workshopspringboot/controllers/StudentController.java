package workshop.spring.boot.workshopspringboot.controllers;

 

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Student;
import workshop.spring.boot.workshopspringboot.services.StudentService;


@RestController
@RequestMapping("students")
public class StudentController {
	

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	
	@GetMapping()
	@ResponseBody
	public Flux<Student> findAll() {
		return studentService.findAll();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Mono<Student>> create(@Valid  @RequestBody Student student) {
		Mono<Student> savesStudent = studentService.create(student);
		
		return new ResponseEntity<Mono<Student>>(savesStudent, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Student> delete(@PathVariable(value="id") Integer id) {
		studentService.deleteM2(id);
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Student> update(@PathVariable(value="id") Integer id, @Valid @RequestBody Student student) {
		Student studentSaved = studentService.update(id, student);
		
		return new ResponseEntity<Student>(studentSaved, HttpStatus.CREATED);
	}
//	
//	@RequestMapping("/inCourse/{id_course}")
//	@ResponseBody
//	public Flux<Student> getStudents(@PathVariable(value="id_course") Integer idCourse) {
//		return studentService.findByCourse(idCourse);
//	}
//
//	@PutMapping("/{studentId}/{courseId}")
//	@ResponseBody
//	public ResponseEntity<Mono<Student>> addStudent(@PathVariable(value = "studentId") Integer studentId,
//			@PathVariable(value = "courseId") Integer courseId) {
//		
//		Mono<Student> studentSaved = studentService.addCourse(courseId, studentId);
//
//		return new ResponseEntity<Mono<Student>>(studentSaved, HttpStatus.OK);
//	}
}