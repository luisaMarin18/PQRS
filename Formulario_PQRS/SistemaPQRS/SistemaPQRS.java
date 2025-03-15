import java.util.*;

public class SistemaPQRS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Crear departamentos
        Departamento soporte = new Departamento("Soporte Técnico");
        Departamento ventas = new Departamento("Ventas");
        List<Departamento> departamentos = Arrays.asList(soporte, ventas);

        // Crear empleados y asignarlos a los departamentos
        Empleado juan = new Empleado("Juan Pérez", soporte);
        Empleado maria = new Empleado("María Gómez", ventas);
        Empleado Emmanuel = new Empleado("Emmanuel Lopez", soporte);
        Empleado Natalia = new Empleado("Natalia Ospina", ventas);

        soporte.agregarEmpleado(juan);
        ventas.agregarEmpleado(maria);
        soporte.agregarEmpleado(Emmanuel);
        ventas.agregarEmpleado(Natalia);


        // Lista de nombres aleatorios de clientes
        String[] nombresClientes = {"Carlos López", "Ana Ramírez", "Pedro Castillo", "Luis Torres", "Mariana Pulido", "Pablo Espinoza", "Paola Hernandez"};

        // Lista para almacenar PQRS
        List<PQRS> casos = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Menú del Sistema PQRS ---");
            System.out.println("1. Crear una nueva PQRS");
            System.out.println("2. Consultar una nueva/existente PQRS");
            System.out.println("3. Actualizar estado de las PQRS");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (opcion == 1) {
                // Crear un cliente aleatorio
                Cliente cliente = new Cliente(nombresClientes[random.nextInt(nombresClientes.length)]);
                Departamento departamento;

                // Asegurar que el departamento tenga empleados
                do {
                    departamento = departamentos.get(random.nextInt(departamentos.size()));
                } while (!departamento.tieneEmpleados());

                // Asignar un agente aleatorio
                List<Empleado> listaEmpleados = departamento.getEmpleados();
                Empleado agente = listaEmpleados.get(random.nextInt(listaEmpleados.size()));

                // Ingresar la descripción del problema
                System.out.print("Ingrese el requerimiento a Solucionar: ");
                String descripcion = scanner.nextLine();

                PQRS nuevaPQRS = new PQRS(cliente, departamento, agente, descripcion);
                casos.add(nuevaPQRS);
                System.out.println("Nueva PQRS creada: " + nuevaPQRS.mostrarDetalles());

            } else if (opcion == 2) {
                System.out.print("Ingrese el ID de la PQRS a consultar: ");
                int idConsulta = scanner.nextInt();
                scanner.nextLine();

                boolean encontrado = false;
                for (PQRS caso : casos) {
                    if (caso.getId() == idConsulta) {
                        System.out.println("Consulta de la PQRS: " + caso.mostrarDetalles());
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("No se encontró ninguna PQRS con ese ID ingresado.");
                }

            } else if (opcion == 3) {
                System.out.print("Ingrese el ID de la PQRS a actualizar: ");
                int idActualizar = scanner.nextInt();
                scanner.nextLine();

                boolean actualizado = false;
                for (PQRS caso : casos) {
                    if (caso.getId() == idActualizar) {
                        System.out.print("Ingrese el nuevo estado (Abierto/En proceso/Resuelto): ");
                        String nuevoEstado = scanner.nextLine();
                        caso.actualizarEstado(nuevoEstado);
                        System.out.println("Estado actualizado: " + caso.mostrarDetalles());
                        actualizado = true;
                        break;
                    }
                }
                if (!actualizado) {
                    System.out.println("No se encontró ninguna PQRS con ese ID.");
                }

            } else if (opcion == 4) {
                System.out.println("Saliendo del sistema...");
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}
