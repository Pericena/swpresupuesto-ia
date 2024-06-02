<?php

namespace App\GraphQL\Queries;

use App\Http\Controllers\GastoController;

class GastoAnomalyQuery
{
    protected $gastoController;

    public function __construct(GastoController $gastoController)
    {
        $this->gastoController = $gastoController;
    }

    public function detectAnomalies($_, array $args)
    {
        $response = $this->gastoController->detectAnomalies();
        return $response->getData(true); // Retorna los datos de la respuesta JSON
    }
}
