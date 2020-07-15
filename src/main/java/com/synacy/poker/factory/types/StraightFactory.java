package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.HighCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StraightFactory extends HandFactory {
    private List<Card> cards;

    @Override
    public boolean check() {
        List<Card> straightCard = null;
        boolean isStraight = false;

        int sizeOfCard = cards.size();
        int i, j;
        Card nextCard, prevCard;
        for (i = 0; i < sizeOfCard; i++) {
            straightCard = new ArrayList<>();
            for (j = i; j < sizeOfCard && straightCard.size() != 5; j++) {
                prevCard = cards.get(j);
                if (straightCard.size() == 4 || j == sizeOfCard-1) { //if last index of straight card
                    straightCard.add(cards.get(j));
                    if (cardContainsAce()) {
                        if (prevCard.getRank() == CardRank.TWO) {
                            straightCard.add(getAce());
                        }
                    }
                } else {
                    nextCard = cards.get(j+1);
                    if (prevCard.getRank().ordinal()-1 != nextCard.getRank().ordinal()
                        && (prevCard.getRank() != CardRank.ACE && nextCard.getRank() != CardRank.KING)) {
                        break;
                    } else {
                        if (prevCard.getRank() == CardRank.ACE && nextCard.getRank() != CardRank.KING) {

                        } else {
                            straightCard.add(cards.get(j));
                        }
                    }
                }

            }
            if (straightCard.size() == 5) {
                isStraight = true;
                super.setCards(straightCard);
                break;
            }
        }

        return isStraight;
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
