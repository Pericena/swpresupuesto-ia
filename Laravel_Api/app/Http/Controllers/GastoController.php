<?php

namespace App\Http\Controllers;

use App\Models\Gasto;
use Carbon\Carbon;
use Illuminate\Http\Request;

class GastoController extends Controller
{
    // Método auxiliar para agrupar y sumar los gastos por año y mes
    private function getGroupedExpenses()
    {
        $expenses = Gasto::orderBy('fecha', 'asc')->get();

        $groupedExpenses = $expenses->groupBy(function ($date) {
            return Carbon::parse($date->fecha)->format('Y-m');
        });

        $result = [];

        foreach ($groupedExpenses as $key => $group) {
            $yearMonth = explode('-', $key);
            $year = (int)$yearMonth[0];
            $month = (int)$yearMonth[1];
            $totalMonto = $group->sum('monto');

            $result[] = [
                'year' => $year,
                'month' => $month,
                'monto' => round($totalMonto, 2),
            ];
        }

        return $result;
    }

    private function calculateMean(array $expenses)
    {
        $sum = array_sum(array_column($expenses, 'monto'));
        return $sum / count($expenses);
    }

    private function calculateStandardDeviation(array $expenses, $mean)
    {
        $sumSquaredDiffs = array_reduce($expenses, function ($carry, $expense) use ($mean) {
            return $carry + pow($expense['monto'] - $mean, 2);
        }, 0);

        return sqrt($sumSquaredDiffs / count($expenses));
    }

    // Método para detectar anomalías
    public function detectAnomalies()
    {
        $expenses = $this->getGroupedExpenses(); // Obtener los datos agrupados y sumados

        $mean = $this->calculateMean($expenses);
        $stdDev = $this->calculateStandardDeviation($expenses, $mean);
        $threshold = 1.7 * $stdDev; //1 Mas estrico, 3 Menos Estricto, puedes manejar 1.5

        $anomalies = array_filter($expenses, function ($expense) use ($mean, $threshold) {
            return abs($expense['monto'] - $mean) > $threshold;
        });

        return response()->json([
            'original_data' => $expenses,
            'anoamlies' => $anomalies,
        ]);
    }

    // Nuevo método para predecir entre dos fechas con un número específico de meses
    public function predictBetweenDatesWithMonths($startDate, $numberOfMonths)
    {
        $expenses = $this->getGroupedExpensesBetweenDates($startDate);

        // Obtener el índice de tiempo (mes en formato YYYYMM) y los montos
        $months = array_map(function ($expense) {
            return $expense['year'] * 12 + $expense['month'];
        }, $expenses);

        $amounts = array_column($expenses, 'monto');

        // Realizar los cálculos para la regresión lineal
        $sumX = array_sum($months);
        $sumY = array_sum($amounts);
        $sumXY = array_sum(array_map(function ($x, $y) {
            return $x * $y;
        }, $months, $amounts));
        $sumXX = array_sum(array_map(function ($x) {
            return $x * $x;
        }, $months));

        $n = count($months);
        $slope = ($n * $sumXY - $sumX * $sumY) / ($n * $sumXX - $sumX * $sumX);
        $intercept = ($sumY - $slope * $sumX) / $n;

        // Encontrar la última fecha en los datos
        $lastDate = Carbon::parse('2024-05-24');
        $predictions = [];

        // Generar predicciones para los meses posteriores a la última fecha
        for ($i = 1; $i <= $numberOfMonths; $i++) {
            $lastDate->addMonth();
            $monthIndex = $lastDate->year * 12 + $lastDate->month;
            $predictedExpense = $slope * $monthIndex + $intercept;
            $monto = round($predictedExpense, 2);
            if ($monto < 0) {
                $monto = 0;
            }
            $predictions[] = [
                'year' => $lastDate->year,
                'month' => $lastDate->month,
                'monto' => $monto,
            ];
        }

        return response()->json([
            'original_data' => $expenses,
            'predictions' => $predictions,
        ]);
    }

    // Método auxiliar para obtener los gastos agrupados entre dos fechas
    public function getGroupedExpensesBetweenDates($startDate)
    {
        $expenses = Gasto::whereBetween('fecha', [$startDate, '2024-05-24'])->orderBy('fecha', 'asc')->get();

        $groupedExpenses = $expenses->groupBy(function ($date) {
            return Carbon::parse($date->fecha)->format('Y-m');
        });

        $result = [];

        foreach ($groupedExpenses as $key => $group) {
            $yearMonth = explode('-', $key);
            $year = (int)$yearMonth[0];
            $month = (int)$yearMonth[1];
            $totalMonto = $group->sum('monto');

            $result[] = [
                'year' => $year,
                'month' => $month,
                'monto' => round($totalMonto, 2),
            ];
        }

        return $result;
    }
}
