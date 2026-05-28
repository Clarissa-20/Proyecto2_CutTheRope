/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_p2;

import java.io.File;

/**
 *
 * @author HP
 */
public class Logica {
    private int txt = 0;
    private int java = 0;
    private int pdf = 0;
    private int otros = 0;
    private StringBuilder rutas = new StringBuilder();

    public void analizar(File raiz, String buscar) {
        txt = 0;
        java = 0;
        pdf = 0;
        otros = 0;
        rutas = new StringBuilder();

        recorrer(raiz, buscar.toLowerCase());
    }

    private void recorrer(File actual, String buscar) {
        File[] lista = actual.listFiles();
        if (lista == null) {
            return;
        }

        for (File f : lista) {
            if (f.isDirectory()) {
                recorrer(f, buscar);
            } else if (f.isFile()) {
                contar(f.getName());
                buscarNombre(f, buscar);
            }
        }
    }

    private void contar(String nombre) {
        String nom = nombre.toLowerCase();
        if (nom.endsWith(".txt")) {
            txt++;
        } else if (nom.endsWith(".java")) {
            java++;
        } else if (nom.endsWith(".pdf")) {
            pdf++;
        } else {
            otros++;
        }
    }

    private void buscarNombre(File f, String buscar) {
        if (buscar.isEmpty()) {
            return;
        }
        if (f.getName().toLowerCase().contains(buscar)) {
            rutas.append(f.getAbsolutePath()).append("\n");
        }
    }

    public int getTxt() {
        return txt;
    }
    public int getJava() {
        return java;
    }
    public int getPdf() {
        return pdf;
    }
    public int getOtros() {
        return otros;
    }

    public String getRutas() {
        if (rutas.length() == 0) {
            return "No se encontraron archivos que coincidan con los criterios.\n";
        }
        return rutas.toString();
    }

}
