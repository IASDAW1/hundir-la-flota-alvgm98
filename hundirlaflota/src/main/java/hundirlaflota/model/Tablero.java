package hundirlaflota.model;

import hundirlaflota.model.barcos.Barco;
import hundirlaflota.model.barcos.Portaaviones;
import hundirlaflota.model.barcos.Acorazado;
import hundirlaflota.model.barcos.Submarino;
import hundirlaflota.model.barcos.Destructor;

public class Tablero {
   private char[][] tablero;

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

   /*
    * METODO PARA COMPROBAR SI EL BARCO CABE, SUPERPONE A OTRO
    * O SE ENCUENTRA ADYACENTE A OTRO.
    */
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
         // Para evitar error de "Fuera de limites" y comparaciones innecesarias
         // ya que según las reglas el barco puede estar en los bordes del tablero.
         if (i < 0 || i >= 10) {
            continue;
         }
         // HORIZONTAL
         if (barco.getDireccion() == 'H') {
            /*
             * Para evitar error de "Fuera de limites" compara primero que el indice no se
             * salga de los limites.
             * (Si no cumple la primera parte de la puerta logica AND no pasa a la segunda
             * parte)
             */
            // Compará con la posicion que ocupará y la adyacente.
            if (tablero[i][barco.getCoordInicialY()] != '*'
                  || (!((barco.getCoordInicialY() + 1) >= 10) && tablero[i][barco.getCoordInicialY() + 1] != '*')
                  || (!((barco.getCoordInicialY() - 1) < 0) && tablero[i][barco.getCoordInicialY() - 1] != '*')) {
               System.out.println("El barco no puede estar adyacente ni superponer a otro.");
               return false;
            }
         }
         // VERICAL
         if (barco.getDireccion() == 'V') {
            /*
             * Para evitar error de "Fuera de limites" compara primero que el indice no se
             * salga de los limites.
             * (Si no cumple la primera parte de la puerta logica AND no pasa a la segunda
             * parte)
             */
            if ((barco.getCoordInicialX() + 1) >= 10 || (barco.getCoordInicialX() - 1) < 0) {
               continue;
            }
            // Compará con la posicion que ocupará y la adyacente.
            if (tablero[barco.getCoordInicialX()][i] != '*'
                  || (!((barco.getCoordInicialX() + 1) >= 10) && tablero[barco.getCoordInicialX() + 1][i] != '*')
                  || (!((barco.getCoordInicialY() - 1) < 0) && tablero[barco.getCoordInicialX() - 1][i] != '*')) {
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

   // IMPRIME EL TABLERO PARA EL RIVAL.
   public void imprimirTableroRival() {
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

            // Evita imprimir los bacos del rival.
            if (tablero[i][j] != 'F' && tablero[i][j] != 'A') {
               System.out.print('*');
               continue;
            }
            System.out.print(tablero[i][j]);
         }
         System.out.println();
      }
   }

   public void crearTableroAleatorio() {
      // Portaaviones
      while (true) {
         int coordInicialX = (int) (Math.random() * 10);
         int coordInicialY = (int) (Math.random() * 10);
         char direccion = Math.random() < 0.5 ? 'H' : 'V';

         Barco poortaaviones = new Portaaviones(direccion, coordInicialX, coordInicialY);
         if (comprobarBarco(poortaaviones)) {
            colocarBarco(poortaaviones);
            break;
         }
      }
      // Acorazado
      while (true) {
         int coordInicialX = (int) (Math.random() * 10);
         int coordInicialY = (int) (Math.random() * 10);
         char direccion = Math.random() < 0.5 ? 'H' : 'V';

         Barco poortaaviones = new Acorazado(direccion, coordInicialX, coordInicialY);
         if (comprobarBarco(poortaaviones)) {
            colocarBarco(poortaaviones);
            break;
         }
      }
      // Submarino
      while (true) {
         int coordInicialX = (int) (Math.random() * 10);
         int coordInicialY = (int) (Math.random() * 10);
         char direccion = Math.random() < 0.5 ? 'H' : 'V';

         Barco poortaaviones = new Submarino(direccion, coordInicialX, coordInicialY);
         if (comprobarBarco(poortaaviones)) {
            colocarBarco(poortaaviones);
            break;
         }
      }
      // Destructor
      while (true) {
         int coordInicialX = (int) (Math.random() * 10);
         int coordInicialY = (int) (Math.random() * 10);
         char direccion = Math.random() < 0.5 ? 'H' : 'V';

         Barco poortaaviones = new Destructor(direccion, coordInicialX, coordInicialY);
         if (comprobarBarco(poortaaviones)) {
            colocarBarco(poortaaviones);
            break;
         }
      }
   }

   // Devuelve true si no le quedan barcos.
   public boolean comprobarDerrota() {
      /*
       * Si encuentra una parte del tablero que no sea Agua, Agua atacada o Fuego
       * Aún quedarian barcos.
       */
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            if (tablero[i][j] != '*'
                  && tablero[i][j] != 'A'
                  && tablero[i][j] != 'F') {
               return false;
            }
         }
      }
      return true;
   }

   // Devuelve false si no es un ataque valido o si ya atacó esta posicion.
   public boolean recibirAtaque(int x, int y) {
      if (x < 0 || x > 10
            || y < 0 || y > 10) {
         System.out.println("Posicion inexsistente.");
         return false;
      }

      if (tablero[x][y] == 'A' || tablero[x][y] == 'F') {
         System.out.println("Ya atacaste esta posición.");
         return false;
      } else if (tablero[x][y] == '*') {
         tablero[x][y] = 'A';
         System.out.println("AGUA!");
         return true;
      } else {
         tablero[x][y] = 'F';
         System.out.println("FUEGO!");
         return true;
      }
   }

   public char[][] getTablero() {
      return tablero;
   }

}
