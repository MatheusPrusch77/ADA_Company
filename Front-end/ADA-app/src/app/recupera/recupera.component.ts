import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recupera',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './recupera.component.html',
  styleUrls: ['./recupera.component.css']
})
export class RecuperaComponent {
  email: string = '';

  constructor(private http: HttpClient, private router: Router) { }

  onSubmit() {
    if (!this.email) {
      alert('Por favor, insira seu email.');
      return;
    }

    this.http.post('https://api.exemplo.com/password-reset', { email: this.email }).subscribe({
      next: () => {
        alert('Instruções de recuperação de senha foram enviadas para o seu email.');
        this.router.navigate(['/login']);
      },
      error: error => {
        alert('Um email de verificação foi enviado para o seu endereço de email. Por favor, verifique sua caixa de entrada e siga as instruções para redefinir sua senha.');
        console.error(error);
      }
    });
  }
}

