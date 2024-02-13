package hundirlaflota;

import java.util.Scanner;

import hundirlaflota.model.Tablero;
import hundirlaflota.model.barcos.Acorazado;
import hundirlaflota.model.barcos.Barco;
import hundirlaflota.model.barcos.Destructor;
import hundirlaflota.model.barcos.Portaaviones;
import hundirlaflota.model.barcos.Submarino;

public class Main {
   static Tablero tableroJugador = new Tablero();
   static Tablero tableroMaquina = new Tablero();
   static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) throws InterruptedException {

      System.out.println("""
            =======================================================================
            ---JUEGO HUNDIR LA FLOTA---
            OBJETIVO DEL JUEGO:
            - Hundir los Barcos del rival antes de que hunda los tuyos.
            LEYENDA:
            * -> Coordenada sin atacar.
            A -> Coordenada ataca siendo esta agua.
            F -> Coordenada ataca siendo esta un barco.
            REGLAS DEL JUEGO:
            - Cada jugador constará de 4 Barcos:
                # 1 Portaaviones.
                # 1 Acorazado.
                # 1 Submarino.
                # 1 Destructor.
            - Cada jugador constará de 1 tablero de 10x10 donde posicionar sus Barcos.
                # Coordenadas del 0 al 9.
            - Los barcos se podrán posicionar tanto en Vertical como en Horizontal.
            =======================================================================""");

      // La maquina se crea su tablero.
      tableroMaquina.crearTableroAleatorio();

      // POSICIONAMIENTO DEL JUGADOR.
      // PORTAAVIONES.
      do {
         // Imprime el tablero para mostrar al jugador las posibilidades de
         // posicionamiento.
         tableroJugador.imprimirTableroAliado();
         System.out.println("=======================================================================");
         System.out.println("Coordenadas de incio del Portaaviones:");
         System.out.print("X: ");
         int coordInicialY = sc.nextInt();
         System.out.print("Y: ");
         int coordInicialX = sc.nextInt();
         System.out.print("Dirección del Barco (0 Horizontal, 1 Vertical)");

         /*
          * ME CONFUNDÍ HACIENDO LOS METODOS DE COLOCAR Y COMPROBAR BARCOS
          * HORIZONTAL ES VERTICAL Y VERTICAL HORIZONTAL
          */
         char direccion = 'H';
         if (sc.nextInt() == 0) {
            direccion = 'V';
         }
         System.out.println("=======================================================================");

         Barco portaaviones = new Portaaviones(direccion, coordInicialX, coordInicialY);

         if (tableroJugador.comprobarBarco(portaaviones)) {
            tableroJugador.colocarBarco(portaaviones);
            break;
         }
      } while (true);

      // ACORAZADO.
      do {
         // Imprime el tablero para mostrar al jugador las posibilidades de
         // posicionamiento.
         tableroJugador.imprimirTableroAliado();
         System.out.println("=======================================================================");
         System.out.println("Coordenadas de incio del Acorazado:");
         System.out.print("X: ");
         int coordInicialY = sc.nextInt();
         System.out.print("Y: ");
         int coordInicialX = sc.nextInt();
         System.out.print("Dirección del Barco (0 Horizontal, 1 Vertical)");

         /*
          * ME CONFUNDÍ HACIENDO LOS METODOS DE COLOCAR Y COMPROBAR BARCOS
          * HORIZONTAL ES VERTICAL Y VERTICAL HORIZONTAL
          */
         char direccion = 'H';
         if (sc.nextInt() == 0) {
            direccion = 'V';
         }
         System.out.println("=======================================================================");

         Barco acorazado = new Acorazado(direccion, coordInicialX, coordInicialY);

         if (tableroJugador.comprobarBarco(acorazado)) {
            tableroJugador.colocarBarco(acorazado);
            break;
         }
      } while (true);

      // SUBMARINO.
      do {
         // Imprime el tablero para mostrar al jugador las posibilidades de
         // posicionamiento.
         tableroJugador.imprimirTableroAliado();
         System.out.println("=======================================================================");
         System.out.println("Coordenadas de incio del Submarino:");
         System.out.print("X: ");
         int coordInicialY = sc.nextInt();
         System.out.print("Y: ");
         int coordInicialX = sc.nextInt();
         System.out.print("Dirección del Barco (0 Horizontal, 1 Vertical)");

         /*
          * ME CONFUNDÍ HACIENDO LOS METODOS DE COLOCAR Y COMPROBAR BARCOS
          * HORIZONTAL ES VERTICAL Y VERTICAL HORIZONTAL
          */
         char direccion = 'H';
         if (sc.nextInt() == 0) {
            direccion = 'V';
         }
         System.out.println("=======================================================================");

         Barco submarino = new Submarino(direccion, coordInicialX, coordInicialY);

         if (tableroJugador.comprobarBarco(submarino)) {
            tableroJugador.colocarBarco(submarino);
            break;
         }
      } while (true);

      // DESTRUCTOR.
      do {
         // Imprime el tablero para mostrar al jugador las posibilidades de
         // posicionamiento.
         tableroJugador.imprimirTableroAliado();
         System.out.println("=======================================================================");
         System.out.println("Coordenadas de incio del Destructor:");
         System.out.print("X: ");
         int coordInicialY = sc.nextInt();
         System.out.print("Y: ");
         int coordInicialX = sc.nextInt();
         System.out.print("Dirección del Barco (0 Horizontal, 1 Vertical)");

         /*
          * ME CONFUNDÍ HACIENDO LOS METODOS DE COLOCAR Y COMPROBAR BARCOS
          * HORIZONTAL ES VERTICAL Y VERTICAL HORIZONTAL
          */
         char direccion = 'H';
         if (sc.nextInt() == 0) {
            direccion = 'V';
         }
         System.out.println("=======================================================================");

         Barco destructor = new Destructor(direccion, coordInicialX, coordInicialY);

         if (tableroJugador.comprobarBarco(destructor)) {
            tableroJugador.colocarBarco(destructor);
            break;
         }
      } while (true);

      // ATAQUE
      /* Hasta que uno de los 2 pierda */
      while (!tableroJugador.comprobarDerrota() || !tableroMaquina.comprobarDerrota()) {
         // Turno jugador. Si acierta el ataque, ataca de nuevo.
         while (true) {
            imprimirTableros(); // Imprime los tableros antes de elegir las coordenadas.

            System.out.println("Elige las coordenadas a atacar.");
            System.out.print("X: ");
            int y = sc.nextInt();
            System.out.print("Y: ");
            int x = sc.nextInt();

            // Si no es valido el ataque vuelve a escribir las coordenadas.
            if (!tableroMaquina.recibirAtaque(x, y)) {
               continue;
            }

            // Si ataca al agua cambia el turno.
            if (tableroMaquina.getTablero()[x][y] == 'A') {
               break;
            }
         }

         Thread.sleep(1000); // Genera un tiempo de espera de 1 segundo para poder leer si acertó.

         // Turno Máquina. Si acierta el ataque, ataca de nuevo.
         while (true) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            System.out.println("El Rival atacó en las coordenadas X" + x + "-Y" + y);

            // Si no es valido el ataque vuelve a escribir las coordenadas.
            if (!tableroJugador.recibirAtaque(x, y)) {
               continue;
            }

            // Si ataca al agua cambia el turno.
            if (tableroJugador.getTablero()[x][y] == 'A') {
               break;
            }
         }

         Thread.sleep(1000); // Genera un tiempo de espera de 1 segundo para poder ver donde atacó el rival.
      }

      // FIN DE LA PARTIDA.
      if (tableroJugador.comprobarDerrota()) {
         System.out.println("PERDISTE CONTRA UNA MAQUINA! VAYA UN TIO MALO!");
      }

      if (tableroMaquina.comprobarDerrota()) {
         System.out.println("LE GANASTE A UNA MAQUINA! VAYA UN ABUSON!");
      }
   }

   static void imprimirTableros() {
      System.out.println("=======================================================================");
      System.out.println("======Tablero Rival======");
      tableroMaquina.imprimirTableroRival();
      System.out.println("======Tu Tablero======");
      tableroJugador.imprimirTableroAliado();
      System.out.println("=======================================================================");
   }
}