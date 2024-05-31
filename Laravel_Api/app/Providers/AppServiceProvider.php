<?php

namespace App\Providers;

use App\Http\Controllers\GastoController;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        // Registra el controlador en el contenedor de servicios
        $this->app->bind(GastoController::class, function ($app) {
            return new GastoController();
        });
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        //
    }
}
