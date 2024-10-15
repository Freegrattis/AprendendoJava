package com.example.api.api_file_example.Services;

import com.example.api.api_file_example.Models.LocalDateTimeAdapter;
import com.example.api.api_file_example.Models.WeatherQuery;
import com.example.api.api_file_example.Models.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WeatherHistoryService {

    private static final String DIRECTORY_PATH = "src/main/resources/weather_history/";
    private static final String GENERAL_HISTORY_FILE_PATH = DIRECTORY_PATH + "weather_history.json";

    // Registrar o TypeAdapter para LocalDateTime
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    // Método para garantir que o diretório existe
    private void ensureDirectoryExists() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria o diretório e quaisquer diretórios pai necessários
        }
    }

    //* Método para salvar uma nova consulta em um arquivo JSON
    public void saveWeatherQuery(Double latitude, Double longitude, WeatherResponse weatherResponse) {
        ensureDirectoryExists();
        List<WeatherQuery> history = loadWeatherHistory(); // Carrega o histórico geral
        // Adiciona a nova consulta ao histórico geral
        WeatherQuery newQuery = new WeatherQuery(LocalDateTime.now(), latitude, longitude, weatherResponse);
        history.add(newQuery);
        saveGeneralHistory(history); // Salva o histórico geral

        // Salva o histórico individual
        saveIndividualHistory(newQuery);
    }

    //* Carrega o histórico do arquivo JSON
    public List<WeatherQuery> loadWeatherHistory() {
        File file = new File(GENERAL_HISTORY_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();  // Retorna uma lista vazia se o arquivo não existir
        }

        try (FileReader reader = new FileReader(file)) {
            Type historyType = new TypeToken<ArrayList<WeatherQuery>>() {}.getType();
            return gson.fromJson(reader, historyType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

    //* Salva o histórico geral no arquivo JSON
    private void saveGeneralHistory(List<WeatherQuery> history) {
        try (FileWriter writer = new FileWriter(GENERAL_HISTORY_FILE_PATH)) {
            gson.toJson(history, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //* Salva o histórico individual em um arquivo JSON separado
    private void saveIndividualHistory(WeatherQuery query) {
        String individualFileName = String.format("%s_%s_%s.json",
                query.getDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")),
                query.getLatitude(),
                query.getLongitude(),
                UUID.randomUUID().toString());
        File individualFile = new File(DIRECTORY_PATH + individualFileName);

        try (FileWriter writer = new FileWriter(individualFile)) {
            gson.toJson(query, writer);
            System.out.println("Arquivo salvo: " + individualFile.getAbsolutePath()); // Log do caminho do arquivo salvo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WeatherQuery getQueryById(String id) {
        List<WeatherQuery> history = loadWeatherHistory(); // Carrega o histórico geral
        for (WeatherQuery query : history) {

            if (query.getId().equals(id)) {
                return query;
            }
        }
        return null;
    }
}
