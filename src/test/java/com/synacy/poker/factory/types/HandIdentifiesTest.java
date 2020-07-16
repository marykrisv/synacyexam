package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactoryType;
import com.synacy.poker.hand.HandIdentifier;
import com.synacy.poker.hand.HandType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandIdentifiesTest {

    private List<HandFactoryType> handFactoryTypes;

    private void initializeMap () {
        handFactoryTypes = new ArrayList<>();
        handFactoryTypes.add(new HandFactoryType(HandType.FULL_HOUSE, new FullHouseFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.FLUSH, new FlushFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.STRAIGHT, new StraightFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.THREE_OF_A_KIND, new ThreeOfAKindFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.TWO_PAIR, new TwoPairFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.ONE_PAIR, new OnePairFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.HIGH_CARD, new HighCardFactory()));
    }

    @Test
    public void identifyHand() {
        this.initializeMap();
        List<Card> playerCards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.SPADES)
        );

        List<Card> communityCards = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.HEARTS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.HEARTS),
                new Card(CardRank.FOUR, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        handIdentifier.identifyHand(playerCards, communityCards);
    }


    private List<Card> highCard() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.HEARTS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS)
        );
    }

    private List<Card> onePair() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );
    }

    private List<Card> twoPair() {
        return Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );
    }

    private List<Card> threeOfAKind() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.HEARTS)
        );
    }

    private List<Card> straight1() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.SPADES),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.HEARTS)
        );
    }

    private List<Card> straight2() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
    }

    private List<Card> flush() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS)
        );
    }

    private List<Card> fullHouse() {
        return Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );
    }
}
