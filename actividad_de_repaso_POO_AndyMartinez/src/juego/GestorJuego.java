package juego;

import personajes.Personaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GestorJuego {

    private final List<Personaje> personajes = new ArrayList<>();
    private final Map<String, Personaje> registro = new HashMap<>();

    private String clave(String nombre) {
        return nombre.trim().toLowerCase();
    }



    public boolean existe(String nombre) {
        return nombre != null && registro.containsKey(clave(nombre));
    }


    public boolean registrar(Personaje p) {
        if (p == null || registro.containsKey(clave(p.getNombre()))) {
            return false;
        }
        personajes.add(p);
        registro.put(clave(p.getNombre()), p);
        return true;
    }

    public Personaje buscar(String nombre) {
        if (nombre == null) {
            return null;
        }
        return registro.get(clave(nombre));
    }

    public boolean estaVacio() {
        return personajes.isEmpty();
    }

    public int cantidad() {
        return personajes.size();
    }


    public void mostrarTodos() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        for (Personaje p : personajes) {
            p.mostrarInformacion(); 
        }
        System.out.println("----------------------------------");
    }

    public void mostrarVivos() {
        int vivos = 0;
        for (Personaje p : personajes) {
            if (p.estaVivo()) {
                p.mostrarInformacion();
                vivos++;
            }
        }
        if (vivos == 0) {
            System.out.println("No hay personajes vivos.");
        } else {
            System.out.println("----------------------------------");
        }
    }


    public void atacar(String nombreAtacante, String nombreObjetivo, int bonificacion) {
        Personaje atacante = buscar(nombreAtacante);
        Personaje objetivo = buscar(nombreObjetivo);
        if (atacante == null || objetivo == null) {
            System.out.println("Alguno de los personajes no existe.");
            return;
        }
        if (atacante == objetivo) {
            System.out.println("Un personaje no puede atacarse a sí mismo.");
            return;
        }
        if (bonificacion < 0) {
            System.out.println("La bonificación no puede ser negativa.");
            return;
        }
        ejecutarTurno(atacante, objetivo, bonificacion);
    }

    private boolean ejecutarTurno(Personaje atacante, Personaje objetivo, int bonificacion) {
        int vidaAntes = objetivo.getVida();
        boolean ok = (bonificacion == 0)
                ? atacante.atacar(objetivo)
                : atacante.atacar(objetivo, bonificacion);
        if (!ok) {
            return false;
        }
        int danio = vidaAntes - objetivo.getVida();
        System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre());
        System.out.println("  Daño efectivo: " + danio);
        System.out.println("  Vida de " + objetivo.getNombre() + ": "
                + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
        if (!objetivo.estaVivo()) {
            System.out.println("  " + objetivo.getNombre() + " ha sido derrotado.");
        }
        return true;
    }

    public void simularCombate(String nombre1, String nombre2) {
        Personaje p1 = buscar(nombre1);
        Personaje p2 = buscar(nombre2);
        if (p1 == null || p2 == null) {
            System.out.println("Alguno de los personajes no existe.");
            return;
        }
        if (p1 == p2) {
            System.out.println("Debes elegir dos personajes distintos.");
            return;
        }
        if (!p1.estaVivo() || !p2.estaVivo()) {
            System.out.println("No se puede iniciar un combate con un personaje derrotado.");
            return;
        }

        System.out.println();
        System.out.println("===== COMBATE RPG =====");
        System.out.println(p1.getNombre() + " (" + p1.getClass().getSimpleName() + ") vs "
                + p2.getNombre() + " (" + p2.getClass().getSimpleName() + ")");

        Personaje atacante = p1;
        Personaje objetivo = p2;
        int turno = 1;
        while (p1.estaVivo() && p2.estaVivo()) {
            System.out.println("Turno " + turno + ":");
            ejecutarTurno(atacante, objetivo, 0);
            if (!objetivo.estaVivo()) {
                break; 
            }
            Personaje temp = atacante; 
            atacante = objetivo;
            objetivo = temp;
            turno++;
        }
        System.out.println("GANADOR: " + atacante.getNombre());
        System.out.println("=======================");
    }
}
