import java.util.Scanner;


import exception.ProductoNoEncontradoException;
import exception.StockInsuficienteException;
import model.Pedido;
import model.Producto;
import service.PedidoService;
import service.ProductoService;
import util.Validador;


public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);

    }

    
}