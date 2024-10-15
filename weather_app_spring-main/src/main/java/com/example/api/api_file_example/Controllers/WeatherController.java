package com.example.api.api_file_example.Controllers;

import com.example.api.api_file_example.Models.WeatherQuery;
import com.example.api.api_file_example.Services.ApiServiceWeather;
import com.example.api.api_file_example.Services.WeatherHistoryService;
import com.example.api.api_file_example.Models.WeatherResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WeatherController {

    private final ApiServiceWeather apiServiceWeather;
    private final WeatherHistoryService weatherHistoryService;

    // Construtor com injeção de dependência
    public WeatherController(ApiServiceWeather apiServiceWeather, WeatherHistoryService weatherHistoryService) {
        this.apiServiceWeather = apiServiceWeather;
        this.weatherHistoryService = weatherHistoryService; // Inicializa o serviço de histórico
    }

    @GetMapping("/weather")
    public String showMain(){
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/history")
    public String getHistory(Model model) {
        List<WeatherQuery> history = weatherHistoryService.loadWeatherHistory();
        model.addAttribute("history", history); // Adiciona o histórico ao modelo
        return "history";
    }

    private static final String DIRECTORY_PATH = "src/main/resources/weather_history/";

    @PostMapping("/weather")
    public String getWeather(@RequestParam Double latitude, @RequestParam Double longitude, Model model) {
        try {
            WeatherResponse weatherResponse = apiServiceWeather.getWeather(latitude, longitude);
            model.addAttribute("hourly", weatherResponse.getHourly());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "main";
    }
}
