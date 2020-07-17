package com.synacy.poker.utils;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardUtil {
    private static int MAX_CARD_ON_HAND = 5;

    public static void sortCardsDesc(List<Card>... toSortCards) {
        for (int i =0; i < toSortCards.length; i++) {
            Collections.sort(toSortCards[i], Comparator.comparing(Card::getRank).reversed());
        }
    }

    public static List<Card> maxOutCardsOnHand(List<Card> initialHandCards, List<Card> cardsToAppend) {
        List<Card> newCardsToAppend = new ArrayList<>();

        for (int i = 0; i < cardsToAppend.size() &&
                newCardsToAppend.size() < MAX_CARD_ON_HAND-initialHandCards.size(); i++) {
            newCardsToAppend.add(cardsToAppend.get(i));
        }

        return newCardsToAppend;
    }

    public static List<Card> maxOutCardsOnHand(List<Card> initialHandCards) {
        List<Card> newCardsToAppend = new ArrayList<>();

        for (int i = 0; i < initialHandCards.size() &&
                newCardsToAppend.size() < MAX_CARD_ON_HAND; i++) {
            newCardsToAppend.add(initialHandCards.get(i));
        }

        return newCardsToAppend;
    }

    public static List<Card> combineCards (List<Card>... cards) {
        List<Card> combinedCard = new ArrayList<>();

        for (int i =0; i < cards.length; i++) {
            combinedCard.addAll(cards[i]);
        }

        return combinedCard;
    }

    public static String getHighestCardToString (List<Card> cards, int numOfCards) {
        if (cards != null && !cards.isEmpty()) {
            List<CardRank> highest = new ArrayList<>();

            for (int i = 0; i < numOfCards; i++) {
                highest.add(cards.get(i).getRank());
            }

            return highest.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replaceAll(" ","");
        }

        return "";
    }

    public static  String getRankOfPack (List<Card> cards) {
        if (cards != null && !cards.isEmpty()) {
            return cards.get(0).getRank().toString();
        }

        return "";
    }
}
