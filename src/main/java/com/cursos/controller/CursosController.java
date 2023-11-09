package com.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.entities.Cursos;
import com.cursos.services.CursosServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cursos", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursosController {
	private final CursosServices cursosServices;

	@Autowired
	public CursosController (CursosServices cursosServices) {
		this.cursosServices = cursosServices;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza curso por ID")
	public ResponseEntity <Cursos> buscaCursosIdControlId(@PathVariable Long id){
		Cursos cursos = cursosServices.buscarCursosId(id);
		if(cursos!= null) {
			return ResponseEntity.ok(cursos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation(summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Cursos>> buscaTodosFuncionarioControl() {
		List<Cursos> Cursos = cursosServices.buscarTodosCursos();

		return ResponseEntity.ok(Cursos);
	}
	@PostMapping
	@Operation(summary = "Cadastra um curso")
	public ResponseEntity<Cursos> salvaCursoControl(@RequestBody @Valid Cursos cursos){
		Cursos salvaCursos = cursosServices.salvaCursos(cursos);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCursos);

	}
	@PutMapping ("/{id}")
	@Operation(summary = "altera as informações do id do cursos")
	public ResponseEntity<Cursos> alterarCursos(@PathVariable Long id, @RequestBody @Valid Cursos cursos) {
		Cursos alterarCursos = cursosServices.alterarCursos(id,cursos);
		if (alterarCursos  != null) {
			return ResponseEntity.ok(alterarCursos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar o id selecionado")
	public ResponseEntity<String> apagaCursosControl(@PathVariable Long id) {
		boolean apagar = cursosServices.apagarCursos(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
