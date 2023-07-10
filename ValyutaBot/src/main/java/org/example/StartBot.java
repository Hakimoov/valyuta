package org.example;

import org.example.services.ValyutaService;
import org.example.services.ValyutaServiceImpl;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class StartBot {
    public static void main(String[] args) {


        try {
            ValyutaService valyutaService = new ValyutaServiceImpl();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            ValyutaBot valyutaBot = new ValyutaBot("6057757316:AAEw-q6gsh-b_wc3dQQpRJEXfH0OHUpK7yQ", valyutaService);
            telegramBotsApi.registerBot(valyutaBot);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}