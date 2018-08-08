import java.util.List;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class BDSqlite implements IBaseDeDatos{

    private Connection conexionPrincipal;

    public BDSqlite() throws SQLException {
         iniciarBaseDeDatos();

    }
    @Override
    public void guardar(Producto producto) throws SQLException {

       final PreparedStatement guardarEnBaseDeDatos = conexionPrincipal
                    .prepareStatement("INSERT INTO product VALUES (?, ?, ?)");
            guardarEnBaseDeDatos.setLong(1, producto.obtenerId());
            guardarEnBaseDeDatos.setString(2, producto.obtenerNombre());
            guardarEnBaseDeDatos.setDouble(3, producto.obtenerPrecio());
            guardarEnBaseDeDatos.executeUpdate();


    }

    @Override
    public List<Producto> obtenerProductos() {
        PreparedStatement productosEnBaseDeDatos;
        try {
            productosEnBaseDeDatos = conexionPrincipal
                    .prepareStatement("SELECT product_id, name, price  FROM product");
            final ResultSet productos = productosEnBaseDeDatos.executeQuery();

            List <Producto> lista  = llenarListaProductos(productos);

            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar en la base de datos. " + e);

        }
        return Collections.<Producto>emptyList();

    }

    @Override
    public boolean estaVacia() {
        return false;
    }

    private List <Producto> llenarListaProductos( final ResultSet productos) throws SQLException {
        List <Producto> lista = new ArrayList<>();
        while(productos.next()) {
            lista.add(new Producto(
                    productos.getInt("product_id"),
                    productos.getString("name"),
                    productos.getDouble("price"))
            );
        }
        return lista;
    }

    private void iniciarBaseDeDatos() throws SQLException {
        inicializar();

        if (conexionNoIniciada()) {
            crearConfiguracion();
        }
        crearEntidad();

    }

    private void crearConfiguracion() throws SQLException {

        SQLiteConfig configuracion = new SQLiteConfig();
        configuracion.enforceForeignKeys(true);
        conexionPrincipal = DriverManager.getConnection(CONEXION, configuracion.toProperties());

    }

    private boolean conexionNoIniciada() throws SQLException {
        return conexionPrincipal == null || conexionPrincipal.isClosed();
    }

    private void inicializar() throws ExceptionInInitializerError {
        try {

            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException error) {

            throw new ExceptionInInitializerError(error);

        }
    }

    private void crearEntidad() {
        try {
            final Statement declaracion = conexionPrincipal.createStatement();
            declaracion.executeUpdate("CREATE TABLE IF NOT EXISTS product "
                    + "(product_id INTEGER, name TEXT, price REAL, PRIMARY KEY(product_id))");

        } catch (SQLException error) {
            System.err.println("[ERROR] createSchema : " + error.getMessage());
        }
    }

    private static final String CONEXION = "jdbc:sqlite::memory:";
}