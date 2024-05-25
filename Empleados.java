package EMPRESA;

import java.sql.Date;
import java.util.Objects;

public class Empleados implements Comparable<Empleados> {

    private final int ID_EMPLEADO;
    private final String NOMBRE;
    private final int EDAD;
    private Integer oficina;
    private String puesto;
    private Date contrato;
//______________________________________________________________________________________________________________________CONSTRUCTOR
    public Empleados(int id_empleado, String nombre, int edad, Integer oficina, String puesto, Date contrato) {
        this.ID_EMPLEADO = id_empleado;
        this.NOMBRE = nombre;
        this.EDAD = edad;
        this.oficina = oficina;
        this.puesto = puesto;
        this.contrato = contrato;
    }

    public Empleados(int id_empleado, String nombre, int edad, String puesto, Date contrato) {
        this.ID_EMPLEADO = id_empleado;
        this.NOMBRE = nombre;
        this.EDAD = edad;
        this.puesto = puesto;
        this.contrato = contrato;
    }
//______________________________________________________________________________________________________________________GETTERS
    public int getId_empleado() {
        return ID_EMPLEADO;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public int getEdad() {
        return EDAD;
    }

    public int getOficina() {
        return oficina;
    }

    public String getPuesto() {
        return puesto;
    }

    public Date getContrato() {
        return contrato;
    }
//______________________________________________________________________________________________________________________SETTERS
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setContrato(Date contrato) {
        this.contrato = contrato;
    }

    @Override
    public int compareTo(Empleados otro) {
        return this.ID_EMPLEADO - otro.ID_EMPLEADO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleados empleados = (Empleados) o;
        return ID_EMPLEADO == empleados.ID_EMPLEADO && EDAD == empleados.EDAD && Objects.equals(NOMBRE, empleados.NOMBRE) && Objects.equals(oficina, empleados.oficina) && Objects.equals(puesto, empleados.puesto) && Objects.equals(contrato, empleados.contrato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_EMPLEADO, NOMBRE, EDAD, oficina, puesto, contrato);
    }

    @Override
    public String toString() {
        return  "\n\nID_EMPLEADO: " + ID_EMPLEADO +
                "\nNOMBRE: " + NOMBRE +
                "\nEDAD: " + EDAD +
                "\nOficina:" + oficina +
                "\nPuesto: " + puesto +
                "\nContrato:" + contrato;
    }

}
