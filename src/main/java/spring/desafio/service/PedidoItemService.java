package spring.desafio.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.desafio.model.Pedido;
import spring.desafio.model.PedidoItem;
import spring.desafio.model.Projeto;
import spring.desafio.repository.PedidoItemRepository;
import spring.desafio.request.PedidoItemPostRequestBody;
import spring.desafio.request.PedidoItemPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoItemService {
    private final PedidoItemRepository pedidoItemRepository;

    public List<PedidoItem> listAll() {
        return pedidoItemRepository.findAll();
    }

    public PedidoItem findByIdOrThrowBadRequestException(Integer id) {
        return pedidoItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "PedidoItem not Found"));
    }

    public PedidoItem save(PedidoItemPostRequestBody pedidoItemPostRequestBody) {
        return pedidoItemRepository.save(PedidoItem.builder()
                .qtd(pedidoItemPostRequestBody.getQtd())
                .pedido(Pedido.builder().id(pedidoItemPostRequestBody.getPedido_id()).build())
                .projeto(Projeto.builder().id(pedidoItemPostRequestBody.getProjeto_id()).build())
                .build());
    }

    public void delete(Integer id) {
        pedidoItemRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(PedidoItemPutRequestBody pedidoItemPutRequestBody) {
        PedidoItem savedPedidoItem = findByIdOrThrowBadRequestException(pedidoItemPutRequestBody.getPid());
        savedPedidoItem.setQtd(pedidoItemPutRequestBody.getQtd());
        pedidoItemRepository.save(savedPedidoItem);
    }
}