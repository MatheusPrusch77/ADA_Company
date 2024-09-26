import { Component, Injectable, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RecuperaComponent } from '../recupera/recupera.component';

@Injectable({
  providedIn: 'root'
})

export class OrderService {
  // Método para recuperar os pedidos do usuário
  getOrders(userId: string): any[] {
    // Lógica para recuperar os pedidos do usuário de uma fonte de dados
    return [
      { id: '#'+111, product: 'Adaptação do site', status: 'Entregue', data: '15/08/2022' },
      { id: '#'+225, product: 'Manutenção', status: 'Entregue',data: '05/07/2023'},
      { id: '#'+305, product: 'Adaptação do site', status: 'Em desenvolvimento' }
    ];
  }
}

@Component({
  selector: 'app-meuacesso',
  standalone: true,
  imports: [NavbarComponent, RouterModule, FooterComponent, MeuacessoComponent, CommonModule, RecuperaComponent],
  templateUrl: './meuacesso.component.html',
  styleUrl: './meuacesso.component.css'
})
export class MeuacessoComponent implements OnInit{
  orders: any[] = [];
  
  constructor(private router: Router, private orderService: OrderService) {}

  ngOnInit(): void {
    if (!this.isBrowser()) {
      return;
    }

    if (!sessionStorage.getItem('usuarioAutenticado')) {
      this.router.navigate(['/login']);
    }

    // Recuperar os pedidos do usuário ao inicializar o componente
    this.orders = this.orderService.getOrders('userId');

    this.checkAndReload();
  }

  isBrowser(): boolean {
    return typeof window !== 'undefined';
  }

  checkAndReload(): void {
    if (!this.isBrowser()) {
      return;
    }

    const reloaded = sessionStorage.getItem('reloaded');
    if (reloaded === 'false') {
      // Atualizando a flag para evitar recarregar novamente
      sessionStorage.setItem('reloaded', 'true');
      // Recarregando a página
      location.reload();
    }
  }
}