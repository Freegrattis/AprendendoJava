package com.example.api.api_file_example.Services;

import com.example.api.api_file_example.Models.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceWeather {

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 2000;

    private final WeatherHistoryService weatherHistoryService;

    @Autowired
    public ApiServiceWeather(WeatherHistoryService weatherHistoryService) {
        this.weatherHistoryService = weatherHistoryService;
    }

    public WeatherResponse getWeather(Double latitude, Double longitude) {
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&hourly=temperature", latitude, longitude);
        RestTemplate restTemplate = new RestTemplate();
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                //* Faz a requisição para a API
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                //* Converte o JSON da resposta para o objeto WeatherResponse
                ObjectMapper objectMapper = new ObjectMapper();
                WeatherResponse weatherResponse = objectMapper.readValue(response.getBody(), WeatherResponse.class);

                //* Salva a consulta no histórico
                weatherHistoryService.saveWeatherQuery(latitude, longitude, weatherResponse);

                return weatherResponse;

            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Erro na solicitação: " + e.getStatusCode() + ". Verifique as coordenadas e tente novamente.");

            } catch (HttpServerErrorException e) {
                throw new RuntimeException("Erro no servidor da API: " + e.getStatusCode() + ". Tente novamente mais tarde.");

            } catch (ResourceAccessException e) {
                if (retries < MAX_RETRIES - 1) {
                    retries++;
                    System.out.println("Tentativa de reconexão #" + retries);
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw new RuntimeException("Erro de conectividade ou timeout. Tente novamente mais tarde.");
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro desconhecido: " + e.getMessage());
            }
        }
        throw new RuntimeException("Erro ao buscar dados do clima após várias tentativas. Verifique a conexão e tente novamente.");
    }
}
