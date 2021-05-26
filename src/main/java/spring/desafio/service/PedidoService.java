package spring.desafio.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.desafio.model.Pedido;
import spring.desafio.repository.PedidoRepository;
import spring.desafio.request.PedidoPostRequestBody;
import spring.desafio.request.PedidoPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public List<Pedido> listAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findByIdOrThrowBadRequestException(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido not Found"));
    }

    public Pedido save(PedidoPostRequestBody PedidoPostRequestBody) {
        return pedidoRepository.save(Pedido.builder().name(PedidoPostRequestBody.getName()).build());
    }

    public void delete(Integer id) {
        pedidoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(PedidoPutRequestBody PedidoPutRequestBody) {
        Pedido savedPedido = findByIdOrThrowBadRequestException(PedidoPutRequestBody.getId());
        Pedido pedido = Pedido.builder()
                .id(savedPedido.getId())
                .name(PedidoPutRequestBody.getName())
                .build();

        pedidoRepository.save(pedido);
    }
}