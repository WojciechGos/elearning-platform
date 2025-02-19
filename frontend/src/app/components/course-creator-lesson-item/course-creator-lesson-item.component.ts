import { Component, EventEmitter, Input, OnInit, Output, isDevMode } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { create } from 'domain';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { LessonService } from 'src/app/services/lesson/lesson.service';
import { courseSelector } from 'src/app/store/course/course.selectors';
import { UploadState } from 'src/app/enums/upload.state';
import { Lesson } from 'src/app/interfaces/lesson.interface';

@Component({
  selector: 'app-course-creator-lesson-item',
  templateUrl: './course-creator-lesson-item.component.html',
  styleUrls: ['./course-creator-lesson-item.component.css']
})
export class CourseCreatorLessonItemComponent implements OnInit {

  @Input() formGroup !: FormGroup;
  @Input() index !: number;
  @Output() deleteLessonEvent = new EventEmitter<void>();
  lessonId: number = -1;
  course$: Observable<Course | null> = this.store.pipe(select(courseSelector));
  uploadState: UploadState = UploadState.NOT_STARTED;
  UploadState = UploadState;
  
  constructor(
    private store: Store<AppStateInterface>,
    private lessonService: LessonService) {
  }

  ngOnInit(): void {
    this.course$.subscribe(course => {
      if (course == null) return;
      if (this.formGroup.controls['id'].value === -1) {
        this.lessonService.createLessonWithLessonNumber(course.id, this.index).subscribe((lesson) => {
          this.lessonId = lesson.id;
          this.formGroup.controls['id'].setValue(lesson.id);
        });
      }
      else {
        this.lessonId = this.formGroup.controls['id'].value;

        if(this.formGroup.controls['videoUrl'].value != null){
          this.uploadState = UploadState.UPLOADED;
        }
      }
    })
  }


  updateLesson(): void {

    const updatedLesson: Lesson = {
      id: this.formGroup.controls['id'].value,
      title: this.formGroup.controls['title'].value,
      description: this.formGroup.controls['description'].value,
      lessonNumber: this.index
    }
    this.lessonService.updateLesson(this.lessonId, updatedLesson).subscribe((lesson) => {

    });
  }

  createLesson(courseId: number): void {
    this.lessonService.createLessonWithLessonNumber(courseId, this.index).subscribe((lesson) => {
      this.lessonId = lesson.id;
    });
  }


  onFileChange(event: any): void {
    if (isDevMode() == true) {
      console.log("onFileChange: ERROR");
      this.uploadState = UploadState.ERROR;
      return;
    }
    console.log("onFileChange: UPLOADING");

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
    console.log("uploadVideo: ?????")
    this.lessonService.getSignedUrlForVideoUpload(this.lessonId).subscribe((signedResponse) => {
      this.lessonService.uploadVideoToSignedUrl(signedResponse.signedUrl, file).subscribe((response) => {
        console.log(response);
        if (response.status === 200) {
          this.uploadState = UploadState.UPLOADED;
        }
        else {
          this.uploadState = UploadState.ERROR;
        }
      })
    });
  }

  deleteLesson():void{
    this.lessonService.deleteLesson(this.lessonId).subscribe((response)=>{
      this.deleteLessonEvent.emit();
    });
  }

}

