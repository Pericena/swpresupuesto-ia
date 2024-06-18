export interface Egreso {
  _id: number;
  concepto: string;
  monto: number;
  fechaEgreso: string;
  cuentaID: number;
  categoriaID: number;
}
const Egreso: Egreso = {
  _id: 0,
  concepto: '',
  monto: 0,
  fechaEgreso:'',
  cuentaID: 0,
  categoriaID: 0,
};

export default Egreso;