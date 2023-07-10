package org.example;


import org.example.models.Valyuta;
import org.example.services.ValyutaService;
import org.example.services.ValyutaServiceImpl;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ValyutaBot extends TelegramLongPollingBot {


  ValyutaService valyuta = new ValyutaServiceImpl();

    public ValyutaBot(String botToken, ValyutaService valyuta) {
        super(botToken);
        this.valyuta = valyuta;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){

            SendMessage sendMessage = new SendMessage();

            String text = update.getMessage().getText();
            String usurName = update.getMessage().getChat().getFirstName();

            Long chatId = update.getMessage().getChatId();
            sendMessage.setChatId(chatId);

            printTerminal(text,usurName);

            switch (text){
                case "/start" ->{
                    statMenu(text,usurName,sendMessage);

                }case "Uzbek\uD83C\uDDFA\uD83C\uDDFF"->{
                    uzMenu(text,sendMessage);
                }case "\uD83D\uDD19Go back" ->{
                    statMenu(text,usurName,sendMessage);
                }case "Kurs bilish\uD83D\uDCDD\uD83D\uDCB0" ->{
                    sendMessage.setText(valyuta.getValyuta().get(0).getCcy()+ "\uD83C\uDFDB\uD83C\uDDFA\uD83C\uDDF8 \n" +
                    "Pul birligi - " + valyuta.getValyuta().get(0).getCcyNm_UZ()+"\uD83D\uDCB0 \n" +
                            valyuta.getValyuta().get(0).getNominal() + "->" + valyuta.getValyuta().get(0).getRate() +
                            "SO'M\uD83C\uDDFA\uD83C\uDDFF \n" + "Yangilangan vaqt:" +
                            valyuta.getValyuta().get(0).getDate() + "\n----------------------------------------------" +
                            valyuta.getValyuta().get(2).getCcy()+ "\uD83C\uDDF7\uD83C\uDDFA \n" +
                            "Pul birligi - " + valyuta.getValyuta().get(2).getCcyNm_UZ()+" \uD83C\uDDF7\uD83C\uDDFA \n" +
                            valyuta.getValyuta().get(2).getNominal() + "->" + valyuta.getValyuta().get(2).getRate() +
                            "SO'M\uD83C\uDDFA\uD83C\uDDFF \n" + "Yangilangan vaqt:" +
                            valyuta.getValyuta().get(2).getDate() + "\n" + valyuta.getValyuta().get(0).getDate() +
                            "\n----------------------------------------------" +
                            valyuta.getValyuta().get(35).getCcy()+ "\uD83C\uDDF0\uD83C\uDDF7 \n" +
                            "Pul birligi - " + valyuta.getValyuta().get(35).getCcyNm_UZ()+"\uD83C\uDDF0\uD83C\uDDF7 \n" +
                            valyuta.getValyuta().get(35).getNominal() + "->" + valyuta.getValyuta().get(35).getRate() +
                            "SO'M\uD83C\uDDFA\uD83C\uDDFF \n" + "Yangilangan vaqt:" +
                            valyuta.getValyuta().get(35).getDate());



                } case "Bot haqida\uD83E\uDD16\uD83E\uDDFE" ->{
                    sendMessage.setText("Bu bot Internetdan hozirgi kurs valyutani aiqlab beruvchi botdir! \uD83D\uDC81 ℹ\uFE0F");
                }


                default -> {
                    sendMessage.setText("Ozgina kutib turing...");
                }
            }
            try {
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "testbot232_bot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    void printTerminal(String text, String userName){

        System.out.println(userName + " -> "+text);
    }

    private void statMenu(String text, String userName, SendMessage sendMessage ){
        sendMessage.setText("Assalomu aleykum\uD83D\uDC4B\n"+userName+
                "Valyuta botimizga Xush kelibsiz\uD83C\uDFDB");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        KeyboardButton uz = new KeyboardButton();
        uz.setText("Uzbek\uD83C\uDDFA\uD83C\uDDFF");

        KeyboardButton en = new KeyboardButton();
        en.setText("English\uD83C\uDDFA\uD83C\uDDF8");

        keyboardRow.add(uz);
        keyboardRow.add(en);

        buttons.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

    }

    private void uzMenu(String text, SendMessage sendMessage){
        sendMessage.setText("\uD83C\uDF10Valyuta hisoblagich botiga Xush kelibsiz. " +
                "Bot yordamida valyuta narxlarini bilishingiz mumkin♻️\uFE0F\uD83D\uDCB8");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        KeyboardButton kursInfo   =  new KeyboardButton();
        kursInfo.setText("Kurs bilish\uD83D\uDCDD\uD83D\uDCB0");

        KeyboardButton botInfo = new KeyboardButton();
        botInfo.setText("Bot haqida\uD83E\uDD16\uD83E\uDDFE");

        KeyboardButton back  = new KeyboardButton();
        back.setText("\uD83D\uDD19Go back");

        keyboardRow.add(kursInfo);
        keyboardRow.add(botInfo);
        keyboardRow.add(back);


        buttons.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

    }
}