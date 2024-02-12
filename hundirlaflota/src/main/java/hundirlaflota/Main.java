package hundirlaflota;

import hundirlaflota.model.Tablero;

public class Main {
    static Tablero tableroJugador = new Tablero();
    static Tablero tableroMaquina = new Tablero();

    public static void main(String[] args) {

        System.out.println("""
                =======================================================================
                ---JUEGO HUNDIR LA FLOTA---
                OBJETIVO DEL JUEGO:
                - Hundir los Barcos del rival antes de que hunda los tuyos.
                REGLAS DEL JUEGO:
                - Cada jugador constará de 4 Barcos:
                    # 1 Portaaviones.
                    # 1 Acorazado.
                    # 1 Submarino.
                    # 1 Destructor.
                LEYENDA:
                * -> Coordenada sin atacar.
                A -> Coordenada ataca siendo esta agua.
                F -> Coordenada ataca siendo esta un barco.
                =======================================================================""");

        // tableroJugador.imprimirTableroAliado();

        tableroMaquina.crearTableroAleatorio();
        tableroMaquina.imprimirTableroAliado();
    }
}