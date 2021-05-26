package spring.desafio.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.desafio.model.Projeto;
import spring.desafio.request.ProjetoPostRequestBody;
import spring.desafio.request.ProjetoPutRequestBody;
import spring.desafio.service.ProjetoService;
import spring.desafio.util.DateUtil;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/projeto")
@Log4j2
@RequiredArgsConstructor
@Api(value = "API Projetos")



public class ProjetoController {
    private final DateUtil dateUtil;
    private final ProjetoService projetoService;

    @ApiOperation(value = "Listar todos os projetos")
    @GetMapping
    public ResponseEntity<List<Projeto>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(projetoService.listAll());
    }
    @ApiOperation(value = "Filtrando apenas um projeto")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Projeto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(projetoService.findByIdOrThrowBadRequestException(id));
    }
    @ApiOperation(value = "Inclusão de um novo registro")
    @PostMapping
    public ResponseEntity<Projeto> save(@RequestBody ProjetoPostRequestBody projetoPostRequestBody) {
        return new ResponseEntity<>(projetoService.save(projetoPostRequestBody), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Exclusão de um registro existente")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        projetoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "Alteração de um registro existente")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ProjetoPutRequestBody projetoPutRequestBody) {
        projetoService.replace(projetoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
