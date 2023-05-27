import { Component } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';

type Alert = { type: 'success' | 'danger', message: string };


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  userForm = this.fb.group({
    name: ['', Validators.required],
    age: ['', Validators.min(0)],
    email: ['', Validators.compose([Validators.email, Validators.required])],
    password: ['', Validators.required],
  });

  alert?: Alert;

  constructor(private fb: FormBuilder) { }

  handleSubmit() {
    if (this.userForm.valid) {
      // TODO create user in backend
      this.alert = { type: 'success', message: 'Usuario creado' };
      this.userForm.reset();
    } else {
      const messages = [];
      if (!this.userForm.get('name')!!.valid) {
        messages.push('Indique el nombre del usuario');
      }
      if (!this.userForm.get('age')!!.valid) {
        messages.push('La edad del usuario debe ser un valor numérico');
      }
      if (!this.userForm.get('email')!!.valid) {
        messages.push('Introduzca un email válido');
      }
      if (!this.userForm.get('password')!!.valid) {
        messages.push('Debe indicar la contraseña del usuario');
      }
      this.alert = { type: 'danger', message: messages.join('\n') };
    }
  }

  closeAlert() {
    delete this.alert;
  }
}
