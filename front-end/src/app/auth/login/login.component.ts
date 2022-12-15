import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginRequestPayload } from './login-request.payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;
  loginRequestPayload : LoginRequestPayload;

  constructor(){
    this.loginRequestPayload = {username: '', password: ''}
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
     username: new FormControl('', Validators.required),
     password: new FormControl('',Validators.required)
    });
  }
  
}
