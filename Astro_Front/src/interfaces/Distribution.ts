// Distribution.ts

// Definimos la interfaz para representar un elemento de distribución de gastos
export interface DistributionItem {
  categoria: string; // Nombre de la categoría
  monto: number; // Monto de gastos en la categoría
}

// Definimos la interfaz para representar los datos de distribución de gastos
export interface DistributionData {
  descripcion: string; // Descripción de la distribución
  distribucion_gastos: DistributionItem[]; // Lista de elementos de distribución de gastos
}
