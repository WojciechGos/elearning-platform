import { Component, ViewChild, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course/course.service';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/interfaces/course.interface';
import { Router } from '@angular/router';
import { LessonService } from 'src/app/services/lesson/lesson.service';
import { LessonDisplayComponent } from '../lesson-display/lesson-display.component';
import { CommentService } from 'src/app/services/comment/comment.service';
import { Comment } from 'src/app/interfaces/comment.interface';

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
  comments: Comment[] = [];
  newCommentContent: string = '';

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    private router: Router,
    private lessonService: LessonService,
    private commentService: CommentService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const courseId = params.get('id');
      const numCourseId = Number(courseId);

      this.courseService.getCourseById(numCourseId).subscribe((course) => {
        if (course !== undefined) {
          this.course = course;
          const lessonId = params.get('lessonId');

          let index = 0;
          if (lessonId) {
            index = Number(lessonId) - 1;
          }

          this.lessonService.getSignedUrlForVideoDownload(this.course.lessons[index].id).subscribe((response) => {
            this.lessonTitle = this.course.lessons[index].title;
            this.lessonDescription = this.course.lessons[index].description;
            this.videoUrl = response.signedUrl;
          });

          this.getComments();
        }
      });
    });
  }
  
  getComments(): void {
    this.commentService.getCommentsByCourseId(this.course.id)
      .subscribe(comments => this.comments = comments);
  }

  goToLesson(index: number) {
    this.showVideo = false;
    this.showVideo = true;
    this.refreshPage(index);
  }

  refreshPage(index: number) {
    this.router.navigate(['/course-display', this.course.id, index], { skipLocationChange: true }).then(() => {
      this.router.navigate(['/course-display', this.course.id, index + 1]);
    });
  }

  addComment(): void {
    const newComment = {
        content: this.newCommentContent,
        courseId: this.course.id
    };

    this.commentService.addComment(newComment).subscribe((comment) => {
        this.comments.push(comment);
        this.newCommentContent = '';
    });
  }

}
