public class PQRS {
    private static int contadorID = 1; // Generador de IDs
    private int id;
    private Cliente cliente;
    private Departamento departamento;
    private Empleado agente;
    private String descripcion;
    private String estado;

    public PQRS(Cliente cliente, Departamento departamento, Empleado agente, String descripcion) {
        this.id = contadorID++;
        this.cliente = cliente;
        this.departamento = departamento;
        this.agente = agente;
        this.descripcion = descripcion;
        this.estado = "Abierto";
    }

    public int getId() {
        return id;
    }

    public String mostrarDetalles() {
        return "ID: " + id +
               ", Cliente: " + cliente.getNombre() +
               ", Departamento: " + departamento.getNombre() +
               ", Agente: " + agente.getNombre() +
               ", Descripción: " + descripcion +
               ", Estado: " + estado;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
