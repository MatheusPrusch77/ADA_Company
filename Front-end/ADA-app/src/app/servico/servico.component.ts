import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import {RouterModule } from '@angular/router';
import { OrcamentoComponent } from '../orcamento/orcamento.component';


@Component({
  selector: 'app-servico',
  standalone: true,
  imports: [NavbarComponent, ServicoComponent, FooterComponent, RouterModule, OrcamentoComponent],
  templateUrl: './servico.component.html',
  styleUrl: './servico.component.css'
})


export class ServicoComponent {

  
}

