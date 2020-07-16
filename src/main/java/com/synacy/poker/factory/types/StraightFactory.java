package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StraightFactory extends HandFactory {
    private List<Card> cards;
    private int CARD_INDEX_INTERVAL = 1;
    private int STRAIGHT_CARD_SIZE = 5;
    private int SECOND_TO_THE_LAST_CARD_SIZE = 4;
    private int LAST_INDEX;

    @Override
    public boolean check() {
        int straightCardCtr = 0;

        int sizeOfCard = cards.size();
        LAST_INDEX = sizeOfCard-1;
        int i, j;
        Card nextCard, prevCard;
        for (i = 0; i < sizeOfCard; i++) {
            straightCardCtr = 0;
            for (j = i; j < sizeOfCard && straightCardCtr != 5; j++) {
                if (j == LAST_INDEX) {
                    straightCardCtr++;
                } else if (straightCardCtr == 4) {
                    prevCard = cards.get(j-1);
                    nextCard = cards.get(j);
                    if (prevCard.getRank().ordinal()-CARD_INDEX_INTERVAL != nextCard.getRank().ordinal()
                            && (prevCard.getRank() != CardRank.ACE && nextCard.getRank() != CardRank.KING)) {
                        break;
                    } else {
                        straightCardCtr++;
                    }
                } else {
                    prevCard = cards.get(j);
                    nextCard = cards.get(j+CARD_INDEX_INTERVAL);
                    if (prevCard.getRank().ordinal()-CARD_INDEX_INTERVAL != nextCard.getRank().ordinal()
                            && (prevCard.getRank() != CardRank.ACE && nextCard.getRank() != CardRank.KING)) {
                        break;
                    } else {
                        if (prevCard.getRank() == CardRank.ACE && nextCard.getRank() != CardRank.KING) {
                            // do nothing
                        } else {
                            straightCardCtr++;
                        }
                    }
                }
            }
            if (straightCardCtr == STRAIGHT_CARD_SIZE) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void populateCards() {
        List<Card> straightCard = null;

        int sizeOfCard = cards.size();
        int i, j;
        LAST_INDEX = sizeOfCard-1;
        Card nextCard, prevCard;
        for (i = 0; i < sizeOfCard; i++) {
            straightCard = new ArrayList<>();
            for (j = i; j < sizeOfCard && straightCard.size() != 5; j++) {
                if (j == LAST_INDEX) {
                    if (cards.get(j).getRank() == CardRank.TWO) {
                        straightCard.add(getAce());
                    } else {
                        straightCard.add(cards.get(j));
                    }

                } else if (straightCard.size() == 4) {
                    prevCard = cards.get(j-1);
                    nextCard = cards.get(j);
                    if (prevCard.getRank().ordinal()-CARD_INDEX_INTERVAL != nextCard.getRank().ordinal()
                            && (prevCard.getRank() != CardRank.ACE && nextCard.getRank() != CardRank.KING)) {
                        break;
                    } else {
                        straightCard.add(nextCard);
                    }
                } else {
                    prevCard = cards.get(j);
                    nextCard = cards.get(j+CARD_INDEX_INTERVAL);
                    if (prevCard.getRank().ordinal()-CARD_INDEX_INTERVAL != nextCard.getRank().ordinal()
                            && (prevCard.getRank() != CardRank.ACE && nextCard.getRank() != CardRank.KING)) {
                        break;
                    } else {
                        if (prevCard.getRank() == CardRank.ACE && nextCard.getRank() != CardRank.KING) {
                            // do nothing
                        } else {
                            straightCard.add(prevCard);
                        }
                    }
                }
            }
            if (straightCard.size() == STRAIGHT_CARD_SIZE) {
                super.setCards(straightCard);
                break;
            }
        }
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
}
