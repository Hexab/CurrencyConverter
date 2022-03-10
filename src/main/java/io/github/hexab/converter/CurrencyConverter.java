package io.github.hexab.converter;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("What currency do you want to convert? (Abbreviations only)");
        String currency = scanner.next();

        System.out.println("What is the other currency do you want to convert?");
        String currencyTwo = scanner.next();

        System.out.println("What is the amount you want to convert?");
        double amount = scanner.nextDouble();
        convert(currency, amount, currencyTwo);
    }


    public static void convert(String currency, double amount, String currencyTwo) {

        String link = "https://v6.exchangerate-api.com/v6/629429c3e4f5ac7aef4c480c/pair/";
        JSONObject jsonResponse = new JSONObject();

        try{
            jsonResponse = Unirest.get(link + currency + "/" + currencyTwo + "/" + amount).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        String lastUpdated = jsonResponse.getString("time_last_update_utc");
        String nextUpdate = jsonResponse.getString("time_next_update_utc");
        String convert = String.valueOf(jsonResponse.getInt("conversion_result"));

        System.out.println("\n" + currency + " to " + currencyTwo + " = " + convert);
        System.out.println("\n last updated (UTC)\n" + lastUpdated);
        System.out.println("\n next update (UTC)\n" + nextUpdate);
    }

}
