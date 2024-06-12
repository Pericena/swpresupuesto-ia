package com.example.proyectosw2.Controller;

public class IngresoInput {
    private String concepto;
    private Double monto;
    private String fechaIngreso;
    private String cuentaID;
    private String categoriaID;

    // Getters y Setters
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCuentaID() {
        return cuentaID;
    }

    public void setCuentaID(String cuentaID) {
        this.cuentaID = cuentaID;
    }

    public String getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(String categoriaID) {
        this.categoriaID = categoriaID;
    }
}
