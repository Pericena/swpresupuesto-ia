import 'package:flutter/material.dart';
import 'package:iaprediction/models/gastos_model.dart';
import 'package:iaprediction/provider/gastos_provider.dart';
import 'package:iaprediction/utils/date_convert.dart';
import 'package:iaprediction/utils/validate.dart';
import 'package:mat_month_picker_dialog/mat_month_picker_dialog.dart';
import 'package:provider/provider.dart';
import 'package:syncfusion_flutter_charts/charts.dart';
import 'package:intl/intl.dart';

class IAScreen extends StatefulWidget {
  const IAScreen({super.key});

  @override
  State<IAScreen> createState() => _IAScreenState();
}

class _IAScreenState extends State<IAScreen> {
  late Future<void> _fetchExpensesFuture;
  late Future<void> _fetchExpensesAnomaliesFuture;
  DateTime _selected = DateTime(2018);
  TextEditingController valueController = TextEditingController();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    super.initState();
    _fetchExpensesFuture = _fetchExpenses('2018-01-01', 3);
    _fetchExpensesAnomaliesFuture = _fetchExpensesAnomalies();
  }

  Future<void> _fetchExpenses(String fecha, int month) async {
    final gastoProvider = Provider.of<GastoProvider>(context, listen: false);
    await gastoProvider.fetchAnomalies();
    await gastoProvider.fetchPredictionsBetweenDates(fecha, month);
  }

  Future<void> _fetchExpensesAnomalies() async {
    final gastoProvider = Provider.of<GastoProvider>(context, listen: false);
    await gastoProvider.fetchAnomalies();
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          centerTitle: true,
          title: const Text('IAs para el control de gastos',
              style: TextStyle(color: Colors.white)),
          backgroundColor: Colors.blue,
          elevation: 1,
        ),
        body: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: [
                Form(
                  key: _formKey,
                  autovalidateMode: AutovalidateMode
                      .onUserInteraction, // Activa la validación dinámica
                  child: Column(
                    children: [
                      const Text(
                        "Predicción por mes",
                        style: TextStyle(
                            fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          TextButton(
                            style: TextButton.styleFrom(
                                backgroundColor: Colors.lightBlueAccent),
                            child: const Text('Seleccionar mes de inicio'),
                            onPressed: () async {
                              final selected = await showMonthPicker(
                                context: context,
                                initialDate: DateTime(2024, 4),
                                firstDate: DateTime(2018),
                                lastDate: DateTime(2024, 4),
                              );
                              setState(() => _selected = selected!);
                            },
                          ),
                          Text(convertirFecha(
                              DateFormat('yMd').format(_selected))),
                        ],
                      ),
                      _buildTextField(
                          valueController, "Mes para la predicción"),
                      _buildBuyNowButton(context, valueController, _selected),
                    ],
                  ),
                ),
                _buildChartFutureBuilder(
                  context,
                  _fetchExpensesFuture,
                  'Predicción de gastos',
                  (expenseProvider) => [
                    _buildAreaSeries(
                        expenseProvider.predictionsStartDate, Colors.blue),
                    _buildLineSeries(expenseProvider.predictionsStartDateMonths,
                        Colors.green),
                  ],
                ),
                const SizedBox(height: 20),
                _buildChartFutureBuilder(
                  context,
                  _fetchExpensesAnomaliesFuture,
                  'Detector de anomalías',
                  (expenseProvider) => [
                    _buildAreaSeries(
                        expenseProvider.expenses, Colors.lightBlueAccent),
                    _buildLineSeries(expenseProvider.anomalies, Colors.red),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildTextField(TextEditingController controller, String labelText) {
    return TextFormField(
      decoration: InputDecoration(hintText: labelText, labelText: labelText),
      validator: validateInput,
      controller: controller,
      textInputAction: TextInputAction.done,
      keyboardType: TextInputType.number,
    );
  }

  Widget _buildBuyNowButton(BuildContext context,
      TextEditingController controller, DateTime datetime) {
    return Container(
      margin: const EdgeInsets.all(10),
      child: ElevatedButton(
        onPressed: () async {
          if (_formKey.currentState!.validate()) {
            String fecha = convertirFecha(DateFormat('yMd').format(_selected));
            setState(() {
              _fetchExpensesFuture = _fetchExpenses(fecha, int.parse(controller.text));
            });
          }
        },
        child: const Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "OK",
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildChartFutureBuilder(
    BuildContext context,
    Future<void> future,
    String title,
    List<CartesianSeries<Gasto, String>> Function(GastoProvider) seriesBuilder,
  ) {
    return FutureBuilder<void>(
      future: future,
      builder: (BuildContext context, AsyncSnapshot<void> snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const SizedBox(
            height: 300,
            child: Center(child: CircularProgressIndicator()),
          );
        } else if (snapshot.hasError) {
          return const SizedBox(
            height: 300,
            child: Center(child: Text('Error loading expenses')),
          );
        } else {
          final expenseProvider = Provider.of<GastoProvider>(context);
          return Container(
            height: 300,
            padding: const EdgeInsets.all(10.0),
            child: SfCartesianChart(
              title: ChartTitle(text: title),
              legend: const Legend(isVisible: true),
              tooltipBehavior: TooltipBehavior(enable: true),
              primaryXAxis: const CategoryAxis(
                edgeLabelPlacement: EdgeLabelPlacement.shift,
                labelPlacement: LabelPlacement.onTicks,
              ),
              primaryYAxis: NumericAxis(
                edgeLabelPlacement: EdgeLabelPlacement.shift,
                numberFormat: NumberFormat.compact(),
                title: const AxisTitle(text: 'Monto'),
              ),
              zoomPanBehavior: ZoomPanBehavior(
                enableDoubleTapZooming: true,
                enablePanning: true,
                zoomMode: ZoomMode.x,
                enablePinching: true,
              ),
              series: seriesBuilder(expenseProvider),
            ),
          );
        }
      },
    );
  }

  AreaSeries<Gasto, String> _buildAreaSeries(List<Gasto> data, Color color) {
    return AreaSeries<Gasto, String>(
      dataSource: data,
      xValueMapper: (Gasto exp, _) => '${exp.year}-${exp.month}',
      yValueMapper: (Gasto exp, _) => exp.monto,
      name: 'Gastos',
      markerSettings: const MarkerSettings(isVisible: true),
      gradient: LinearGradient(
        colors: [color, Colors.transparent],
        begin: Alignment.topCenter,
        end: Alignment.bottomCenter,
      ),
    );
  }

  LineSeries<Gasto, String> _buildLineSeries(List<Gasto> data, Color color) {
    return LineSeries<Gasto, String>(
      dataSource: data,
      xValueMapper: (Gasto exp, _) => '${exp.year}-${exp.month}',
      yValueMapper: (Gasto exp, _) => exp.monto,
      name: 'Predicción',
      markerSettings: const MarkerSettings(isVisible: true),
      color: color,
    );
  }
}
