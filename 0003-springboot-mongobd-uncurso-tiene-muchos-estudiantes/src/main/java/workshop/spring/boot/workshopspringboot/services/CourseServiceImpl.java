package workshop.spring.boot.workshopspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Course;
import workshop.spring.boot.workshopspringboot.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public Flux<Course> findAll() {
		Flux<Course> courses = courseRepository.findAll();
		return courses;
	}

	@Override
	public Course findBy(Integer idCourse) {
		Mono<Course> courseSaved = courseRepository.findById(idCourse);

		checkExistCourse(idCourse, courseSaved);

		return courseSaved.block();
	}
	
	@Override
	public Mono<Course> monoFindBy(Integer idCourse) {
		Mono<Course> courseSaved = courseRepository.findById(idCourse);

		checkExistCourse(idCourse, courseSaved);

		return courseSaved;
	}

	@Override
	public Course create(Course course) {
		Mono<Course> mono = courseRepository.save(course);
		return mono.block();
	}

	@Override
	public Course update(Integer id, Course course) {

		Course courseSaved = this.findBy(id);

		courseSaved.setName(course.getName());

		Mono<Course> mono = courseRepository.save(courseSaved);
		return mono.block();
	}

	private void checkExistCourse(Integer id, Mono<Course> courseSaved) {
		if (courseSaved == null) {
			throw new CourseNotFoundException("Doesnt not exist the course with id:" + id);
		}
	}

	@Override
	public Mono<Void> delete(Integer id) {
		Course courseSaved = this.findBy(id);

		return courseRepository.deleteById(id);
	}

}
