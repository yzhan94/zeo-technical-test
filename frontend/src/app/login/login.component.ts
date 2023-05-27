import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm = this.fb.group({
    email: ['', Validators.compose([Validators.email, Validators.required])],
    password: ['', Validators.required],
  });

  alert?: string;

  constructor(private fb: FormBuilder) { }

  get emailControl() { return this.loginForm.get('email')!!; }
  get passwordControl() { return this.loginForm.get('password')!!; }

  handleSubmit() {
    if (this.loginForm.valid) {
      // TODO llamar al backend
      const response = 200;
      if (response === 200) {
        // TODO redireccionar
      } else if (response === 400) {
        // TODO analizar respuesta
        this.alert = 'No existe el usuario';
      } else {
        this.alert = 'Ha ocurrido un error inesperado';
      }
    } else {
      // Los errores en el frontend los mostramos en plantilla
      this.emailControl.markAsDirty();
      this.passwordControl.markAsDirty();
    }
  }

  close() {
    delete this.alert;
  }
}
