package com.rgy.dice.exception;

public class ErrorStrand {

    // No intention to make an instance external since instance variables are all static
    private ErrorStrand() {
    }

    public static final String GENERAL_ERROR_MESSAGE = "{sorry.something.went.wrong}";

    public static final String DICE_AND_ROLL_EXCEPTION = "{number.of.dice.and.roll.at.least.1}";

    public static final String DICE_SIDE_EXCEPTION = "{dice.side.at.least.4}";

}
