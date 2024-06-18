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
 // const [error, setError] = useState<string | null>(null) // Estado para almacenar errores en la carga de datos
  const [data, setData] = useState<DistributionData | null>(null) // Estado para almacenar los datos de la distribución
  const [descripcion, setDescripcion] = useState<string | null>(null) // Estado para almacenar la descripción generada por IA

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://167.99.63.51:4000', {
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
  // if (error) return <p>Error: {error}</p> 

  // Construye los datos necesarios para el gráfico de líneas
  const chartData: ChartData<'line'> = {
    labels: data?.distribucion_gastos.map((item) => item.categoria) || [],
datasets: [
  {
    label: 'Gastos por categoría',
    data: data?.distribucion_gastos.map((item) => item.monto) || [],
    borderColor: '#ED5630', // Color del borde de la línea
    backgroundColor: 'rgba(1, 212, 146, 0.2)', // Color del fondo (opcional)
    borderWidth: 2, // Ancho del borde de la línea
    pointRadius: 3, // Tamaño de los puntos
    tension: 0.4, // Tensión de la línea (curvatura)
    fill: true // Rellenar el área bajo la línea (true/false)
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
    maintainAspectRatio: false, // Permite ajustar el tamaño de la gráfica
    responsive: true, // Hace que la gráfica sea responsive
    plugins: {
      legend: {
        display: true, // Muestra la leyenda
      },
    },
    scales: {
      y: {
        ticks: {
          color: '#AEAFBB', // Color de las marcas en el eje Y
        },
        grid: {
          display: true,
          color: 'rgba(174, 175, 187, 0.1)', // Color de las líneas de la cuadrícula en el eje Y
        },
      },
      x: {
        ticks: {
          color: '#AEAFBB', // Color de las marcas en el eje X
        },
        grid: {
          display: false, // Oculta las líneas de la cuadrícula en el eje X
        },
      },
    },
  }}
/>

        {/* Muestra la descripción generada por IA */}
        <p className="text-sm text-white">Detalle generado por IA: {descripcion}</p>
      </div>
    </div>
  )
}

export default EarningDataComponent
