import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CadastroService } from './cadastro.service'; // Importe o serviço de cadastro


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioAutenticado: boolean = false;

  constructor(
    private router: Router,
    private cadastroService: CadastroService // Injete o serviço de cadastro

  ) {
    if(this.isBrowser()){
      const storedAuth = sessionStorage.getItem('usuarioAutenticado');
      this.usuarioAutenticado = storedAuth === 'true';
    }
  }

  login(email: string, senha: string): boolean {
    const credenciaisCadastradas = this.cadastroService.getCredenciais().find(c => c.email === email && c.senha === senha);

    if (credenciaisCadastradas) {
      this.usuarioAutenticado = true;
      sessionStorage.setItem('usuarioAutenticado', 'true');
      return true;
    } else {
      this.usuarioAutenticado = false;
      sessionStorage.setItem('usuarioAutenticado', 'false');
      return false;
    }
  }

  logout(): void {
    this.usuarioAutenticado = false;
    sessionStorage.removeItem('usuarioAutenticado');
    this.router.navigate(['/login']); // Redireciona para a página de login após logout
  }

  isLoggedIn(): boolean {
    return this.usuarioAutenticado;
  }

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof sessionStorage !== 'undefined';
  }
}
