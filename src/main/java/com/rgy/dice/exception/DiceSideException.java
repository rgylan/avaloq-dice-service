package com.rgy.dice.exception;

import static com.rgy.dice.exception.ErrorStrand.DICE_SIDE_EXCEPTION;

public class DiceSideException extends BaseException{

    public DiceSideException(){
        super(DICE_SIDE_EXCEPTION);
    }
}
