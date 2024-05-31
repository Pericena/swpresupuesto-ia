<?php

namespace App\GraphQL\Queries;

use App\Http\Controllers\GastoController;

class GastoPredictionQuery
{
    protected $gastoController;

    public function __construct(GastoController $gastoController)
    {
        $this->gastoController = $gastoController;
    }

    public function predictBetweenDatesWithMonths($_, array $args)
    {
        $startDate = $args['startDate'];
        $numberOfMonths = $args['numberOfMonths'];
        $response = $this->gastoController->predictBetweenDatesWithMonths($startDate, $numberOfMonths);
        return $response->getData(true); // Retorna los datos de la respuesta JSON
    }
}
