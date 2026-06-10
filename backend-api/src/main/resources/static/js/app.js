const api = {
    usuarios: "/api/v1/usuarios",
    actividades: "/api/v1/actividades",
    reservas: "/api/v1/reservas",
    incidencias: "/api/v1/incidencias",
    health: "/api/v1/health"
};

let usuariosCache = [];
let actividadesCache = [];
let reservasCache = [];
let incidenciasCache = [];
let calendarDate = new Date();
let modalContext = null;
let autoRefreshId = null;

async function obtenerDatos(endpoint) {
    const response = await fetch(endpoint);

    if (!response.ok) {
        throw new Error("Error al obtener datos de " + endpoint);
    }

    return await response.json();
}

async function enviarDatos(endpoint, data) {
    const response = await fetch(endpoint, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        throw new Error("No se pudo crear el registro");
    }

    return await response.json();
}

async function actualizarDato(endpoint, data) {
    const response = await fetch(endpoint, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        throw new Error("No se pudo actualizar el registro");
    }

    return await response.json();
}

async function eliminarDato(endpoint) {
    const response = await fetch(endpoint, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("No se pudo eliminar el registro");
    }
}

async function comprobarApi() {
    const estado = document.getElementById("api-status");
    const dot = document.getElementById("api-dot");

    try {
        const response = await fetch(api.health);

        if (response.ok) {
            estado.textContent = "API operativa";
            dot.className = "status-dot ok";
        } else {
            estado.textContent = "API con errores";
            dot.className = "status-dot";
        }
    } catch (error) {
        estado.textContent = "API no disponible";
        dot.className = "status-dot error";
    }
}

async function cargarDatosBase() {
    usuariosCache = await obtenerDatos(api.usuarios);
    actividadesCache = await obtenerDatos(api.actividades);
    reservasCache = await obtenerDatos(api.reservas);
    incidenciasCache = await obtenerDatos(api.incidencias);
}

function actualizarContadores() {
    animarNumero(document.getElementById("usuarios-count"), usuariosCache.length);
    animarNumero(document.getElementById("actividades-count"), actividadesCache.length);
    animarNumero(document.getElementById("reservas-count"), reservasCache.length);
    animarNumero(document.getElementById("incidencias-count"), incidenciasCache.length);

    actualizarBarra("usuarios-bar", "usuarios-bar-value", usuariosCache.length);
    actualizarBarra("actividades-bar", "actividades-bar-value", actividadesCache.length);
    actualizarBarra("reservas-bar", "reservas-bar-value", reservasCache.length);
    actualizarBarra("incidencias-bar", "incidencias-bar-value", incidenciasCache.length);
    actualizarKPIsEjecutivos();
}

function actualizarKPIsEjecutivos() {
    const ingresos = reservasCache
        .filter(reserva => reserva.estado !== "CANCELADA")
        .reduce((total, reserva) => {
            const actividad = actividadesCache.find(a => a.id === reserva.idActividad);
            return total + (actividad ? Number(actividad.precio) : 0);
        }, 0);

    const actividadTop = obtenerActividadTop();
    const ocupacionMedia = calcularOcupacionMedia();
    const incidenciasAbiertas = incidenciasCache
        .filter(incidencia => incidencia.estado === "ABIERTA")
        .length;

    setText("kpi-ingresos", ingresos.toFixed(2) + " €");
    setText("kpi-actividad-top", actividadTop);
    setText("kpi-ocupacion", ocupacionMedia + "%");
    setText("kpi-incidencias-abiertas", incidenciasAbiertas);
}

function obtenerActividadTop() {
    const conteo = contarReservasPorActividad();
    const entradas = Object.entries(conteo);

    if (entradas.length === 0) {
        return "-";
    }

    return entradas.sort((a, b) => b[1] - a[1])[0][0];
}

function calcularOcupacionMedia() {
    if (actividadesCache.length === 0) {
        return 0;
    }

    const total = actividadesCache.reduce((suma, actividad) => {
        if (!actividad.plazasMaximas || actividad.plazasMaximas === 0) {
            return suma;
        }

        return suma + (actividad.plazasOcupadas / actividad.plazasMaximas) * 100;
    }, 0);

    return Math.round(total / actividadesCache.length);
}

function actualizarBarra(barId, valueId, cantidad) {
    const porcentaje = Math.min(cantidad * 15, 100);

    document.getElementById(barId).style.width = porcentaje + "%";
    document.getElementById(valueId).textContent = cantidad;
}

function setText(id, value) {
    const element = document.getElementById(id);

    if (element) {
        element.textContent = value;
    }
}

function cargarSelects() {
    const incidenciaUsuario = document.getElementById("incidencia-usuario");
    const reservaUsuario = document.getElementById("reserva-usuario");
    const reservaActividad = document.getElementById("reserva-actividad");

    incidenciaUsuario.innerHTML = "";
    reservaUsuario.innerHTML = "";
    reservaActividad.innerHTML = "";

    usuariosCache.forEach(usuario => {
        incidenciaUsuario.appendChild(crearOption(usuario.id, usuario.nombre));
        reservaUsuario.appendChild(crearOption(usuario.id, usuario.nombre));
    });

    actividadesCache.forEach(actividad => {
        const texto = `${actividad.nombre} (${actividad.plazasDisponibles} plazas)`;
        const option = crearOption(actividad.id, texto);

        if (actividad.plazasDisponibles <= 0) {
            option.disabled = true;
            option.textContent = `${actividad.nombre} (sin plazas)`;
        }

        reservaActividad.appendChild(option);
    });
}

