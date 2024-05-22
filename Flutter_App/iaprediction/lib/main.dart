import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';
import 'package:iaprediction/services/ia_prediction.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ExpenseChartScreen(),
    );
  }
}

class ExpenseChartScreen extends StatelessWidget {
  final List<ExpenseData> expenseData = [
    ExpenseData(2018, 1, 180.0),
    ExpenseData(2018, 2, 195.0),
    ExpenseData(2018, 3, 210.0),
    ExpenseData(2018, 4, 175.0),
    ExpenseData(2018, 5, 190.0),
    ExpenseData(2018, 6, 200.0),
    ExpenseData(2018, 7, 215.0),
    ExpenseData(2018, 8, 185.0),
    ExpenseData(2018, 9, 205.0),
    ExpenseData(2018, 10, 195.0),
    ExpenseData(2018, 11, 210.0),
    ExpenseData(2018, 12, 190.0),
    ExpenseData(2019, 1, 195.0),
    ExpenseData(2019, 2, 200.0),
    ExpenseData(2019, 3, 215.0),
    ExpenseData(2019, 4, 185.0),
    ExpenseData(2019, 5, 205.0),
    ExpenseData(2019, 6, 190.0),
    ExpenseData(2019, 7, 210.0),
    ExpenseData(2019, 8, 195.0),
    ExpenseData(2019, 9, 205.0),
    ExpenseData(2019, 10, 200.0),
    ExpenseData(2019, 11, 215.0),
    ExpenseData(2019, 12, 195.0),
    ExpenseData(2020, 1, 190.0),
    ExpenseData(2020, 2, 205.0),
    ExpenseData(2020, 3, 210.0),
    ExpenseData(2020, 4, 195.0),
    ExpenseData(2020, 5, 200.0),
    ExpenseData(2020, 6, 215.0),
    ExpenseData(2020, 7, 190.0),
    ExpenseData(2020, 8, 205.0),
    ExpenseData(2020, 9, 210.0),
    ExpenseData(2020, 10, 195.0),
    ExpenseData(2020, 11, 200.0),
    ExpenseData(2020, 12, 215.0),
    ExpenseData(2021, 1, 205.0),
    ExpenseData(2021, 2, 195.0),
    ExpenseData(2021, 3, 210.0),
    ExpenseData(2021, 4, 200.0),
    ExpenseData(2021, 5, 215.0),
    ExpenseData(2021, 6, 190.0),
    ExpenseData(2021, 7, 205.0),
    ExpenseData(2021, 8, 210.0),
    ExpenseData(2021, 9, 195.0),
    ExpenseData(2021, 10, 200.0),
    ExpenseData(2021, 11, 215.0),
    ExpenseData(2021, 12, 190.0),
    ExpenseData(2022, 1, 205.0),
    ExpenseData(2022, 2, 200.0),
    ExpenseData(2022, 3, 225.0),
    ExpenseData(2022, 4, 190.0),
    ExpenseData(2022, 5, 210.0),
    ExpenseData(2022, 6, 200.0),
    ExpenseData(2022, 7, 210.0),
    ExpenseData(2022, 8, 200.0),
    ExpenseData(2022, 9, 220.0),
    ExpenseData(2022, 10, 180.0),
    ExpenseData(2022, 11, 210.0),
    ExpenseData(2022, 12, 190.0)
  ];

