package hundirlaflota.model.barcos;

public abstract class Barco {
   protected char nombre;
   protected char direccion; /* 'H' -> horizontal | 'V' -> vertical. */
   protected int longitud;
   protected int coordInicialX;
   protected int coordInicialY;

   public Barco() {

   }

   public Barco(char nombre, char direccion, int longitud, int coordInicialX, int coordInicialY) {
      this.nombre = nombre;
      this.direccion = direccion;
      this.longitud = longitud;
      this.coordInicialX = coordInicialX;
      this.coordInicialY = coordInicialY;
   }

   public char getNombre() {
      return nombre;
   }

   public void setNombre(char nombre) {
      this.nombre = nombre;
   }

   public int getLongitud() {
      return longitud;
   }

   public void setLongitud(int longitud) {
      this.longitud = longitud;
   }

   public char getDireccion() {
      return direccion;
   }

   public void setDireccion(char direccion) {
      this.direccion = direccion;
   }

   public int getCoordInicialX() {
      return coordInicialX;
   }

   public void setCoordInicialX(int coordInicialX) {
      this.coordInicialX = coordInicialX;
   }

   public int getCoordInicialY() {
      return coordInicialY;
   }

   public void setCoordInicialY(int coordInicialY) {
      this.coordInicialY = coordInicialY;
   }

}
