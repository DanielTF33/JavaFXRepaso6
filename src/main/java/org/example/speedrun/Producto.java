package org.example.speedrun;

import javafx.beans.property.*;

public class Producto {

    private StringProperty descripcion = new SimpleStringProperty(this, "descripcion", "");
    private IntegerProperty cantidad = new SimpleIntegerProperty(this, "cantidad", 0);
    private DoubleProperty precio = new SimpleDoubleProperty(this, "precio", 0.0);

    public Producto(String descripcion, Integer cantidad, Double precio) {
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setPrecio(precio);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public double getPrecio() {
        return precio.get();
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }
}
