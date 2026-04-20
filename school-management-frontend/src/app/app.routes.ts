
import { Routes } from '@angular/router';
import { AlumnoListComponent } from './features/alumnos/alumno-list/alumno-list.component';

export const routes: Routes = [
  { path: 'alumnos', component: AlumnoListComponent },
  { path: 'alumnos/nuevo', component: AlumnoListComponent }, // Aquí puedes agregar un componente para crear un nuevo alumno
  { path: '', redirectTo: '/alumnos', pathMatch: 'full' } // Redirige al inicio
];