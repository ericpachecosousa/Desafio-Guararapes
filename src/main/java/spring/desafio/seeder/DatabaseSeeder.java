package spring.desafio.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.desafio.model.Pedido;
import spring.desafio.model.PedidoItem;
import spring.desafio.model.Projeto;
import spring.desafio.repository.PedidoItemRepository;
import spring.desafio.repository.PedidoRepository;
import spring.desafio.repository.ProjetoRepository;

import java.util.ArrayList;
import java.util.Arrays;

@Component

public class DatabaseSeeder {
    @Autowired
    ProjetoRepository projetoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PedidoItemRepository pedidoItemRepository;

    Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        inicializarDados();
    }

    public void inicializarDados(){
        Projeto proj1 = new Projeto((Integer)0, "Saia");
        Projeto proj2 = new Projeto((Integer)0, "Calça");
        Projeto proj3 = new Projeto((Integer)0, "Casaco");

        Pedido ped1 = new Pedido((Integer)0, "RNGUARA01", new ArrayList<>());
        Pedido ped2 = new Pedido((Integer)0, "RNGUARA02", new ArrayList<>());
        Pedido ped3 = new Pedido((Integer)0, "RNGUARA03", new ArrayList<>());





        logger.info("Inserindo Projetos ... ");
        projetoRepository.saveAll(Arrays.asList(proj1,proj2,proj3));

        logger.info("Projetos Salvos!");

        logger.info("Indexando Projetos ... ");
        Iterable<Projeto> projetos = projetoRepository.findAll();
        for (Projeto projeto : projetos){
            if(proj1.isEqual(projeto)){
                proj1.setId(projeto.getId());
            }else if (proj2.isEqual(projeto)){
                proj2.setId(projeto.getId());
            }
            else if (proj3.isEqual(projeto)){
                proj3.setId(projeto.getId());
            }
        }
        logger.info("Projetos Indexados!");

        logger.info("Inserindo Pedidos ... ");
        pedidoRepository.saveAll(Arrays.asList(ped1,ped2,ped3));

        logger.info("Pedidos Salvos!");

        logger.info("Indexando Pedidos ... ");
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        for (Pedido pedido : pedidos){
            if(ped1.isEqual(pedido)){
                ped1.setId(pedido.getId());
            }else if (ped2.isEqual(pedido)){
                ped2.setId(pedido.getId());
            }
            else if (ped3.isEqual(pedido)){
                ped3.setId(pedido.getId());
            }
        }
        logger.info("Pedidos Indexados!");


        PedidoItem item1 = new PedidoItem((Integer)0, 4, proj1,ped1,0);

        PedidoItem item2 = new PedidoItem((Integer)0, 1, proj1,ped2,0);
        PedidoItem item3 = new PedidoItem((Integer)0, 2, proj2,ped2,0);

        PedidoItem item4 = new PedidoItem((Integer)0, 1, proj1,ped3,0);
        PedidoItem item5 = new PedidoItem((Integer)0, 2, proj2,ped3,0);
        PedidoItem item6 = new PedidoItem((Integer)0, 3, proj3,ped3,0);

        logger.info("Inserindo Itens Pedido ... ");
        pedidoItemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item5,item6));
        logger.info("Itens Pedido Salvos!");


    }

}