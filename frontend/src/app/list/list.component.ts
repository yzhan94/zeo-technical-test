import { Component } from '@angular/core';
import { User } from '../user.model';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
})
export class ListComponent {
  users: User[] = [{name: 'foo', age: 20, email: 'foo@bar.net'}];
  // TODO fetch users from backend
}
