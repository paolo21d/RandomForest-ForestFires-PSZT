package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Argument {
    public enum ArgumentType {
        X,
        Y,
        MONTH,
        DAY,
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
}