  final List<ExpenseData> expenseDataAnomalies = [
    ExpenseData(2018, 1, 180.0),
    ExpenseData(2018, 2, 195.0),
    ExpenseData(2018, 3, 210.0),
    ExpenseData(2018, 4, 175.0),
    ExpenseData(2018, 5, 190.0),
    ExpenseData(2018, 6, 200.0),
    ExpenseData(2018, 7, 215.0),
    ExpenseData(2018, 8, 185.0),
    ExpenseData(2018, 9, 205.0),
    ExpenseData(2018, 10, 195.0),
    ExpenseData(2018, 11, 210.0),
    ExpenseData(2018, 12, 190.0),
    ExpenseData(2019, 1, 195.0),
    ExpenseData(2019, 2, 200.0),
    ExpenseData(2019, 3, 215.0),
    ExpenseData(2019, 4, 185.0),
    ExpenseData(2019, 5, 205.0),
    ExpenseData(2019, 6, 190.0),
    ExpenseData(2019, 7, 210.0),
    ExpenseData(2019, 8, 195.0),
    ExpenseData(2019, 9, 205.0),
    ExpenseData(2019, 10, 200.0),
    ExpenseData(2019, 11, 215.0),
    ExpenseData(2019, 12, 195.0),
    ExpenseData(2020, 1, 190.0),
    ExpenseData(2020, 2, 205.0),
    ExpenseData(2020, 3, 210.0),
    ExpenseData(2020, 4, 195.0),
    ExpenseData(2020, 5, 200.0),
    ExpenseData(2020, 6, 215.0),
    ExpenseData(2020, 7, 190.0),
    ExpenseData(2020, 8, 205.0),
    ExpenseData(2020, 9, 210.0),
    ExpenseData(2020, 10, 195.0),
    ExpenseData(2020, 11, 200.0),
    ExpenseData(2020, 12, 215.0),
    ExpenseData(2021, 1, 205.0),
    ExpenseData(2021, 2, 195.0),
    ExpenseData(2021, 3, 210.0),
    ExpenseData(2021, 4, 200.0),
    ExpenseData(2021, 5, 230.0),
    ExpenseData(2021, 6, 190.0),
    ExpenseData(2021, 7, 205.0),
    ExpenseData(2021, 8, 210.0),
    ExpenseData(2021, 9, 195.0),
    ExpenseData(2021, 10, 200.0),
    ExpenseData(2021, 11, 215.0),
    ExpenseData(2021, 12, 190.0),
    ExpenseData(2022, 1, 205.0),
    ExpenseData(2022, 2, 200.0),
    ExpenseData(2022, 3, 225.0),
    ExpenseData(2022, 4, 190.0),
    ExpenseData(2022, 5, 210.0),
    ExpenseData(2022, 6, 200.0),
    ExpenseData(2022, 7, 210.0),
    ExpenseData(2022, 8, 200.0),
    ExpenseData(2022, 9, 220.0),
    ExpenseData(2022, 10, 180.0),
    ExpenseData(2022, 11, 210.0),
    ExpenseData(2022, 12, 200.0)
  ];

