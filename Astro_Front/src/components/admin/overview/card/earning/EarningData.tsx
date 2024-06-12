import React, { useEffect, useState } from 'react'
import { Line } from 'react-chartjs-2'
import type { DistributionData } from '@interfaces/Distribution.ts' // Importa el tipo de datos de la distribución

import {
  Chart as ChartJS,
  LineElement,
  PointElement,
  LinearScale,
  Title,
  CategoryScale,
  Tooltip,
  type ChartData
} from 'chart.js'

// Registra elementos necesarios para Chart.js
ChartJS.register(LineElement, PointElement, LinearScale, Title, CategoryScale, Tooltip)

// Consulta GraphQL para obtener la distribución de gastos por categoría
const DistributionQuery = `
  query DistribucionGastosPorCategoria {
    distribucionGastosPorCategoria {
      descripcion 
      distribucion_gastos {
        categoria 
        monto 
      }
    }
  }
`

const EarningDataComponent: React.FC = () => {
  const [loading, setLoading] = useState(true) // Estado para indicar si se están cargando los datos
  const [error, setError] = useState<string | null>(null) // Estado para almacenar errores en la carga de datos
  const [data, setData] = useState<DistributionData | null>(null) // Estado para almacenar los datos de la distribución
  const [descripcion, setDescripcion] = useState<string | null>(null) // Estado para almacenar la descripción generada por IA

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://167.99.63.51:4000/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            query: DistributionQuery
          })
        })

        if (!response.ok) {
          throw new Error(
            `Error al cargar los datos. Estado: ${response.status} ${response.statusText}`
          )
        }

        const { data } = await response.json()
        setData(data.distribucionGastosPorCategoria)
        setDescripcion(data.distribucionGastosPorCategoria.descripcion) // Almacena la descripción generada por IA
      } catch (error) {
        // setError(error.message) // Almacena cualquier error que ocurra durante la carga de datos
      } finally {
        setLoading(false) 
      }
    }

    fetchData()
  }, [])

  if (loading) return <p>Cargando...</p> 
  if (error) return <p>Error: {error}</p> 

  // Construye los datos necesarios para el gráfico de líneas
  const chartData: ChartData<'line'> = {
    labels: data?.distribucion_gastos.map((item) => item.categoria) || [],
    datasets: [
      {
        label: 'Gastos por categoría',
        data: data?.distribucion_gastos.map((item) => item.monto) || [],
        borderColor: 'rgba(1, 212, 146, 1)',
        backgroundColor: 'rgba(255, 255, 255, 0.6)',
        borderWidth: 2,
        pointRadius: 0,
        tension: 0.4,
        fill: false
      }
    ]
  }

  return (
    <div>
      <div className="h-96 w-full max-w-full">
        {/* Renderiza el gráfico de líneas */}
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
              x: { ticks: { color: '#AEAFBB' }, grid: { display: false } }
            }
          }}
        />
        {/* Muestra la descripción generada por IA */}
        <p className="text-sm text-white">Detalle generado por IA: {descripcion}</p>
      </div>
    </div>
  )
}

export default EarningDataComponent
