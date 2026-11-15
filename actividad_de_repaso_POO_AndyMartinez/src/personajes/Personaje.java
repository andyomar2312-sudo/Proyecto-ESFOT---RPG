package personajes;


public class Personaje {

    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;

    
    public Personaje(String nombre, int nivel, int vidaMaxima, int ataque, int defensa) {
        setNombre(nombre);
        setNivel(nivel);
        setVidaMaxima(vidaMaxima);
        setAtaque(ataque);
        setDefensa(defensa);
        this.vida = this.vidaMaxima; 
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel <= 0) {
            throw new IllegalArgumentException("El nivel debe ser mayor que 0.");
        }
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0 || vida > vidaMaxima) {
            throw new IllegalArgumentException(
                    "La vida debe estar entre 0 y " + vidaMaxima + ".");
        }
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("La vida máxima debe ser mayor que 0.");
        }
        this.vidaMaxima = vidaMaxima;
        if (this.vida > vidaMaxima) { 
            this.vida = vidaMaxima;
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque < 0) {
            throw new IllegalArgumentException("El ataque no puede ser negativo.");
        }
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa < 0) {
            throw new IllegalArgumentException("La defensa no puede ser negativa.");
        }
        this.defensa = defensa;
    }

  
    public void mostrarInformacion() {
        System.out.println("----------------------------------");
        System.out.println("Tipo:     " + getClass().getSimpleName());
        System.out.println("Nombre:   " + nombre);
        System.out.println("Nivel:    " + nivel);
        System.out.println("Vida:     " + vida + "/" + vidaMaxima);
        System.out.println("Ataque:   " + ataque);
        System.out.println("Defensa:  " + defensa);
        System.out.println("Estado:   " + (estaVivo() ? "Vivo" : "Derrotado"));
    }

    public boolean estaVivo() {
        return vida > 0;
    }

   
    public void recibirDanio(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
        if (!estaVivo()) {
            return; 
        }
        int danioReal = Math.max(1, cantidad - defensa);
        vida = Math.max(0, vida - danioReal);
    }

   
    public boolean atacar(Personaje enemigo) {
        return atacar(enemigo, 0);
    }

    
    public boolean atacar(Personaje enemigo, int bonificacion) {
        if (bonificacion < 0) {
            throw new IllegalArgumentException("La bonificación no puede ser negativa.");
        }
        if (!validarObjetivo(enemigo)) {
            return false;
        }
        enemigo.recibirDanio(ataque + bonificacion);
        return true;
    }

    protected boolean validarObjetivo(Personaje objetivo) {
        if (objetivo == null) {
            System.out.println("El objetivo no existe.");
            return false;
        }
        if (objetivo == this) {
            System.out.println(nombre + " no puede actuar contra sí mismo.");
            return false;
        }
        if (!estaVivo()) {
            System.out.println(nombre + " está derrotado y no puede actuar.");
            return false;
        }
        if (!objetivo.estaVivo()) {
            System.out.println(objetivo.getNombre() + " ya está derrotado.");
            return false;
        }
        return true;
    }
}