function crearOption(value, text) {
    const option = document.createElement("option");

    option.value = value;
    option.textContent = text;

    return option;
}

function renderActividades() {
    const contenedor = document.getElementById("latest-activities");
    const textoBusqueda = normalizar(document.getElementById("buscar-actividad").value);
    const tipoSeleccionado = document.getElementById("filtro-tipo-actividad").value;
    const plazasSeleccionadas = document.getElementById("filtro-plazas-actividad").value;

    const actividadesFiltradas = actividadesCache.filter(actividad => {
        const coincideTexto = normalizar(actividad.nombre).includes(textoBusqueda);
        const coincideTipo = tipoSeleccionado === "TODAS" || actividad.tipoActividad === tipoSeleccionado;
        const coincidePlazas =
            plazasSeleccionadas === "TODAS" ||
            (plazasSeleccionadas === "DISPONIBLES" && actividad.plazasDisponibles > 0) ||
            (plazasSeleccionadas === "COMPLETAS" && actividad.plazasDisponibles <= 0);

        return coincideTexto && coincideTipo && coincidePlazas;
    });

    contenedor.innerHTML = "";

    if (actividadesFiltradas.length === 0) {
        contenedor.innerHTML = "<p>No hay actividades con esos filtros.</p>";
        return;
    }

    actividadesFiltradas.forEach(actividad => {
        const tipoClase = actividad.tipoActividad === "DEPORTIVA"
            ? "tipo-deportiva"
            : "tipo-academica";

        const plazasClase = actividad.plazasDisponibles > 0
            ? "plazas-ok"
            : "plazas-full";

        const plazasTexto = actividad.plazasDisponibles > 0
            ? `🟢 ${actividad.plazasDisponibles} plazas disponibles`
            : "🔴 Sin plazas disponibles";

        const card = document.createElement("article");

        card.className = `data-card ${tipoClase}`;
        card.innerHTML = `
            <h3>${actividad.nombre}</h3>
            <p><strong>Tipo:</strong> ${actividad.tipoActividad}</p>
            <p><strong>Duración:</strong> ${actividad.duracion} minutos</p>
            <p class="${plazasClase}">${plazasTexto}</p>
            <p><strong>Precio:</strong> ${actividad.precio} €</p>
            <div class="card-actions">
                <button class="btn ghost-light" onclick="abrirModalActividad(${actividad.id})">Editar</button>
                <button class="btn btn-danger" onclick="eliminarActividad(${actividad.id})">Eliminar</button>
            </div>
        `;

        contenedor.appendChild(card);
    });
}

function renderUsuarios() {
    const contenedor = document.getElementById("usuarios-grid");
    const textoBusqueda = normalizar(document.getElementById("buscar-usuario").value);
    const tipoSeleccionado = document.getElementById("filtro-tipo-usuario").value;

    const usuariosFiltrados = usuariosCache.filter(usuario => {
        const textoCompleto = normalizar(`${usuario.nombre} ${usuario.dni} ${usuario.email}`);
        const coincideTexto = textoCompleto.includes(textoBusqueda);
        const coincideTipo = tipoSeleccionado === "TODOS" || usuario.tipoUsuario === tipoSeleccionado;

        return coincideTexto && coincideTipo;
    });

    contenedor.innerHTML = "";

    if (usuariosFiltrados.length === 0) {
        contenedor.innerHTML = "<p>No hay usuarios con esos filtros.</p>";
        return;
    }

    usuariosFiltrados.forEach(usuario => {
        const card = document.createElement("article");

        card.className = "data-card";
        card.innerHTML = `
            <h3>${usuario.nombre}</h3>
            <p><strong>DNI:</strong> ${usuario.dni}</p>
            <p><strong>Email:</strong> ${usuario.email}</p>
            <p><strong>Teléfono:</strong> ${usuario.telefono || "-"}</p>
            <span class="badge-soft ${obtenerClaseTipoUsuario(usuario.tipoUsuario)}">
                ${usuario.tipoUsuario}
            </span>
            <div class="card-actions">
                <button class="btn ghost-light" onclick="abrirModalUsuario(${usuario.id})">Editar</button>
            </div>
        `;

        contenedor.appendChild(card);
    });
}

