package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.interfaces.Straight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StraightFactory extends HandFactory implements Straight {
    private List<Card> cards;
    private int CARD_INDEX_INTERVAL = 1;
    private int STRAIGHT_CARD_SIZE = 5;
    private int SECOND_TO_THE_LAST_CARD_SIZE = 4;
    private int LAST_INDEX;

    @Override
    public boolean check() {
        List<CardRank> cardRanks = new ArrayList<>();

        //get rank of the card
        for (Card card: cards) {
            cardRanks.add(card.getRank());
        }

        return checkStraight(cardRanks);
    }

    @Override
    public void populateCards() {
        List<Card> straightCards = new ArrayList<>();;
        int i, j;
        int sizeOfCard = cards.size();
        LAST_INDEX = sizeOfCard-1;

        Card prevCard, nextCard;

        for (i = 0; i < sizeOfCard && straightCards.size() != 5; i++) {
            straightCards = new ArrayList<>();
            for (j = i+1; j < sizeOfCard; j++) {
                prevCard = cards.get(j-1);
                nextCard = cards.get(j);
                if (prevCard.getRank().ordinal()-1 != nextCard.getRank().ordinal()) {
                    break;
                } else {
                    straightCards.add(prevCard);
                    if (straightCards.size() == 4) {
                        //add the last one
                        straightCards.add(nextCard);
                        break;
                    }
                    if (j == LAST_INDEX) {
                        if (cardContainsAce() && nextCard.getRank() == CardRank.TWO) {
                            // add to and ace
                            straightCards.add(nextCard);
                            straightCards.add(getAce());
                            break;
                        }
                    }
                }
            }
        }

        super.setCards(straightCards);
    }

    @Override
    public void groupDeck() {
        // do nothing
    }

    private boolean cardContainsAce() {
        List<Card> aces = Arrays.asList(
            new Card(CardRank.ACE, CardSuit.CLUBS),
            new Card(CardRank.ACE, CardSuit.SPADES),
            new Card(CardRank.ACE, CardSuit.HEARTS),
            new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        for (Card ace: aces
             ) {
            if (cards.contains(ace)) {
                return true;
            }
        }

        return false;
    }

    private Card getAce () {
        List<Card> aces = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        for (Card ace: aces
        ) {
            if (cards.contains(ace)) {
                return ace;
            }
        }

        return null;
    }

    @Override
    public void initializeCards() {
        this.cards = super.getCards();
    }

    @Override
    public boolean checkStraight(List<CardRank> cardRanks) {
        int straightCardCtr = 0;
        int i, j;
        int sizeOfCard = cardRanks.size();
        LAST_INDEX = sizeOfCard-1;

        CardRank prevCard, nextCard;

        for (i = 0; i < sizeOfCard && straightCardCtr != 5; i++) {
            straightCardCtr = 0;
            for (j = i+1; j < sizeOfCard; j++) {
                prevCard = cardRanks.get(j-1);
                nextCard = cardRanks.get(j);
                if (prevCard.ordinal()-1 != nextCard.ordinal()) {
                    break;
                } else {
                    straightCardCtr++;
                    if (straightCardCtr == 4) {
                        //add the last one
                        straightCardCtr++;
                    }
                    if (j == LAST_INDEX) {
                        if (cardContainsAce() && nextCard == CardRank.TWO) {
                            // add to and ace
                            straightCardCtr+=2;
                        }
                    }
                }
            }
        }

        if (straightCardCtr == 5) {
            return true;
        } else {
            return false;
        }
    }
}
