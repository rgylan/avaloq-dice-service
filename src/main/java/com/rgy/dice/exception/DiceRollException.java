package com.rgy.dice.exception;

import static com.rgy.dice.exception.ErrorStrand.DICE_AND_ROLL_EXCEPTION;

public class DiceRollException extends BaseException {

    public DiceRollException() {
        super(DICE_AND_ROLL_EXCEPTION);
    }
}
