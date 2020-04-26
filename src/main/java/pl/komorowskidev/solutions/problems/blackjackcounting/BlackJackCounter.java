package pl.komorowskidev.solutions.problems.blackjackcounting;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.exception.DataNotValidException;

@Component
public class BlackJackCounter {

    private CardDecoder cardDecoder;

    public BlackJackCounter(CardDecoder cardDecoder) {
        this.cardDecoder = cardDecoder;
    }

    public String count(String[] cards) throws DataNotValidException {
        BlackJackResult blackJackResult = new BlackJackResult(0 ,0);
        for(String card : cards){
            BlackJackResult cardValue = cardDecoder.decodeValue(card);
            updateResult(blackJackResult, cardValue);
        }
        return blackJackResult.getMainValue() > 21 ? "Bust" : findBestValue(blackJackResult);
    }

    private void updateResult(BlackJackResult result, BlackJackResult newValues) {
        int mainResult = result.getMainValue();
        int canAdd = result.getAdditionalValue();
        result.setMainValue(mainResult + newValues.getMainValue());
        result.setAdditionalValue(canAdd + newValues.getAdditionalValue());
    }

    private String findBestValue(BlackJackResult blackJackResult) {
        int value = blackJackResult.getMainValue();
        int canAdd = blackJackResult.getAdditionalValue();
        while(canAdd > 0){
            if(value + 10 > 21){
                canAdd = 0;
            } else {
                value += 10;
                canAdd -= 10;
            }
        }
        return String.valueOf(value);
    }
}
