package personajes;

public class Mago extends Personaje {

    public static final int COSTO_HECHIZO = 10;
    public static final int POTENCIA_HECHIZO = 20;

    private int mana;

    public Mago(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int mana) {
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setMana(mana);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("El maná no puede ser negativo.");
        }
        this.mana = mana;
    }

  
    public boolean lanzarHechizo(Personaje objetivo) {
        if (!validarObjetivo(objetivo)) {
            return false;
        }
        if (mana < COSTO_HECHIZO) {
            System.out.println(getNombre() + " no tiene maná suficiente (necesita "
                    + COSTO_HECHIZO + ", tiene " + mana + ").");
            return false;
        }
        mana -= COSTO_HECHIZO;
        objetivo.recibirDanio(POTENCIA_HECHIZO);
        return true;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Maná:     " + mana);
    }
}
