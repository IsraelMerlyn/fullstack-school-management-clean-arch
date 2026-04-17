import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscribirMateria } from './inscribir-materia';

describe('InscribirMateria', () => {
  let component: InscribirMateria;
  let fixture: ComponentFixture<InscribirMateria>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InscribirMateria],
    }).compileComponents();

    fixture = TestBed.createComponent(InscribirMateria);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
