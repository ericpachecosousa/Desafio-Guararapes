# DesafioGuararapes

Criar um conjunto de API's

## Baixando o repositório 

Pode-se baixar via HTTPS ou GIT


## Abrindo a Solução via Intellij

> File
>>Open Project
>>>Localizar o projeto em seu computador

##  Considerações dos Padrões e Diretórios de Projeto

O desafio está composto em criar api de projetos que recebe dados de projeto de um front espeífico para ele e poderá visualizar os pedidos por aquele projeto, porém a criação de pedidos se dá por outro frontend especializado com sua própria API, os dados de pedido serão então replicados para a api de pedidos para ganhar mais agilidade nas pesquisas. Toda a integração deve ser realizada por um componente central uqe transfere os dados de um lado para o outro. 

*A requisição HTTP navega entre as camadas do sistema, respeitando o Single Reponsibility Principle*

Seguindo o devido Fluxo:

> Cliente HTTP
>> Controllers
>>> Request
>>>> Servicos
>>>>> Repositórios
>
>>>Serviços
>
>>>Controllers
>
>>Request
>
> Cliente HTTP


As Interfaces nas camadas de Repositório fomentam o Principio de Inversão de Dependencia e Diminui o acoplamento, 

#### Pom.xml
> Abriga todas as dependencias do projeto

#### Application.properties
> Abriga todas configuração do banco e configurações do Swagger

#### Controllers
>Mapeia os Endpoints que formam a API Rest

#### Models
> Abriga as Classes de Modelo (Entidades) do Projeto

#### Repository
> Abriga as interfaces e classes de repositório que norteiam o acesso e armazenamento de dados

#### Seeder
> Abriga o arquivo para preenchimento do banco dados automatico.

#### Service
> Abriga o clases que executam a lógica de negócio 


#### Dto
> Abriga o clases que é usada para logica de negocio da Fachada

#### Utils
> Classes de Apoio para execução de tarefas específicas não funcionais.
>> Date Utils (Comparação de Datas )
>> 
### Executando o Projeto

O ideal antes da execução do projeto é rodar o mavem para ele sincronizar o projeto em seu ambiente de trabalho
```
No canto inferior esquerdo, na barra de ações tem um botão de play
```

### Utilizando o Swagger


Para utilizar o Swagger acesse o link abaixo, nesse link terá todas nossas rotas para vizualização.
```
http://localhost:8080/swagger-ui.html
```



#### Todas as classes estão contemplandoas quatros operações básicas do CRUD (Create, Read, Update,Delete)


> URL: /api/pedido Pedido
>  URL: /api/pedidoitem ItemPedido
>  URL: /api/projeto Projeto
>> Verbo GET
>>> Retorna todos os  Cadastrados

>> Verbo POST
>>> Cria um novo registro  (o json´é passado no corpo da requisição)

>> Verbo PUT
>>> Atualiza o registro  (o json´é passado no corpo da requisição)

>> Verbo DELETE
>>> Remove um  registro  (neste caso, o json precisa conter somente a propriedade id preenchida)


#### Criar uma fachada que faça um iteração entre os Rest via HTTPS montar o objeto completo entre pedido e seus respectivos itens


> Controller
> > Fachada Controller: a classe está implemtando toda a logica para funcionalidade especificada.
```
http://localhost:8080/api/fachada/projetos
```




