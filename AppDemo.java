package EMPRESA;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppDemo extends JFrame{

    private JPanel MainPanel;
    private JButton muestraEmpleados;
    private JPanel Oficinas;
    private JPanel Empleados;
    private JTextField IntroduceId;
    private JTextField IntroduceNombre;
    private JTextField IntroduceEdad;
    private JTextField IntroduceOficinaEmpleado;
    private JTextField IntroducePuesto;
    private JTextField IntroduceIdADespedir;
    private JButton BotonContratar;
    private JButton BotonDespedir;
    private JTextField IntroduceCiudad;
    private JButton BotonBuscaOficinas;
    private JTextField IntroduceIdOficina;
    private JTextField IntroduceCiudadDestino;
    private JButton BotonTraslado;
    private JButton muestraOficinas;
    private JPanel Consultas;
    private JTextField EdadMaxima;
    private JButton BotonBuscarPorEdad;
    private JTextField EdadMinima;
    private JTextField IntroduceOficinaConsultas;
    private JButton buscarButton;

    public AppDemo() {
        setTitle("EMPRESA JC DEMOSTRACIONES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,440);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(MainPanel);

        BotonBuscaOficinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Se guarda en una variable el texto que se introdujo en el JTextField 'IntroduceCiudad'
                String ciudad = IntroduceCiudad.getText();
                ArrayList<Oficinas> listaOficinas = OficinasDAO.oficinasXCiudad(ciudad);
                System.out.println(listaOficinas);
            }
        });
        muestraOficinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(OficinasDAO.recuperaOficinas());
            }
        });
        BotonTraslado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OficinasDAO.trasladoDeOficina(Integer.parseInt(IntroduceIdOficina.getText()), IntroduceCiudadDestino.getText());
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        muestraEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmpleadosDAO.muestraEmpleados();
            }
        });
        BotonContratar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id_empleado = Integer.parseInt(IntroduceId.getText());
                    String nombre = IntroduceNombre.getText();
                    int edad = Integer.parseInt(IntroduceEdad.getText());
                    String oficina = IntroduceOficinaEmpleado.getText();
                    String puesto = IntroducePuesto.getText();

                    if (oficina.isEmpty()) {
                        EmpleadosDAO.creaEmpleado(id_empleado, nombre, edad, puesto);
                    } else
                        EmpleadosDAO.creaEmpleado(id_empleado, nombre, edad, Integer.parseInt(oficina), puesto);

                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        BotonDespedir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EmpleadosDAO.eliminaEmpleado(Integer.parseInt(IntroduceIdADespedir.getText()));
                } catch (Exception a) {
                    System.out.println(a.getMessage());
                }
            }
        });
        BotonBuscarPorEdad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Empleados> listaEmpleados = EmpleadosDAO.empleadosEntreEdades(Integer.parseInt(EdadMinima.getText()), Integer.parseInt(EdadMaxima.getText()));
                    System.out.println(listaEmpleados);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Empleados> listaEmpleados = EmpleadosDAO.empleadosXOficina(Integer.parseInt(IntroduceOficinaConsultas.getText()));
                    System.out.println(listaEmpleados);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
    }

}
