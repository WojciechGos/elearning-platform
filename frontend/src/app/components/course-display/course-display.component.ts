import { Component, ViewChild, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course/course.service';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/interfaces/course.interface';
import { Router } from '@angular/router';
import { LessonService } from 'src/app/services/lesson/lesson.service';
import { LessonDisplayComponent } from '../lesson-display/lesson-display.component';

@Component({
  selector: 'app-course-display',
  templateUrl: './course-display.component.html',
  styleUrls: ['./course-display.component.css']
})
export class CourseDisplayComponent implements OnInit {
  course!: Course;
  lessonTitle!: string;
  lessonDescription!: string;
  videoUrl !: string;
  showVideo: boolean = true;
  @ViewChild(LessonDisplayComponent) lessonDisplayComponent !: LessonDisplayComponent;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    private router: Router,
    private lessonService: LessonService
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
          console.log(index)

          this.lessonService.getSignedUrlForVideoDownload(this.course.lessons[index].id).subscribe((response) => {
            this.lessonTitle = this.course.lessons[index].title;
            this.lessonDescription = this.course.lessons[index].description;
            this.videoUrl = response.signedUrl;
          });
        }
      });
    });
  }
  

  goToLesson(index: number) {
    this.showVideo = false;
    this.showVideo = true;
    this.refreshPage(index);
    // this.router.navigate(['/course-display', this.course.id, index + 1]);

  }
  refreshPage(index: number) {
    this.router.navigate(['/course-display', this.course.id, index], { skipLocationChange: true }).then(() => {
      this.router.navigate(['/course-display', this.course.id, index + 1]);
    });
  }
}
