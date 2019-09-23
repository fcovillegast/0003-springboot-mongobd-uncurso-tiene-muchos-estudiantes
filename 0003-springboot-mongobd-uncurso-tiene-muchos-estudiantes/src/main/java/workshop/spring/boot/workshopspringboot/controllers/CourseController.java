package workshop.spring.boot.workshopspringboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Course;
import workshop.spring.boot.workshopspringboot.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@RequestMapping
	@ResponseBody
	public Flux<Course> findAll() {
		return courseService.findAll();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Course> create(@Valid @RequestBody Course course ) {
		Course courseCreate = courseService.create(course);
		
		return new ResponseEntity<Course>(courseCreate, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Course> update(@PathVariable(value="id") Integer id , @RequestBody Course course) {
		Course courseSaved = courseService.update(id, course);
		
		return new ResponseEntity<Course>(courseSaved, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Mono> delete(@PathVariable(value="id") Integer id) {
		Mono<Void> deleted = courseService.delete(id);
		
		return new ResponseEntity<Mono>(deleted, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{courseId}/{studentId}")
	@ResponseBody
	public ResponseEntity<Course> addStudent(@PathVariable(value = "studentId") Integer idStudent,
			@PathVariable(value = "courseId") Integer idCourse) {
		
		Course courseSaved = courseService.addStudent(idCourse, idStudent);

		return new ResponseEntity<Course>(courseSaved, HttpStatus.OK);
	}
	
}
