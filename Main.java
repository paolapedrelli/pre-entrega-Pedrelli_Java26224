import java.util.Scanner;

//import org.jcp.xml.dsig.internal.SignerOutputStream;

import exception.ProductoNoEncontradoException;
import exception.StockInsuficienteException;
import model.DetallePedido;
import model.Pedido;
import model.Producto;
import service.PedidoService;
import service.ProductoService;
import util.Validador;


public class Main {

    public static void main(String[] args) {

        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);
        Scanner sc = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("\n MENÚ PRINCIPAL\n");
            System.out.println("1 - AGREGAR PRODUCTO");
            System.out.println("2 - MOSTRAR PRODUCTOS");
            System.out.println("3 - BUSCAR PRODUCTO");
            System.out.println("4 - ACTUALIZAR PRODUCTO");
            System.out.println("5 - ELIMINAR PRODUCTO");
            System.out.println("6 - CREAR PEDIDO");
            System.out.println("7 - MOSTRAR PEDIDOS");
            System.out.println("8 - SALIR\n");
            

            opcion = Validador.leerEntero(sc, "Elija una opción: ");

            try {

                switch (opcion) {

                    case 1:
                        System.out.println("\n AGREGAR PRODUCTO");

                        String nombre = Validador.leerTexto(
                            sc, "Ingrese el nombre: ");

                        double precio = Validador.leerDouble(
                            sc, "Ingrese el precio: ");

                        int stock = Validador.leerEntero(
                        sc, "Ingrese el Stock: ");

                        String categoria = Validador.leerTexto(
                            sc, "Ingrese la Categoría: ");


                        Producto producto = new Producto(
                            nombre,
                            precio,
                            stock,
                            categoria);


                        productoService.guardar(producto);

                        System.out.println("Producto agregado correctamente.");
                        producto.mostrar();                        
                        break;


                    case 2:
                        System.out.println("\n LISTA DE PRODUCTOS.");

                        if (productoService.listarTodos().isEmpty())

                            {

                            System.out.println("No hay productos cargados.");

                        } else {

                            for (Producto p : productoService.listarTodos()) {
                                p.mostrar();
                            }
                        }

                        break;

                    case 3:
                        System.out.println("\n BUSCAR PRODUCTO POR ID.");

                        int idBuscar = Validador.leerEntero(
                                sc, "Ingrese el ID del producto: ");

                        Producto encontrado =
                                productoService.obtenerPorId(idBuscar);

                        System.out.println("\nProducto encontrado:");
                        encontrado.mostrar();

                        
                        break;


                    
                    case 4:
                        System.out.println("\n ACTUALIZAR PRODUCTO.");

                        int idActualizar = Validador.leerEntero(
                                sc, "Ingrese el ID del producto a actualizar: ");

                        Producto productoActual =
                                productoService.obtenerPorId(idActualizar);

                        System.out.println("\n Producto actual: ");
                        productoActual.mostrar();

                        String nuevoNombre = Validador.leerTexto(
                                sc, "Ingrese el nuevo nombre: ");

                        double nuevoPrecio = Validador.leerDouble(
                                sc, "Ingrese el nuevo precio: ");

                        int nuevoStock = Validador.leerEntero(
                                sc, "Ingrese el nuevo stock: ");

                        String nuevaCategoria = Validador.leerTexto(
                                sc, "Ingrese la nueva categoría: ");

                        Producto datosActualizados = new Producto(
                                nuevoNombre,
                                nuevoPrecio,
                                nuevoStock,
                                nuevaCategoria);

                        productoService.actualizar(
                                idActualizar,
                                datosActualizados);

                        System.out.println(
                                "Producto actualizado correctamente.");

                        break;


                    case 5:
                        System.out.println("\n ELIMINAR PRODUCTO.");

                        int idEliminar = Validador.leerEntero(
                                sc,
                                "Ingrese el ID del producto a eliminar: ");

                        productoService.eliminar(idEliminar);

                        System.out.println(
                                "Producto eliminado correctamente.");

                        break;

                    case 6:
                        System.out.println("\n CREAR PEDIDO.");

                        Pedido pedido = pedidoService.crearPedido();

                        System.out.println(
                                "Pedido creado. ID: " + pedido.getId());

                        boolean continuar = true;

                        while (continuar) {

                            int idProducto = Validador.leerEntero(
                                    sc,
                                    "Ingrese el ID del producto: ");

                            int cantidad = Validador.leerEntero(
                                    sc,
                                    "Ingrese la cantidad: ");

                            pedidoService.agregarProducto(
                                    pedido,
                                    idProducto,
                                    cantidad);

                            System.out.println(
                                    "Producto agregado al pedido.");

                            int respuesta = Validador.leerEntero(
                                    sc,
                                    "¿Desea agregar otro producto? 1-Sí / 2-No");

                            if (respuesta == 2) {
                                continuar = false;
                            }
                        }

                        System.out.println("\n--- RESUMEN DEL PEDIDO ---");

                        for (DetallePedido detalle : pedido.getDetalles()) {

                            System.out.println(
                                    detalle.getProducto().getNombre()
                                    + " | Cantidad: "
                                    + detalle.getCantidad()
                                    + " | Subtotal: $"
                                    + detalle.getSubtotal());
                        }

                        System.out.println(
                                "TOTAL: $" + pedido.calcularTotal());

                        int confirmar = Validador.leerEntero(
                                sc,
                                "¿Confirmar pedido? 1-Sí / 2-No");

                        if (confirmar == 1) {

                            pedidoService.confirmarPedido(pedido);

                            System.out.println(
                                    "Pedido confirmado correctamente.");

                        } else {

                            System.out.println(
                                    "Pedido no confirmado.");
                        }

                        break;

                    case 7:
                        System.out.println("\n--- LISTA DE PEDIDOS ---");

                        if (pedidoService.listarTodos().isEmpty()) {

                            System.out.println("No hay pedidos cargados.");

                        } else {

                            for (Pedido p : pedidoService.listarTodos()) {

                                System.out.println(
                                        "\nPedido ID: " + p.getId());

                                for (DetallePedido detalle : p.getDetalles()) {

                                    System.out.println(
                                            "- "
                                            + detalle.getProducto().getNombre()
                                            + " | Cantidad: "
                                            + detalle.getCantidad()
                                            + " | Subtotal: $"
                                            + detalle.getSubtotal());
                                }

                                System.out.println(
                                        "Total: $" + p.calcularTotal());
                            }
                        }

                        break;

                    case 8:
                        System.out.println(
                                "\n¡Gracias por utilizar el sistema!\n");
                        break;

                    default:
                        System.out.println(
                                "Opción inválida. Elija un número del 1 al 8.");
                }

            } catch (ProductoNoEncontradoException |
                     StockInsuficienteException e) {

                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());
            }

        } while (opcion != 8);

        sc.close();
    }
}
     