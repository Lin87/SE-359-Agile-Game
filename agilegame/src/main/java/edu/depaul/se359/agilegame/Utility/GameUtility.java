package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Game;
import edu.depaul.se359.agilegame.Player.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

//Utility class responsible for providing utility functions for agile game such as shuffling cards in deck.
public final class GameUtility
{
	//Shuffles card for a given deck of cards,uses Knuth algorithm.
	public static void shuffleCards(Card[] deck)
	{
		int deckSize = deck.length;
		for(int i=0; i < deckSize; i++)
		{
			int randomIndex = i + (int)(Math.random() *(deckSize-i));
			Card temp = deck[i];
			deck[i] = deck[randomIndex];
			deck[randomIndex] = temp;
		}
	}

	public static void parseJSONtoDecks() throws IOException, ParseException {

	    // JSON.Simple lib
        JSONParser parser = new JSONParser();

        // get the current project directory
        // System.out.println("Working Directory = " +
        // System.getProperty("user.dir"));

        File folder = new File(System.getProperty("user.dir") + "/agilegame/src/main/resources/decks");
        File[] listOfFiles = folder.listFiles();

        // the current directory should contain the JSON files
        assert listOfFiles != null;

        for (File file : listOfFiles) {
            if (file.isFile())
            {
                parseJSONtoDecks(parser, file.getName());
            }
        }
    }

    private static void parseJSONtoDecks(JSONParser parser, String fileName) throws IOException, ParseException {

	    // grab whole file
        JSONObject jsonObject = (JSONObject)parser.parse(
                new InputStreamReader(Game.class.
                        getResourceAsStream("/decks/" + fileName)));

        JSONArray jsonArray = (JSONArray) jsonObject.get("Cards");
        JSONObject currObj = (JSONObject) jsonArray.get(0);
        String cardType = (String) currObj.get("Type");
        //System.out.println(cardType);

        // loop file with array
        for (int i = 1; i < jsonArray.size(); ++i)
        {
            currObj = (JSONObject) jsonArray.get(i);
            String cardID = (String) currObj.get("ID");
            String cardRole = (String) currObj.get("Role");
            String cardContent = (String) currObj.get("Content");
            String cardDescription = (String) currObj.get("Description");
            String cardEffect = (String) currObj.get("Effect");
            String cardAmount = (String) currObj.get("Amount");

            Deck.AddCard(cardType,
                         cardID, cardRole, cardContent,
                         cardDescription, cardEffect, cardAmount);
        }
    }

    public static void doEffect(Card card, Team team)
    {
        String effectType = card.getEffect();
        String amount = card.getAmount();

        doEffect(effectType, amount, team);
    }

    private static void doEffect(String effectType, String amount, Team team)
    {
        String sign = amount.substring(0, 1);
        int score = Integer.parseInt(amount.substring(1));

        switch (effectType)
        {
            case "pointChange":
                team.setStoryPoint(sign, score);
                break;
        }
    }
}