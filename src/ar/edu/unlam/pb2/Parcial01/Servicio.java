package ar.edu.unlam.pb2.Parcial01;

public class Servicio extends Item{
	
	private String fechaDeInicio;
	private String fechaDeFinalizacion;

	public Servicio(Integer codigo, String nombre, Double precio, String fechaDeInicio, String fechaDeFinalizacion) {
		super(codigo,nombre,precio);
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		this.fechaDeInicio = fechaDeInicio;
	}

	public String getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public String getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}

	public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
	}

	// TODO: Completar con los getters y setters necesarios
	
}
