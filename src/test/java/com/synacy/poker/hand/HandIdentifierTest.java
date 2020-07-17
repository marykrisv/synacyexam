package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HandIdentifierTest {

    @Test
    public void initializeMap () {
        HandIdentifier handIdentifier = new HandIdentifier();

        assertNotNull(handIdentifier.getHandFactoryTypes());
    }

    @Test
    public void identifyHand_highCard() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.CLUBS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.HIGH_CARD);
        assertEquals("A,9,7,5,4", hand.toString());
    }

    @Test
    public void identifyHand_onePairCard() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.ONE_PAIR);
        assertEquals("One Pair (5) - A,7,4 High", hand.toString());
    }

    @Test
    public void identifyHand_twoPairCard() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.EIGHT, CardSuit.SPADES),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.TWO_PAIR);
        assertEquals("Two Pair (9,8) - 5 High", hand.toString());
    }

    @Test
    public void identifyHand_threeOfAKind() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.THREE_OF_A_KIND);
        assertEquals("Trips (4) - A,9 High", hand.toString());
    }

    @Test
    public void identifyHand_straight() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.SPADES),
                new Card(CardRank.THREE, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.STRAIGHT);
        assertEquals("Straight (5 High)", hand.toString());
    }

    @Test
    public void identifyHand_flush() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.FLUSH);
        assertEquals("Flush (A High)", hand.toString());
    }

    @Test
    public void identifyHand_fullHouse() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.KING, CardSuit.SPADES),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.FULL_HOUSE);
        assertEquals("Full House (2,K)", hand.toString());
    }

    @Test
    public void identifyHand_fourOfAKind() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.KING, CardSuit.SPADES),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.FOUR_OF_A_KIND);
        assertEquals("Quads (K) - 3 High", hand.toString());
    }

    @Test
    public void identifyHand_straightFlush() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.JACK, CardSuit.DIAMONDS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.STRAIGHT_FLUSH);
        assertEquals("Straight Flush (K High)", hand.toString());
    }

    @Test
    public void identifyHand_royalFlush() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.JACK, CardSuit.DIAMONDS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);

        assertEquals(hand.getHandType(), HandType.STRAIGHT_FLUSH);
        assertEquals("Royal Flush", hand.toString());
    }
}
