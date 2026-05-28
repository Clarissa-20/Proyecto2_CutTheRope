/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_p2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 * @author HP
 */
public class VisualArchivos extends JFrame {
    private JTextField txtRuta;
    private JTextField txtBusqueda;
    private JButton btnAnalizar;
    private JTextArea txtResultado;
    private Logica logica;

    public VisualArchivos() {
        logica = new Logica();

        setTitle("Analizador de Archivos");
        setSize(700, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout(15, 15));

        JPanel panelFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        panelFormulario.add(new JLabel("Ruta:"));
        txtRuta = new JTextField(18); 
        panelFormulario.add(txtRuta);

        panelFormulario.add(new JLabel("Buscar texto:"));
        txtBusqueda = new JTextField(12); 
        panelFormulario.add(txtBusqueda);

        btnAnalizar = new JButton("Analizar");
        panelFormulario.add(btnAnalizar);

        add(panelFormulario, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(5, 5));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        
        JLabel lblResultados = new JLabel("Resultados del Analisis:");
        lblResultados.setFont(new Font("Arial", Font.BOLD, 12));
        panelCentro.add(lblResultados, BorderLayout.NORTH);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        txtResultado.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); 
        
        JScrollPane scroll = new JScrollPane(txtResultado);
        panelCentro.add(scroll, BorderLayout.CENTER);
        
        add(panelCentro, BorderLayout.CENTER);

        btnAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ruta = txtRuta.getText().trim();
                String buscar = txtBusqueda.getText().trim();

                if (ruta.isEmpty()) {
                    JOptionPane.showMessageDialog(VisualArchivos.this, "Introduce una ruta.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                File dir = new File(ruta);

                if (!dir.exists()) {
                    JOptionPane.showMessageDialog(VisualArchivos.this, "La ruta no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!dir.isDirectory()) {
                    JOptionPane.showMessageDialog(VisualArchivos.this, "No es un directorio valido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                logica.analizar(dir, buscar);

                StringBuilder res = new StringBuilder();
                res.append("==================================================\n");
                res.append("1. CONTADOR DE ARCHIVOS POR EXTENSION\n");
                res.append("==================================================\n");
                res.append("TXT: ").append(logica.getTxt()).append(" archivos\n");
                res.append("JAVA: ").append(logica.getJava()).append(" archivos\n");
                res.append("PDF: ").append(logica.getPdf()).append(" archivos\n");
                res.append("OTROS: ").append(logica.getOtros()).append(" archivos\n\n");

                res.append("==================================================\n");
                res.append("2. BUSQUEDA RECURSIVA DE ARCHIVOS POR NOMBRE\n");
                res.append("==================================================\n");
                res.append(logica.getRutas());

                txtResultado.setText(res.toString());
            }
        });
    }
}
