import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CommonModule } from '@angular/common';
import { ServicoComponent } from './servico/servico.component';
import { SobreComponent } from './sobre/sobre.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MeuacessoComponent } from './meuacesso/meuacesso.component';
import { AppRoutes } from './app.routes';
import { OrcamentoComponent } from './orcamento/orcamento.component';
import { RecuperaComponent } from './recupera/recupera.component';
import { HttpClientModule } from '@angular/common/http';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, HeaderComponent, FooterComponent, CommonModule,
 ServicoComponent, SobreComponent, CadastroComponent, FormsModule, AppComponent, LoginComponent, MeuacessoComponent, AppRoutes, RouterModule, CommonModule, OrcamentoComponent, ReactiveFormsModule, RecuperaComponent, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {
  title = 'ADA-app';

  
  ngOnInit(): void {
  }
}
