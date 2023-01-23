package entidad;
import java.io.Serializable;
import java.util.ArrayList;

public class Coche implements Serializable{

	private static final long serialVersionUID = -4179974122075764123L;

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    
    
	public Coche() {
	}


	public Coche(int id, String matricula, String marca, String modelo, String color) {
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}


	public static boolean existeMatricula(String matricula, ArrayList<Coche> listaCoches) {
		 for (Coche coche : listaCoches) {
		        if (coche.getMatricula().equals(matricula)) {
		            return true;
		        }
		    }
		 return false;
	}
	
	public static boolean existeId(int id, ArrayList<Coche> listaCoches) {
		for(Coche coche : listaCoches) {
			if (coche.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
    
    
	
	
}
