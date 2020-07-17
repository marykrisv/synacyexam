package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.HandFactoryType;
import com.synacy.poker.factory.types.*;
import com.synacy.poker.hand.types.*;
import com.synacy.poker.utils.CardUtil;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * A service that is to used to identify the {@link Hand} given the player's cards and the community
 * cards.
 */
@Component
public class HandIdentifier {
    private List<HandFactoryType> handFactoryTypes;

    public HandIdentifier () {
        this.initializeMap();
    }

    /**
     * This function will initialize the Hand Factory Types Map on instantiation
     */
    private void initializeMap () {
        handFactoryTypes = new ArrayList<>();
        handFactoryTypes.add(new HandFactoryType(HandType.STRAIGHT_FLUSH, new StraightFlushFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.FOUR_OF_A_KIND, new FourOfAKindFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.FULL_HOUSE, new FullHouseFactory()));
        handFactoryTypes.add(new HandFactoryType(HandType.FLUSH, new FlushFactory()));
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
        return getHand(getCurrentHand(CardUtil.combineCards(playerCards, communityCards)));
    }

    /**
     * Given the combined cards, it will identify what Hand Factory Type
     *      *
     * @param cards Combined list of cards of Player's and Community card.
     * @return The player's {@link HandFactoryType} or `null` if no Hand Factory Type was identified.
     */
    private HandFactoryType getCurrentHand (List<Card> cards) {
        List<Card> tempCards;
        HandFactoryType currentHand = null;
        HandFactory tempHandFactory;
        for (int i = 0; i < handFactoryTypes.size(); i++) {
            tempCards = cards;
            tempHandFactory = handFactoryTypes.get(i).getHandFactory();
            tempHandFactory.setCards(tempCards);
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

    /**
     * Given the Hand Factory Type, identiy what Hand.
     *
     * @param currentHand Hand Factory Type of the player's and community cards.
     * @return The player's {@link Hand} or `null` if no Hand was mapped with HandType identified.
     */
    private Hand getHand(HandFactoryType currentHand) {
        Hand retHand;

        if (currentHand != null) {
            switch (currentHand.getHandType()) {
                case STRAIGHT_FLUSH:
                    StraightFlushFactory straightFlushFactory = (StraightFlushFactory) currentHand.getHandFactory();
                    retHand = new StraightFlush(straightFlushFactory.getCards());
                    break;
                case FOUR_OF_A_KIND:
                    FourOfAKindFactory fourOfAKindFactory = (FourOfAKindFactory) currentHand.getHandFactory();
                    retHand = new FourOfAKind(fourOfAKindFactory.getFourOfAKindCards(),
                            fourOfAKindFactory.getOtherCards());
                    break;
                case FULL_HOUSE:
                    FullHouseFactory fullHouseFactory = (FullHouseFactory) currentHand.getHandFactory();
                    retHand = new FullHouse(fullHouseFactory.getThreeOfAKindCards(), fullHouseFactory.getPairCards());
                    break;
                case FLUSH:
                    FlushFactory flushFactory = (FlushFactory) currentHand.getHandFactory();
                    retHand = new Flush(flushFactory.getCards());
                    break;
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

    public List<HandFactoryType> getHandFactoryTypes() {
        return handFactoryTypes;
    }

    public void setHandFactoryTypes(List<HandFactoryType> handFactoryTypes) {
        this.handFactoryTypes = handFactoryTypes;
    }
}
