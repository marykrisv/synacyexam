package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.HandFactoryType;
import com.synacy.poker.factory.types.*;
import com.synacy.poker.hand.types.*;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * A service that is to used to identify the {@link Hand} given the player's cards and the community
 * cards.
 */
@Component
public class HandIdentifier {

    List<HandFactoryType> handFactoryTypes;
//    Map<HandType, HandFactory> factoryMap;

    public HandIdentifier () {
        this.initializeMap();
    }

    private void initializeMap () {
        handFactoryTypes = new ArrayList<>();
        handFactoryTypes.add(new HandFactoryType(HandType.STRAIGHT, new StraightFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.THREE_OF_A_KIND, new ThreeOfAKindFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.TWO_PAIR, new TwoPairFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.ONE_PAIR, new OnePairFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.HIGH_CARD, new HighCardFactory()));
    }

    /**
     * Given the player's cards and the community cards, identifies the player's hand.
     *
     * @param playerCards
     * @param communityCards
     * @return The player's {@link Hand} or `null` if no Hand was identified.
     */
    public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {
        return getHand(getCurrentHand(this.combineCards(playerCards, communityCards)));
    }

    private List<Card> combineCards(List<Card> playerCards, List<Card> communityCards) {
//        List<Card> cards = new ArrayList<Card>();
//        cards.addAll(playerCards);
//        cards.addAll(communityCards);
//        return cards;

//        return highCard();
//        return onePair();
//        return twoPair();
//        return threeOfAKind();
//        return straight1();
        return straight2();
    }

    private List<Card> highCard() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS)
        );
    }

    private List<Card> onePair() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );
    }

    private List<Card> twoPair() {
        return Arrays.asList(
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );
    }

    private List<Card> threeOfAKind() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS)
        );
    }

    private List<Card> straight1() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS)
        );
    }

    private List<Card> straight2() {
        return Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS)
        );
    }

    private HandFactoryType getCurrentHand (List<Card> cards) {
        HandFactoryType currentHand = null;
        HandFactory tempHandFactory;
        for (int i = 0; i < handFactoryTypes.size(); i++) {
            tempHandFactory = handFactoryTypes.get(i).getHandFactory();
            tempHandFactory.setCards(cards);
            tempHandFactory = tempHandFactory.create();
            if (tempHandFactory == null){
                continue;
            } else {
                currentHand = new HandFactoryType(handFactoryTypes.get(i).getHandType(), tempHandFactory);
                break;
            }
        }

        return currentHand;
    }

    private Hand getHand(HandFactoryType currentHand) {
        Hand retHand;

        if (currentHand != null) {
            switch (currentHand.getHandType()) {
                case STRAIGHT:
                    StraightFactory straightFactory = (StraightFactory)  currentHand.getHandFactory();
                    retHand = new Straight(straightFactory.getCards());
                    break;
                case THREE_OF_A_KIND:
                    ThreeOfAKindFactory threeOfAKindFactory = (ThreeOfAKindFactory)  currentHand.getHandFactory();
                    retHand = new ThreeOfAKind(threeOfAKindFactory.getThreeOfAKindCards(),
                            threeOfAKindFactory.getOtherCards());
                    break;
                case TWO_PAIR:
                    TwoPairFactory twoPairFactoryFactory = (TwoPairFactory)  currentHand.getHandFactory();
                    retHand = new TwoPair(twoPairFactoryFactory.getFirstPairCards(),
                            twoPairFactoryFactory.getSecondPairCards(), twoPairFactoryFactory.getOtherCards());
                    break;
                case ONE_PAIR:
                    OnePairFactory onePairFactory = (OnePairFactory)  currentHand.getHandFactory();
                    retHand = new OnePair(onePairFactory.getPairCards(), onePairFactory.getOtherCards());
                    break;
                case HIGH_CARD:
                    HighCardFactory highCardFactory = (HighCardFactory)  currentHand.getHandFactory();
                    retHand = new HighCard(highCardFactory.getCards());
                    break;
                default:
                    retHand = null;
            }
        } else {
            return null;
        }



        return retHand;
    }

