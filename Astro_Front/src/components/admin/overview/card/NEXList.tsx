import React, { useEffect, useState } from 'react';

interface Categoria {
  [key: string]: number;
}

interface GastosData {
  total_gastos: number;
  descripcion: string;
  categorias: Categoria;
}

const App: React.FC = () => {
  const [gastosData, setGastosData] = useState<GastosData | null>(null);
  const [sortedCategorias, setSortedCategorias] = useState<string[]>([]);

  // Función para formatear números como moneda
  const formatCurrency = (amount: number) => {
    return new Intl.NumberFormat('es-BO', { style: 'currency', currency: 'BOB' }).format(amount);
  };

  useEffect(() => {
    // Consumir la API y mostrar la información en la página
    fetch('http://152.67.56.238:5000/api/gastos')
      .then((response) => response.json())
      .then((data: GastosData) => {
        setGastosData(data);

        // Encontrar las 3 categorías con mayores gastos
        const sorted = Object.keys(data.categorias)
          .sort((a, b) => data.categorias[b] - data.categorias[a])
          .slice(0, 3);
        setSortedCategorias(sorted);
      })
      .catch((error) => {
        console.error('Error al obtener los datos:', error);
        // Manejo de errores
      });
  }, []);

  return (
    <div id="gastosInfo">
      {gastosData && (
        <div>

          <div
            className="w-full rounded-md p-6 shadow-md transition duration-300 hover:shadow-lg"
            style={{ background: 'linear-gradient(135deg, #f05f22, #e53f52, #e53f52)' }}
          >
            <div className="mb-6 flex items-center justify-between">
              <div className="flex items-center">
                <div className="cursor-pointer rounded-full bg-white text-black transition duration-300 hover:bg-gray-200 hover:shadow-md">
                  <div className="inline-flex h-10 w-10 items-center justify-center rounded-full bg-white p-3 text-center text-xl text-black shadow-lg">
                   <a href="http://152.67.56.238:5000/gasto"> <i className="Total Balance" /></a>
                  </div>
                </div>
                <div className="ml-2 rounded bg-emerald-500/10 p-1 text-[12px] font-semibold leading-none text-emerald-500">
                  Cantidad de Categorías: {Object.keys(gastosData.categorias).length}
                </div>
              </div>
            </div>
            <div className="text-sm font-medium text-white">Total Gasto</div>
            <a target="_blank" href="http://152.67.56.238:5000/gasto" className="text-sm font-medium text-white hover:text-black">
              {formatCurrency(gastosData.total_gastos)}
            </a>
            <div className="text-2xl font-semibold text-white">{formatCurrency(gastosData.total_gastos)}</div>
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
