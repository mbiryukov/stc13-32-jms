package ru.innopolis.stc13.jms;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.innopolis.stc13.jms.request.Item;
import ru.innopolis.stc13.jms.request.ItemRequest;

import javax.jms.JMSException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        try {
            Scanner scanner = new Scanner(System.in);
            String message;
            while ((message = scanner.nextLine()) != null) {
                ItemRequest itemRequest = new ItemRequest("byCategory", 1);
                sender.sendMessage(itemRequest);
                String answer = receiver.receiveMessage();
                Type listType = new TypeToken<ArrayList<Item>>() {
                }.getType();
                List<Item> result = new Gson().fromJson(answer, listType);
                System.out.println(result);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
