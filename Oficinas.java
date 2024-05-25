package EMPRESA;

import java.util.Objects;

public class Oficinas implements Comparable<Oficinas> {

    private final int ID_OFICINA;
    private String ciudad;
    private float superficie;
    private float ventas;
//______________________________________________________________________________________________________________________CONSTRUCTOR
    public Oficinas(int ID_OFICINA, String ciudad, float superficie, float ventas) {
        this.ID_OFICINA = ID_OFICINA;
        this.ciudad = ciudad;
        this.superficie = superficie;
        this.ventas = ventas;
    }
//______________________________________________________________________________________________________________________GETTERS
    public int getID_OFICINA() {
        return ID_OFICINA;
    }

    public String getCiudad() {
        return ciudad;
    }

    public float getSuperficie() {
        return superficie;
    }

    public float getVentas() {
        return ventas;
    }
//______________________________________________________________________________________________________________________SETTERS
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    @Override
    public int compareTo(Oficinas o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oficinas oficinas = (Oficinas) o;
        return ID_OFICINA == oficinas.ID_OFICINA && Float.compare(superficie, oficinas.superficie) == 0 && Float.compare(ventas, oficinas.ventas) == 0 && Objects.equals(ciudad, oficinas.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_OFICINA, ciudad, superficie, ventas);
    }

    @Override
    public String toString() {
        return  "\n\nID_OFICINA: " + ID_OFICINA +
                "\nCiudad: " + ciudad +
                "\nSuperficie: " + superficie +
                "\nVentas: " + ventas;
    }

}
