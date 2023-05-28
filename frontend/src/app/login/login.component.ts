import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

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

  constructor(private fb: FormBuilder, private router: Router, private loginService: LoginService) { }

  get emailControl() { return this.loginForm.get('email')!!; }
  get passwordControl() { return this.loginForm.get('password')!!; }

  handleSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.loginService.login(email!!, password!!)
        .subscribe({
          next: () => {
            this.router.navigateByUrl('/users');
          },
          error: res => {
            if (res.status === 400) {
              this.alert = 'No existe el usuario con la contraseña';
            } else {
              this.alert = 'Ha ocurrido un error inesperado';
            }
          }
        });
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
