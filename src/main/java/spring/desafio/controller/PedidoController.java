package spring.desafio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.desafio.model.Pedido;
import spring.desafio.request.PedidoPostRequestBody;
import spring.desafio.request.PedidoPutRequestBody;
import spring.desafio.service.PedidoService;
import spring.desafio.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@Api(value = "API Pedidos")
@Log4j2
@RequiredArgsConstructor

public class PedidoController {
    private final DateUtil dateUtil;
    private final PedidoService pedidoService;

    @ApiOperation(value = "Listar todos os pedidos")
    @GetMapping
    public ResponseEntity<List<Pedido>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(pedidoService.listAll());
    }
    @ApiOperation(value = "Filtrando apenas um pedido")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.findByIdOrThrowBadRequestException(id));
    }
    @ApiOperation(value = "Inclusão de um novo registro")
    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody PedidoPostRequestBody PedidoPostRequestBody) {
        return new ResponseEntity<>(pedidoService.save(PedidoPostRequestBody), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Exclusão de um registro existente")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Alteração de um registro existente")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody PedidoPutRequestBody PedidoPutRequestBody) {
        pedidoService.replace(PedidoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
