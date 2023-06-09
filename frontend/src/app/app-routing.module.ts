import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ListComponent } from './list/list.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
  { title: 'Crear usuario', component: SignupComponent, path: 'signup' },
  { title: 'Acceder', component: LoginComponent, path: 'login' },
  { title: 'Listado usuario', component: ListComponent, path: 'users' }, // TODO authenticated guard
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
