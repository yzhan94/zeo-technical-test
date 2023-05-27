import { Component } from '@angular/core';
import { routes } from './app-routing.module';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'zeo-users';
  links = routes;

  constructor(public route: ActivatedRoute) { }
}
