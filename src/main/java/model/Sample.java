package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sample {
    private Argument X, Y, month, day, ffmc, dmc, dc, isi, temp, rh, wind, rain;
    private Double result; //burned area

    public Sample(Double argX, Double argY, Double argMonth, Double argDay, Double argFfmc, Double argDmc,
                  Double argDc, Double argIsi, Double argTemp, Double argRh, Double argWind, Double argRain,
                  Double argResult) {
        X = new Argument(Argument.ArgumentType.X, argX);
        Y = new Argument(Argument.ArgumentType.Y, argY);
        month = new Argument(Argument.ArgumentType.MONTH, argMonth);
        day = new Argument(Argument.ArgumentType.DAY, argDay);
        ffmc = new Argument(Argument.ArgumentType.FFMC, argFfmc);
        dmc = new Argument(Argument.ArgumentType.DMC, argDmc);
        dc = new Argument(Argument.ArgumentType.DC, argDc);
        isi = new Argument(Argument.ArgumentType.ISI, argIsi);
        temp = new Argument(Argument.ArgumentType.TEMP, argTemp);
        rh = new Argument(Argument.ArgumentType.RH, argRh);
        wind = new Argument(Argument.ArgumentType.WIND, argWind);
        rain = new Argument(Argument.ArgumentType.RAIN, argRain);

        result = argResult;
    }
}