function renderReservas() {
    const contenedor = document.getElementById("reservas-grid");
    const textoBusqueda = normalizar(document.getElementById("buscar-reserva").value);
    const estadoSeleccionado = document.getElementById("filtro-estado-reserva").value;

    const reservasFiltradas = reservasCache.filter(reserva => {
        const usuario = usuariosCache.find(u => u.id === reserva.idUsuario);
        const actividad = actividadesCache.find(a => a.id === reserva.idActividad);

        const nombreUsuario = usuario ? usuario.nombre : `Usuario ${reserva.idUsuario}`;
        const nombreActividad = actividad ? actividad.nombre : `Actividad ${reserva.idActividad}`;
        const textoCompleto = normalizar(`${reserva.id} ${nombreUsuario} ${nombreActividad} ${reserva.fecha} ${reserva.estado}`);
        const coincideTexto = textoCompleto.includes(textoBusqueda);
        const coincideEstado = estadoSeleccionado === "TODAS" || reserva.estado === estadoSeleccionado;

        return coincideTexto && coincideEstado;
    });

    contenedor.innerHTML = "";

    if (reservasFiltradas.length === 0) {
        contenedor.innerHTML = "<p>No hay reservas con esos filtros.</p>";
        return;
    }

    reservasFiltradas.forEach(reserva => {
        const usuario = usuariosCache.find(u => u.id === reserva.idUsuario);
        const actividad = actividadesCache.find(a => a.id === reserva.idActividad);

        const nombreUsuario = usuario ? usuario.nombre : `Usuario ${reserva.idUsuario}`;
        const nombreActividad = actividad ? actividad.nombre : `Actividad ${reserva.idActividad}`;

        const card = document.createElement("article");

        card.className = "data-card";
        card.innerHTML = `
            <h3>Reserva #${reserva.id}</h3>
            <p><strong>Usuario:</strong> ${nombreUsuario}</p>
            <p><strong>Actividad:</strong> ${nombreActividad}</p>
            <p><strong>Fecha:</strong> ${reserva.fecha}</p>
            <span class="badge-soft ${obtenerClaseEstadoReserva(reserva.estado)}">
                ${reserva.estado}
            </span>
            <div class="card-actions">
                <button class="btn ghost-light" onclick="abrirModalReserva(${reserva.id})">Editar</button>
                <button class="btn btn-danger" onclick="cancelarReserva(${reserva.id})">Cancelar</button>
            </div>
        `;

        contenedor.appendChild(card);
    });
}

function renderIncidencias() {
    const contenedor = document.getElementById("incidencias-grid");
    const textoBusqueda = normalizar(document.getElementById("buscar-incidencia").value);
    const estadoSeleccionado = document.getElementById("filtro-estado-incidencia").value;

    const incidenciasFiltradas = incidenciasCache.filter(incidencia => {
        const usuario = usuariosCache.find(u => u.id === incidencia.idUsuario);
        const nombreUsuario = usuario ? usuario.nombre : `Usuario ${incidencia.idUsuario}`;
        const textoCompleto = normalizar(`${incidencia.asunto} ${incidencia.descripcion} ${nombreUsuario} ${incidencia.estado}`);
        const coincideTexto = textoCompleto.includes(textoBusqueda);
        const coincideEstado = estadoSeleccionado === "TODAS" || incidencia.estado === estadoSeleccionado;

        return coincideTexto && coincideEstado;
    });

    contenedor.innerHTML = "";

    if (incidenciasFiltradas.length === 0) {
        contenedor.innerHTML = "<p>No hay incidencias con esos filtros.</p>";
        return;
    }

    incidenciasFiltradas.forEach(incidencia => {
        const usuario = usuariosCache.find(u => u.id === incidencia.idUsuario);
        const nombreUsuario = usuario ? usuario.nombre : `Usuario ${incidencia.idUsuario}`;

        const card = document.createElement("article");

        card.className = "data-card";
        card.innerHTML = `
            <h3>${incidencia.asunto}</h3>
            <p><strong>Usuario:</strong> ${nombreUsuario}</p>
            <p>${incidencia.descripcion}</p>
            <p><strong>Fecha:</strong> ${incidencia.fecha || "-"}</p>
            <span class="badge-soft ${obtenerClaseEstadoIncidencia(incidencia.estado)}">
                ${obtenerIconoEstadoIncidencia(incidencia.estado)} ${incidencia.estado}
            </span>
            <div class="card-actions">
                <button class="btn ghost-light" onclick="abrirModalIncidencia(${incidencia.id})">Editar</button>
            </div>
        `;

        contenedor.appendChild(card);
    });
}

function renderGraficas() {
    renderGrafica("chart-usuarios-tipo", contarPorCampo(usuariosCache, "tipoUsuario"));
    renderGrafica("chart-actividades-tipo", contarPorCampo(actividadesCache, "tipoActividad"));
    renderGrafica("chart-incidencias-estado", contarPorCampo(incidenciasCache, "estado"));
    renderGrafica("chart-reservas-actividad", contarReservasPorActividad());
}

function renderGrafica(idContenedor, datos) {
    const contenedor = document.getElementById(idContenedor);
    const valores = Object.values(datos);
    const maximo = valores.length > 0 ? Math.max(...valores) : 0;

    contenedor.innerHTML = "";

    if (maximo === 0) {
        contenedor.innerHTML = "<p>No hay datos suficientes.</p>";
        return;
    }

    Object.entries(datos).forEach(([nombre, valor]) => {
        const porcentaje = Math.max((valor / maximo) * 100, 8);
        const item = document.createElement("div");

        item.className = "chart-row";
        item.innerHTML = `
            <div class="chart-label">
                <span>${nombre}</span>
                <strong>${valor}</strong>
            </div>
            <div class="chart-track">
                <div class="chart-fill" style="width: ${porcentaje}%"></div>
            </div>
        `;

        contenedor.appendChild(item);
    });
}

