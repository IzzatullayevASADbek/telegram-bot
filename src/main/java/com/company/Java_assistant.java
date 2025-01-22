package com.company;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java_assistant extends TelegramLongPollingBot {


    HttpClient client = HttpClient.newHttpClient();
    String API_KEY = "AIzaSyCLsc1po4eHtc688qfulsXAPhdO3UA0fd4";
    public Java_assistant() {
        super("7595348990:AAG_b2YsIt23Q-vnxnGijO89IY25GwE9mJo");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String userInput = message.getText();

        String json = "{\n" +
                "   \"contents\":[\n" +
                "      {\n" +
                "         \"parts\":[\n" +
                "            {\n" +
                "               \"text\": \"" + userInput +  "give only java lesson and speak only Uzbek \"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" +API_KEY ))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        JSONArray candidatesArray = jsonObject.getJSONArray("candidates");
        JSONObject firstCandidate = candidatesArray.getJSONObject(0);
        JSONObject contentObject = firstCandidate.getJSONObject("content");
        JSONArray partsArray = contentObject.getJSONArray("parts");
        String text = partsArray.getJSONObject(0).getString("text");

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        execute(sendMessage);


    }

    @Override
    public String getBotUsername() {
        return "@inagamovbaxrom1222Bot";
    }
}



