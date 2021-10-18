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
	TeatroRepository teatroRepository;

	public static void main(String[] args) {
		SpringApplication.run(M2RetoAlvaroGarciaApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		//Variable para el funcionamiento del programa principal.
		Integer option = scanner.nextInt();
		//Mientras no introduzcamos el 0. El programa seguirá ejecutándose.
		while (true) {
			mainMenu();
			try {
				if (option == 0) {
					System.out.println("Hasta luego !!");
					break;

					//Aquí llamaremos a las funciones definidas previamente.
					//Acorde a la opción introducida por el usuario/a. El programa irá llamando a las funciones.
				} else if (option == 1){
					viewTheaters();

				} else if (option == 2) {
					addTheater();

				} else if (option == 3) {
					findById();

				} else if (option == 4) {
					updateTheater();

				} else if (option == 5) {
					deleteById();

				} else if (option == 6){
					delete();

				} else if (option == 7){
					total();

				} else if (option == 8){
					findByName();

				} else if (option == 9){
					findByLocation();
				}

			} catch(Exception e){
				e.printStackTrace();
			}
		}

	}

	public void mainMenu(){
			//Menu principal
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

		//Funcionalidad 1. Visualizar teatros existentes.
		public void viewTheaters(){
			//Simplemente mostrar teatros si exiten previamente.
			List<Teatro> theaterList = new ArrayList<>();
			if (theaterList.isEmpty()){
				System.out.println("No hay teatros");
			} else
				System.out.println(theaterList);
		}

		//Funcionalidad 2. Agregar un teatro a la BBDD
		public void addTheater(){
			//Simplemente, el usuario deberá introducir los datos del teatro a añadir.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduce el nombre del teatro: ");
			String nombre = scanner.next();

			System.out.println("Introduce la localidad: ");
			String localidad = scanner.next();

			System.out.println("Anyo de construcción: ");
			Integer year = scanner.nextInt();

			Teatro theaterList = new Teatro(null, nombre, localidad, year);
			teatroRepository.save(theaterList);
			System.out.println("Teatro añadido con éxito");
		}

		//Funcionalidad 3. Filtrar por id
		public void findById(){
			//El usuario deberá introducir un id por teclado.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduce el id del teatro a buscar.");
			Long id = scanner.nextLong();
			Optional<Teatro> theatersOptional = teatroRepository.findById(id);
			//Si el id consta en la BBDD lo mostrará, en cambio, si no existe mostrará un mensaje de error.
			if (theatersOptional.isPresent()){
				Teatro theaterList = theatersOptional.get();
				System.out.println(theaterList);
			} else {
				System.out.println("El ID solicitado no pertenece a ningún teatro.");
			}
		}

		//Funcionalidad 4. Modificar un teatro.
		public void updateTheater(){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduce el id del teatro que quieres modificar: ");
			Long id = scanner.nextLong();
			scanner.nextLine();
			Optional<Teatro> teatroOptional = teatroRepository.findById(id);

			//Si el id no pertenece a ningún teatro, ERROR.
			if (teatroOptional.isEmpty()){
				System.out.println("El teatro con el id solicitado NO existe.");
			}
			Teatro theater = teatroOptional.get();


			//Nuevos datos

			//Nombre
			System.out.println("Introduce nuevo nombre: (" + theater.getNombre() + ")");
			String nombre = scanner.nextLine();
			scanner.nextLine();
			theater.setNombre(nombre);

			//Localidad
			System.out.println("Introduce la localidad(actual): (" + theater.getLocalidad() + ")");
			String localidad = scanner.nextLine();
			scanner.nextLine();
			theater.setLocalidad(localidad);

			//Año (si ha habido alguna reforma integral)
			System.out.println("Introduce el año (reforma): (" + theater.getYear() + ")");
			Integer year = scanner.nextInt();
			scanner.nextLine();
			theater.setYear(year);

			//Guardar los datos introducidos en la BBDD.
			teatroRepository.save(theater);
			System.out.println("Cambios guardados satisfactoriamente. ");
		}

		//Funcionalidad 5. Borrar por Id
		public void deleteById(){
			//El usuario deberá introducir un id por teclado.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduce el id del teatro a eliminar");
			Long id = scanner.nextLong();
			boolean existe = teatroRepository.existsById(id);

			//Si no existe, enviaremos un mensaje de error.
			//En cambio si existe borrará el teatro, por id solicitado.
			//Y enviará un mensaje de confirmación de la supresión del id en cuestión.
			if (!existe) {
				System.out.println("ID inexistente");
			} else {
				teatroRepository.deleteById(id);
				System.out.println("Teatro eliminado con éxito");
			}
		}

		//Funcionalidad 6. Vaciar la BBDD.
		public void delete(){
			//Preguntamos al usuario si queremos proceder al barrido de la BBDD
			Scanner scanner = new Scanner(System.in);
			System.out.println("Estás seguro/a de borrar los registros ? ");
			Boolean confirm = scanner.nextBoolean();

			//Si no queremos borrar nada, printeará un mensaje de error.
			//Dado que el continue solo funciona cuando hay un bucle while. He tenido que añadir un sout.
			if (!confirm) {
				System.out.println("Error al borrar");
			}

			//Si la respuesta a la pregunta es un sí. Borrará todos los registros.
			teatroRepository.deleteAll();
			System.out.println("Registros borrados correctamente");

		}

		//Funcionalidad 7. Obtener el total de registros almacenados en la BBDD
		public Long total() {
			//Aquí retornamos el total de registros almacenados en la BBDD
			return teatroRepository.count();
		}

		//Funcionalidad 8. Filtrar por nombre de teatro, para comprobar su existencia en la BBDD
		public void findByName(){
			//Pediremos al usuario el nombre de un teatro para hacer la búsqueda.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduce el nombre del teatro a buscar: ");
			String nombre = scanner.next();
			List<Teatro> theaterList = teatroRepository.findByNombre(nombre);
			//For-each para recorrer la BBDD.
			for (Teatro teatro: theaterList){
				System.out.println(teatro);
			}
			System.out.println(theaterList);
		}

		//Funcionalidad 9. Permite buscar por localidad.
		public void findByLocation(){
			//Pediremos al usuario que introduzca la localidad.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduce la localidad: ");
			String localidad = scanner.next();

			//También le pediremos el nombre del teatro.
			System.out.println("Introduce el nombre teatro: ");
			String nombre = scanner.next();

			List<Teatro> theaterList = teatroRepository.findByLocalidadAndNombre(localidad, nombre);
			//Bucle for-each encargado de recorrer la BBDD en busca de los parámetros pasado por teclado.
			for (Teatro teatro : theaterList){
				System.out.println(teatro);
			}

			System.out.println(theaterList);
		}
	}