  @override
  Widget build(BuildContext context) {
    ExpensePrediction prediction = ExpensePrediction(expenseData);
    List<ExpenseData> predictions = prediction.predictNextMonths(5);

    ExpenseAnomalyDetection anomalyDetection =
        ExpenseAnomalyDetection(expenseDataAnomalies);
    List<ExpenseData> anomalies = anomalyDetection.detectAnomalies();

    return Scaffold(
      appBar: AppBar(
        title: const Text('IA prediction and report anomalies'),
      ),
      body: Column(
        children: [
          Container(
            height: 300,
            padding: const EdgeInsets.all(10.0),
            child: LineChart(
              LineChartData(
                gridData: const FlGridData(show: true),
                titlesData: FlTitlesData(
                    rightTitles: const AxisTitles(
                      sideTitles: SideTitles(showTitles: false),
                    ),
                    topTitles: const AxisTitles(
                      sideTitles: SideTitles(showTitles: false),
                    ),
                    bottomTitles: AxisTitles(
                      sideTitles: SideTitles(
                        showTitles: true,
                        // reservedSize: 32,
                        // interval: 1,
                        getTitlesWidget: bottomTitleWidgets,
                      ),
                    ),
                    leftTitles: const AxisTitles(
                        axisNameWidget: Text(
                          'Gastos',
                          style: TextStyle(
                            fontSize: 10,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        sideTitles:
                            SideTitles(reservedSize: 30, showTitles: true))),
                lineTouchData: LineTouchData(
                  touchTooltipData: LineTouchTooltipData(
                    getTooltipItems: (List<LineBarSpot> touchedBarSpots) {
                      return touchedBarSpots.map((barSpot) {
                        final flSpot = barSpot;
                    
                        int year = (flSpot.x.toInt() / 12).floor();
                        int month = (flSpot.x.toInt() % 12);
                        if (month == 0) {
                          month = 12;
                          year = year - 1;
                        }
                    
                        return LineTooltipItem(
                          '${flSpot.y.toStringAsFixed(1)} Bs \n',
                          const TextStyle(
                            fontWeight: FontWeight.bold,
                          ),
                          children: [
                            TextSpan(
                              text: '$month/$year',
                              style: const TextStyle(
                                fontStyle: FontStyle.italic,
                                fontWeight: FontWeight.w900,
                              ),
                            ),
                          ],
                        );
                      }).toList();
                    },
                  ),
                ),
                borderData: FlBorderData(show: true),
                lineBarsData: [
                  LineChartBarData(
                    spots: expenseData
                        .map((e) => FlSpot(e.year * 12.0 + e.month, e.expense))
                        .toList(),
                    // isCurved: true,
                    color: Colors.blue,
                    barWidth: 2,
                    belowBarData: BarAreaData(
                      show: true, 
                      gradient: const LinearGradient(
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                        colors: [Color.fromARGB(210, 33, 149, 243), Color.fromARGB(0, 0, 0, 0)]
                      )
                    ),
                  ),
                  LineChartBarData(
                    dashArray: [20, 10],
                    spots: predictions
                        .map((e) => FlSpot(e.year * 12.0 + e.month, e.expense))
                        .toList(),
                    // isCurved: true,
                    color: Colors.green,
                    barWidth: 2,
                    belowBarData: BarAreaData(show: false),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 20),
          Container(
            height: 300,
            padding: const EdgeInsets.all(10.0),
            child: LineChart(
              LineChartData(
                gridData: const FlGridData(show: true),
                titlesData: FlTitlesData(
                    rightTitles: const AxisTitles(
                      sideTitles: SideTitles(showTitles: false),
                    ),
                    topTitles: const AxisTitles(
                      sideTitles: SideTitles(showTitles: false),
                    ),
                    bottomTitles: AxisTitles(
                      sideTitles: SideTitles(
                        showTitles: true,
                        // reservedSize: 32,
                        // interval: 1,
                        getTitlesWidget: bottomTitleWidgets,
                      ),
                    ),
                    leftTitles: const AxisTitles(
                        axisNameWidget: Text(
                          'Gastos',
                          style: TextStyle(
                            fontSize: 10,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        sideTitles:
                            SideTitles(reservedSize: 30, showTitles: true))),
                lineTouchData: LineTouchData(
                  touchTooltipData: LineTouchTooltipData(
                    getTooltipColor: (touchedSpot) => Colors.blueGrey.withOpacity(0.8),
                    getTooltipItems: (List<LineBarSpot> touchedBarSpots) {
                      return touchedBarSpots.map((barSpot) {
                        final flSpot = barSpot;
                    
                        int year = (flSpot.x.toInt() / 12).floor();
                        int month = (flSpot.x.toInt() % 12);
                        if (month == 0) {
                          month = 12;
                          year = year - 1;
                        }
                    
                        return LineTooltipItem(
                          '${flSpot.y.toStringAsFixed(1)} Bs \n',
                          const TextStyle(),
                          children: [
                            TextSpan(
                              text: '$month/$year',
                            ),
                          ],
                        );
                      }).toList();
                    },
                  ),
                ),
                borderData: FlBorderData(show: true),
                lineBarsData: [
                  LineChartBarData(
                    spots: expenseDataAnomalies
                        .map((e) => FlSpot(e.year * 12.0 + e.month, e.expense))
                        .toList(),
                    // isCurved: true,
                    color: Colors.blue,
                    barWidth: 2,
                    belowBarData: BarAreaData(
                      show: true, 
                      gradient: const LinearGradient(
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                        colors: [Color.fromARGB(210, 33, 149, 243), Color.fromARGB(0, 0, 0, 0)]
                      )
                    ),
                  ),
                  LineChartBarData(
                    dashArray: [20, 10],
                    spots: anomalies
                    .map((e) => FlSpot(e.year * 12.0 + e.month, e.expense))
                    .toList(),
                    // isCurved: true,
                    color: Colors.red,
                    barWidth: 2,
                    belowBarData: BarAreaData(show: false),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget bottomTitleWidgets(double value, TitleMeta meta) {
    const style = TextStyle(
      fontWeight: FontWeight.bold,
      fontSize: 10,
    );
    Widget text;
    int year = (value.toInt() / 12).floor();
    int month = (value.toInt() % 12);
    if (month == 0) {
      month = 12;
      year = year - 1;
    }
    text = Text('$month/$year', style: style);

    return SideTitleWidget(
      axisSide: meta.axisSide,
      space: 7,
      angle: 1.55,
      child: text,
    );
  }
}
