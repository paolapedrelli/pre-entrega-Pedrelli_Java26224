package service;

import java.util.ArrayList;
import java.util.List;

import model.Pedido;


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
}
