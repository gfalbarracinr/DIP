import com.sun.istack.internal.NotNull;

import java.sql.SQLException;

public class ProductoFactory {

    private ProductoFactory(){}

    public static ProductoFactory obtenerFactory()  {
        return factory;
    }

    public IBaseDeDatos obtenerBaseDeDatos (String baseDeDatos){
        switch (baseDeDatos){
            case "default":
               return sqliteBaseDeDatos();
            default:
                throw new IllegalArgumentException();
        }
    }

    private IBaseDeDatos sqliteBaseDeDatos(){

        try {
            return new BDSqlite();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaseDeDatosVacia.obtenerBD();
    }

    private static final ProductoFactory factory = new ProductoFactory();
}
