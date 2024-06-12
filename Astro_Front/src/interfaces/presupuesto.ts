// DetallePresupuesto interface represents a single budget detail item
interface DetallePresupuesto {
  categoria: string;    // Category of the budget item, e.g., "Alimentación"
  descripcion: string;  // Description of the budget item
  monto: number;        // Amount allocated for the budget item
}

// Presupuesto interface represents the overall budget
interface Presupuesto {
  nombre: string;           // Name of the budget, e.g., "Presupuesto Mensual"
  descripcion: string;      // Description of the budget
  montoTotal: number;       // Total amount allocated for the budget
  fechaInicio: string;      // Start date of the budget period (YYYY-MM-DD format)
  fechaFinal: string;       // End date of the budget period (YYYY-MM-DD format)
  detalledelpresupuesto: DetallePresupuesto[];  // Array of budget details
}

export type { Presupuesto, DetallePresupuesto };