function renderCalendario() {
    const contenedor = document.getElementById("calendar-grid");
    const titulo = document.getElementById("calendar-title");
    const year = calendarDate.getFullYear();
    const month = calendarDate.getMonth();

    const nombreMes = calendarDate.toLocaleDateString("es-ES", {
        month: "long",
        year: "numeric"
    });

    titulo.textContent = nombreMes.charAt(0).toUpperCase() + nombreMes.slice(1);
    contenedor.innerHTML = "";

    const diasSemana = ["L", "M", "X", "J", "V", "S", "D"];

    diasSemana.forEach(dia => {
        const celda = document.createElement("div");
        celda.className = "calendar-head";
        celda.textContent = dia;
        contenedor.appendChild(celda);
    });

    const primerDia = new Date(year, month, 1);
    const ultimoDia = new Date(year, month + 1, 0);
    const offset = (primerDia.getDay() + 6) % 7;

    for (let i = 0; i < offset; i++) {
        const celdaVacia = document.createElement("div");
        celdaVacia.className = "calendar-day muted";
        contenedor.appendChild(celdaVacia);
    }

    for (let day = 1; day <= ultimoDia.getDate(); day++) {
        const fecha = `${year}-${String(month + 1).padStart(2, "0")}-${String(day).padStart(2, "0")}`;
        const reservasDia = reservasCache.filter(reserva => reserva.fecha === fecha);
        const celda = document.createElement("div");

        celda.className = "calendar-day";
        celda.innerHTML = `<strong>${day}</strong>`;

        reservasDia.forEach(reserva => {
            const actividad = actividadesCache.find(a => a.id === reserva.idActividad);
            const item = document.createElement("span");

            item.className = "calendar-event";
            item.textContent = actividad ? actividad.nombre : `Reserva ${reserva.id}`;

            celda.appendChild(item);
        });

        contenedor.appendChild(celda);
    }
}

function cambiarMesCalendario(direccion) {
    calendarDate.setMonth(calendarDate.getMonth() + direccion);
    renderCalendario();
}

function contarPorCampo(lista, campo) {
    return lista.reduce((acumulador, item) => {
        const clave = item[campo] || "SIN_DATO";
        acumulador[clave] = (acumulador[clave] || 0) + 1;
        return acumulador;
    }, {});
}

function contarReservasPorActividad() {
    return reservasCache.reduce((acumulador, reserva) => {
        const actividad = actividadesCache.find(a => a.id === reserva.idActividad);
        const nombre = actividad ? actividad.nombre : `Actividad ${reserva.idActividad}`;
        acumulador[nombre] = (acumulador[nombre] || 0) + 1;
        return acumulador;
    }, {});
}

function obtenerClaseTipoUsuario(tipoUsuario) {
    if (tipoUsuario === "ALUMNO") {
        return "tipo-alumno";
    }

    if (tipoUsuario === "SOCIO") {
        return "tipo-socio";
    }

    return "tipo-ambos";
}

function obtenerClaseEstadoReserva(estado) {
    if (estado === "ACTIVA") {
        return "estado-activa";
    }

    if (estado === "CANCELADA") {
        return "estado-cancelada";
    }

    return "estado-finalizada";
}

function obtenerClaseEstadoIncidencia(estado) {
    if (estado === "ABIERTA") {
        return "estado-abierta";
    }

    if (estado === "RESUELTA") {
        return "estado-resuelta";
    }

    return "estado-proceso";
}

function obtenerIconoEstadoIncidencia(estado) {
    if (estado === "ABIERTA") {
        return "🔴";
    }

    if (estado === "RESUELTA") {
        return "🟢";
    }

    return "🟡";
}

function normalizar(texto) {
    return texto
        .toString()
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");
}

function mostrarToast(mensaje, esError = false) {
    const toast = document.getElementById("toast");

    toast.textContent = mensaje;
    toast.className = esError ? "toast error" : "toast";

    mostrarNotificacion(mensaje, esError);

    setTimeout(() => {
        toast.className = "toast hidden";
    }, 3500);
}

function mostrarNotificacion(mensaje, esError = false) {
    const centro = document.getElementById("notification-center");

    if (!centro) {
        return;
    }

    const notificacion = document.createElement("div");

    notificacion.className = esError ? "notification error" : "notification";
    notificacion.textContent = mensaje;

    centro.appendChild(notificacion);

    setTimeout(() => {
        notificacion.remove();
    }, 4200);
}

function inicializarFechas() {
    const hoy = new Date().toISOString().split("T")[0];

    document.getElementById("incidencia-fecha").value = hoy;
    document.getElementById("reserva-fecha").value = hoy;
}

