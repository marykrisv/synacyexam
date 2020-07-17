package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.interfaces.ThreeOfAKind;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.DeckByRank;

import java.util.ArrayList;
import java.util.List;

public class ThreeOfAKindFactory extends HandFactory implements ThreeOfAKind {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    private int SAME_RANK_SIZE = 3;

    @Override
    public boolean check() {
        return this.checkThreeOfAKind();
    }

    @Override
    public void populateCards() {
        for (DeckByRank deckByRank: super.getGroupedDeckByRank()) {
            CardRank cardRank = deckByRank.getCardRank();
            List<CardSuit> cardSuits = deckByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_SIZE && threeOfAKindCards.isEmpty()) {
                populateThreeOfAKindCards(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(otherCards);
        otherCards = CardUtil.maxOutCardsOnHand(threeOfAKindCards, otherCards);
    }

    @Override
    public void groupDeck() {
        super.groupDeckByRank();
    }

    @Override
    public void initializeCards() {
        threeOfAKindCards = new ArrayList<>();
        otherCards = new ArrayList<>();
    }

    public void populateThreeOfAKindCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (threeOfAKindCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                threeOfAKindCards.add(new Card(cardRank, cardSuit));
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
    public boolean checkThreeOfAKind() {
        for (DeckByRank deckByRank : super.getGroupedDeckByRank()) {
            if (deckByRank.getCardSuits().size() == SAME_RANK_SIZE) {
                return true;
            }
        }

        return false;
    }

    public List<Card> getThreeOfAKindCards() {
        return threeOfAKindCards;
    }

    public void setThreeOfAKindCards(List<Card> threeOfAKindCards) {
        this.threeOfAKindCards = threeOfAKindCards;
    }

    public List<Card> getOtherCards() {
        return otherCards;
    }

    public void setOtherCards(List<Card> otherCards) {
        this.otherCards = otherCards;
    }
}
