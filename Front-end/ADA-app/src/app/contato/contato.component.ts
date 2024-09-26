import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import {NgForm, NgModel } from '@angular/forms';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-contato',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './contato.component.html',
  styleUrl: './contato.component.css'
})
export class ContatoComponent {

}

