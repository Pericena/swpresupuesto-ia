    document.addEventListener("DOMContentLoaded", () => {
            const socket = io();

            socket.on('connect', () => {
                console.log('Conectado al servidor');
            });

            socket.on('response', (data) => {
                console.log(data.message);
            });

            socket.on('respuesta_analisis', (data) => {
                console.log(data.analisis);
                document.getElementById("analisisTexto").innerText = data.analisis;
            });
            

            // Enviar solicitud de análisis de gastos al servidor
            socket.emit('analizar_gastos', { solicitud: 'Analizar mis gastos' });

            // Crear gráficos
            createCharts();
        });

        async function fetchGastosData() {
            const response = await fetch("/kpi/gastos");
            const data = await response.json();
            return data;
        }

        async function createCharts() {
            const dataDoughnut = await fetchGastosData();
            createChart('doughnut', 'myChartDoughnut', dataDoughnut);

            const dataBar = await fetchGastosData();
            createChart('bar', 'myChartBar', dataBar);

            const dataLine = await fetchGastosData();
            createChart('line', 'myChartLine', dataLine);

            const dataScatter = await fetchGastosData();
            createChart('scatter', 'myChartScatter', dataScatter);
        }

        function createChart(type, canvasId, data) {
            const ctx = document.getElementById(canvasId).getContext("2d");
            new Chart(ctx, {
                type: type,
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: "Distribución de Gastos",
                        data: data.data,
                        backgroundColor: data.backgroundColor,
                    }]
                },
                options: {
                    plugins: {
                        legend: {
                            position: "top",
                            labels: {
                                font: {
                                    size: 14
                                }
                            }
                        },
                        tooltip: {
                            callbacks: {
                                label: function (context) {
                                    let label = context.label || "";
                                    let value = context.formattedValue || "";
                                    return `${label}: ${value}%`;
                                }
                            }
                        }
                    }
                }
            });
        }