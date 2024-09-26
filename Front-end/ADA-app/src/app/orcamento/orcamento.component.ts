import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-orcamento',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './orcamento.component.html',
  styleUrl: './orcamento.component.css'
})
export class OrcamentoComponent implements OnInit {
  orcamentoForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.orcamentoForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefone: ['', Validators.required],
      descricao: ['', Validators.required],
      publico: ['', Validators.required],
      servico: ['', Validators.required],
      tipoServico: ['', Validators.required],
      urgencia: [false]
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.orcamentoForm.valid) {
      console.log(this.orcamentoForm.value);
      alert('Seu formulário foi enviado, retornaremos em breve!');
      // Aqui você pode adicionar a lógica para enviar o formulário, como fazer uma requisição HTTP
    } else {
      console.log('Formulário inválido');
    }
  }
}