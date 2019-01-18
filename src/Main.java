import java.util.ArrayList;

public class Main {

    public static void main (String args[]){
        IBaseDeDatos baseDeDatos = ProductoFactory.obtenerFactory().obtenerBaseDeDatos("default");
        Servicio miServicio = new Servicio(baseDeDatos);
        Producto producto0 = new Producto(34, "producto que si se muestra", 4500);
        Producto producto1 = new Producto(45, "producto a borrar", 4500);
        Producto falsoProducto = new Producto(60, "producto', 5000); DELETE FROM product WHERE product_id = 45; --", 10);
        //Producto miProducto = new Producto(60, "producto', 5000); DROP TABLE IF EXISTS product; --", 10);


        miServicio.guardarProducto(producto0);
        miServicio.guardarProducto(producto1);
        miServicio.guardarProducto(falsoProducto);

        ArrayList<Producto> listaProductos = (ArrayList<Producto>) miServicio.listarProductos();

        for (Producto producto : listaProductos){
            System.out.println(" id: "+ producto.obtenerId() + " nombre: " + producto.obtenerNombre());
        }

    }
}
