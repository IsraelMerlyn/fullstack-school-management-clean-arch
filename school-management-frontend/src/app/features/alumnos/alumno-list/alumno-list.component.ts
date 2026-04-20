import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AlumnoService } from '../../../core/services/alumno';
import { Alumno } from '../../../core/models/alumno.model';

@Component({
  selector: 'app-alumno-list',
  standalone: true, // Indica que es un componente moderno e independiente
  imports: [CommonModule, RouterModule], //  AQUÍ registra CommonModule para que funcione el *ngIf y *ngFor
  templateUrl: './alumno-list.html',
  styleUrls: ['./alumno-list.scss'] //  Cambiado a .scss para que coincida con tu archivo
})
export class AlumnoListComponent implements OnInit {
  alumnos: Alumno[] = [];
  isLoading: boolean = true;

  constructor(
    private readonly alumnoService: AlumnoService
  ) {}

  ngOnInit(): void {
    this.cargarAlumnos();
  }

  cargarAlumnos(): void {
    this.isLoading = true;
    this.alumnoService.getAll().subscribe({
      next: (data) => {
        this.alumnos = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al cargar alumnos', err);
        this.isLoading = false;
      }
    });
  }

  eliminarAlumno(id: number | undefined): void {
    if (id && confirm('¿Estás seguro de eliminar este alumno?')) {
      this.alumnoService.delete(id).subscribe(() => {
        this.cargarAlumnos();
      });
    }
  }
}