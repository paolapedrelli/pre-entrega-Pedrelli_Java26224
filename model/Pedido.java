package model;

import java.util.ArrayList;
import java.util.List;


public class Pedido {

    private int id;
    private List<DetallePedido> detalles;
    
    public Pedido() {
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetallePedido detalle) {
        detalles.add(detalle);
    }

    public double calcularTotal(){
        double total = 0;

        for (DetallePedido detalle : detalles) {
        total += detalle.getSubtotal();
        }

        return total;
    }

public int getId() {
        
        return id;
}


public void setId(int id) {
        
        this.id = id;
}

public List<DetallePedido> getDetalles() {

    return detalles;
      
    }
    

}
