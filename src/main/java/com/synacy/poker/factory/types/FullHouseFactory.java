package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.interfaces.Pair;
import com.synacy.poker.utils.PackByRank;

import java.util.ArrayList;
import java.util.List;

public class FullHouseFactory extends ThreeOfAKindFactory implements Pair {
    private List<Card> pairCards;

    private int SAME_RANK_TRIP_SIZE = 3;
    private int SAME_RANK_PAIR_SIZE = 2;
    private int TWO_PAIR = 2;

    @Override
    public void initializeCards() {
        super.initializeCards();

        pairCards = new ArrayList<>();
    }

    @Override
    public boolean check() {
        boolean isThreeOfAKind = super.checkThreeOfAKind();
        boolean isPair = checkPair();
        if (isThreeOfAKind && isPair) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void populateCards() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            CardRank cardRank = packByRank.getCardRank();
            List<CardSuit> cardSuits = packByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_TRIP_SIZE && getThreeOfAKindCards().isEmpty()) {
                super.populateThreeOfAKindCards(cardRank, cardSuits);
            } else if (cardSuits.size() >= SAME_RANK_PAIR_SIZE){
                this.populatePairCards(cardRank, cardSuits);
            }
        }
    }

    @Override
    public void groupDeck() {
        super.groupPackByRank();
    }

    public List<Card> getPairCards() {
        return pairCards;
    }

    public void setPairCards(List<Card> pairCards) {
        this.pairCards = pairCards;
    }

    @Override
    public void populatePairCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (pairCards != null) {
            for (int i = 0; i < SAME_RANK_PAIR_SIZE; i++) {
                pairCards.add(new Card(cardRank, cardSuits.get(i)));
            }
        }
    }

    @Override
    public boolean checkPair() {
        int ctr = 0;

        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            if (packByRank.getCardSuits().size() >= SAME_RANK_PAIR_SIZE) {
                ctr++;
            }
        }

        if (ctr == TWO_PAIR) {
            return true;
        }

        return false;
    }
}
