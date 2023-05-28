import { Component } from '@angular/core';
import { User } from '../user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
})
export class ListComponent {
  users: User[] = [];

  constructor(private userService: UserService) {
    userService.listUsers()
      .subscribe(users => {
        this.users = users as User[];
      });
  }
}
