import 'dart:math';

class ExpenseData {
  final int year;
  final int month;
  final double expense;

  ExpenseData(this.year, this.month, this.expense);
}

class ExpensePrediction {
  List<ExpenseData> data;

  ExpensePrediction(this.data);

  // Función para realizar la predicción
  List<ExpenseData> predictNextMonths(int numberOfMonths) {
    List<ExpenseData> predictions = [];
    int currentYear = data.last.year;
    int currentMonth = data.last.month;

    // Convertir los meses en una secuencia numérica para la regresión
    List<int> months = [];
    List<double> expenses = [];

    for (var entry in data) {
      months.add(entry.year * 12 + entry.month);
      expenses.add(entry.expense);
    }

    // Calcular los coeficientes de la regresión lineal
    double sumX = months.reduce((a, b) => a + b).toDouble();
    double sumY = expenses.reduce((a, b) => a + b);
    double sumXY = 0;
    double sumXX = 0;

    for (int i = 0; i < months.length; i++) {
      sumXY += months[i] * expenses[i];
      sumXX += months[i] * months[i];
    }

    double slope = (months.length * sumXY - sumX * sumY) / (months.length * sumXX - sumX * sumX);
    double intercept = (sumY - slope * sumX) / months.length;

    // Generar predicciones
    for (int i = 1; i <= numberOfMonths; i++) {
      currentMonth++;
      if (currentMonth > 12) {
        currentMonth = 1;
        currentYear++;
      }
      int monthIndex = currentYear * 12 + currentMonth;
      double predictedExpense = slope * monthIndex + intercept;
      predictions.add(ExpenseData(currentYear, currentMonth, predictedExpense));
    }

    return predictions;
  }
}

class ExpenseAnomalyDetection {
  List<ExpenseData> data;

  ExpenseAnomalyDetection(this.data);

  // Función para detectar anomalías
  List<ExpenseData> detectAnomalies() {
    double mean = _calculateMean();
    double stdDev = _calculateStandardDeviation(mean);
    double threshold = 1.5 * stdDev; // Ajustar el umbral según sea necesario

    List<ExpenseData> anomalies = [];

    for (var entry in data) {
      if ((entry.expense - mean).abs() > threshold) {
        anomalies.add(entry);
      }
    }

    return anomalies;
  }

  double _calculateMean() {
    double sum = data.fold(0, (previous, current) => previous + current.expense);
    return sum / data.length;
  }

  double _calculateStandardDeviation(double mean) {
    double sumSquaredDiffs = data.fold(0, (previous, current) => previous + pow(current.expense - mean, 2));
    return sqrt(sumSquaredDiffs / data.length);
  }
}