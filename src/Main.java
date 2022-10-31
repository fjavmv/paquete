import gaussJordan.GaussJordan;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        GaussJordan gaussJordan = new GaussJordan();
        gaussJordan.setFilas(Integer.parseInt(JOptionPane.showInputDialog("Introduce el número de filas: ")));
        gaussJordan.setColumnas(Integer.parseInt(JOptionPane.showInputDialog("Introduce el número de columnas: ")));
        GaussJordan.llenarMatriz(entrada);
        GaussJordan.resolverMatriz();


    }
}
