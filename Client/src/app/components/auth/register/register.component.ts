import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

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

  register() {
    console.log(this.form.controls)
    this.authService.register(this.form.value)
    .subscribe( data => {
  
      this.router.navigate(['/home'])
    })
  }

}
