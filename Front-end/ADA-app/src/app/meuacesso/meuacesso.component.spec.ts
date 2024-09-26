import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeuacessoComponent } from './meuacesso.component';

describe('MeuacessoComponent', () => {
  let component: MeuacessoComponent;
  let fixture: ComponentFixture<MeuacessoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MeuacessoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MeuacessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
