package com.company;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

        public class Main {
            public static void main(String[] args) throws TelegramApiException {

                Java_assistant java = new Java_assistant();
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(java);
                System.out.println("ishlavoti");
            }
}