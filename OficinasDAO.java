package EMPRESA;

import java.sql.*;
import java.util.ArrayList;

public class OficinasDAO {

    public static Connection conexion = conecta();
    public static final String CIUDADES_VALIDAS = Ciudades.expresionRegular();
//______________________________________________________________________________________________________________________CONEXION
    private static Connection conecta(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3333/empresa", user = "root", password = "Root2324";
        try {
            //Se crea una conexión entre el entorno y la base de datos.
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
//______________________________________________________________________________________________________________________RECUPERA OFICINAS
    public static ArrayList<Oficinas> recuperaOficinas() {

        ArrayList<Oficinas> listaOficinas = new ArrayList<>();
        String sentencia = "SELECT * FROM oficinas";
        try {
            Statement miSt = conexion.createStatement();
            ResultSet res = miSt.executeQuery(sentencia);

            while (res.next()) {
                listaOficinas.add(new Oficinas(res.getInt("id_oficina"),
                                                res.getString("ciudad"),
                                                res.getFloat("superficie"),
                                                res.getFloat("ventas")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaOficinas;
    }
//______________________________________________________________________________________________________________________RECUPERA OFICINAS CON SUPERFICIE MAYOR
    public static ArrayList<Oficinas> recuperaConSuperficieMayor(float superficieMinima) {

        ArrayList<Oficinas> listaOficinas = new ArrayList<>();
        String sentencia = "SELECT * FROM oficinas WHERE superficie >= ?";
        try {
            PreparedStatement prep = conexion.prepareStatement(sentencia);
            prep.setFloat(1, superficieMinima);

            ResultSet res = prep.executeQuery();

            while (res.next()) {
                listaOficinas.add(new Oficinas(res.getInt("id_oficina"),
                                                res.getString("ciudad"),
                                                res.getFloat("superficie"),
                                                res.getFloat("ventas")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaOficinas;
    }
//______________________________________________________________________________________________________________________OFICINAS X CIUDAD
    public static ArrayList<Oficinas> oficinasXCiudad(String ciudad) {

        ArrayList<Oficinas> listaOficinas = new ArrayList<>();
        String sentencia = "SELECT * FROM oficinas WHERE ciudad = ?";
        try {
            //Se crea una clase 'PreparedStatement' porque se pretende realizar una consultar con un parámetro, se precisa pasar la consulta
            PreparedStatement miPrep = conexion.prepareStatement(sentencia);
            //Se necesitan sustituir los parámetros
            miPrep.setString(1, ciudad);
            //Una clase ResultSet para recuperar los resultados de la consulta
            ResultSet res = miPrep.executeQuery();
            //Mientras el ResultSet tenga algún registro...
            while (res.next()) {
                listaOficinas.add(new Oficinas(res.getInt("id_oficina"),
                                                res.getString("ciudad"),
                                                res.getFloat("superficie"),
                                                res.getFloat("ventas")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaOficinas;
    }
//______________________________________________________________________________________________________________________TRASLADO DE OFICINA
    public static void trasladoDeOficina(Integer oficina, String ciudadDestino) {

        //Si la oficina introducida es valida y la ciudad introducida tambien
        if (validaOficina(oficina) && validaCiudad(ciudadDestino)) {

            try {
                //Si la oficina ya tiene como ciudad la introducida como destino NO ENTRARÁ
                if (!mismaOfi(oficina,ciudadDestino)) {

                    String sentencia = "UPDATE oficinas SET ciudad = ? WHERE id_oficina = ?";
                    PreparedStatement prep = conexion.prepareStatement(sentencia);
                    prep.setInt(2, oficina);
                    prep.setString(1, ciudadDestino);

                    prep.executeUpdate();
                    System.out.println("La oficina con id " + oficina + " ha sido trasladada a " + ciudadDestino + " correctamente");
                } else
                    System.out.println("La oficina ya se encuentra en la ciudad destino");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else
            System.out.println("La oficina o ciudad introducida no se encuentra registrada en la base de datos");
    }
//______________________________________________________________________________________________________________________AUMENTA VENTAS
    public static void aumentaVentas(int oficina, float ventas) {

        if (validaOficina(oficina) && ventas > 0) {
            String sentencia = "UPDATE oficinas SET ventas = ventas + ? WHERE id_oficina = ?";

            try {
                PreparedStatement prep = conexion.prepareStatement(sentencia);
                prep.setFloat(1, ventas);
                prep.setInt(2, oficina);

                prep.executeUpdate();
                System.out.println("Se han registrado " + ventas + " ventas más en la oficina con id " + oficina);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else
            System.out.println("Alguno de los valors introducidos no es válido");

    }
//______________________________________________________________________________________________________________________VALIDA OFICINA
    public static boolean validaOficina(int id) {

        String sentencia = "SELECT * FROM oficinas WHERE id_oficina = ?";
        boolean valido = false;

        try {
            PreparedStatement miPrep = conexion.prepareStatement(sentencia);
            miPrep.setInt(1, id);

            ResultSet res = miPrep.executeQuery();

            if (res.next())
                valido = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return valido;
    }
//______________________________________________________________________________________________________________________VALIDA CIUDAD
    private static boolean validaCiudad(String ciudad) {

        return ciudad.matches(CIUDADES_VALIDAS);
    }
//______________________________________________________________________________________________________________________
    private static boolean mismaOfi(int oficina, String ciudad) {

        boolean mismaOficina = false;
        String sentencia = "SELECT * FROM oficinas WHERE id_oficina = ? AND ciudad = ?";

        try {
            PreparedStatement prep = conexion.prepareStatement(sentencia);
            prep.setInt(1, oficina);
            prep.setString(2, ciudad);

            ResultSet res = prep.executeQuery();

            if (res.next()) {
                mismaOficina = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mismaOficina;
    }

}