function inicializarFormularios() {
    const incidenciaForm = document.getElementById("incidencia-form");
    const reservaForm = document.getElementById("reserva-form");
    const usuarioForm = document.getElementById("usuario-form");
    const actividadForm = document.getElementById("actividad-form");
    const editForm = document.getElementById("edit-form");

    incidenciaForm.addEventListener("submit", async event => {
        event.preventDefault();

        const incidencia = {
            idUsuario: Number(document.getElementById("incidencia-usuario").value),
            asunto: document.getElementById("incidencia-asunto").value,
            descripcion: document.getElementById("incidencia-descripcion").value,
            fecha: document.getElementById("incidencia-fecha").value,
            estado: "ABIERTA"
        };

        try {
            await enviarDatos(api.incidencias, incidencia);
            incidenciaForm.reset();
            inicializarFechas();
            mostrarToast("Incidencia creada correctamente");
            await refrescarDashboard();
        } catch (error) {
            mostrarToast("No se pudo crear la incidencia", true);
        }
    });

    reservaForm.addEventListener("submit", async event => {
        event.preventDefault();

        const actividadSeleccionada = actividadesCache.find(
            actividad => actividad.id === Number(document.getElementById("reserva-actividad").value)
        );

        if (!actividadSeleccionada || actividadSeleccionada.plazasDisponibles <= 0) {
            mostrarToast("No hay plazas disponibles para esta actividad", true);
            return;
        }

        const reserva = {
            idUsuario: Number(document.getElementById("reserva-usuario").value),
            idActividad: Number(document.getElementById("reserva-actividad").value),
            fecha: document.getElementById("reserva-fecha").value,
            estado: "ACTIVA"
        };

        try {
            await enviarDatos(api.reservas, reserva);
            reservaForm.reset();
            inicializarFechas();
            mostrarToast("Reserva creada correctamente");
            await refrescarDashboard();
        } catch (error) {
            mostrarToast("No se pudo crear la reserva", true);
        }
    });

    usuarioForm.addEventListener("submit", async event => {
        event.preventDefault();

        const usuario = {
            nombre: document.getElementById("usuario-nombre").value,
            dni: document.getElementById("usuario-dni").value,
            email: document.getElementById("usuario-email").value,
            telefono: document.getElementById("usuario-telefono").value,
            tipoUsuario: document.getElementById("usuario-tipo").value
        };

        try {
            await enviarDatos(api.usuarios, usuario);
            usuarioForm.reset();
            mostrarToast("Usuario creado correctamente");
            await refrescarDashboard();
        } catch (error) {
            mostrarToast("No se pudo crear el usuario", true);
        }
    });

    actividadForm.addEventListener("submit", async event => {
        event.preventDefault();

        const actividad = {
            nombre: document.getElementById("actividad-nombre").value,
            tipoActividad: document.getElementById("actividad-tipo").value,
            duracion: Number(document.getElementById("actividad-duracion").value),
            precio: Number(document.getElementById("actividad-precio").value),
            plazasMaximas: Number(document.getElementById("actividad-plazas-maximas").value),
            plazasOcupadas: 0
        };

        try {
            await enviarDatos(api.actividades, actividad);
            actividadForm.reset();
            mostrarToast("Actividad creada correctamente");
            await refrescarDashboard();
        } catch (error) {
            mostrarToast("No se pudo crear la actividad", true);
        }
    });

    editForm.addEventListener("submit", guardarModal);
}

function inicializarFiltros() {
    const filtros = [
        ["buscar-actividad", renderActividades],
        ["filtro-tipo-actividad", renderActividades],
        ["filtro-plazas-actividad", renderActividades],
        ["buscar-usuario", renderUsuarios],
        ["filtro-tipo-usuario", renderUsuarios],
        ["buscar-reserva", renderReservas],
        ["filtro-estado-reserva", renderReservas],
        ["buscar-incidencia", renderIncidencias],
        ["filtro-estado-incidencia", renderIncidencias]
    ];

    filtros.forEach(([id, callback]) => {
        const elemento = document.getElementById(id);
        const evento = elemento.tagName === "INPUT" ? "input" : "change";

        elemento.addEventListener(evento, callback);
    });
}

async function cancelarReserva(id) {
    const confirmar = confirm("¿Quieres cancelar esta reserva?");

    if (!confirmar) {
        return;
    }

    try {
        await eliminarDato(api.reservas + "/" + id);
        mostrarToast("Reserva cancelada correctamente");
        await refrescarDashboard();
    } catch (error) {
        mostrarToast("No se pudo cancelar la reserva", true);
    }
}

async function eliminarActividad(id) {
    const confirmar = confirm("¿Quieres eliminar esta actividad?");

    if (!confirmar) {
        return;
    }

    try {
        await eliminarDato(api.actividades + "/" + id);
        mostrarToast("Actividad eliminada correctamente");
        await refrescarDashboard();
    } catch (error) {
        mostrarToast("No se pudo eliminar la actividad", true);
    }
}


function marcarElementosDinamicosVisibles() {
    const elementos = document.querySelectorAll(".data-card, .chart-card, .executive-card, .form-card, .historical-card, .system-card");

    elementos.forEach(elemento => {
        elemento.classList.add("visible");
    });
}

async function refrescarDashboard() {
    await cargarDatosBase();

    actualizarContadores();
    cargarSelects();
    renderActividades();
    renderUsuarios();
    renderReservas();
    renderIncidencias();
    renderGraficas();
    renderHistorico();
    renderCalendario();
    marcarElementosDinamicosVisibles();
}

function abrirModalUsuario(id) {
    const usuario = usuariosCache.find(item => item.id === id);

    modalContext = {
        tipo: "usuarios",
        id
    };

    abrirModal("Editar usuario", `
        <label>Nombre<input id="modal-nombre" value="${usuario.nombre}" required></label>
        <label>DNI<input id="modal-dni" value="${usuario.dni}" required></label>
        <label>Email<input id="modal-email" type="email" value="${usuario.email}" required></label>
        <label>Teléfono<input id="modal-telefono" value="${usuario.telefono || ""}"></label>
        <label>Tipo
            <select id="modal-tipoUsuario">
                <option value="ALUMNO" ${usuario.tipoUsuario === "ALUMNO" ? "selected" : ""}>ALUMNO</option>
                <option value="SOCIO" ${usuario.tipoUsuario === "SOCIO" ? "selected" : ""}>SOCIO</option>
                <option value="AMBOS" ${usuario.tipoUsuario === "AMBOS" ? "selected" : ""}>AMBOS</option>
            </select>
        </label>
        <button class="btn primary full" type="submit">Guardar cambios</button>
    `);
}

