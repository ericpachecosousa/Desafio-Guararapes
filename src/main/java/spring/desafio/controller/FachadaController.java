package spring.desafio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.desafio.dto.PedidoDto;
import spring.desafio.model.Pedido;
import spring.desafio.model.PedidoItem;
import spring.desafio.service.PedidoService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fachada")
@Api(value = "Fachada para exibição de dados")
@Log4j2
@RequiredArgsConstructor

public class FachadaController {
    //duas requisições HTTP para montar o objeto completo
    // obsejeto completo seria os pedidos com seus respectivos itens
    @GetMapping("/projetos")
    @ApiOperation(value = "Listar todos os pedidos")
    public ResponseEntity<List<PedidoDto>> list() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Pedido[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8080/api/pedido/",
                        Pedido[].class);

        List<Pedido> pedidos = Arrays.asList(response.getBody());
         restTemplate = new RestTemplate();
                ResponseEntity<PedidoItem[]> responseItem =
                restTemplate.getForEntity(
                        "http://localhost:8080/api/pedidoitem/",
                        PedidoItem[].class);

        List<PedidoItem> pedidoItens = Arrays.asList(responseItem.getBody());
        Map<Integer,List<PedidoItem>> map = new HashMap<>();
        for (PedidoItem pedidoItem: pedidoItens) {
            List<PedidoItem> listItem = null;
            if(map.containsKey(pedidoItem.getPedidoid()))
            {
                listItem = map.get(pedidoItem.getPedidoid());
            }
            else
            {
                listItem = new ArrayList<>();
            }
            listItem.add(pedidoItem);
            map.put(pedidoItem.getPedidoid(),listItem);
        }
       List<PedidoDto> pedidosDtos = pedidos.stream().map(pedido -> {
         return   PedidoDto.builder()
                   .id(pedido.getId())
                   .name(pedido.getName())
                   .itempedido(map.get(pedido.getId()))
                   .build();
       }).collect(Collectors.toList());
        return ResponseEntity.ok(pedidosDtos);
    }
}
