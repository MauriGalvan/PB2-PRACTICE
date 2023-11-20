package ar.edu.unlam.pb2.Parcial01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tienda {

	/**
	 * En esta ocasion deberemos resolver un producto software que nos permita
	 * administrar la venta de productos o servicios de nuestra tienda. Venderemos
	 * entonces, productos como mouse o teclados y servicios como el soporte tecnico
	 * a domicilio. Sabemos que la tienda cuenta con items Vendibles que pueden ser
	 * del tipo Producto o Servicio. Ademas, podemos registrar el stock de los
	 * productos, los clientes a quienes les vendemos algun producto o servicio, las
	 * ventas y los vendedores de la tienda. Antes de realizar alguna operacion, se
	 * debera obtener el elemento correspondiente de las colecciones. Ejemplo: Si
	 * quisiera realizar alguna operacion con un cliente, el mismo debe obtenerse de
	 * la coleccion de clientes.
	 * 
	 * Cada Venta contiene renglones los cuales representa a los productos o
	 * servicios que se incluyen en la misma. Tambien cuenta con el Cliente y
	 * Vendedor que participan en la Venta. Cuando agregamos un vendible a una
	 * venta, lo haremos con 1 unidad. En una version posterior, admitiremos
	 * cantidades variables.
	 * 
	 * Cada Item debe compararse por nombre y precio, en caso de ser necesario.
	 * Recordar que los items deben ser Vendibles.
	 * 
	 */

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock;
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;

	public Tienda(String cuit, String nombre) {
		this.cuit = cuit;
		this.nombre = nombre;
		this.vendibles = new HashSet<Vendible>();
		this.stock = new HashMap<Producto, Integer>();
		this.clientes = new ArrayList<Cliente>();
		this.ventas = new HashSet<Venta>();
		this.vendedores = new HashSet<Vendedor>();
		// TODO: Completar el constructor para el correcto funcionamiento del software
	}

	// TODO: Completar con los getters y setters necesarios

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Vendible getVendible(Integer codigo) {
		// TODO: Obtiene un producto o servicio de la coleccion de vendibles utilizando
		// el codigo. En caso de no existir devuelve null.
		return null;
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		vendibles.add(producto);
		stock.put(producto, stockInicial);
		// TODO: Agrega un producto a la coleccion de vendibles y pone en la coleccion
		// de stocks al producto con su stock inicial
	}

	public void agregarServicio(Servicio servicio) {
		vendibles.add(servicio);
		// TODO: Agrega un servicio a la coleccion de vendibles
	}

	public Integer getStock(Producto producto) {
		return stock.get(producto);
	}

	public void agregarStock(Producto producto, Integer incremento){
		// TODO: se debe agregar stock a un producto existente
		Integer stock = this.getStock(producto);
		this.stock.put(producto, stock + incremento);
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarVendedor(Vendedor vendedor) {
		vendedores.add(vendedor);
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaException {
		if(venta.getVendedor().isDeLicencia()) {
			throw new VendedorDeLicenciaException();
		}
		ventas.add(venta);
		// TODO: Agrega una venta a la coleccion correspondiente. En caso de que el
		// vendedor este de licencia, arroja una
		// VendedorDeLicenciaException
	}

	public Producto obtenerProductoPorCodigo(Integer codigo) {
		// TODO: Obtiene un producto de los posibles por su codigo. En caso de no
		// encontrarlo se debera devolver null
		for (Vendible vendibles: this.vendibles) {
			if(vendibles instanceof Producto && vendibles.getCodigo().equals(codigo) ) {
				return (Producto) vendibles;
			}
		}
		return null;
	}

	public void agregarProductoAVenta(String codigoVenta, Producto producto) throws VendibleInexistenteException {
		Venta venta = this.getVenta(codigoVenta);
		if(obtenerProductoPorCodigo(producto.getCodigo())== null) {
			throw new VendibleInexistenteException();

		}
		venta.agregarRenglon(producto, 1);
		Integer stock = this.getStock(producto);
		this.stock.put(producto, stock - 1);
		// TODO: Agrega un producto a una venta. Si el vendible no existe (utilizando su
		// codigo), se debe lanzar una VendibleInexistenteException
		// Se debe actualizar el stock en la tienda del producto que se agrega a la
		// venta
	}

	private Venta getVenta(String codigoVenta) {
		for (Venta v : this.ventas) {
			if(v.getCodigo().equals(codigoVenta)) {
				return v;
			}
		}
		return null;
	}

	public void agregarServicioAVenta(String codigoVenta, Servicio servicio) {
		// TODO: Agrega un servicio a la venta. Recordar que los productos y servicios
		// se traducen en renglones
		Venta venta = this.getVenta(codigoVenta);
		venta.agregarRenglon(servicio, 1);
	}

	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		List<Producto> productos = new ArrayList<Producto>();
		for(Vendible v : this.vendibles) {
			if(v instanceof Producto) {
				Producto producto = (Producto) v;
				Integer stockActual = stock.get(producto);
				if(stockActual <= producto.getPuntoDeReposicion()) {
					productos.add(producto);
				}
			}
			
		}
		// TODO: Obtiene una lista de productos cuyo stock es menor o igual al punto de
		// reposicion. El punto de reposicion, es un valor que
		// definimos de manera estrategica para que nos indique cuando debemos reponer
		// stock para no quedarnos sin productos
		return productos;
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		Collections.sort(this.clientes);
		return this.clientes;
		 //para que el método Collections.sort funcione correctamente, la clase Cliente debe implementar la interfaz Comparable 
		// y proporcionar una implementación del método compareTo que define cómo comparar dos instancias de Cliente. Además, 
		//la comparación basada en la razón social parece ser la clave para el ordenamiento en este caso
		// TODO: obtiene una lista de clientes ordenados por su razon social de manera
		// descendente
		
	}

	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {
		
		Map<Vendedor, Set<Venta>> ventasPorVendedor = new HashMap<Vendedor, Set<Venta>>();
		// TODO: Obtiene un mapa que contiene las ventas realizadas por cada vendedor.
		
		for (Venta v : this.ventas) {
			if(!ventasPorVendedor.containsKey(v.getVendedor())) {  //Se verifica si el vendedor de la venta actual ya está presente como clave en el mapa ventasPorVendedor.
				ventasPorVendedor.put(v.getVendedor(), new HashSet<Venta>());
			}
			ventasPorVendedor.get(v.getVendedor()).add(v);
		}	
		
		return ventasPorVendedor;
	}

	public Double obtenerTotalDeVentasDeServicios() {
		Double totalDeVentasPorServicio = 0d;
		for (Venta venta : this.ventas) {
			Map<Vendible,Integer> renglones = venta.getRenglones();
				for(Vendible vendible : renglones.keySet()) {
				
				if(vendible instanceof Servicio) {
					totalDeVentasPorServicio += vendible.getPrecio();
				}
			}
			
		}	
		return totalDeVentasPorServicio;
		// TODO: obtiene el total acumulado de los vendibles que son servicios incluidos
				// en todas las ventas.
				// Si una venta incluye productos y servicios, solo nos interesa saber el total
				// de los servicios
	
	}
}
