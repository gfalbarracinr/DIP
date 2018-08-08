public class Producto {

    public Producto( int id, final String nombre, double precio ){
        this.id = id;
        this.nombre=nombre;
        this.precio = precio;
    }

    public int obtenerId(){
        return id;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public double obtenerPrecio(){
        return precio;
    }
    private int id;
    private String nombre;
    private double precio;
}
