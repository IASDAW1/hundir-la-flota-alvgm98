package hundirlaflota.model;

// import java.util.List;

import hundirlaflota.model.barcos.Barco;

public class Tablero {
   private char[][] tablero;
   // private List<Barco> barcos;

   public Tablero() {
      tablero = new char[10][10];
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            tablero[i][j] = '*';
         }
      }
   }

   public void colocarBarco(Barco barco) {
      /***
       * Si el barco va a estar en Horizontal, itera el eje X.
       * Si el barco va a estar en Vertical itera el eje Y.
       ***/
      int i = barco.getDireccion() == 'H' ? barco.getCoordInicialX() : barco.getCoordInicialY();
      int aux = i + barco.getLongitud();

      for (; i < aux; i++) {
         if (barco.getDireccion() == 'H') {
            tablero[i][barco.getCoordInicialY()] = barco.getNombre();
         } else {
            tablero[barco.getCoordInicialX()][i] = barco.getNombre();
         }
      }
   }

   // METODO PARA COMPROBAR SI EL BARCO CABE, SUPERPONE A OTRO O SE ENCUENTRA
   // ADYACENTE A OTRO.
   public boolean comprobarBarco(Barco barco) {
      // Descarta error de que el barco no cabe en el tablero.
      if ((barco.getDireccion() == 'H' && barco.getCoordInicialX() + barco.getLongitud() > 10)
            || (barco.getDireccion() == 'V' && barco.getCoordInicialY() + barco.getLongitud() > 10)) {
         System.out.println("El barco no cabe en el tablero");
         return false;
      }

      /***
       * Si el barco va a estar en Horizontal, itera el eje X.
       * Si el barco va a estar en Vertical itera el eje Y.
       * Itero 1 más tanto alante como atrás ya que el barco no puede estar adyacente
       * a otro.
       ***/
      int i = barco.getDireccion() == 'H' ? barco.getCoordInicialX() - 1 : barco.getCoordInicialY() - 1;
      int aux = i + barco.getLongitud() + 1;

      for (; i <= aux; i++) {
         // Para evitar error de "Fuera de limites".
         if (i < 0 || i > 10) {
            continue;
         }
         if (barco.getDireccion() == 'H') {
            if (tablero[i][barco.getCoordInicialY()] != '*'
                  || tablero[i][barco.getCoordInicialY() + 1] != '*'
                  || tablero[i][barco.getCoordInicialY() - 1] != '*') {
               System.out.println("El barco no puede estar adyacente ni superponer a otro.");
               return false;
            }
         } else {
            if (tablero[barco.getCoordInicialX()][i] != '*'
                  || tablero[barco.getCoordInicialX()][i] != '*'
                  || tablero[barco.getCoordInicialX()][i] != '*') {
               System.out.println("El barco no puede estar adyacente ni superponer a otro.");
               return false;
            }
         }
      }

      // Si llega hasta aquí todo va bien.
      return true;
   }

   // IMPRIME TANTO EL TABLERO COMO LAS COORDENADAS.
   public void imprimirTableroAliado() {
      for (int i = -1; i < 10; i++) {
         for (int j = -1; j < 10; j++) {
            System.out.print(" ");
            if (i < 0 && j < 0) {
               System.out.print(" ");
               continue;
            }
            if (i < 0) {
               System.out.print(j);
               continue;
            }
            if (j < 0) {
               System.out.print(i);
               continue;
            }
            System.out.print(tablero[i][j]);
         }
         System.out.println();
      }
   }
}
