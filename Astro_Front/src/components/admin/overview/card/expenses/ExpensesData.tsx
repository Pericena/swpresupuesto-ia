import React, { useEffect, useState } from 'react';
import { Doughnut } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import type { ExpensesData } from '@interfaces/Expenses'; // Importa solo el tipo

// Registra elementos necesarios para Chart.js
ChartJS.register(ArcElement, Tooltip, Legend);

// Consulta GraphQL para obtener los datos de gastos
const GastosCategoriaTop5Query = `
  query GastosCategoriaTop5 {
    gastosCategoriaTop5 {
      IA
      top_5_categorias {
        categoria
        monto
      }
    }
  }
`;

const ExpensesChart: React.FC = () => {
  const [errorLoadingData, setErrorLoadingData] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [chartData, setChartData] = useState<ExpensesData | null>(null);
  const [IA, setIA] = useState<string | null>(null); 

  useEffect(() => {
    fetchDataAndDrawChart();
  }, []);

  async function fetchDataAndDrawChart() {
    try {
      // Realiza la solicitud para obtener los datos de gastos
      const response = await fetch('http://167.99.63.51:4000', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          query: GastosCategoriaTop5Query, // Utiliza la consulta GraphQL
        }),
      });

      if (!response.ok) {
        handleErrors(response);
        return;
      }

      const { data } = await response.json();
      console.log('Datos recibidos:', data);

      // Extrae el valor de "IA" del resultado de la consulta
      const IAValue = data.gastosCategoriaTop5.IA;
      setIA(IAValue); // Establece el valor de "IA" en el estado

      // Extrae las etiquetas y valores de datos del resultado de la consulta
      const labels = data.gastosCategoriaTop5.top_5_categorias.map((categoria: { categoria: string }) => categoria.categoria);
      const dataValues = data.gastosCategoriaTop5.top_5_categorias.map((categoria: { monto: number }) => categoria.monto);

      // Formatea los datos para Chart.js
      const formattedData: ExpensesData = {
        labels: labels,
        datasets: [
          {
            label: 'Expenses',
            data: dataValues,
            backgroundColor: [
              'rgba(255, 99, 132)',
              'rgba(54, 162, 235)',
              'rgba(255, 206, 86)',
              'rgba(75, 192, 192)',
              'rgba(153, 102, 255)',
            ],
          },
        ],
      };
      setChartData(formattedData);
    } catch (err: any) {
      console.error('Error al obtener los datos de la API:', err.message);
      setError(err.message);
      setErrorLoadingData(true);
    }
  }

  function handleErrors(response: Response) {
    switch (response.status) {
      case 400:
        throw new Error('Error: Solicitud incorrecta (400)');
      case 404:
        throw new Error('Error: Recurso no encontrado (404)');
      case 500:
        throw new Error('Error: Error interno del servidor (500)');
      default:
        throw new Error(`Error al cargar los datos. Estado: ${response.status} ${response.statusText}`);
    }
  }

  return (
    <div>
      {/* Mostrar mensaje de error si falla la carga de datos */}
      {errorLoadingData ? (
        <p id="errorMessageExpenses">{error}</p>
      ) : (
        <div>
          {/* Mostrar gráfico si hay datos */}
          {chartData && (
            <div style={{ width: '100%', maxWidth: '400px', height: '200px' }}>
              <Doughnut
                data={chartData}
                options={{
                  maintainAspectRatio: false,
                  responsive: true,
                  cutout: '70%',
                  plugins: {
                    legend: {
                      position: 'right',
                      labels: {
                        boxWidth: 15,
                        padding: 20,
                        usePointStyle: true,
                        font: {
                          size: 10,
                        },
                      },
                    },
                  },
                }}
              />
            </div>
          )}
        </div>
      )}

      {/* Mostrar el valor de "IA" */}
      <p className='text-white text-sm'>Detalle generado por ia: {IA}</p>
    </div>
  );
};

export default ExpensesChart;
