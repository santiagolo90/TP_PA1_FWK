package utilidades;

import anotaciones.Columna;
import anotaciones.Id;
import anotaciones.Tabla;

@Tabla(nombre = "auto")
public class Auto {
	
	@Id
	private Long id;
	
	@Columna(nombre = "marca")
	private String marca;
	
	@Columna(nombre = "color")
	private String color;

	public Auto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Auto [id=" + id + ", marca=" + marca + ", color=" + color + "]";
	}
	
	
	
	
	

}
