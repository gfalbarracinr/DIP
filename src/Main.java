import java.util.ArrayList;

public class Main {

    public static void main (String args[]){
        IBaseDeDatos baseDeDatos = ProductoFactory.obtenerFactory().obtenerBaseDeDatos("default");
        Servicio miServicio = new Servicio(baseDeDatos);

        Producto miProducto = new Producto(1, "producto 1", 10);

        System.out.println(miServicio.calcularImpuesto(miProducto));

        miServicio.guardarProducto(miProducto);

        ArrayList<Producto> listaProductos = (ArrayList<Producto>) miServicio.listarProductos();

        for (Producto producto : listaProductos){
            System.out.println(" id: "+ producto.obtenerId() + " nombre: " + producto.obtenerNombre());
        }

    }
}
