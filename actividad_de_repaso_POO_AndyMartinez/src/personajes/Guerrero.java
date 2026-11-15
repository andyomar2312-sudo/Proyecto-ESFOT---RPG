package personajes;

public class Guerrero extends Personaje {

    private int fuerza;

    public Guerrero(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int fuerza) {
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setFuerza(fuerza);
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        if (fuerza < 0) {
            throw new IllegalArgumentException("La fuerza no puede ser negativa.");
        }
        this.fuerza = fuerza;
    }

    @Override
    public boolean atacar(Personaje enemigo) {
        return super.atacar(enemigo, fuerza);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Fuerza:   " + fuerza);
    }
}
