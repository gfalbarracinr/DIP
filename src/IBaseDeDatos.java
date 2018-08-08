import java.sql.SQLException;
import java.util.List;

public interface IBaseDeDatos {

    public void guardar(Producto producto) throws SQLException;

    public List<Producto> obtenerProductos();

    public boolean estaVacia();

}
