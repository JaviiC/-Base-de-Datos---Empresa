package EMPRESA;

public enum Ciudades {
    BARCELONA,
    LISBOA,
    MADRID,
    MALAGA,
    ROMA,
    SIDNEY,
    VALENCIA;
//______________________________________________________________________________________________________________________EXPRESIÓN REGULAR
    public static String expresionRegular() {
        String cadena = "^(";
        for (Ciudades ciudad : Ciudades.values()) {
            cadena += ciudad.toString() + "|";
        }
        cadena = cadena.substring(0, cadena.length()-1); cadena += ")$";
        return cadena;
    }

}


