// To parse this JSON data, do
//
//     final gasto = gastoFromJson(jsonString);

import 'dart:convert';

Map<String, Gasto> gastoFromJson(String str) => Map.from(json.decode(str)).map((k, v) => MapEntry<String, Gasto>(k, Gasto.fromJson(v)));

String gastoToJson(Map<String, Gasto> data) => json.encode(Map.from(data).map((k, v) => MapEntry<String, dynamic>(k, v.toJson())));

class Gasto {
    final int year;
    final int month;
    final double monto;

    Gasto({
        required this.year,
        required this.month,
        required this.monto,
    });

    factory Gasto.fromJson(Map<String, dynamic> json) => Gasto(
        year: json["year"],
        month: json["month"],
        monto: json["monto"].toDouble(),
    );

    Map<String, dynamic> toJson() => {
        "year": year,
        "month": month,
        "monto": monto,
    };

    @override
    String toString() {
      return 'Gasto{year: $year, month: $month, monto: $monto}';
    }
}
