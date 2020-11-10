import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  login: boolean;
  register: boolean;

  constructor() { }

  ngOnInit(): void {
    this.login = false;
    this.register = false;
  }

  selectRegister() {
    this.register = true;
  }

  selectLogin() {
    this.login = true;
  }

}
