package model;

import exception.ReadFileException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Sample {
    private Double result; //burned area

    //X, Y, ffmc, dmc, dc, isi, temp, rh, wind, rain
    //jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec,
    //mon, tue, wed, thu, fri, sat, sun,
    private List<Argument> arguments = new ArrayList<>();

    public Sample(Double argX, Double argY, String argMonth, String argDay, Double argFfmc, Double argDmc,
                  Double argDc, Double argIsi, Double argTemp, Double argRh, Double argWind, Double argRain,
                  Double argResult) throws ReadFileException {
        arguments.add(new Argument(Argument.ArgumentType.X, argX));
        arguments.add(new Argument(Argument.ArgumentType.Y, argY));
        arguments.add(new Argument(Argument.ArgumentType.FFMC, argFfmc));
        arguments.add(new Argument(Argument.ArgumentType.DMC, argDmc));
        arguments.add(new Argument(Argument.ArgumentType.DC, argDc));
        arguments.add(new Argument(Argument.ArgumentType.ISI, argIsi));
        arguments.add(new Argument(Argument.ArgumentType.TEMP, argTemp));
        arguments.add(new Argument(Argument.ArgumentType.RH, argRh));
        arguments.add(new Argument(Argument.ArgumentType.WIND, argWind));
        arguments.add(new Argument(Argument.ArgumentType.RAIN, argRain));

        arguments.add(new Argument(Argument.ArgumentType.JAN));
        arguments.add(new Argument(Argument.ArgumentType.FEB));
        arguments.add(new Argument(Argument.ArgumentType.MAR));
        arguments.add(new Argument(Argument.ArgumentType.APR));
        arguments.add(new Argument(Argument.ArgumentType.MAY));
        arguments.add(new Argument(Argument.ArgumentType.JUN));
        arguments.add(new Argument(Argument.ArgumentType.JUL));
        arguments.add(new Argument(Argument.ArgumentType.AUG));
        arguments.add(new Argument(Argument.ArgumentType.SEP));
        arguments.add(new Argument(Argument.ArgumentType.OCT));
        arguments.add(new Argument(Argument.ArgumentType.NOV));
        arguments.add(new Argument(Argument.ArgumentType.DEC));

        arguments.add(new Argument(Argument.ArgumentType.MON));
        arguments.add(new Argument(Argument.ArgumentType.TUE));
        arguments.add(new Argument(Argument.ArgumentType.WED));
        arguments.add(new Argument(Argument.ArgumentType.THU));
        arguments.add(new Argument(Argument.ArgumentType.FRI));
        arguments.add(new Argument(Argument.ArgumentType.SAT));
        arguments.add(new Argument(Argument.ArgumentType.SUN));

        result = argResult;

        if (argMonth.equals("jan")) {
            getArgumentByType(Argument.ArgumentType.JAN).setValue(1.0);
        } else if (argMonth.equals("feb")) {
            getArgumentByType(Argument.ArgumentType.FEB).setValue(1.0);
        } else if (argMonth.equals("mar")) {
            getArgumentByType(Argument.ArgumentType.MAR).setValue(1.0);
        } else if (argMonth.equals("apr")) {
            getArgumentByType(Argument.ArgumentType.APR).setValue(1.0);
        } else if (argMonth.equals("may")) {
            getArgumentByType(Argument.ArgumentType.MAY).setValue(1.0);
        } else if (argMonth.equals("jun")) {
            getArgumentByType(Argument.ArgumentType.JUN).setValue(1.0);
        } else if (argMonth.equals("jul")) {
            getArgumentByType(Argument.ArgumentType.JUL).setValue(1.0);
        } else if (argMonth.equals("aug")) {
            getArgumentByType(Argument.ArgumentType.AUG).setValue(1.0);
        } else if (argMonth.equals("sep")) {
            getArgumentByType(Argument.ArgumentType.SEP).setValue(1.0);
        } else if (argMonth.equals("oct")) {
            getArgumentByType(Argument.ArgumentType.OCT).setValue(1.0);
        } else if (argMonth.equals("nov")) {
            getArgumentByType(Argument.ArgumentType.NOV).setValue(1.0);
        } else if (argMonth.equals("dec")) {
            getArgumentByType(Argument.ArgumentType.DEC).setValue(1.0);
        } else {
            throw new ReadFileException("Incorrect month name");
        }

        if (argDay.equals("mon")) {
            getArgumentByType(Argument.ArgumentType.MON).setValue(1.0);
        } else if (argDay.equals("tue")) {
            getArgumentByType(Argument.ArgumentType.TUE).setValue(1.0);
        } else if (argDay.equals("wed")) {
            getArgumentByType(Argument.ArgumentType.WED).setValue(1.0);
        } else if (argDay.equals("thu")) {
            getArgumentByType(Argument.ArgumentType.THU).setValue(1.0);
        } else if (argDay.equals("fri")) {
            getArgumentByType(Argument.ArgumentType.FRI).setValue(1.0);
        } else if (argDay.equals("sat")) {
            getArgumentByType(Argument.ArgumentType.SAT).setValue(1.0);
        } else if (argDay.equals("sun")) {
            getArgumentByType(Argument.ArgumentType.SUN).setValue(1.0);
        } else {
            throw new ReadFileException("Incorrect week day name");
        }
    }

    public Argument getArgumentByType(Argument.ArgumentType type) {
        for (Argument arg : arguments) {
            if (arg.getType().equals(type)) {
                return arg;
            }
        }
        return null;
    }
}
