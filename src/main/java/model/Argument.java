package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ArgumentType> getAllTypes() {
        List<Argument.ArgumentType> argumentTypes = new ArrayList<>();

        argumentTypes.add(Argument.ArgumentType.X);
        argumentTypes.add(Argument.ArgumentType.Y);
        argumentTypes.add(Argument.ArgumentType.FFMC);
        argumentTypes.add(Argument.ArgumentType.DMC);
        argumentTypes.add(Argument.ArgumentType.DC);
        argumentTypes.add(Argument.ArgumentType.ISI);
        argumentTypes.add(Argument.ArgumentType.TEMP);
        argumentTypes.add(Argument.ArgumentType.RH);
        argumentTypes.add(Argument.ArgumentType.WIND);
        argumentTypes.add(Argument.ArgumentType.RAIN) ;

        argumentTypes.add(Argument.ArgumentType.JAN);
        argumentTypes.add(Argument.ArgumentType.FEB);
        argumentTypes.add(Argument.ArgumentType.MAR);
        argumentTypes.add(Argument.ArgumentType.APR);
        argumentTypes.add(Argument.ArgumentType.MAY);
        argumentTypes.add(Argument.ArgumentType.JUN);
        argumentTypes.add(Argument.ArgumentType.JUL);
        argumentTypes.add(Argument.ArgumentType.AUG);
        argumentTypes.add(Argument.ArgumentType.SEP);
        argumentTypes.add(Argument.ArgumentType.OCT);
        argumentTypes.add(Argument.ArgumentType.NOV);
        argumentTypes.add(Argument.ArgumentType.DEC);

        argumentTypes.add(Argument.ArgumentType.MON);
        argumentTypes.add(Argument.ArgumentType.TUE);
        argumentTypes.add(Argument.ArgumentType.WED);
        argumentTypes.add(Argument.ArgumentType.THU);
        argumentTypes.add(Argument.ArgumentType.FRI);
        argumentTypes.add(Argument.ArgumentType.SAT);
        argumentTypes.add(Argument.ArgumentType.SUN);

        return argumentTypes;
    }

}
