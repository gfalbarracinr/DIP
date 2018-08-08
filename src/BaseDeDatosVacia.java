import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BaseDeDatosVacia implements IBaseDeDatos{

    private BaseDeDatosVacia(){
        estaVacia = true;
    }

    public static BaseDeDatosVacia obtenerBD (){
        return Inicializacion.INSTANCIA;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        //Do nothing
    }

    @Override
    public List<Producto> obtenerProductos() {
        return Collections.emptyList();
    }

    @Override
    public boolean estaVacia() {
        return estaVacia;
    }

    private static class Inicializacion {
        static final BaseDeDatosVacia INSTANCIA = new BaseDeDatosVacia();
    }

    private boolean estaVacia;

}
