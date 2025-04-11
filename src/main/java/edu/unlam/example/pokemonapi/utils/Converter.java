package edu.unlam.example.pokemonapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.unlam.example.pokemonapi.domain.PokemonEntity;
import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.PokemonResponse;
import edu.unlam.example.pokemonapi.dto.TrainerResponse;
import edu.unlam.example.pokemonapi.exceptions.ConverterException;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;

@UtilityClass
public class Converter {

    public static TrainerResponse convertToResponse(TrainerEntity trainerEntity) {
        TrainerResponse trainerResponse = new TrainerResponse();
        trainerResponse.setId(trainerEntity.getId());
        trainerResponse.setName(trainerEntity.getName());
        trainerResponse.setPokemons(convertToPokemonResponse(trainerEntity.getPokemons()));
        return trainerResponse;
    }

    private static List<PokemonResponse> convertToPokemonResponse(Set<PokemonEntity> pokemonEntityList) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(pokemonEntityList, new TypeReference<>() {
        });
    }

    public static List<TrainerResponse> convertToResponseList(List<TrainerEntity> trainerEntityList) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(trainerEntityList, new TypeReference<>() {
        });
    }

    public String convertToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ConverterException(e.getMessage());
        }
    }

}
