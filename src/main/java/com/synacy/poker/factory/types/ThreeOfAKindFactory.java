package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.ThreeOfAKind;
import com.synacy.poker.utils.CardUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreeOfAKindFactory extends HandFactory implements ThreeOfAKind {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    @Override
    public boolean check() {
        CardUtil.sortCardsDesc(otherCards);
        return this.checkThreeOfAKind();
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

    @Override
    public boolean checkThreeOfAKind() {
        boolean ifThreeOfAKind = false;
        Map<CardRank, List<CardSuit>> groupedDeck = this.groupDeckByRank();

        for (Map.Entry<CardRank, List<CardSuit>> entry : groupedDeck.entrySet()) {
            CardRank cardRank = entry.getKey();
            List<CardSuit> cardSuits = entry.getValue();
            if (cardSuits.size() == 3) {
                ifThreeOfAKind = true;
                populateThreeOfAKindCards(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        return ifThreeOfAKind;
    }
}
