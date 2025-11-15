package ej10;

public class Main {
    public static void main(String[] args) {
        Evento evento = new Evento("Tech Summit 2025");

        Speaker sp1 = new Speaker("Ana", "Lopez", 35, 1111, "IA");
        Speaker sp2 = new Speaker("Carlos", "Perez", 40, 2222, "Ciberseguridad");

        Charla c1 = new Charla("Auditorio A", "Introducción a IA", sp1);
        Charla c2 = new Charla("Auditorio B", "Seguridad Digital", sp2);

        Participante p1 = new Participante("Maria", "Gomez", 22, 5001, 1);
        Participante p2 = new Participante("Luis", "Mamani", 30, 5002, 2);
        Participante p3 = new Participante("Jose", "Quispe", 28, 5003, 3);

        c1.agregarParticipante(p1);
        c1.agregarParticipante(p2);
        c2.agregarParticipante(p3);

        evento.agregarCharla(c1);
        evento.agregarCharla(c2);

        System.out.println("Edad promedio participantes: " + evento.edadPromedioParticipantes());

        System.out.println("¿Existe Luis Mamani? " + evento.existePersona("Luis", "Mamani"));
        System.out.println("¿Existe Speaker Ana Lopez? " + evento.existePersona("Ana", "Lopez"));

        evento.eliminarCharlasPorCI(1111);

        evento.ordenarCharlasPorParticipantes();

        System.out.println("Charlas ordenadas por número de participantes:");
    }
}

class Persona {
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected int ci;

    public Persona(String n, String a, int e, int ci) {
        this.nombre = n;
        this.apellido = a;
        this.edad = e;
        this.ci = ci;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public int getCI() { return ci; }
}

class Speaker extends Persona {
    private String especialidad;

    public Speaker(String n, String a, int e, int ci, String esp) {
        super(n,a,e,ci);
        this.especialidad = esp;
    }
}

class Participante extends Persona {
    private int nroTicket;

    public Participante(String n, String a, int e, int ci, int nt) {
        super(n,a,e,ci);
        this.nroTicket = nt;
    }
}

class Charla {
    private String lugar;
    private String nombreCharla;
    Speaker S;
    private int np;
    private Participante P[] = new Participante[50];

    public Charla(String lugar, String nombreCharla, Speaker S) {
        this.lugar = lugar;
        this.nombreCharla = nombreCharla;
        this.S = S;
        this.np = 0;
    }

    public void agregarParticipante(Participante p) {
        if (np < 50) P[np++] = p;
    }

    public int getNumParticipantes() { return np; }

    public Participante getParticipante(int i) { return P[i]; }

    public Speaker getSpeaker() { return S; }
}

class Evento {
    private String nombre;
    private int nc;
    private Charla C[] = new Charla[50];

    public Evento(String nombre) {
        this.nombre = nombre;
        this.nc = 0;
    }

    public void agregarCharla(Charla c) {
        if (nc < 50) C[nc++] = c;
    }

    // (a) Edad promedio participantes
    public double edadPromedioParticipantes() {
        int suma = 0;
        int count = 0;
        for (int i = 0; i < nc; i++) {
            Charla ch = C[i];
            for (int j = 0; j < ch.getNumParticipantes(); j++) {
                suma += ch.getParticipante(j).getEdad();
                count++;
            }
        }
        return count == 0 ? 0 : (double)suma / count;
    }

    public boolean existePersona(String nombre, String apellido) {
        for (int i = 0; i < nc; i++) {
            Charla ch = C[i];
            if (ch.getSpeaker().getNombre().equals(nombre) &&
                ch.getSpeaker().getApellido().equals(apellido))
                return true;

            for (int j = 0; j < ch.getNumParticipantes(); j++) {
                Participante p = ch.getParticipante(j);
                if (p.getNombre().equals(nombre) && p.getApellido().equals(apellido))
                    return true;
            }
        }
        return false;
    }

    public void eliminarCharlasPorCI(int ci) {
        for (int i = 0; i < nc; i++) {
            if (C[i].getSpeaker().getCI() == ci) {
                for (int k = i; k < nc - 1; k++) C[k] = C[k+1];
                nc--;
                i--;
            }
        }
    }

    public void ordenarCharlasPorParticipantes() {
        for (int i = 0; i < nc - 1; i++) {
            for (int j = i + 1; j < nc; j++) {
                if (C[i].getNumParticipantes() > C[j].getNumParticipantes()) {
                    Charla aux = C[i];
                    C[i] = C[j];
                    C[j] = aux;
                }
            }
        }
    }
}
