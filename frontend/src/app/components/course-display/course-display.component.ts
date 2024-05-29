import { Component } from '@angular/core';
import { CourseService } from 'src/app/services/course/course.service';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/interfaces/course.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-display',
  templateUrl: './course-display.component.html',
  styleUrls: ['./course-display.component.css']
})
export class CourseDisplayComponent {
  course!: Course;
  lessonTitle!: string;
  lessonDescription!: string;
  videoUrl!: string;
  lessonContent!: string;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const courseId = params.get('id');
      const numCourseId = Number(courseId);
  
      this.courseService.getCourseById(numCourseId).subscribe((course) => {
        if (course !== undefined) {
          this.course = course;
          console.log(course)
          const lessonId = params.get('lessonId');

          let index = 0;
          if (lessonId) {
            index = Number(lessonId) - 1;
          } 
          this.lessonTitle = this.course.lessons[index].title;
          this.lessonDescription = this.course.lessons[index].description;
          this.videoUrl = this.course.lessons[index].videoUrl;
          this.lessonContent = this.course.lessons[index].content;
        }
      });
    });
  }  

  goToLesson(index: number) {
    this.router.navigate(['/course-display', this.course.id, index + 1]);
  }

}
