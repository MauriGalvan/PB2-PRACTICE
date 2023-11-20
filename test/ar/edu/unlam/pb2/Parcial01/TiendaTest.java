package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TiendaTest {

	/**
	 * Resolver los siguientes tests
	 */

	@Test(expected = VendedorDeLicenciaException.class)
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException()throws VendedorDeLicenciaException{
		Cliente cliente = new Cliente ("12", "Cliente 1");
		Vendedor vendedor = new Vendedor("3213", "Vendedor Carlo");
		vendedor.setDeLicencia(true);
		Venta venta = new Venta("01",cliente,vendedor);
		Tienda tienda = new Tienda ("032" ,"tienda");
		tienda.agregarVenta(venta);
		}

	@Test(expected = VendibleInexistenteException.class)
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() throws VendedorDeLicenciaException, VendibleInexistenteException {
		Cliente cliente = new Cliente("123","cliente 1");
		Vendedor vendedor = new Vendedor("3213", "Vendedor Carlo");
		
		Producto producto = new Producto(1, "Producto 1", 100D, 20);
		Venta venta = new Venta ("02",cliente,vendedor);
		Tienda tienda = new Tienda ("032" ,"tienda");
		tienda.agregarVenta(venta);
		tienda.agregarProductoAVenta(venta.getCodigo(), producto);
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		Tienda tienda = new Tienda("2132" , "tienda2");
		
		Producto producto = new Producto (1,"producto 1",100D,20);
		Producto producto2 = new Producto (2,"producto 2",100D,20);
		Producto producto3= new Producto (3,"producto 3",100D,20);
		Producto producto4 = new Producto (4,"producto 4",100D,20);
		
		tienda.agregarProducto(producto);
		tienda.agregarProducto(producto2);
		tienda.agregarProducto(producto3);
		tienda.agregarProducto(producto4);
		
		tienda.agregarStock(producto, 10);
		tienda.agregarStock(producto2, 10);
		tienda.agregarStock(producto3, 30);
		tienda.agregarStock(producto4, 50);
		
		 List<Producto> productos = tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion();
		 
		 assertEquals(productos.size(), 2);
	}

	@Test
	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {
		Tienda tienda = new Tienda ("11", "tienda");
		Cliente cliente = new Cliente("1231","cliente 1");
		Cliente cliente2 = new Cliente("1232","cliente 2");
		Cliente cliente3 = new Cliente("1233","cliente 3");
		Cliente cliente4 = new Cliente("1234","cliente 4");
		
		tienda.agregarCliente(cliente);
		tienda.agregarCliente(cliente2);
		tienda.agregarCliente(cliente3);
		tienda.agregarCliente(cliente4);
		
		List<Cliente> clientes = tienda.obtenerClientesOrdenadosPorRazonSocialDescendente();
		for (int i = 0; i < clientes.size() -1; i++) {
		assertTrue(clientes.get(i).getRazonSocial().compareTo(clientes.get(i + 1).getRazonSocial()) > 0);
		//Se utiliza un bucle para iterar sobre la lista de clientes y verificar que cada cliente en la posición i
		//tenga una razón social mayor que el cliente en la posición i + 1. 
		//Esto asegura que la lista está ordenada de manera descendente según la razón social.	
		}
	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() throws VendedorDeLicenciaException, VendibleInexistenteException {
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
		Tienda tienda = new Tienda("11","tienda");
		Vendedor vendedor1 = new Vendedor("12", "carlos");
		Vendedor vendedor2 = new Vendedor("2323", "juan");
		
		tienda.agregarVendedor(vendedor1);
		tienda.agregarVendedor(vendedor2);
		
		Cliente cliente = new Cliente("2131", "cliente 1");

		Producto producto1 = new Producto(1, "Producto 1", 100D, 20);
		Producto producto2 = new Producto(2, "Producto 2", 100D, 20);
		Producto producto3 = new Producto(3, "Producto 3", 100D, 20);
		Producto producto4 = new Producto(4, "Producto 4", 100D, 20);
		tienda.agregarProducto(producto1);
		tienda.agregarProducto(producto2);
		tienda.agregarProducto(producto3);
		tienda.agregarProducto(producto4);
		
		tienda.agregarStock(producto1, 10);
		tienda.agregarStock(producto2, 10);
		tienda.agregarStock(producto3, 20);
		tienda.agregarStock(producto4, 20);
		
		Venta venta = new Venta("01", cliente, vendedor1);
		Venta venta2 = new Venta("02",cliente,vendedor2);
		Venta venta3 = new Venta("03",cliente,vendedor2);
		Venta venta4 = new Venta("04",cliente,vendedor2);
		
		tienda.agregarVenta(venta);
		tienda.agregarVenta(venta2);
		tienda.agregarVenta(venta3);
		tienda.agregarVenta(venta4);
		
		tienda.agregarProductoAVenta("001", producto1);
		tienda.agregarProductoAVenta("002", producto2);
		tienda.agregarProductoAVenta("003", producto3);
		tienda.agregarProductoAVenta("004", producto4);
	Map<Vendedor,Set<Venta>> ventasPorVendedor = tienda.obtenerVentasPorVendedor();
		
		assertEquals(ventasPorVendedor.get(vendedor1).size(), 1);
		assertEquals(ventasPorVendedor.get(vendedor2).size(), 3);
		
	}

	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios() throws VendedorDeLicenciaException, VendibleInexistenteException {
		Tienda tienda = new Tienda("12","tienda");
		Vendedor carlos = new Vendedor("40-00000000-1", "Carlos");
		Cliente cliente1 = new Cliente("30-00000000-1", "Cliente 1");
		
		Producto producto1 = new Producto(1, "Producto 1", 500D, 20);
		Servicio servicio1 = new Servicio(2, "Servicio Técnico", 100d, "2023-02-01", "2023-03-01");
		Servicio servicio2 = new Servicio(3, "Servicio Técnico", 50d, "2023-02-01", "2023-03-01");
		Servicio servicio3 = new Servicio(4, "Servicio Técnico", 30d, "2023-02-01", "2023-03-01");
		
		tienda.agregarProducto(producto1);
		tienda.agregarServicio(servicio1);
		tienda.agregarServicio(servicio2);
		tienda.agregarServicio(servicio3);
		
		Venta venta1 = new Venta("C-0001",cliente1, carlos);
		Venta venta2 = new Venta("C-0002",cliente1, carlos);
		Venta venta3 = new Venta("C-0003",cliente1, carlos);
		Venta venta4 = new Venta("C-0004",cliente1, carlos);
		
		tienda.agregarVenta(venta1);
		tienda.agregarVenta(venta2);
		tienda.agregarVenta(venta3);
		tienda.agregarVenta(venta4);
		
		tienda.agregarProductoAVenta("C-0001", producto1);
		tienda.agregarServicioAVenta("C-0002", servicio1);
		tienda.agregarServicioAVenta("C-0003", servicio2);
		tienda.agregarServicioAVenta("C-0004", servicio3);

		Double totalDeVentasDeServicios = tienda.obtenerTotalDeVentasDeServicios();
		assertTrue(totalDeVentasDeServicios.equals(180D));
	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() throws VendedorDeLicenciaException, VendibleInexistenteException {
		Tienda tienda = new Tienda("12","tienda");
		Vendedor carlos = new Vendedor("40-00000000-1", "Carlos");
		Cliente cliente1 = new Cliente("30-00000000-1", "Cliente 1");
		
		Producto producto1 = new Producto(1, "Producto 1", 500D, 20);
		tienda.agregarProducto(producto1);
		tienda.agregarStock(producto1, 10);
		
		Venta venta1 = new Venta("C-0001",cliente1, carlos);
		tienda.agregarVenta(venta1);
		tienda.agregarProductoAVenta("C-0001", producto1);
		Integer stock = tienda.getStock(producto1);
		assertTrue(stock.equals(9));
	}
}
