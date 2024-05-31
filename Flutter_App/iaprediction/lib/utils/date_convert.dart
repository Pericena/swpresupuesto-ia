import 'package:intl/intl.dart';

String convertirFecha(String fechaOriginal) {
  // Define el formato de entrada
  DateFormat formatoEntrada = DateFormat('M/d/yyyy');
  // Define el formato de salida
  DateFormat formatoSalida = DateFormat('yyyy-MM-dd');
  
  // Convierte la fecha del formato de entrada al formato de salida
  DateTime fecha = formatoEntrada.parse(fechaOriginal);
  return formatoSalida.format(fecha);
}