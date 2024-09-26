import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ServicoComponent } from '../servico/servico.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { SobreComponent } from '../sobre/sobre.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [NavbarComponent, ServicoComponent, FooterComponent, RouterModule, SobreComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  constructor(){

  }

  ngOnInit(): void {
  }
}
