package br.com.first.funcionario.controller;

import br.com.first.funcionario.models.Funcionario;
import br.com.first.funcionario.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


/**
 * <b>API funcionarioService</b>
 * </p>
 * Classe de controle da API de Funcionarios que disponibiliza as chamadas: salvar, buscar, atualizar, excluir e health
 * @author Thiago Peixoto
 */
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController implements BasicController<Funcionario>{


	@Autowired
	private FuncionarioService funcionarioService  ;

	/**
	 * <b>salvar</b>
	 * </p>
	 * Chamada para o método Salvar
	 * @param objectInsert Recebe um JSon dos dados do funcionario
	 * @return  Retorna o Funcionario com o novo ID, ou os erros 400 ou 403
	 */
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Salva o funcionário.", description = "Irá gerar um ID para o funcionário e salvar no banco de dados.", method = "Post")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Registro criado com sucesso", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400",  description = "Parametros inválidos", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "403",  description = "Falha ao inserir o funcionário", content = @Content(schema = @Schema(hidden = true)))
	})
	public Funcionario salvar(@RequestBody Funcionario objectInsert) {
		if ( objectInsert == null || objectInsert.getNome() == null ) {
			throw  retornoParametroInvalido();
		}
		return funcionarioService.insert(objectInsert).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.FORBIDDEN, "Falha ao inserir o funcionário. Possivel causa: já existe um funcionário com ID: " + objectInsert.getId() ) );

	}


	/**
	 * <b>buscar</b>
	 * </p>
	 * Chamada para o método buscar
	 * @param id Recebe a chave primaria do registro de funcionario
	 * @return  Retorna o Funcionario com o novo ID, ou os erros 404
	 */
	@GetMapping("/buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Busca de  funcionário.", description = "Busca o funcionário pelo ID.", method = "Get")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro encontrado"),
			@ApiResponse(responseCode = "404",  description = "Falha ao buscar o funcionário. Não existe um funcionário com este ID.", content = @Content(schema = @Schema(hidden = true)))
	})
	public Funcionario buscar(@PathVariable("id") Long id) {
		return funcionarioService.findById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Falha ao buscar o funcionário. Não existe um funcionário com este ID."));
	}

	/**
	 * <b>atualizar</b>
	 * </p>
	 * Chamada para o método atualizar
	 * @param objectUpdate  Recebe um JSon dos dados do funcionario
	 * @return  Retorna o Funcionario alterado ou o erro 404
	 */
	@PutMapping("/atualizar")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Atualização de funcionário.", description = "Modifica as propriedades de um funcionário no banco de dados.", method = "Put")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro modificado"),
			@ApiResponse(responseCode = "404",  description = "Falha ao atualizar o funcionário. Não existe um funcionário com este ID", content = @Content(schema = @Schema(hidden = true)))
	})
	public void atualizar(@RequestBody Funcionario objectUpdate) {
		if (!funcionarioService.update(objectUpdate))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Falha ao atualizar o funcionário. Não existe um funcionário com este ID.");
	}

	/**
	 * <b>excluir</b>
	 * </p>
	 * Chamada para o método excluir
	 * @param id Recebe a chave primaria do registro de funcionario que deseja excluir
	 */
	@DeleteMapping("/excluir/{id}")
	@Operation(summary = "Exclusão de funcionário.", description = "Apaga o registro do funcionário no banco de dados.", method = "Delete")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro deletado"),
			@ApiResponse(responseCode = "404",  description = "Falha ao excluir o funcionário. Possivel causa: não existe um funcionário com ID.", content = @Content(schema = @Schema(hidden = true)))
	})
	public void excluir(@PathVariable("id") Long id) {
		if (!funcionarioService.delete(id))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Falha ao excluir o funcionário. Possivel causa: não existe um funcionário com ID." + id);
	}


	/**
	 * <b>health</b>
	 * </p>
	 * Chamada para o método health, teste se a API esta ativa
	 */
	@GetMapping("/health")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Health", description = "Monitora se a API esta ativa.", method = "Get")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "API ativa", content = @Content(schema = @Schema(hidden = true)))
	})

	public ResponseEntity<?> health( ) {
		return  new ResponseEntity<>(HttpStatus.OK);
	}

	private ResponseStatusException retornoParametroInvalido() {
		return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parametro invalido /n O JSon para uso da API deverá seguir o seguinte exemplo: /n " +
				"{\n" +
				"    \"id\": 1,\n" +
				"    \"nome\": \"Thiago \",\n" +
				"    \"designacao\": \"Gerente\",\n" +
				"    \"salario\": \"10000.00\",\n" +
				"    \"telefone\": \"11 99999-9999\",\n" +
				"    \"endereco\": \"Al Timbauva, 13\"\n" +
				"}" +
				"" );
	}


}
