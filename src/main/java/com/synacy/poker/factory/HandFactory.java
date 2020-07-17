package com.synacy.poker.factory;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.PackByRank;
import com.synacy.poker.utils.PackBySuit;

import java.util.*;

public abstract class HandFactory {
    private List<Card> cards;
    private List<PackByRank> groupedPackByRank;
    private List<PackBySuit> groupedPackBySuit;

    public abstract void initializeCards();

    public abstract boolean check();

    public abstract void populateCards();

    public abstract void groupDeck();

    public HandFactory() {}

    public HandFactory create () {
        this.initializeCards();
        CardUtil.sortCardsDesc(cards);
        this.groupDeck();
        if (this.check()) {
            populateCards();
            return this;
        } else {
            return null;
        }
    }

    public void groupPackBySuit () {
        groupedPackBySuit = new ArrayList<>();

        int cardSuitInd;

        for (Card card: cards) {
            cardSuitInd = packBySuitContains(card.getSuit());
            if (cardSuitInd != -1) {
                groupedPackBySuit.get(cardSuitInd).addCardRankToList(card.getRank());
            } else {
                PackBySuit packBySuit = new PackBySuit();
                packBySuit.setCardSuit(card.getSuit());
                packBySuit.addCardRankToList(card.getRank());

                groupedPackBySuit.add(packBySuit);
            }
        }
    }

    private int packBySuitContains(CardSuit cardSuit) {
        int i, cardInd = -1;
        for (i=0; i < groupedPackBySuit.size(); i++) {
            if (groupedPackBySuit.get(i).getCardSuit().equals(cardSuit)) {
                cardInd = i;
                break;
            }
        }

        return cardInd;
    }

    public void groupPackByRank () {
        groupedPackByRank = new ArrayList<>();

        int cardRankInd;

        for (Card card: cards) {
            cardRankInd = packByRankContains(card.getRank());
            if (cardRankInd != -1) {
                groupedPackByRank.get(cardRankInd).addCardSuitToList(card.getSuit());
            } else {
                PackByRank packByRank = new PackByRank();
                packByRank.setCardRank(card.getRank());
                packByRank.addCardSuitToList(card.getSuit());

                groupedPackByRank.add(packByRank);
            }
        }
    }

    private int packByRankContains(CardRank cardRank) {
        int i, cardInd = -1;
        for (i=0; i < groupedPackByRank.size(); i++) {
            if (groupedPackByRank.get(i).getCardRank().equals(cardRank)) {
                cardInd = i;
                break;
            }
        }

        return cardInd;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<PackByRank> getGroupedPackByRank() {
        return groupedPackByRank;
    }

    public void setGroupedPackByRank(List<PackByRank> groupedPackByRank) {
        this.groupedPackByRank = groupedPackByRank;
    }

    public List<PackBySuit> getGroupedPackBySuit() {
        return groupedPackBySuit;
    }

    public void setGroupedPackBySuit(List<PackBySuit> groupedPackBySuit) {
        this.groupedPackBySuit = groupedPackBySuit;
    }
}
