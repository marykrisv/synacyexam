package com.synacy.poker.utils;

import com.synacy.poker.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardUtil {
    private static int MAX_CARD_ON_HAND = 5;

    public static void sortCardAsc(List<Card>... toSortCards) {
        for (int i =0; i < toSortCards.length; i++) {
            Collections.sort(toSortCards[i], Comparator.comparing(Card::getRank));
        }
    }


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
}
