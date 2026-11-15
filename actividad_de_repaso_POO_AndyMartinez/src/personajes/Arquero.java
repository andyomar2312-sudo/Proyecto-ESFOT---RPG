package personajes;

public class Arquero extends Personaje {

    public static final int BONO_FLECHA = 5;

    private int flechas;

    public Arquero(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int flechas) {
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setFlechas(flechas);
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        if (flechas < 0) {
            throw new IllegalArgumentException("Las flechas no pueden ser negativas.");
        }
        this.flechas = flechas;
    }

    
    public boolean dispararFlecha(Personaje objetivo) {
        if (!validarObjetivo(objetivo)) {
            return false;
        }
        if (flechas <= 0) {
            System.out.println(getNombre() + " no tiene flechas.");
            return false;
        }
        flechas--;
        objetivo.recibirDanio(getAtaque() + BONO_FLECHA);
        return true;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Flechas:  " + flechas);
    }
}
