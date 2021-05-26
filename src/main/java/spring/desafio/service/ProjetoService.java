package spring.desafio.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.desafio.model.Projeto;
import spring.desafio.repository.ProjetoRepository;
import spring.desafio.request.ProjetoPostRequestBody;
import spring.desafio.request.ProjetoPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    private final ProjetoRepository projetoRepository;

    public List<Projeto> listAll() {
        return projetoRepository.findAll();
    }

    public Projeto findByIdOrThrowBadRequestException(Integer id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Projeto not Found"));
    }

    public Projeto save(ProjetoPostRequestBody projetoPostRequestBody) {
        return projetoRepository.save(Projeto.builder().name(projetoPostRequestBody.getName()).build());
    }

    public void delete(Integer id) {
        projetoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProjetoPutRequestBody projetoPutRequestBody) {
        Projeto savedProjeto = findByIdOrThrowBadRequestException(projetoPutRequestBody.getId());
        Projeto projeto = Projeto.builder()
                .id(savedProjeto.getId())
                .name(projetoPutRequestBody.getName())
                .build();

        projetoRepository.save(projeto);
    }
}