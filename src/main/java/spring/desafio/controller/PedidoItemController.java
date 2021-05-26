package spring.desafio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.desafio.model.PedidoItem;
import spring.desafio.model.PedidoItem;
import spring.desafio.request.PedidoItemPostRequestBody;
import spring.desafio.request.PedidoItemPutRequestBody;
import spring.desafio.service.PedidoItemService;
import spring.desafio.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidoitem")
@Api(value = "API Item Pedidos")
@Log4j2
@RequiredArgsConstructor

public class PedidoItemController {
    private final DateUtil dateUtil;
    private final PedidoItemService pedidoItemService;

    @ApiOperation(value = "Listar todos os itens pedido")
    @GetMapping
    public ResponseEntity<List<PedidoItem>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(pedidoItemService.listAll());
    }

    @ApiOperation(value = "Filtrando apenas um item pedido")
    @GetMapping(path = "/{id}")
    public ResponseEntity<PedidoItem> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoItemService.findByIdOrThrowBadRequestException(id));
    }

    @ApiOperation(value = "Inclusão de um novo registro")
    @PostMapping
    public ResponseEntity<PedidoItem> save(@RequestBody PedidoItemPostRequestBody PedidoItemPostRequestBody) {
        return new ResponseEntity<>(pedidoItemService.save(PedidoItemPostRequestBody), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Exclusão de um registro existente")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Alteração de um registro existente")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody PedidoItemPutRequestBody PedidoItemPutRequestBody) {
        pedidoItemService.replace(PedidoItemPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

