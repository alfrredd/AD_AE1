package main;

import entidad.Coche;
import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;


public class Menu {
    private static final int AÑADIR = 1;
    private static final int ELIMINAR = 2;
    private static final int CONSULTAR = 3;
    private static final int LISTADO = 4;
    private static final int EXPORTAR = 5;
    private static final int SALIR =6;
    

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		 ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		 //Scanner sc = new Scanner(System.in);
		 
		 File archivo = new File("coches.dat");
		 if (archivo.exists()) {
			 try {
				    FileInputStream fichero = new FileInputStream("coches.dat");
				    ObjectInputStream lectura = new ObjectInputStream(fichero);

					ArrayList<Coche> cocheLeido = (ArrayList<Coche>) lectura.readObject();
				    while (cocheLeido != null) {
				        listaCoches.addAll(cocheLeido);
				        cocheLeido = (ArrayList<Coche>) lectura.readObject();
				    }

				    lectura.close();
				    fichero.close();
				} catch (EOFException e) {
				    // Fin del archivo alcanzado
				} catch (IOException e) {
				    e.printStackTrace();
				} catch (ClassNotFoundException e) {
				    e.printStackTrace();
				}
		 } else {
		     System.out.println("El archivo "+archivo+" no existe");
		 }
		 
        try (Scanner sc = new Scanner(System.in)) {
			int eleccion;
			do {
			    //Mostrar menú
				System.out.println("##################");
			    System.out.println("Menu Concesionario:");
			    System.out.println(AÑADIR + ". Añadir nuevo coche");
			    System.out.println(ELIMINAR + ". Borrar coche por id");
			    System.out.println(CONSULTAR + ". Consulta coche por id");
			    System.out.println(LISTADO + ". Listado de coches");
			    System.out.println(EXPORTAR + ". Exportar listado de coches");
			    System.out.println(SALIR + ". Terminar el programa");

			    //Leer la opción del usuario
			    eleccion = sc.nextInt();

			    switch (eleccion) {
			        case AÑADIR:
			        	System.out.println("Introduzca el id:");
			            int id = sc.nextInt();
			            if (Coche.existeId(id, listaCoches)) {
			            	System.out.println("El ID ya existe, no se puede agregar el coche");
			            	break;
			            }
			            System.out.println("Introduzca la matricula:");
			            String matricula = sc.next();
			            if (Coche.existeMatricula(matricula, listaCoches)) {
			            	System.out.println("La matrícula ya existe, no se puede agregar el coche");
			            	break;
			            }
		                System.out.println("Introduzca la marca:");
		                String marca = sc.next();
		                System.out.println("Introduzca el modelo:");
		                String modelo = sc.next();
		                System.out.println("Introduzca el color:");
		                String color = sc.next();
		                Coche nuevoCoche = new Coche(id, matricula, marca, modelo, color);
		                listaCoches.add(nuevoCoche);
		                System.out.println("Nuevo Coche Añadido");
		                break;
			        case ELIMINAR:
			        	System.out.println("Escriba la ID del coche que quiera borrar:");
		         		int idBorrar = sc.nextInt();
		         		if (!Coche.existeId(idBorrar, listaCoches)) {
			            	System.out.println("ID no encontrado, no se puede borrar");
			            	break;
			            }
		         		for (int i = 0; i < listaCoches.size(); i++) {
		                    Coche coches = listaCoches.get(i);
		                    	if (idBorrar == coches.getId()) {
								listaCoches.remove(coches);
								System.out.println("Vehículo con ID " + idBorrar + " eliminado.");
		                    	}
							}
		                break;
			        case CONSULTAR:
			        	System.out.println("Escriba la ID del coche que quiera consultar:");
		         		int idConsulta = sc.nextInt();
		         		if (!Coche.existeId(idConsulta, listaCoches)) {
			            	System.out.println("ID no encontrado, no se puede consultar");
			            	break;
			            }
		         		for (int i = 0; i < listaCoches.size(); i++) {
		                    Coche coches = listaCoches.get(i);
		                    	if (idConsulta == coches.getId()) {
								System.out.println(coches);	
		                    	}
							}
		         		break;
			        case LISTADO:
			        	
			        	if(listaCoches.isEmpty()) {
			        		System.out.println("**LISTADO VACÍO**");
			        	}else {
			        		System.out.println("Listado Vehiculos:");
				        	for (int i = 0; i < listaCoches.size(); i++) {
			                    Coche coches = listaCoches.get(i);
									System.out.println(coches);	
								}
			        	}
		                break;
			        case EXPORTAR:	
			        	FileWriter csv = null;
		                try {
		                    csv = new FileWriter("coches.csv");
		                    csv.append("Id" + ";" + "Matricula"+ ";" + "Marca"+ ";" + "Modelo"+ ";" + "Color" + "\n");
		                    for (Coche coche : listaCoches) {
		                    	 
		                        csv.write(coche.getId() + ";" + coche.getMatricula() + ";" + coche.getMarca() + ";" + coche.getModelo() + ";" + coche.getColor() + "\n");
		                    }
		                    System.out.println("Listado Exportado al archivo: coches.csv");
		                } catch (IOException e) {
		                    e.printStackTrace();
		                } finally {
		                    try {
		                        csv.close();
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                }
		                break;			            			        
			        case SALIR:
			        	try {
		         			FileOutputStream fichero = new FileOutputStream("coches.dat");
		         			ObjectOutputStream escritura = new ObjectOutputStream(fichero);
		         			escritura.writeObject(listaCoches);
		         			escritura.close();
		         			fichero.close();
		                     System.out.println("Programa terminado, se ha guardado la información en: " + archivo);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		                break;
			           
			        default:
			            System.out.println("Opción no válida, seleccione un número válido.");
			    }
			} while (eleccion != SALIR);
		}
    }

}
