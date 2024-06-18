import React, { useEffect, useState } from 'react';

const NEXTransaction = () => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://167.99.63.51:4000", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            query: `
              query PromedioGastos {
                promedioGastos {
                  descripcion
                  promedio_gastos_por_anio {
                    anio
                    promedio
                  }
                }
              }
            `,
          }),
        });

        if (!response.ok) {
          throw new Error(`Error al cargar los datos. Estado: ${response.status} ${response.statusText}`);
        }

        const { data } = await response.json();
        setData(data.promedioGastos);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <p className="text-white">Cargando...</p>;

  // Encontrar el promedio más alto
  let maxAverage = 0;
  if (data && data.promedio_gastos_por_anio) {
    data.promedio_gastos_por_anio.forEach(item => {
      if (item.promedio > maxAverage) {
        maxAverage = item.promedio;
      }
    });
  }

  return (
    <div>
      <table className="w-full border-collapse items-center bg-transparent">
        <thead>
          <tr className="text-white">
            <th className="whitespace-nowrap border-l-0 border-r-0 border-t-0 p-4 px-4 text-left align-middle text-xs">
              Año
            </th>
            <th className="whitespace-nowrap border-l-0 border-r-0 border-t-0 p-4 px-4 text-left align-middle text-xs">
              Promedio
            </th>
          </tr>
        </thead>
        <tbody>
          {data && data.promedio_gastos_por_anio ? (
            data.promedio_gastos_por_anio.map((item) => (
              <tr key={item.anio} className="text-white">
                <td className="border-r-0 border-t-0 p-2 px-2 align-middle text-sm">
                  {item.anio} Año
                </td>
                <td className={`border-r-0 border-t-0 p-2 px-4 align-middle text-sm ${item.promedio === maxAverage ? 'text-red-500' : ''}`}>
                  {Math.round(item.promedio)} 
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td className="text-white text-center p-4">No hay datos disponibles</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default NEXTransaction;
