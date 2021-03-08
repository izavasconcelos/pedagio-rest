### REST

**Pedágio REST**

```
Sistema de pedágio usando REST
BUS $1.59
MOTORCYCLE $1
BIKE $0.49
TRUCK $3.95
BEETLE $2.11

Pagamento: Recebe um valor e retornar o troco se houver. 
Para caminhões é cobrado adicional por eixo extra.
Possui a lista de preços do pedágio.
``` 
**Passo a passo para utilizar a aplicação:**

- Rodando a aplicação pelo terminal
```
Abra o terminal dentro do diretório do projeto "pedagio-rest/" e digite o comando: 

./gradlew run

Pronto, a aplicação está rodando e pode ser confirmada em http://localhost:8080/tema05
```

- Utilizando a aplicação no navegador
``` 
 | Realizar pagamento do pedágio |

# BEETLE | BIKE | BUS | MOTORCYCLE

Para veículos que não possuem eixo adicional acessar com a url abaixo:
http://localhost:8080/tema05/toll/{vehicleType}/{payment}
Inserir o veículo em {vehicleType} [beetle, bike, bus ou motorcycle]
Inserir o pagamento em {payment}

Exemplo: http://localhost:8080/tema05/toll/BIKE/5.50

 ------     ------     %      ------     -----

# TRUCK

Para caminhões acessar com a url abaixo com o número de eixos adicionais:
http://localhost:8080/tema05/toll/TRUCK/{payment}/{axles}
Inserir o pagamento em {payment}
Inserir o número de eixos em {axles}

Exemplo: Caminhão com 2 eixos adicionais:
http://localhost:8080/tema05/toll/TRUCK/19.50/2


..................................................................


| Acessando a Lista de preços: |

http://localhost:8080/tema05/toll/prices

``` 

**Referências de estudo e desenvolvimento:**


- https://www.youtube.com/watch?v=7YcW25PHnAA
- https://www.youtube.com/watch?v=RlB5wcuFvcc
- https://www.youtube.com/watch?v=ghTrp1x_1As&t=2s
- https://www.tutorialspoint.com/restful/restful_methods.htm
- https://www.baeldung.com/jackson
- http://fasterxml.github.io/jackson-jaxrs-providers/javadoc/2.8/com/fasterxml/jackson/jaxrs/json/JacksonJsonProvider.html
- https://www.logicbig.com/how-to/code-snippets/jcode-jax-rs-responsebuilder-and-response.html
- https://codedestine.com/jax-rs-get-restful-web-services/
- https://stackoverflow.com/questions/38032635/pass-multiple-parameters-to-rest-api-spring
- https://spring.io/guides/gs/rest-service/
- https://www.baeldung.com/gradle-run-java-main
- https://docs.gradle.org/current/userguide/application_plugin.html
- https://restfulapi.net/http-status-codes/
- https://pt.stackoverflow.com/questions/25495
- https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationContext.html
- https://www.codota.com/code/java/methods/org.springframework.context.ApplicationContext/getBeanNamesForType
- https://spring.io/guides/gs/testing-web/
- https://rest-assured.io/
- https://github.com/rest-assured/rest-assured
- https://www.alura.com.br/conteudo/rest-assured
- https://techbeacon.com/app-dev-testing/how-perform-api-testing-rest-assured