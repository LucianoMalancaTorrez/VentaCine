
document.addEventListener('DOMContentLoaded', function () {

    document.querySelectorAll('.solo-numeros').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            if (!/^[0-9]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Solo se permiten números en este campo');
            }
        });

        campo.addEventListener('input', function () {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    });

    document.querySelectorAll('.solo-decimales').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            if (!/^[0-9.]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Solo se permiten números y punto decimal');
                return;
            }
            if (tecla === '.' && this.value.includes('.')) {
                evento.preventDefault();
            }
        });

        campo.addEventListener('input', function () {
            var valor = this.value.replace(/[^0-9.]/g, '');
            var partes = valor.split('.');
            if (partes.length > 2) {
                valor = partes[0] + '.' + partes.slice(1).join('');
            }
            this.value = valor;
        });
    });

    document.querySelectorAll('.solo-letras').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Solo se permiten letras en este campo');
            }
        });

        campo.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]/g, '');
        });
    });

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

    document.querySelectorAll('.validar-telefono').forEach(function (campo) {
        campo.addEventListener('keypress', function (evento) {
            var tecla = evento.key;
            if (tecla === 'Backspace' || tecla === 'Tab' || tecla === 'Enter') {
                return;
            }
            if (!/^[0-9+\-() ]$/.test(tecla)) {
                evento.preventDefault();
                mostrarAdvertencia(campo, 'Formato de teléfono: solo números, +, -, (, )');
            }
        });
    });

    document.querySelectorAll('form').forEach(function (formulario) {
        formulario.addEventListener('submit', function (evento) {
            var esValido = true;

            formulario.querySelectorAll('[required]').forEach(function (campo) {
                if (!campo.value || campo.value.trim() === '') {
                    campo.classList.add('is-invalid');
                    esValido = false;
                } else {
                    campo.classList.remove('is-invalid');
                }
            });

            formulario.querySelectorAll('.solo-numeros, .solo-decimales').forEach(function (campo) {
                if (campo.value && parseFloat(campo.value) < 0) {
                    campo.classList.add('is-invalid');
                    esValido = false;
                    mostrarError(campo, 'El valor no puede ser negativo');
                }
            });

            if (!esValido) {
                evento.preventDefault();
                var primerError = formulario.querySelector('.is-invalid');
                if (primerError) {
                    primerError.scrollIntoView({behavior: 'smooth', block: 'center'});
                    primerError.focus();
                }
            }
        });
    });

    document.querySelectorAll('.form-control, .form-select').forEach(function (campo) {
        campo.addEventListener('input', function () {
            if (this.classList.contains('is-invalid') && this.value.trim() !== '') {
                this.classList.remove('is-invalid');
                ocultarError(this);
            }
        });
    });

    document.querySelectorAll('.btn-eliminar').forEach(function (boton) {
        boton.addEventListener('click', function (evento) {
            var nombre = this.getAttribute('data-nombre') || 'este registro';
            if (!confirm('¿Estás seguro de que querés eliminar ' + nombre + '? Esta acción no se puede deshacer.')) {
                evento.preventDefault();
            }
        });
    });

    
    function mostrarAdvertencia(campo, mensaje) {
        var advertenciaExistente = campo.parentElement.querySelector('.advertencia-temp');
        if (advertenciaExistente) {
            advertenciaExistente.remove();
        }

        var advertencia = document.createElement('small');
        advertencia.className = 'advertencia-temp';
        advertencia.style.cssText = 'color: #ffc107; font-size: 0.75rem; display: block; margin-top: 2px;';
        advertencia.textContent = mensaje;
        campo.parentElement.appendChild(advertencia);

        setTimeout(function () {
            if (advertencia.parentElement) {
                advertencia.remove();
            }
        }, 3000);
    }

    
    function mostrarError(campo, mensaje) {
        ocultarError(campo);
        var error = document.createElement('div');
        error.className = 'invalid-feedback';
        error.style.display = 'block';
        error.textContent = mensaje;
        campo.parentElement.appendChild(error);
    }

    
    function ocultarError(campo) {
        var errorExistente = campo.parentElement.querySelector('.invalid-feedback');
        if (errorExistente) {
            errorExistente.remove();
        }
    }
});
