// package ladybugger.rest;

// import java.net.URI;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import ladybugger.model.Employee;
// import ladybugger.service.EmployeeService;


// @RestController
// @RequestMapping ("/api/persona/")
// public class EmployeeREST {
	
// 	@Autowired
// 	private EmployeeService personaService;
	
// 	@PostMapping ("/guardar")
// 	private ResponseEntity<Employee> guardar (@RequestBody Employee persona){
// 		Employee temporal = personaService.create(persona);
		
// 		try {
// 			return ResponseEntity.created(new URI("/api/persona"+temporal.getId())).body(temporal);
			
// 		}catch (Exception e) {
// 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
// 		}
// 	}
	
	
// 	@GetMapping
// 	private ResponseEntity<List<Employee>> listarTodasLasPersona (){
// 		return ResponseEntity.ok(personaService.getAllPersonas());
// 	}
    
// 	@DeleteMapping
// 	private ResponseEntity<Void> eliminarPersona (@RequestBody Employee persona){
// 		personaService.delete(persona);
// 		return ResponseEntity.ok().build();
// 	}
	
// 	@GetMapping (value = "{id}")
// 	private ResponseEntity<Optional<Employee>> listarPersonasPorID (@PathVariable ("id") Long id){
// 		return ResponseEntity.ok(personaService.findById(id));
// 	}
	

// }