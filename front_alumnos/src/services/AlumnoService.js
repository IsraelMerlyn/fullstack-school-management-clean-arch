
import axios from 'axios';

const API_URL = 'http://localhost:8080/alumnos';
export default {
    obtenerAlumnos() {
        return axios.get(`${API_URL}/traer-alumnos`);
    },

    guardarAlumno(alumno) {
        return axios.post(`${API_URL}/insertar-alumnos`, alumno);
    },

    actualizarAlumno(id, alumno) {
        return axios.put(`${API_URL}/editar-alumnos/${id}`, alumno);
    },

    eliminarAlumno(id) {
        return axios.delete(`${API_URL}/eliminar-alumnos/${id}`);
    }
}