package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Argument {
    public enum ArgumentType {
        X,
        Y,
        JAN,
        FEB,
        MAR,
        APR,
        MAY,
        JUN,
        JUL,
        AUG,
        SEP,
        OCT,
        NOV,
        DEC,
        MON,
        TUE,
        WED,
        THU,
        FRI,
        SAT,
        SUN,
        FFMC,
        DMC,
        DC,
        ISI,
        TEMP,
        RH,
        WIND,
        RAIN
    }

    private ArgumentType type;
    private Double value;

    public Argument(ArgumentType argumentType) {
        type = argumentType;
        value = 0.0;
    }
}
