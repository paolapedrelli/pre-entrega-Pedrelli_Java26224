package service;

import java.util.ArrayList;
import java.util.List;

import exception.StockInsuficienteException;
import model.DetallePedido;
import model.Pedido;
import model.Producto;


public class PedidoService {

    private List<Pedido> pedidos = new ArrayList<>();

    private static int contadorId = 1;

    private ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

public Pedido crearPedido() {
    Pedido pedido = new Pedido();

    pedido.setId(contadorId);
    contadorId++;

    pedidos.add(pedido);

    return pedido;
}

public void agregarProducto (Pedido pedido, int idProducto, int cantidad) {

    Producto producto = productoService.obtenerPorId(idProducto);

    if (cantidad <= 0) {
        throw new IllegalArgumentException ("La cantidad debe ser mayor a 0.");
        
    }    
     
    if (cantidad > producto.getStock()) {
        
        throw new StockInsuficienteException(
            "Stock Insuficiente. Stock disponible: " + producto.getStock());
    }

    DetallePedido detalle = new DetallePedido(producto, cantidad);

    pedido.agregarDetalle(detalle);
}


public void confirmarPedido(Pedido pedido) {

    for (DetallePedido detalle : pedido.getDetalles()) {

        Producto producto = detalle.getProducto();

        int cantidad = detalle.getCantidad();

        producto.setStock(producto.getStock() - cantidad);
    }
}

public List<Pedido> listarTodos() {
    return pedidos;
}

}