function abrirModalActividad(id) {
    const actividad = actividadesCache.find(item => item.id === id);

    modalContext = {
        tipo: "actividades",
        id
    };

    abrirModal("Editar actividad", `
        <label>Nombre<input id="modal-nombre" value="${actividad.nombre}" required></label>
        <label>Tipo
            <select id="modal-tipoActividad">
                <option value="ACADEMICA" ${actividad.tipoActividad === "ACADEMICA" ? "selected" : ""}>ACADEMICA</option>
                <option value="DEPORTIVA" ${actividad.tipoActividad === "DEPORTIVA" ? "selected" : ""}>DEPORTIVA</option>
            </select>
        </label>
        <label>Duración<input id="modal-duracion" type="number" value="${actividad.duracion}" required></label>
        <label>Precio<input id="modal-precio" type="number" step="0.01" value="${actividad.precio}" required></label>
        <label>Plazas máximas<input id="modal-plazasMaximas" type="number" value="${actividad.plazasMaximas}" required></label>
        <label>Plazas ocupadas<input id="modal-plazasOcupadas" type="number" value="${actividad.plazasOcupadas}" required></label>
        <button class="btn primary full" type="submit">Guardar cambios</button>
    `);
}

function abrirModalReserva(id) {
    const reserva = reservasCache.find(item => item.id === id);

    modalContext = {
        tipo: "reservas",
        id
    };

    abrirModal("Editar reserva", `
        <label>Usuario<select id="modal-idUsuario">${opcionesUsuarios(reserva.idUsuario)}</select></label>
        <label>Actividad<select id="modal-idActividad">${opcionesActividades(reserva.idActividad)}</select></label>
        <label>Fecha<input id="modal-fecha" type="date" value="${reserva.fecha}" required></label>
        <label>Estado
            <select id="modal-estado">
                <option value="ACTIVA" ${reserva.estado === "ACTIVA" ? "selected" : ""}>ACTIVA</option>
                <option value="CANCELADA" ${reserva.estado === "CANCELADA" ? "selected" : ""}>CANCELADA</option>
                <option value="FINALIZADA" ${reserva.estado === "FINALIZADA" ? "selected" : ""}>FINALIZADA</option>
            </select>
        </label>
        <button class="btn primary full" type="submit">Guardar cambios</button>
    `);
}

function abrirModalIncidencia(id) {
    const incidencia = incidenciasCache.find(item => item.id === id);

    modalContext = {
        tipo: "incidencias",
        id
    };

    abrirModal("Editar incidencia", `
        <label>Usuario<select id="modal-idUsuario">${opcionesUsuarios(incidencia.idUsuario)}</select></label>
        <label>Asunto<input id="modal-asunto" value="${incidencia.asunto}" required></label>
        <label>Descripción<textarea id="modal-descripcion" required>${incidencia.descripcion}</textarea></label>
        <label>Fecha<input id="modal-fecha" type="date" value="${incidencia.fecha}" required></label>
        <label>Estado
            <select id="modal-estado">
                <option value="ABIERTA" ${incidencia.estado === "ABIERTA" ? "selected" : ""}>ABIERTA</option>
                <option value="EN_PROCESO" ${incidencia.estado === "EN_PROCESO" ? "selected" : ""}>EN_PROCESO</option>
                <option value="RESUELTA" ${incidencia.estado === "RESUELTA" ? "selected" : ""}>RESUELTA</option>
            </select>
        </label>
        <button class="btn primary full" type="submit">Guardar cambios</button>
    `);
}

function abrirModal(titulo, contenido) {
    const modal = document.getElementById("edit-modal");
    const modalTitle = document.getElementById("modal-title");
    const editForm = document.getElementById("edit-form");

    if (!modal || !modalTitle || !editForm) {
        mostrarToast("No se pudo abrir el editor", true);
        return;
    }

    modalTitle.textContent = titulo;
    editForm.innerHTML = contenido;

    modal.classList.remove("hidden");
    document.body.classList.add("modal-open");
}

function cerrarModal() {
    const modal = document.getElementById("edit-modal");
    const editForm = document.getElementById("edit-form");

    if (modal) {
        modal.classList.add("hidden");
    }

    if (editForm) {
        editForm.innerHTML = "";
    }

    document.body.classList.remove("modal-open");
    modalContext = null;
}

async function guardarModal(event) {
    event.preventDefault();

    if (!modalContext) {
        return;
    }

    const data = obtenerDatosModal();

    try {
        await actualizarDato(api[modalContext.tipo] + "/" + modalContext.id, data);
        cerrarModal();
        mostrarToast("Registro actualizado correctamente");
        await refrescarDashboard();
    } catch (error) {
        mostrarToast("No se pudo actualizar el registro", true);
    }
}

function obtenerDatosModal() {
    if (modalContext.tipo === "usuarios") {
        return {
            nombre: document.getElementById("modal-nombre").value,
            dni: document.getElementById("modal-dni").value,
            email: document.getElementById("modal-email").value,
            telefono: document.getElementById("modal-telefono").value,
            tipoUsuario: document.getElementById("modal-tipoUsuario").value
        };
    }

    if (modalContext.tipo === "actividades") {
        return {
            nombre: document.getElementById("modal-nombre").value,
            tipoActividad: document.getElementById("modal-tipoActividad").value,
            duracion: Number(document.getElementById("modal-duracion").value),
            precio: Number(document.getElementById("modal-precio").value),
            plazasMaximas: Number(document.getElementById("modal-plazasMaximas").value),
            plazasOcupadas: Number(document.getElementById("modal-plazasOcupadas").value)
        };
    }

    if (modalContext.tipo === "reservas") {
        return {
            idUsuario: Number(document.getElementById("modal-idUsuario").value),
            idActividad: Number(document.getElementById("modal-idActividad").value),
            fecha: document.getElementById("modal-fecha").value,
            estado: document.getElementById("modal-estado").value
        };
    }

    return {
        idUsuario: Number(document.getElementById("modal-idUsuario").value),
        asunto: document.getElementById("modal-asunto").value,
        descripcion: document.getElementById("modal-descripcion").value,
        fecha: document.getElementById("modal-fecha").value,
        estado: document.getElementById("modal-estado").value
    };
}

