package EMPRESA;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpleadosDAO { //CRUD (Create, Read, Update, Delete).

    public static Connection conexion = conecta();

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
//______________________________________________________________________________________________________________________MUESTRA EMPLEADOS
    public static void muestraEmpleados() {
        try {
            //Sentencia escrita para llevar a cabo
            String sentencia = "SELECT * FROM empleados";
            //Se crea un objeto Statement sobre la conexión para posteriormente introducir una tipo de sentencia específica
            Statement miSt = conexion.createStatement();
            //Se crea un Resultset sobre el Statement con la sentencia especificada que contendrá el "contenedor/paquete/caja" 'Statement'
            ResultSet resultado = miSt.executeQuery(sentencia);

            //Mientras haya lineas por leer en el resultado de consulta: (itera sobre las filas del ResultSet)
            while (resultado.next()) {

                Empleados e = new Empleados(resultado.getInt("id_empleado"),
                        resultado.getString("nombre"),
                        resultado.getInt("edad"),
                        resultado.getInt("id_oficina"),
                        resultado.getString("puesto"),
                        resultado.getDate("contrato"));

                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
//______________________________________________________________________________________________________________________EMPLEADOS ENTRE EDADES
    public static ArrayList<Empleados> empleadosEntreEdades(int a, int b) {

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        String sentencia = "SELECT * FROM empleados WHERE edad <= ? AND edad >= ?";

        int mayor, menor;
        //Math.max/min recoge el valor máximo/mínimo de los dos números, en caso de ser iguales recogen de igual forma los números
        mayor = Math.max(a, b);
        menor = Math.min(a, b);

        try {
            PreparedStatement miPrep = conexion.prepareStatement(sentencia);
            miPrep.setInt(1, mayor);
            miPrep.setInt(2, menor);
            ResultSet res = miPrep.executeQuery();

            while(res.next()) {
                listaEmpleados.add(new Empleados(res.getInt("id_empleado"),
                        res.getString("nombre"),
                        res.getInt("edad"),
                        res.getInt("id_oficina"),
                        res.getString("puesto"),
                        res.getDate("contrato")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaEmpleados;
    }
//______________________________________________________________________________________________________________________EMPLEADOS POR OFICINA
    public static ArrayList<Empleados> empleadosXOficina(int oficina) {

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        String sentencia = "SELECT * FROM empleados WHERE id_oficina = ?";

        if (OficinasDAO.validaOficina(oficina)) {

            try {
                PreparedStatement prep = conexion.prepareStatement(sentencia);
                prep.setInt(1, oficina);
                ResultSet res = prep.executeQuery();

                while (res.next()) {
                    listaEmpleados.add(new Empleados(res.getInt("id_empleado"),
                                                        res.getString("nombre"),
                                                        res.getInt("edad"),
                                                        res.getInt("id_oficina"),
                                                        res.getString("puesto"),
                                                        res.getDate("contrato")));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else
            System.out.println("\n\nLa oficina introducida no se encuentra registrada en la base de datos");

        return listaEmpleados;
    }
//______________________________________________________________________________________________________________________
    public static void creaEmpleado(int id_empleado, String nombre, int edad, String puesto) {

        Empleados insertado;

        //Validar los datos de entrada
        if (validaString(nombre) && edad > 0 && validaString(puesto) && validaIdEmpleado(id_empleado)) {

            insertado = new Empleados(id_empleado, nombre, edad, puesto, Date.valueOf(LocalDate.now()));

            String sentencia = "INSERT INTO empleados (id_empleado, nombre, edad, puesto, contrato) VALUES (?, ?, ?, ?, ?)";

            try {
                PreparedStatement miPrep = conexion.prepareStatement(sentencia);
                miPrep.setInt(1, insertado.getId_empleado());
                miPrep.setString(2, insertado.getNombre());
                miPrep.setInt(3, insertado.getEdad());
                miPrep.setString(4, insertado.getPuesto());
                miPrep.setDate(5, insertado.getContrato());

                miPrep.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\n\nSe ha registrado correctamente en la base de datos el nuevo empleado: " + insertado);

        } else
            System.out.println("\n\nAlguno de estos valores introducidos no es válido: " + id_empleado + "-" + nombre + "-" + edad + "-" + puesto);

    }
//______________________________________________________________________________________________________________________
    public static void creaEmpleado(int id_empleado, String nombre, int edad, Integer oficina, String puesto) {

        Empleados insertado;

        //Validar los datos de entrada
        if (validaString(nombre) && edad > 0 && validaString(puesto) && validaIdEmpleado(id_empleado) && OficinasDAO.validaOficina(oficina)) {

            insertado = new Empleados(id_empleado, nombre, edad, oficina, puesto, Date.valueOf(LocalDate.now()));

            String sentencia = "INSERT INTO empleados (id_empleado, nombre, edad, id_oficina, puesto, contrato) VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement miPrep = conexion.prepareStatement(sentencia);
                miPrep.setInt(1, insertado.getId_empleado());
                miPrep.setString(2, insertado.getNombre());
                miPrep.setInt(3, insertado.getEdad());
                miPrep.setInt(4, insertado.getOficina());
                miPrep.setString(5, insertado.getPuesto());
                miPrep.setDate(6, insertado.getContrato());

                miPrep.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\n\nSe ha registrado correctamente en la base de datos el nuevo empleado: " + insertado);

        } else
            System.out.println("\n\nAlguno de estos valores introducidos no es válido: " + id_empleado + "-" + nombre + "-" + edad + "-" + oficina + "-" + puesto);

    }
//______________________________________________________________________________________________________________________ELIMINA EMPLEADO
    public static void eliminaEmpleado(int id_empleado) {

        Empleados eliminado = null;
        String sentencia = "DELETE empleados FROM empleados WHERE id_empleado = ?";

        if (!validaIdEmpleado(id_empleado)) {
            try {
                PreparedStatement prep = conexion.prepareStatement(sentencia);
                prep.setInt(1, id_empleado);

                prep.executeUpdate();
                System.out.println("\n\nSe ha eliminado el empleado con id " + id_empleado + " correctamente");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else
            System.out.println("\n\nEl id introducido " + id_empleado + " no se encuentra registrado en la base de datos");

    }
//______________________________________________________________________________________________________________________TRASPASO DE OFICINA
    //Transpasa todos los empleados de una oficina a otra
    public static void traspasoEmpleados(Integer oficOrigen, Integer oficDestino) {

        if (OficinasDAO.validaOficina(oficOrigen) && OficinasDAO.validaOficina(oficDestino)) {

            muestraEmpleados();

            String sentencia = "UPDATE empleados SET id_oficina = ? WHERE id_oficina = ?";
            try {
                PreparedStatement prep = conexion.prepareStatement(sentencia);
                prep.setInt(1, oficDestino);
                prep.setInt(2, oficOrigen);

                prep.executeUpdate();

                System.out.println("\n--------------------------------------------------\nSe han transpasado los empleados correctamente\n--------------------------------------------------");
                muestraEmpleados();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else
            System.out.println("\n\nAlguna de las oficinas no está registrada en la base de datos");

    }
//______________________________________________________________________________________________________________________VALIDA ID_EMPLEADO
    private static boolean validaIdEmpleado(int id) {

        String sentencia = "SELECT * FROM empleados WHERE id_empleado = ?";
        boolean valido = true;

        try {
            PreparedStatement miPrep = conexion.prepareStatement(sentencia);
            miPrep.setInt(1, id);

            ResultSet res = miPrep.executeQuery();

            if (res.next())
                valido = false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return valido;
    }
//______________________________________________________________________________________________________________________VALIDA STRING
    private static boolean validaString(String nombre) {
        if (nombre.length() > 100)
            return false;
        else
            return true;
    }


}
