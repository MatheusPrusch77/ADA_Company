import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CadastroService } from '../cadastro.service';

@Component({
  selector: 'app-login',
  imports: [RouterModule, FormsModule, ReactiveFormsModule],
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {
  email = '';
  senha = '';
  mensagem = '';
  
  
  constructor(
    private fb: FormBuilder, 
    private authService: AuthService,
    private cadastroService: CadastroService, 
    private router: Router

  ) {}
  
  ngOnInit(): void {

  }
  
  login(): void {

    // Verificar se as credenciais existem no serviço de cadastro
    const credenciaisCadastradas = this.cadastroService.getCredenciais().find(c => c.email === this.email && c.senha === this.senha);
    if(this.authService.login(this.email, this.senha)){ 
        this.router.navigate(['/meuacesso']);
        sessionStorage.setItem('reloaded', 'false');
    } else {
      // Mensagem de erro
      this.mensagem = "Credenciais inválidas. Tente novamente!";
    }

  }

  preventDefault(event: Event) {
    event.preventDefault();
  }

}

 

