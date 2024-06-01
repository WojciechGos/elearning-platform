import { FormGroup, FormControl, Validators } from '@angular/forms';

export const getNewLessonFormGroup = (lessonNumber:number = 1): FormGroup =>{
    return new FormGroup({
      id: new FormControl(-1, {

      }),
      title: new FormControl('', {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
        nonNullable: false,
      }),

      description: new FormControl('', {
        validators: [
          Validators.required
        ],
        nonNullable: false,
      }),

      content: new FormControl('', {
        nonNullable: true,
      }),

      lessonNumber: new FormControl(lessonNumber, {
        validators: [
          Validators.pattern("^[0-9]*$"),
        ],
        nonNullable: true,
      }),

      videoUrl: new FormControl('', {
        nonNullable: true,
      }),
    });
  }
