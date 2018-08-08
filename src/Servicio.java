import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Servicio {

    public Servicio(IBaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }
    public double calcularImpuesto (Producto producto){
        Objects.requireNonNull(producto);
        return PORCENTAJE_IMPUESTO * producto.obtenerPrecio();

    }

    public void guardarProducto (Producto producto){
        Objects.requireNonNull(producto);
        try {
            baseDeDatos.guardar(producto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> listarProductos (){
        return baseDeDatos.obtenerProductos();
    }

    private static final double PORCENTAJE_IMPUESTO = 0.19;
    private IBaseDeDatos baseDeDatos;
}
