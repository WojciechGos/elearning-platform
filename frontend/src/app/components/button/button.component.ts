import { Component, OnInit, Input,  Output, EventEmitter } from '@angular/core';
import {Type} from './button.types';



@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})

export class ButtonComponent implements OnInit {

  @Input() text : string = '';
  @Input() type : string = Type.DEFAULT;
  @Output() buttonClick = new EventEmitter<void>(); // Add this line

  constructor() { }

  ngOnInit(): void {
  }

  getButtonClass(): string {
    let classType:string= 'default-button'; // refers to the css class button

    if (this.type === Type.BUY) {
      classType='buy-button'
    }
    else if (this.type === Type.LOG_IN) {
      classType='log-in-button'
    }
    else if (this.type === Type.SIGN_UP) {
      classType='sign-up-button'
    }
    else if (this.type === Type.SUBMIT) {
      classType='submit-button'
    }
    return classType;
  }

  onClick(): void { // Add this method
    this.buttonClick.emit();
  }
}
