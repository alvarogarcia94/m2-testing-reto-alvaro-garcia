package com.example.m2retoalvarogarcia;

import com.example.m2retoalvarogarcia.clases.Teatro;
import com.example.m2retoalvarogarcia.repositories.TeatroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class M2RetoAlvaroGarciaApplication implements CommandLineRunner {

	@Autowired
	TeatroRepository TeatroRepository;

	public static void main(String[] args) {
		SpringApplication.run(M2RetoAlvaroGarciaApplication.class, args);

	}

	public static void mainMenu(){
		System.out.println("==============================================================");
		System.out.println("Bienvenido al CRM de gestión de teatros, que desea realizar ? ");
		System.out.println("==============================================================");
		System.out.println("0) Salir");
		System.out.println("1) Visualizar teatros existentes");
		System.out.println("2) Añadir un nuevo teatro");
		System.out.println("3) Consultar teatro por id");
		System.out.println("4) Modificar un teatro");
		System.out.println("5) Borrar un teatro");
		System.out.println("6) Borrar todos los teatros");
		System.out.println("7) Obtener el número de teatros existentes");
		System.out.println("8) Buscar por nombre");
		System.out.println("9) Buscar por localidad");
	}

	public void viewTheaters(){
		List<Teatro> theaterList = new ArrayList<>();
		if (theaterList.isEmpty()){
			System.out.println("No hay teatros");
		} else
			System.out.println(theaterList);
	}

	public void addTheater(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el nombre del teatro: ");
		String nombre = scanner.next();

		System.out.println("Introduce la localidad: ");
		String localidad = scanner.next();

		System.out.println("Anyo de construcción: ");
		Integer year = scanner.nextInt();

		Teatro theaterList = new Teatro(null, nombre, localidad, year);
		TeatroRepository.save(theaterList);
		System.out.println("Teatro añadido con éxito");
	}

	public void findById(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el id del teatro a buscar.");
		Long id = scanner.nextLong();
		Optional<Teatro> theatersOptional = TeatroRepository.findById(id);
		if (theatersOptional.isPresent()){
			Teatro theaterList = theatersOptional.get();
			System.out.println(theaterList);
		} else {
			System.out.println("El ID solicitado no pertenece a ningún teatro.");
		}
	}

	public void deleteById(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el id del teatro a eliminar");
		Long id = scanner.nextLong();
		boolean existe = TeatroRepository.existsById(id);

		if (!existe) {
			System.out.println("ID inexistente");
		} else {
			TeatroRepository.deleteById(id);
			System.out.println("Teatro eliminado con éxito");
		}
	}

	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Estás seguro/a de borrar los registros ? ");
		Boolean confirm = scanner.nextBoolean();

		//Si no queremos borrar nada, printeará un mensaje de error. Dado que el continue solo funciona cuando hay un bucle
		if (!confirm) {
			System.out.println("Error al borrar");
		}

		TeatroRepository.deleteAll();
		System.out.println("Registros borrados correctamente");

	}

	public Long total() {
		return TeatroRepository.count();
	}

	public void findByName(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el nombre del teatro a buscar: ");
		String nombre = scanner.next();
		List<Teatro> theaterList = TeatroRepository.findByName(nombre);
		for (Teatro teatro: theaterList){
			System.out.println(teatro);
		}
		System.out.println(theaterList);
	}

	public void findByLocation(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce la localidad: ");
		String localidad = scanner.next();

		System.out.println("Introduce el nombre teatro: ");
		String nombre = scanner.next();

		List<Teatro> theaterList = TeatroRepository.findByLocationAndName(localidad, nombre);
		for (Teatro teatro : theaterList){
			System.out.println(teatro);
		}

		System.out.println(theaterList);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		Integer option = scanner.nextInt();

		while (option!=0) {
			mainMenu();
			try {
				if (option == 0) {
					System.out.println("Hasta luego !!");
					break;
				} else if (option == 1){
					viewTheaters();

				} else if (option == 2) {
					addTheater();

				} else if (option == 3) {
					findById();

				} else if (option == 4) {


				} else if (option == 5) {
					deleteById();

				} else if (option == 6){
					delete();

				} else if (option == 7){
					total();

				} else if (option == 8){
					findByName();

				} else if (option == 9){

				}

			} catch(Exception e){
				e.printStackTrace();
			}
		}

	}
}
