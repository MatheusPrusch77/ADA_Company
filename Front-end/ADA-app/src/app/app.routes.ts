import { NgModule } from '@angular/core';
import { ServicoComponent } from './servico/servico.component';
import { SobreComponent } from './sobre/sobre.component';
import { ContatoComponent } from './contato/contato.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { MeuacessoComponent } from './meuacesso/meuacesso.component';
import {RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { OrcamentoComponent } from './orcamento/orcamento.component';
import { AuthService } from './auth.service';
import { FormsModule } from '@angular/forms';
import { RecuperaComponent } from './recupera/recupera.component';

export const routes: Routes = [
    {path: 'header', component: HeaderComponent},
    {path: 'navbar', component: NavbarComponent},
    {path: 'footer', component: FooterComponent},
    {path: 'servico', component: ServicoComponent},
    {path: 'sobre', component: SobreComponent},
    {path: 'contato', component: ContatoComponent},
    {path: '', redirectTo: '/login', pathMatch: 'full' },
    {path: 'login', component: LoginComponent},
    { path: 'meuacesso', component: MeuacessoComponent},
    {path: 'cadastro', component: CadastroComponent},
    { path: '', redirectTo: '/cadastro', pathMatch: 'full' },
    { path: 'orcamento', component: OrcamentoComponent, canActivate: [AuthGuard] },
    { path: 'recupera', component: RecuperaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes), FormsModule],
  exports: [RouterModule],
  providers: [AuthService]

})


export class AppRoutes{}
