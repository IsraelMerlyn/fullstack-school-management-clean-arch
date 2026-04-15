// Solo letras, acentos y espacios. Mínimo 3, máximo 50 caracteres.
export const validarTexto = (texto) => /^[a-zA-ZÀ-ÿ\s]{3,50}$/.test(texto);

//10 dígitos numéricos
export const validarTelefono = (telefono) => /^\d{10}$/.test(telefono);

// Formato básico de URL
export const validarURL = (url) => {
    if (!url) return true; 
    return /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/.test(url);
};