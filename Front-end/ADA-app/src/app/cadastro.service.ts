import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {
  private credenciais: { email: string, senha: string }[] = [];

  constructor() {}

  salvarCredenciais(email: string, senha: string): void {
    this.credenciais.push({ email, senha });
    // Você pode adicionar lógica adicional aqui, como salvar no LocalStorage ou no SessionStorage
  }

  getCredenciais(): { email: string, senha: string }[] {
    return this.credenciais;
  }
}