import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form;

  constructor(private fb: FormBuilder, 
    private authService: AuthService, 
    private router: Router) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  get f () {
    return this.form.controls;
  }

  login() {
   this.authService.login(this.form.value)
   .subscribe(data => {
     this.authService.saveUserInfo(data);
     this.router.navigate(['/bills']);
   })
  }


}
