import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ServicoComponent } from '../servico/servico.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sobre',
  standalone: true,
  imports: [NavbarComponent, ServicoComponent, FooterComponent, RouterModule],
  templateUrl: './sobre.component.html',
  styleUrl: './sobre.component.css'
})
export class SobreComponent {

}
