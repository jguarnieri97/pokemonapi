package edu.unlam.example.pokemonapi.client.impl;

import edu.unlam.example.pokemonapi.client.PokeapiClient;
import edu.unlam.example.pokemonapi.dto.PokemonResponse;
import edu.unlam.example.pokemonapi.exceptions.PokeapiClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokeapiClientImpl implements PokeapiClient {

    private final WebClient webClient;

    @Value("${pokeapi.host}")
    private String host;

    @Override
    public PokemonResponse getPokemonByName(String name) {
        return webClient.get()
                .uri(host + name)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(error -> {
                                    log.error("Error del cliente externo Pokemon API: {}", error);
                                    return Mono.error(new PokeapiClientException(clientResponse.statusCode().value(), error));
                                } ))
                .bodyToMono(PokemonResponse.class)
                .block();

    }


}