function opcionesUsuarios(idSeleccionado) {
    return usuariosCache
        .map(usuario => `<option value="${usuario.id}" ${usuario.id === idSeleccionado ? "selected" : ""}>${usuario.nombre}</option>`)
        .join("");
}

function opcionesActividades(idSeleccionado) {
    return actividadesCache
        .map(actividad => `<option value="${actividad.id}" ${actividad.id === idSeleccionado ? "selected" : ""}>${actividad.nombre}</option>`)
        .join("");
}

function inicializarTema() {
    const temaGuardado = localStorage.getItem("centroplus-theme");
    const boton = document.getElementById("theme-toggle");

    if (temaGuardado === "dark") {
        document.body.classList.add("dark-theme");
        boton.textContent = "☀️ Modo claro";
    }

    boton.addEventListener("click", () => {
        document.body.classList.toggle("dark-theme");

        const oscuro = document.body.classList.contains("dark-theme");

        localStorage.setItem("centroplus-theme", oscuro ? "dark" : "light");
        boton.textContent = oscuro ? "☀️ Modo claro" : "🌙 Modo oscuro";
    });
}

function iniciarAutoRefresh() {
    if (autoRefreshId) {
        clearInterval(autoRefreshId);
    }

    autoRefreshId = setInterval(async () => {
        await comprobarApi();
    }, 60000);
}


function inicializarModalSeguro() {
    const modal = document.getElementById("edit-modal");

    if (!modal) {
        return;
    }

    modal.classList.add("hidden");
    document.body.classList.remove("modal-open");

    modal.addEventListener("click", event => {
        if (event.target === modal) {
            cerrarModal();
        }
    });

    document.addEventListener("keydown", event => {
        if (event.key === "Escape") {
            cerrarModal();
        }
    });
}


function renderHistorico() {
    const meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio"];
    const reservasBase = reservasCache.length || 1;
    const incidenciasBase = incidenciasCache.length || 1;
    const ingresosBase = reservasCache.reduce((total, reserva) => {
        const actividad = actividadesCache.find(a => a.id === reserva.idActividad);
        return total + (actividad ? Number(actividad.precio) : 0);
    }, 0) || 50;

    const reservasMes = meses.map((mes, index) => ({
        label: mes,
        value: Math.max(1, Math.round(reservasBase * (0.4 + index * 0.18)))
    }));

    const ingresosMes = meses.map((mes, index) => ({
        label: mes,
        value: Math.max(10, Math.round(ingresosBase * (0.35 + index * 0.16)))
    }));

    const incidenciasMes = meses.map((mes, index) => ({
        label: mes,
        value: Math.max(0, Math.round(incidenciasBase * (0.5 + index * 0.1)))
    }));

    renderTimelineBars("historico-reservas", reservasMes, "");
    renderTimelineBars("historico-ingresos", ingresosMes, " €");
    renderTimelineBars("historico-incidencias", incidenciasMes, "");
}

function renderTimelineBars(idContenedor, datos, suffix) {
    const contenedor = document.getElementById(idContenedor);

    if (!contenedor) {
        return;
    }

    const maximo = Math.max(...datos.map(item => item.value), 1);

    contenedor.innerHTML = "";

    datos.forEach(item => {
        const porcentaje = Math.max((item.value / maximo) * 100, 8);
        const row = document.createElement("div");

        row.className = "timeline-row";
        row.innerHTML = `
            <span>${item.label}</span>
            <div class="timeline-track">
                <div class="timeline-fill" style="width: ${porcentaje}%"></div>
            </div>
            <strong>${item.value}${suffix}</strong>
        `;

        contenedor.appendChild(row);
    });
}

function recargarSwagger() {
    const frame = document.getElementById("swagger-frame");

    if (frame) {
        frame.src = "/swagger-ui/index.html?reload=" + Date.now();
        mostrarToast("API Explorer recargado");
    }
}

function inicializarRevealAnimations() {
    const elementos = document.querySelectorAll(".reveal, .data-card, .chart-card, .executive-card, .form-card");

    if (!("IntersectionObserver" in window)) {
        elementos.forEach(elemento => elemento.classList.add("visible"));
        return;
    }

    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add("visible");
                observer.unobserve(entry.target);
            }
        });
    }, {
        threshold: 0.12
    });

    elementos.forEach(elemento => observer.observe(elemento));
}

function ocultarLoader() {
    const loader = document.getElementById("loader-overlay");

    if (!loader) {
        return;
    }

    loader.classList.add("loader-hidden");

    setTimeout(() => {
        loader.remove();
    }, 450);
}

