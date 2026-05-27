/**
 * Validaciones del lado del cliente para formularios de VentaCine.
 * Controla que los campos numéricos solo acepten números,
 * los campos de texto solo acepten letras, y valida formatos.
 */
document.addEventListener('DOMContentLoaded', function () {

    // =============================================
    // SOLO NÚMEROS: bloquea la entrada de letras
    // Aplicar clase "solo-numeros" al input
    // =============================================
    document.querySelectorAll('.solo-numeros').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            // Permitir teclas de control (Backspace, Tab, Enter, flechas)
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            // Solo permitir dígitos del 0 al 9
            if (!/^[0-9]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Solo se permiten números en este campo');
            }
        });

        // Limpiar caracteres no numéricos al pegar
        campo.addEventListener('input', function () {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    });

    // =============================================
    // SOLO DECIMALES: permite números y un punto
    // Aplicar clase "solo-decimales" al input
    // =============================================
    document.querySelectorAll('.solo-decimales').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            // Permitir dígitos y un solo punto decimal
            if (!/^[0-9.]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Solo se permiten números y punto decimal');
                return;
            }
            // Evitar más de un punto decimal
            if (tecla === '.' && this.value.includes('.')) {
                evento.preventDefault();
            }
        });

        campo.addEventListener('input', function () {
            // Remover todo excepto números y un punto
            var valor = this.value.replace(/[^0-9.]/g, '');
            // Asegurar solo un punto decimal
            var partes = valor.split('.');
            if (partes.length > 2) {
                valor = partes[0] + '.' + partes.slice(1).join('');
            }
            this.value = valor;
        });
    });

    // =============================================
    // SOLO LETRAS: bloquea la entrada de números
    // Aplicar clase "solo-letras" al input
    // =============================================
    document.querySelectorAll('.solo-letras').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            // Permitir letras (incluyendo acentos y ñ) y espacios
            if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Solo se permiten letras en este campo');
            }
        });

        campo.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]/g, '');
        });
    });

    // =============================================
    // VALIDACIÓN DE EMAIL
    // Aplicar clase "validar-email" al input
    // =============================================
    document.querySelectorAll('.validar-email').forEach(function (campo) {
        campo.addEventListener('blur', function () {
            var email = this.value.trim();
            if (email.length > 0) {
                var patron = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                if (!patron.test(email)) {
                    this.classList.add('is-invalid');
                    this.classList.remove('is-valid');
                    mostrarError(campo, 'Por favor ingresá un email válido (ejemplo: nombre@mail.com)');
                } else {
                    this.classList.remove('is-invalid');
                    this.classList.add('is-valid');
                    ocultarError(campo);
                }
            }
        });
    });

    // =============================================
    // VALIDACIÓN DE TELÉFONO
    // Aplicar clase "validar-telefono" al input
    // =============================================
    document.querySelectorAll('.validar-telefono').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            // Permitir números, +, -, (, ), y espacios
            if (!/^[0-9+\-() ]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Formato de teléfono: solo números, +, -, (, )');
            }
        });
    });

    // =============================================
    // VALIDACIÓN DE CAMPOS REQUERIDOS AL ENVIAR
    // =============================================
    document.querySelectorAll('form').forEach(function (formulario) {
        formulario.addEventListener('submit', function (evento) {
            var esValido = true;

            // Verificar campos requeridos vacíos
            formulario.querySelectorAll('[required]').forEach(function (campo) {
                if (!campo.value || campo.value.trim() === '') {
                    campo.classList.add('is-invalid');
                    esValido = false;
                } else {
                    campo.classList.remove('is-invalid');
                }
            });

            // Verificar campos numéricos con valores negativos
            formulario.querySelectorAll('.solo-numeros, .solo-decimales').forEach(function (campo) {
                if (campo.value && parseFloat(campo.value) < 0) {
                    campo.classList.add('is-invalid');
                    esValido = false;
                    mostrarError(campo, 'El valor no puede ser negativo');
                }
            });

            if (!esValido) {
                evento.preventDefault();
                // Hacer scroll al primer campo con error
                var primerError = formulario.querySelector('.is-invalid');
                if (primerError) {
                    primerError.scrollIntoView({behavior: 'smooth', block: 'center'});
                    primerError.focus();
                }
            }
        });
    });

    // =============================================
    // LIMPIAR ESTADO DE ERROR AL MODIFICAR CAMPO
    // =============================================
    document.querySelectorAll('.form-control, .form-select').forEach(function (campo) {
        campo.addEventListener('input', function () {
            if (this.classList.contains('is-invalid') && this.value.trim() !== '') {
                this.classList.remove('is-invalid');
                ocultarError(this);
            }
        });
    });

    // =============================================
    // CONFIRMACIÓN PARA ELIMINAR REGISTROS
    // =============================================
    document.querySelectorAll('.btn-eliminar').forEach(function (boton) {
        boton.addEventListener('click', function (evento) {
            var nombre = this.getAttribute('data-nombre') || 'este registro';
            if (!confirm('¿Estás seguro de que querés eliminar ' + nombre + '? Esta acción no se puede deshacer.')) {
                evento.preventDefault();
            }
        });
    });

    // =============================================
    // FUNCIONES AUXILIARES
    // =============================================

    /**
     * Muestra una advertencia temporal debajo del campo
     */
    function mostrarAdvertencia(campo, mensaje) {
        // Remover advertencia anterior si existe
        var advertenciaExistente = campo.parentElement.querySelector('.advertencia-temp');
        if (advertenciaExistente) {
            advertenciaExistente.remove();
        }

        var advertencia = document.createElement('small');
        advertencia.className = 'advertencia-temp';
        advertencia.style.cssText = 'color: #ffc107; font-size: 0.75rem; display: block; margin-top: 2px;';
        advertencia.textContent = mensaje;
        campo.parentElement.appendChild(advertencia);

        // Remover después de 3 segundos
        setTimeout(function () {
            if (advertencia.parentElement) {
                advertencia.remove();
            }
        }, 3000);
    }

    /**
     * Muestra un mensaje de error persistente debajo del campo
     */
    function mostrarError(campo, mensaje) {
        ocultarError(campo);
        var error = document.createElement('div');
        error.className = 'invalid-feedback';
        error.style.display = 'block';
        error.textContent = mensaje;
        campo.parentElement.appendChild(error);
    }

    /**
     * Oculta el mensaje de error de un campo
     */
    function ocultarError(campo) {
        var errorExistente = campo.parentElement.querySelector('.invalid-feedback');
        if (errorExistente) {
            errorExistente.remove();
        }
    }
});
