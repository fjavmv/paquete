package gaussJordan;
import java.util.Scanner;
/**
 * @author Francisco Javier Méndez Vazquez
 * @version 1.0
 * @Note Resuelve una matriz aumentada de m x n columnas.
 */
public class GaussJordan {
    private static int[][] matrizGauss;
    private static int[][] matrizAux;
    private static int filas;
    private static int columnas;

    /**
     * @param filas Establece el número de filas de la matriz
     */
    public void setFilas(int filas) {
        GaussJordan.filas = filas;
    }

    /**
     * @param columnas Establece el número de columnas de la matriz
     */
    public void setColumnas(int columnas) {
        GaussJordan.columnas = columnas + 1;
    }

    /**
     * @param entrada Recibe un dato de tipo Scanner que pasa los elementos para calcular el valor de la matriz.
     */
    public static void llenarMatriz(Scanner entrada) {
        matrizGauss = new int[filas][columnas];
        matrizAux = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Ingresa el valor para la fila [" + i + "]" + "columna [" + j + "]: ");
                matrizGauss[i][j] = entrada.nextInt();
            }
        }
    }

    /**
     * @implNote Método estatico que resuelve la matriz ingresada.
     */
    public static void resolverMatriz() {
        int[][] matrizNueva = Matriz3X3Extendida.paso6();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                System.out.print(matrizNueva[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("*************************");
        System.out.println("X1 = " + matrizNueva[0][3] + "\n" + "X2 = " + matrizNueva[1][3] + "\n" + "X3 = " + matrizNueva[2][3]);
    }

    private static class Matriz3X3Extendida {

        //multiplicamos a los elementos de la la fila uno R1 -> CR1
        private static int[][] paso1() {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (i == 0) {
                        matrizAux[i][j] = matrizGauss[i][j] / matrizGauss[0][0];
                    } else {
                        matrizAux[i][j] = matrizGauss[i][j];
                    }
                }
            }
            return matrizAux;
        }

        /**
         * R2 -> R2 - CR1
         * R3 -> R3 - CR1
         */
        private static int[][] paso2() {
            int[][] matrizNueva = paso1();
            int val = matrizNueva[1][0];
            int val2 = matrizNueva[2][0];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (i == 1) {
                        matrizAux[i][j] = matrizNueva[i][j] - (val * matrizNueva[0][j]);
                    } else if (i == 2) {
                        matrizAux[i][j] = matrizNueva[i][j] - (val2 * matrizNueva[0][j]);
                    }
                }
            }
            return matrizAux;
        }

        //multiplicamos a los elementos de la la fila uno R2 -> CR2
        private static int[][] paso3() {
            int[][] matrizNueva = paso2();
            int val = matrizNueva[1][1];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (i == 1) {
                        matrizAux[i][j] = matrizNueva[i][j] / val;
                    } else {
                        matrizAux[i][j] = matrizNueva[i][j];
                    }
                }
            }
            return matrizAux;
        }

        /**
         * R1 -> R1 - CR2
         * R3 -> R3 - CR2
         */
        private static int[][] paso4() {
            int[][] matrizNueva = paso3();
            int val = matrizNueva[0][1];
            int val2 = matrizNueva[2][1];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (i == 0) {
                        matrizAux[i][j] = matrizNueva[i][j] - (val * matrizNueva[1][j]);
                    } else if (i == 2) {
                        matrizAux[i][j] = matrizNueva[i][j] - (val2 * matrizNueva[1][j]);
                    }
                }
            }
            return matrizAux;
        }

        //multiplicamos a los elementos de la la fila uno R3 -> -R3
        private static int[][] paso5() {
            int[][] matrizNueva = paso4();
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (i == 2) {
                        matrizAux[i][j] = -(matrizNueva[i][j]);
                    } else {
                        matrizAux[i][j] = matrizNueva[i][j];
                    }
                }
            }
            return matrizAux;
        }

        /**
         * R1 -> R1 + R3
         * R2 -> R2 - CR3
         */
        private static int[][] paso6() {
            int[][] matrizNueva = paso5();
            int val = matrizNueva[1][2];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (i == 0) {
                        matrizAux[i][j] = matrizNueva[i][j] + matrizNueva[2][j];
                    } else if (i == 1) {
                        matrizAux[i][j] = matrizNueva[i][j] - (val * matrizNueva[2][j]);
                    }
                }
            }
            return matrizAux;
        }
    }

}
