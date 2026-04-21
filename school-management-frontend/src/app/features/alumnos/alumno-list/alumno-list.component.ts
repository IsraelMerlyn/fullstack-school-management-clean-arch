import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AlumnoService } from '../../../core/services/alumno';
import { Alumno } from '../../../core/models/alumno.model';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-alumno-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule], 
  templateUrl: './alumno-list.html',
  styleUrls: ['./alumno-list.scss']
})
export class AlumnoListComponent implements OnInit {
  alumnos: Alumno[] = [];
  isLoading: boolean = true;
  mostrarModal: boolean = false;
  
  // ... tus variables actuales
  editando: boolean = false; // Para saber si es edición o creación
  alumnoSeleccionado: Alumno = this.initAlumno(); // Alumno vacío inicial

  // Función para inicializar un alumno vacío
  private initAlumno(): Alumno {
    return {id:'', nombre: '', apellido: '', numeroControl: '', carrera: '', telefono: '', email: '' ,imagenurl: ''};
  }

  // Abrir modal para NUEVO
  abrirModalNuevo() {
    this.editando = false;
    this.alumnoSeleccionado = this.initAlumno();
    this.mostrarModal = true;
  }

  // Abrir modal para EDITAR
  abrirModalEditar(alumno: Alumno) {
    this.editando = true;
    // Usamos spread operator (...) para no modificar el original antes de guardar
    this.alumnoSeleccionado = { ...alumno }; 
    this.mostrarModal = true;
  }

  guardar() {
    if (this.editando) {
      // Llamar a tu servicio.update()
      console.log('Actualizando:', this.alumnoSeleccionado);
    } else {
      // Llamar a tu servicio.create()
      console.log('Creando:', this.alumnoSeleccionado);
    }
    this.mostrarModal = false;
  }

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

  // Cambia (id: number | undefined) por (id: any) para evitar el conflicto de tipos momentáneamente
eliminarAlumno(id: any): void {
  if (id !== undefined && confirm('¿Estás seguro de eliminar este alumno?')) {
    // Si tu servicio espera un número pero recibes un string, usamos el signo + para convertirlo
    const idNumerico = typeof id === 'string' ? +id : id;
    
    this.alumnoService.delete(idNumerico).subscribe({
      next: () => {
        this.cargarAlumnos();
      },
      error: (err) => console.error('Error al borrar', err)
    });
  }
}
}