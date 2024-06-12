import React, { useEffect, useState } from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, BarElement, LinearScale, CategoryScale, Title, Tooltip, Legend, type ChartData } from 'chart.js';

// Registra elementos necesarios para Chart.js
ChartJS.register(
  BarElement,
  LinearScale,
  CategoryScale,
  Title,
  Tooltip,
  Legend
);

// Consulta GraphQL para obtener el promedio de gastos
const PromedioGastosQuery = `
  query PromedioGastos {
    promedioGastos {
      descripcion
      promedio_gastos_por_anio {
        anio
        promedio
      }
    }
  }
`;

const ExpensesChart: React.FC = () => {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [data, setData] = useState<ChartData<'bar'> | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://167.99.63.51:4000/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            query: PromedioGastosQuery,
          }),
        });

        if (!response.ok) {
          throw new Error(`Error al cargar los datos. Estado: ${response.status} ${response.statusText}`);
        }

        const { data } = await response.json();
        const labels = data.promedioGastos.promedio_gastos_por_anio.map((item: { anio: string }) => item.anio);
        const promedios = data.promedioGastos.promedio_gastos_por_anio.map((item: { promedio: number }) => item.promedio);

        // Generar colores aleatorios para cada barra
        const backgroundColors = promedios.map(() => getRandomColor());

        setData({
          labels: labels,
          datasets: [
            {
              label: 'Promedio de gastos por año',
              data: promedios,
              backgroundColor: backgroundColors,
              borderColor: 'rgba(54, 162, 235, 1)',
              borderWidth: 1,
            },
          ],
        });
      } catch (error) {
        // setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  // Función para generar colores aleatorios en formato RGBA
  const getRandomColor = () => {
    return `rgba(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, 0.6)`;
  };

  if (loading) return <p>Cargando...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div style={{ width: '100%', maxWidth: '600px', height: '400px' }}>
      <Bar
        data={data}
        options={{
          maintainAspectRatio: false,
          responsive: true,
          plugins: {
            legend: { display: true },
          },
          scales: {
            y: {
              beginAtZero: true,
              ticks: { color: '#AEAFBB' },
              grid: { display: true, color: 'rgba(174, 175, 187)' },
            },
            x: { ticks: { color: '#AEAFBB' }, grid: { display: false } },
          },
        }}
      />
    </div>
  );
};

export default ExpensesChart;
