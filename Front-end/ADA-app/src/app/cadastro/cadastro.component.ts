import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CadastroService } from '../cadastro.service';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule]
})
export class CadastroComponent {
  cadastroForm: FormGroup;


  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private cadastroService: CadastroService
  ) {


    this.cadastroForm = this.fb.group({
      nome: ['', Validators.required],
      sobrenome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', Validators.required],
      confirmarSenha: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.cadastroForm.valid) {
      // Salvar as credenciais usando o serviço de cadastro
      const { email, senha } = this.cadastroForm.value;
      this.cadastroService.salvarCredenciais(email, senha);

      // Após salvar, redirecionar para a página de perfil
      this.router.navigate(['/login']);
    } else {
      // Exibir mensagens de erro ou marcar os campos inválidos
      this.cadastroForm.markAllAsTouched();
    }
  }
}
