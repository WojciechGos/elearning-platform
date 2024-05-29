import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { create } from 'domain';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { LessonService } from 'src/app/services/lesson/lesson.service';
import { courseSelector } from 'src/app/store/course/course.selectors';
import { UploadState } from 'src/app/enums/upload.state';

@Component({
  selector: 'app-course-creator-lesson-item',
  templateUrl: './course-creator-lesson-item.component.html',
  styleUrls: ['./course-creator-lesson-item.component.css']
})
export class CourseCreatorLessonItemComponent {

  @Input() formGroup !: FormGroup;
  lessonId: number = -1;
  course$: Observable<Course | null> = this.store.pipe(select(courseSelector));
  uploadState: UploadState = UploadState.NOT_STARTED;
  UploadState = UploadState;

  constructor(
    private store: Store<AppStateInterface>,
    private lessonService: LessonService) {
  }



  createOrUpdateLesson(): void {
    this.course$.subscribe((course) => {

      if (course == null)
        return;

      if (this.lessonId === -1) {
        this.createLesson(course.id);
      }
      else {
        this.updateLesson(this.lessonId);
      }

    });
  }
  updateLesson(lessonId: number): void {
    this.lessonService.updateLesson(lessonId, this.formGroup.value).subscribe((lesson) => {
      console.log(`Lesson updated ${lesson}`);
    });
  }

  createLesson(courseId: number): void {
    this.lessonService.createLesson(courseId, this.formGroup.value).subscribe((lesson) => {
      this.lessonId = lesson.id;
    });
  }


  onFileChange(event: any): void {
    const file = event.target.files[0];
    this.uploadState = UploadState.UPLOADING;

    this.course$.subscribe((course) => {

      if (course == null) return;

      if (this.lessonId === -1) {
        this.lessonService.createLesson(course.id, this.formGroup.value).subscribe((lesson) => {
          this.lessonId = lesson.id;
          this.uploadVideo(file);
        });
      }
      else {
        this.uploadVideo(file);
      }
    });

  }

  uploadVideo(file: File): void {
    this.lessonService.getSignedUrlForVideoUpload(this.lessonId).subscribe((signedResponse) => {
      this.lessonService.uploadVideoToSignedUrl(signedResponse.signedUrl, file).subscribe((response) => {
        console.log(response);
        if(response.status === 200){
          this.uploadState = UploadState.UPLOADED;
        }
        else
        {
          this.uploadState = UploadState.ERROR;
        }
      })
    });
  }

  

}