//    private boolean isFlush(List<Card> cards) {
//        Map<CardSuit, Integer> suitMap = new HashMap<CardSuit, Integer>();
//
//        // put a map for suits
//        for (Card card: cards) {
//            int ctr;
//            try {
//                ctr = suitMap.get(card.getSuit());
//                ctr++;
//                suitMap.replace(card.getSuit(), ctr);
//            } catch (NullPointerException e) {
//                ctr = 1;
//                suitMap.put(card.getSuit(), ctr);
//            }
//        }
//
//        // check for suit with 5 or greater than 5 value
//        for (Integer value: suitMap.values()){
//            if (value >= 5) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isFourOfAKind(List<Card> cards) {
//        Map<CardRank, Integer> rankMap = new HashMap<CardRank, Integer>();
//
//        // put a map for rank
//        for (Card card: cards) {
//            int ctr;
//            try {
//                ctr = rankMap.get(card.getRank());
//                ctr++;
//                rankMap.replace(card.getRank(), ctr);
//            } catch (NullPointerException e) {
//                ctr = 1;
//                rankMap.put(card.getRank(), ctr);
//            }
//        }
//
//        // check for rank with 4 value
//        for (Integer value: rankMap.values()){
//            if (value == 4) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isOnePair (List<Card> cards) {
//        Map<CardRank, Integer> rankMap = new HashMap<CardRank, Integer>();
//
//        // put a map for rank
//        for (Card card: cards) {
//            int ctr;
//            try {
//                ctr = rankMap.get(card.getRank());
//                ctr++;
//                rankMap.replace(card.getRank(), ctr);
//            } catch (NullPointerException e) {
//                ctr = 1;
//                rankMap.put(card.getRank(), ctr);
//            }
//        }
//
//        // check for rank with 4 value
//        for (Integer value: rankMap.values()){
//            if (value == 2) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isTwoPair (List<Card> cards) {
//        Map<CardRank, Integer> rankMap = new HashMap<CardRank, Integer>();
//
//        // put a map for rank
//        for (Card card: cards) {
//            int ctr;
//            try {
//                ctr = rankMap.get(card.getRank());
//                ctr++;
//                rankMap.replace(card.getRank(), ctr);
//            } catch (NullPointerException e) {
//                ctr = 1;
//                rankMap.put(card.getRank(), ctr);
//            }
//        }
//
//        int counterForPairs = 0;
//        // check for rank with 4 value
//        for (Integer value: rankMap.values()){
//            if (value == 2) {
//                counterForPairs++;
//            }
//        }
//
//        if (counterForPairs == 2) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private boolean isThreeOfAKind (List<Card> cards) {
//        Map<CardRank, Integer> rankMap = new HashMap<CardRank, Integer>();
//
//        // put a map for rank
//        for (Card card: cards) {
//            int ctr;
//            try {
//                ctr = rankMap.get(card.getRank());
//                ctr++;
//                rankMap.replace(card.getRank(), ctr);
//            } catch (NullPointerException e) {
//                ctr = 1;
//                rankMap.put(card.getRank(), ctr);
//            }
//        }
//
//        // check for rank with 3 value
//        for (Integer value: rankMap.values()){
//            if (value == 3) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isStraight (List<Card> cards) {
//        List<CardRank> cardRanks = new ArrayList<CardRank>();
//
//        // put a map for rank
//        for (Card card: cards) {
//            cardRanks.add(card.getRank());
//        }
//
//        // sort the card by rank
//        Collections.sort(cardRanks);
//
//        int sizeOfCard = cardRanks.size();
//        int i, j;
//        CardRank nextCard;
//        for (i = 0; i < sizeOfCard; i++) {
//            for (j = i; j < sizeOfCard-1; j++) {
//                nextCard = cardRanks.get(j+1);
//                if (cardRanks.get(j).ordinal()+1 != nextCard.ordinal()) {
//                    break;
//                }
//            }
//            if ((j - i)+1 >= 5) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isFullHouse (List<Card> cards) {
//        boolean hasThreeOfAKind = isThreeOfAKind(cards);
//        boolean hasOnePair = isOnePair(cards);
//
//        return hasThreeOfAKind && hasOnePair;
//    }

}
