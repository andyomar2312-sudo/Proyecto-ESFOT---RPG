package personajes;


public class Paladin extends Guerrero {

    public static final int COSTO_CURACION = 10;
    public static final int CURACION_MAXIMA = 20;
    public static final int REDUCCION_ESCUDO = 5;

    private int energiaSagrada;
    private boolean escudoActivo;

    public Paladin(String nombre, int nivel, int vidaMaxima, int ataque, int defensa,
                   int fuerza, int energiaSagrada) {
        super(nombre, nivel, vidaMaxima, ataque, defensa, fuerza);
        setEnergiaSagrada(energiaSagrada);
        this.escudoActivo = false;
    }

    public int getEnergiaSagrada() {
        return energiaSagrada;
    }

    public void setEnergiaSagrada(int energiaSagrada) {
        if (energiaSagrada < 0) {
            throw new IllegalArgumentException("La energía sagrada no puede ser negativa.");
        }
        this.energiaSagrada = energiaSagrada;
    }

    public boolean isEscudoActivo() {
        return escudoActivo;
    }

   
    public boolean curar(Personaje objetivo) {
        if (objetivo == null) {
            System.out.println("El objetivo no existe.");
            return false;
        }
        if (!estaVivo()) {
            System.out.println(getNombre() + " está derrotado y no puede actuar.");
            return false;
        }
        if (!objetivo.estaVivo()) {
            System.out.println(objetivo.getNombre() + " está derrotado y no puede ser curado.");
            return false;
        }
        if (objetivo.getVida() == objetivo.getVidaMaxima()) {
            System.out.println(objetivo.getNombre() + " ya tiene la vida completa.");
            return false;
        }
        if (energiaSagrada < COSTO_CURACION) {
            System.out.println(getNombre() + " no tiene energía sagrada suficiente (necesita "
                    + COSTO_CURACION + ", tiene " + energiaSagrada + ").");
            return false;
        }
        int faltante = objetivo.getVidaMaxima() - objetivo.getVida();
        int curacion = Math.min(CURACION_MAXIMA, faltante);
        energiaSagrada -= COSTO_CURACION;
        objetivo.setVida(objetivo.getVida() + curacion);
        return true;
    }

  
    public boolean activarEscudo() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está derrotado y no puede actuar.");
            return false;
        }
        if (escudoActivo) {
            System.out.println("El escudo de " + getNombre() + " ya está activo.");
            return false;
        }
        escudoActivo = true;
        return true;
    }

    @Override
    public void recibirDanio(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
        if (escudoActivo && estaVivo()) {
            cantidad = Math.max(0, cantidad - REDUCCION_ESCUDO);
            escudoActivo = false;
        }
        super.recibirDanio(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Energía sagrada: " + energiaSagrada);
        System.out.println("Escudo:   " + (escudoActivo ? "Activo" : "Inactivo"));
    }
}
