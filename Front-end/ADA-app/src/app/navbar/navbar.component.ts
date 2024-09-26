import { Component, DoCheck, OnInit } from '@angular/core';
import { ServicoComponent } from '../servico/servico.component';
import { ContatoComponent } from '../contato/contato.component';
import { NavigationStart, Router, RouterModule } from '@angular/router';
import { SobreComponent } from '../sobre/sobre.component';
import { MeuacessoComponent } from '../meuacesso/meuacesso.component';
import { OrcamentoComponent } from '../orcamento/orcamento.component';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, ServicoComponent, ContatoComponent, RouterModule, SobreComponent, MeuacessoComponent, OrcamentoComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit, DoCheck {
  usuarioLogado = false;

  constructor (private authService: AuthService, private router: Router) {
  }

  reloaded = false;

  ngOnInit(): void {
    this.usuarioLogado = this.authService.isLoggedIn();

  }

  ngDoCheck(): void {
    this.usuarioLogado = this.authService.isLoggedIn();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // Redireciona para a página inicial ou página de login
    window.location.reload(); 
  }

}

