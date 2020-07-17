package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.DeckByRank;

import java.util.ArrayList;
import java.util.List;

public class FourOfAKindFactory extends HandFactory {
    private List<Card> fourOfAKindCards;
    private List<Card> otherCards;

    int SAME_RANK_SIZE = 4;

    @Override
    public boolean check() {
        for (DeckByRank deckByRank : super.getGroupedDeckByRank()) {
            if (deckByRank.getCardSuits().size() == SAME_RANK_SIZE) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void populateCards() {
        for (DeckByRank deckByRank: super.getGroupedDeckByRank()) {
            CardRank cardRank = deckByRank.getCardRank();
            List<CardSuit> cardSuits = deckByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_SIZE && fourOfAKindCards.isEmpty()) {
                populateFourOfAKind(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(otherCards);
        otherCards = CardUtil.maxOutCardsOnHand(fourOfAKindCards, otherCards);
    }

    public void populateFourOfAKind(CardRank cardRank, List<CardSuit> cardSuits) {
        if (fourOfAKindCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                fourOfAKindCards.add(new Card(cardRank, cardSuit));
            }
        }
    }
    public void populateOtherCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (otherCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                otherCards.add(new Card(cardRank, cardSuit));
            }
        }
    }


    @Override
    public void groupDeck() {
        super.groupDeckByRank();
    }

    @Override
    public void initializeCards() {
        fourOfAKindCards = new ArrayList<>();
        otherCards = new ArrayList<>();
    }

    public List<Card> getFourOfAKindCards() {
        return fourOfAKindCards;
    }

    public void setFourOfAKindCards(List<Card> fourOfAKindCards) {
        this.fourOfAKindCards = fourOfAKindCards;
    }

    public List<Card> getOtherCards() {
        return otherCards;
    }

    public void setOtherCards(List<Card> otherCards) {
        this.otherCards = otherCards;
    }
}
