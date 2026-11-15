import juego.GestorJuego;
import personajes.Arquero;
import personajes.Guerrero;
import personajes.Mago;
import personajes.Paladin;
import personajes.Personaje;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final GestorJuego gestor = new GestorJuego();

    public static void main(String[] args) {
        int opcion = 0;
        try {
            do {
                mostrarMenu();
                opcion = leerEntero("Seleccione una opción: ");
                System.out.println();
                switch (opcion) {
                    case 1: crearGuerrero(); break;
                    case 2: crearMago(); break;
                    case 3: crearArquero(); break;
                    case 4: crearPaladin(); break;
                    case 5: gestor.mostrarTodos(); break;
                    case 6: buscarPersonaje(); break;
                    case 7: atacarOtroPersonaje(); break;
                    case 8: usarHabilidadEspecial(); break;
                    case 9: simularCombate(); break;
                    case 10: gestor.mostrarVivos(); break;
                    case 11: System.out.println("¡Hasta pronto!"); break;
                    default: System.out.println("Opción no válida. Elija un número del 1 al 11.");
                }
            } while (opcion != 11);
        } catch (NoSuchElementException e) {
            System.out.println("\nFin de la entrada. Saliendo del juego.");
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("       ESFOT RPG - JAVA POO");
        System.out.println("====================================");
        System.out.println("1. Crear Guerrero");
        System.out.println("2. Crear Mago");
        System.out.println("3. Crear Arquero");
        System.out.println("4. Crear Paladín");
        System.out.println("5. Mostrar personajes");
        System.out.println("6. Buscar personaje por nombre");
        System.out.println("7. Atacar a otro personaje");
        System.out.println("8. Utilizar habilidad especial");
        System.out.println("9. Simular combate por turnos");
        System.out.println("10. Mostrar personajes vivos");
        System.out.println("11. Salir");
    }

   

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    private static Personaje pedirPersonaje(String mensaje) {
        String nombre = leerTexto(mensaje);
        Personaje p = gestor.buscar(nombre);
        if (p == null) {
            System.out.println("No existe un personaje llamado \"" + nombre + "\".");
        }
        return p;
    }

 

    private static String pedirNombreNuevo() {
        String nombre = leerTexto("Nombre: ");
        if (gestor.existe(nombre)) {
            System.out.println("Ya existe un personaje con ese nombre. No se creó el personaje.");
            return null;
        }
        return nombre;
    }

    private static void registrar(Personaje p) {
        if (gestor.registrar(p)) {
            System.out.println("Personaje creado correctamente:");
            p.mostrarInformacion();
        } else {
            System.out.println("Ya existe un personaje con ese nombre.");
        }
    }

    private static void crearGuerrero() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        int nivel = leerEntero("Nivel: ");
        int vidaMax = leerEntero("Vida máxima: ");
        int ataque = leerEntero("Ataque: ");
        int defensa = leerEntero("Defensa: ");
        int fuerza = leerEntero("Fuerza: ");
        try {
            registrar(new Guerrero(nombre, nivel, vidaMax, ataque, defensa, fuerza));
        } catch (IllegalArgumentException e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        }
    }

    private static void crearMago() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        int nivel = leerEntero("Nivel: ");
        int vidaMax = leerEntero("Vida máxima: ");
        int ataque = leerEntero("Ataque: ");
        int defensa = leerEntero("Defensa: ");
        int mana = leerEntero("Maná: ");
        try {
            registrar(new Mago(nombre, nivel, vidaMax, ataque, defensa, mana));
        } catch (IllegalArgumentException e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        }
    }

    private static void crearArquero() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        int nivel = leerEntero("Nivel: ");
        int vidaMax = leerEntero("Vida máxima: ");
        int ataque = leerEntero("Ataque: ");
        int defensa = leerEntero("Defensa: ");
        int flechas = leerEntero("Flechas: ");
        try {
            registrar(new Arquero(nombre, nivel, vidaMax, ataque, defensa, flechas));
        } catch (IllegalArgumentException e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        }
    }

    private static void crearPaladin() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        int nivel = leerEntero("Nivel: ");
        int vidaMax = leerEntero("Vida máxima: ");
        int ataque = leerEntero("Ataque: ");
        int defensa = leerEntero("Defensa: ");
        int fuerza = leerEntero("Fuerza: ");
        int energia = leerEntero("Energía sagrada: ");
        try {
            registrar(new Paladin(nombre, nivel, vidaMax, ataque, defensa, fuerza, energia));
        } catch (IllegalArgumentException e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        }
    }


    private static void buscarPersonaje() {
        Personaje p = pedirPersonaje("Nombre a buscar: ");
        if (p != null) {
            p.mostrarInformacion();
            System.out.println("----------------------------------");
        }
    }

    private static void atacarOtroPersonaje() {
        if (gestor.cantidad() < 2) {
            System.out.println("Se necesitan al menos 2 personajes registrados.");
            return;
        }
        String atacante = leerTexto("Nombre del atacante: ");
        String objetivo = leerTexto("Nombre del objetivo: ");
        int bonificacion = leerEntero("Bonificación (0 = ataque normal): ");
        gestor.atacar(atacante, objetivo, bonificacion);
    }

    private static void simularCombate() {
        if (gestor.cantidad() < 2) {
            System.out.println("Se necesitan al menos 2 personajes registrados.");
            return;
        }
        String n1 = leerTexto("Nombre del primer personaje: ");
        String n2 = leerTexto("Nombre del segundo personaje: ");
        gestor.simularCombate(n1, n2);
    }


    private static void usarHabilidadEspecial() {
        if (gestor.estaVacio()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        Personaje p = pedirPersonaje("Nombre del personaje que usará la habilidad: ");
        if (p == null) {
            return;
        }

        if (p instanceof Paladin) {
            Paladin paladin = (Paladin) p;
            System.out.println("1. Curar");
            System.out.println("2. Activar escudo");
            int op = leerEntero("Seleccione la habilidad: ");
            if (op == 1) {
                Personaje objetivo = pedirPersonaje("Nombre del personaje a curar: ");
                if (objetivo != null) {
                    int antes = objetivo.getVida();
                    if (paladin.curar(objetivo)) {
                        System.out.println(paladin.getNombre() + " cura a " + objetivo.getNombre()
                                + " y recupera " + (objetivo.getVida() - antes) + " de vida.");
                        System.out.println("Vida de " + objetivo.getNombre() + ": "
                                + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
                        System.out.println("Energía sagrada restante: " + paladin.getEnergiaSagrada());
                    }
                }
            } else if (op == 2) {
                if (paladin.activarEscudo()) {
                    System.out.println("Escudo activado: " + paladin.getNombre()
                            + " reducirá en " + Paladin.REDUCCION_ESCUDO + " el daño del próximo ataque.");
                }
            } else {
                System.out.println("Habilidad no válida.");
            }

        } else if (p instanceof Mago) {
            Mago mago = (Mago) p;
            Personaje objetivo = pedirPersonaje("Nombre del objetivo del hechizo: ");
            if (objetivo != null) {
                int antes = objetivo.getVida();
                if (mago.lanzarHechizo(objetivo)) {
                    System.out.println(mago.getNombre() + " lanza un hechizo a " + objetivo.getNombre());
                    System.out.println("  Daño efectivo: " + (antes - objetivo.getVida()));
                    System.out.println("  Vida de " + objetivo.getNombre() + ": "
                            + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
                    System.out.println("  Maná restante: " + mago.getMana());
                }
            }

        } else if (p instanceof Arquero) {
            Arquero arquero = (Arquero) p;
            Personaje objetivo = pedirPersonaje("Nombre del objetivo de la flecha: ");
            if (objetivo != null) {
                int antes = objetivo.getVida();
                if (arquero.dispararFlecha(objetivo)) {
                    System.out.println(arquero.getNombre() + " dispara una flecha a " + objetivo.getNombre());
                    System.out.println("  Daño efectivo: " + (antes - objetivo.getVida()));
                    System.out.println("  Vida de " + objetivo.getNombre() + ": "
                            + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
                    System.out.println("  Flechas restantes: " + arquero.getFlechas());
                }
            }

        } else {
            System.out.println(p.getNombre() + " (" + p.getClass().getSimpleName()
                    + ") no tiene una habilidad especial.");
        }
    }
}
