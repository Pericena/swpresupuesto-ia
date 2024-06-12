package com.example.proyectosw2.Controller;


public class EgresoInput {
    private String concepto;
    private Double monto;
    private String fechaEgreso;
    private String cuentaID;
    private String categoriaID;


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

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
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