package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OnePairTest {

    @Test
    public void toString_withOnePairAndKickers() {
        List<Card> pair = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.HEARTS)
        );
        List<Card> kickers = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );

        OnePair onePair = new OnePair(pair, kickers);

        assertEquals("One Pair (2) - A,K,Q High", onePair.toString());
    }

    @Test
    public void toString_withOnePairNoKickers() {
        List<Card> pair = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.HEARTS)
        );

        OnePair onePair = new OnePair(pair, Collections.emptyList());

        assertEquals("One Pair (2)", onePair.toString());
    }

}