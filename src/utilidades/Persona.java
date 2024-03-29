package utilidades;
import anotaciones.*;

@Tabla(nombre = "Persona")
public class Persona {
	
	@Id
	private Long id;
	@Columna(nombre = "nombre")
	private String nombre;
	@Columna(nombre = "apellido")
	private String apellido;
	@Columna(nombre = "edad")
	private Integer edad;
	
	public Persona() {
		super();

	}
	
	
	public Persona(Long id, String nombre, String apellido, Integer edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + "]";
	}

}
