package EMPRESA;

public class Main {

    public static void main(String[] args) {

        //EJERCICIOS DEL LIBRO - REPASO DE EJERCICIOS DE BASES DE DATOS

        //14.11 - Muestra los empleados
        EmpleadosDAO.muestraEmpleados();

        //14.12 - Muestra las oficinas devolviendo una lista
        System.out.println(OficinasDAO.recuperaOficinas());

        //14.13 - Muestra las oficinas por ciudad devolviendo una lista
        System.out.println(OficinasDAO.oficinasXCiudad("madrid"));

        //14.14 - Muestra una lista con los empleados cuya edad está comprendida entre 2 valores
        System.out.println(EmpleadosDAO.empleadosEntreEdades(33, 42));

        //14.16 - Crea un empleado y lo añade a la base de Datos
        //14.17 - Añade la funcionalidad de restringir la entrada de los datos para que no coincidan los id_empleado
        //14.18 - Añade la funcionalidad de controlar que la oficina en la creación del empleado existe
        EmpleadosDAO.creaEmpleado(20, "Pato Donald", 8, 2, "Logopeda titulado en Japón");

        //14.19 - Crear una aplicación que cambie a todos los empleados que trabajan en una oficina a otra, mostrar los empleados antes y después del cambio
        //En este caso estoy creando u método para introducir de forma hardcodeada las oficinas
        EmpleadosDAO.traspasoEmpleados(3,1);

        //14.20 - Se elimina un empleado cuyo id sea el pasado por parámetro
        EmpleadosDAO.eliminaEmpleado(12);

        //14.21 - Crear un método que devuelva una lista de todas las oficinas
        System.out.println(OficinasDAO.recuperaOficinas());
        //14.22 - Modificar el 14.21 para que solo devuelva las oficinas con una superficie mayor a la introducida por el usuario, utilizaré un parámetro
        System.out.println(OficinasDAO.recuperaConSuperficieMayor(480.0f));

        //14.23 - Crear un programa que permita cambiar la ciudad e incrementar las ventas de las oficinas
        //Cambiar la Ciudad
        OficinasDAO.trasladoDeOficina(2, "MALAGA");
        //Aumentar las Ventas
        OficinasDAO.aumentaVentas(4, 100);


    }

}
