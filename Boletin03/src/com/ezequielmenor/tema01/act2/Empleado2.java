package com.ezequielmenor.tema01.act2;

public class Empleado2 {

    private String id;
    private String nombre;
    private double sueldo;
    private String departamento;

    public Empleado2(String id, String nombre, double sueldo, String departamento){
        this.id = id;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.departamento = departamento;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Sueldo: " + sueldo + "\n" +
                "Departamento: " + departamento;
    }
}
