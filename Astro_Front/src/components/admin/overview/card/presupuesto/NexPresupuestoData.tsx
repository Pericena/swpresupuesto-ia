import React, { useEffect, useState } from 'react';
import { Line } from 'react-chartjs-2';

interface PresupuestoTotal {
  cambio_mensual_promedio: number;
  crecimiento_presupuesto: number;
  duraciones: number[];
  promedios_mensuales: number[];
}

const EarningDataComponent: React.FC = () => {
  const [chartData, setChartData] = useState<{
    labels: string[];
    datasets: {
      label: string;
      data: number[];
      fill: boolean;
      borderColor: string;
      tension: number;
    }[];
  }>({
    labels: [],
    datasets: [{
      label: 'Presupuesto Mensual',
      data: [],
      fill: false,
      borderColor: '#1f78b4', // Cambiando el color de la línea a azul
      tension: 0.4 // Aumentando la tensión para que la línea sea más curva
    }]
  });
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://152.67.56.238:5000/api/presupuesto-total');
        if (!response.ok) {
          throw new Error('No se pudo obtener los datos');
        }
        const data: { "presupuesto total": PresupuestoTotal; usuario: string; }[] = await response.json();

        // Filtrar datos solo para el usuario "chile"
        const dataUsuarioChile = data.find(item => item.usuario === 'chile');

        if (dataUsuarioChile) {
          const { promedios_mensuales } = dataUsuarioChile['presupuesto total'];

          // Configurar los datos para el gráfico
          setChartData({
            labels: promedios_mensuales.map((_, index) => `Mes ${index + 1}`),
            datasets: [{
              label: 'Presupuesto Mensual',
              data: promedios_mensuales,
              fill: false,
              borderColor: '#1f78b4',
              tension: 0.4
            }]
          });
        } else {
          throw new Error('No se encontraron datos para el usuario "chile"');
        }
      } catch (error) {
        console.error('Error al obtener los datos:', error);
        setError('No se pudieron cargar los datos. Por favor, intenta nuevamente más tarde.');
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {error ? (
        <div className="error-message">{error}</div>
      ) : (
        <div className="h-96 w-full max-w-full">
          <Line
            data={chartData}
            options={{
              maintainAspectRatio: false,
              responsive: true,
              plugins: {
                legend: { display: true }
              },
              scales: {
                y: {
                  ticks: { color: '#AEAFBB' },
                  grid: { display: true, color: 'rgba(174, 175, 187, 0.1)' }
                },
                x: {
                  ticks: { color: '#AEAFBB', autoSkip: true, maxTicksLimit: 10 },
                  grid: { display: false }
                }
              }
            }}
          />
        </div>
      )}
    </div>
  );
};

export default EarningDataComponent;