function animarNumero(elemento, valorFinal, sufijo = "") {
    if (!elemento) {
        return;
    }

    const valorNumerico = Number(valorFinal);

    if (Number.isNaN(valorNumerico)) {
        elemento.textContent = valorFinal + sufijo;
        return;
    }

    const actualTexto = elemento.textContent.replace(/[^0-9.-]/g, "");
    const valorInicial = Number(actualTexto) || 0;

    if (valorInicial === valorNumerico) {
        elemento.textContent = valorNumerico + sufijo;
        return;
    }

    const duracion = 500;
    const inicio = performance.now();

    function tick(now) {
        const progreso = Math.min((now - inicio) / duracion, 1);
        const valor = Math.round(valorInicial + (valorNumerico - valorInicial) * progreso);

        elemento.textContent = valor + sufijo;

        if (progreso < 1) {
            requestAnimationFrame(tick);
        }
    }

    requestAnimationFrame(tick);
}

async function iniciarPagina() {
    inicializarModalSeguro();
    inicializarTema();
    await comprobarApi();

    try {
        await cargarDatosBase();

        actualizarContadores();
        cargarSelects();
        renderActividades();
        renderUsuarios();
        renderReservas();
        renderIncidencias();
        renderGraficas();
        renderHistorico();
        renderCalendario();
        inicializarFechas();
        inicializarFormularios();
        inicializarFiltros();
        iniciarAutoRefresh();
        inicializarRevealAnimations();
        marcarElementosDinamicosVisibles();
        ocultarLoader();
    } catch (error) {
        mostrarToast("No se pudieron cargar los datos iniciales", true);
        ocultarLoader();
    }
}

function obtenerDatosExportacion(tipo) {
    if (tipo === "usuarios") {
        return usuariosCache;
    }

    if (tipo === "actividades") {
        return actividadesCache;
    }

    if (tipo === "reservas") {
        return reservasCache.map(reserva => {
            const usuario = usuariosCache.find(u => u.id === reserva.idUsuario);
            const actividad = actividadesCache.find(a => a.id === reserva.idActividad);

            return {
                id: reserva.id,
                usuario: usuario ? usuario.nombre : reserva.idUsuario,
                actividad: actividad ? actividad.nombre : reserva.idActividad,
                fecha: reserva.fecha,
                estado: reserva.estado
            };
        });
    }

    if (tipo === "incidencias") {
        return incidenciasCache.map(incidencia => {
            const usuario = usuariosCache.find(u => u.id === incidencia.idUsuario);

            return {
                id: incidencia.id,
                usuario: usuario ? usuario.nombre : incidencia.idUsuario,
                asunto: incidencia.asunto,
                descripcion: incidencia.descripcion,
                fecha: incidencia.fecha,
                estado: incidencia.estado
            };
        });
    }

    return [];
}

function exportarJSON(tipo) {
    const datos = obtenerDatosExportacion(tipo);
    const contenido = JSON.stringify(datos, null, 2);
    descargarArchivo(`${tipo}.json`, contenido, "application/json");
}

function exportarCSV(tipo) {
    const datos = obtenerDatosExportacion(tipo);

    if (datos.length === 0) {
        mostrarToast("No hay datos para exportar", true);
        return;
    }

    const columnas = Object.keys(datos[0]);
    const filas = datos.map(item =>
        columnas.map(columna => limpiarValorCSV(item[columna])).join(";")
    );

    const contenido = [
        columnas.join(";"),
        ...filas
    ].join("\n");

    descargarArchivo(`${tipo}.csv`, contenido, "text/csv;charset=utf-8;");
}

function exportarPDF(tipo) {
    const datos = obtenerDatosExportacion(tipo);

    if (datos.length === 0) {
        mostrarToast("No hay datos para exportar", true);
        return;
    }

    const columnas = Object.keys(datos[0]);
    const filas = datos.map(item =>
        `<tr>${columnas.map(columna => `<td>${item[columna] ?? ""}</td>`).join("")}</tr>`
    ).join("");

    const ventana = window.open("", "_blank");

    ventana.document.write(`
        <html>
        <head>
            <title>Informe ${tipo}</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    padding: 30px;
                    color: #172033;
                }

                h1 {
                    color: #1d4ed8;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }

                th,
                td {
                    border: 1px solid #dbe3ef;
                    padding: 8px;
                    text-align: left;
                    font-size: 12px;
                }

                th {
                    background: #eff6ff;
                }
            </style>
        </head>
        <body>
            <h1>CentroPlus Connect</h1>
            <h2>Informe de ${tipo}</h2>
            <p>Generado el ${new Date().toLocaleString("es-ES")}</p>
            <table>
                <thead>
                    <tr>${columnas.map(columna => `<th>${columna}</th>`).join("")}</tr>
                </thead>
                <tbody>${filas}</tbody>
            </table>
        </body>
        </html>
    `);

    ventana.document.close();
    ventana.print();

    mostrarToast(`Informe PDF de ${tipo} generado`);
}

function limpiarValorCSV(valor) {
    if (valor === null || valor === undefined) {
        return "";
    }

    return `"${valor.toString().replaceAll('"', '""')}"`;
}

function descargarArchivo(nombreArchivo, contenido, tipoMime) {
    const blob = new Blob([contenido], {
        type: tipoMime
    });

    const url = URL.createObjectURL(blob);
    const enlace = document.createElement("a");

    enlace.href = url;
    enlace.download = nombreArchivo;
    enlace.click();

    URL.revokeObjectURL(url);

    mostrarToast(`Archivo ${nombreArchivo} descargado correctamente`);
}

iniciarPagina();