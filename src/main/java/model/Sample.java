package model;

import exception.ReadFileException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sample {
    private Argument X, Y, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec,
            mon, tue, wed, thu, fri, sat, sun,
            ffmc, dmc, dc, isi, temp, rh, wind, rain;
    private Double result; //burned area

    public Sample(Double argX, Double argY, String argMonth, String argDay, Double argFfmc, Double argDmc,
                  Double argDc, Double argIsi, Double argTemp, Double argRh, Double argWind, Double argRain,
                  Double argResult) throws ReadFileException {
//        new Sample(argX, argY, argFfmc, argDmc, argDc, argIsi, argTemp, argRh, argWind, argRain, argResult);
        X = new Argument(Argument.ArgumentType.X, argX);
        Y = new Argument(Argument.ArgumentType.Y, argY);
        ffmc = new Argument(Argument.ArgumentType.FFMC, argFfmc);
        dmc = new Argument(Argument.ArgumentType.DMC, argDmc);
        dc = new Argument(Argument.ArgumentType.DC, argDc);
        isi = new Argument(Argument.ArgumentType.ISI, argIsi);
        temp = new Argument(Argument.ArgumentType.TEMP, argTemp);
        rh = new Argument(Argument.ArgumentType.RH, argRh);
        wind = new Argument(Argument.ArgumentType.WIND, argWind);
        rain = new Argument(Argument.ArgumentType.RAIN, argRain);

        jan = new Argument(Argument.ArgumentType.JAN);
        feb = new Argument(Argument.ArgumentType.FEB);
        mar = new Argument(Argument.ArgumentType.MAR);
        apr = new Argument(Argument.ArgumentType.APR);
        may = new Argument(Argument.ArgumentType.MAY);
        jun = new Argument(Argument.ArgumentType.JUN);
        jul = new Argument(Argument.ArgumentType.JUL);
        aug = new Argument(Argument.ArgumentType.AUG);
        sep = new Argument(Argument.ArgumentType.SEP);
        oct = new Argument(Argument.ArgumentType.OCT);
        nov = new Argument(Argument.ArgumentType.NOV);
        dec = new Argument(Argument.ArgumentType.DEC);

        mon = new Argument(Argument.ArgumentType.MON);
        tue = new Argument(Argument.ArgumentType.TUE);
        wed = new Argument(Argument.ArgumentType.WED);
        thu = new Argument(Argument.ArgumentType.THU);
        fri = new Argument(Argument.ArgumentType.FRI);
        sat = new Argument(Argument.ArgumentType.SAT);
        sun = new Argument(Argument.ArgumentType.SUN);

        result = argResult;

        if (argMonth.equals("jan")) {
            jan.setValue(1.0);
        } else if (argMonth.equals("feb")) {
            feb.setValue(1.0);
        } else if (argMonth.equals("mar")) {
            mar.setValue(1.0);
        } else if (argMonth.equals("apr")) {
            apr.setValue(1.0);
        } else if (argMonth.equals("may")) {
            may.setValue(1.0);
        } else if (argMonth.equals("jun")) {
            jun.setValue(1.0);
        } else if (argMonth.equals("jul")) {
            jul.setValue(1.0);
        } else if (argMonth.equals("aug")) {
            aug.setValue(1.0);
        } else if (argMonth.equals("sep")) {
            sep.setValue(1.0);
        } else if (argMonth.equals("oct")) {
            oct.setValue(1.0);
        } else if (argMonth.equals("nov")) {
            nov.setValue(1.0);
        } else if (argMonth.equals("dec")) {
            dec.setValue(1.0);
        } else {
            throw new ReadFileException("Incorrect month name");
        }

        if (argDay.equals("mon")) {
            mon.setValue(1.0);
        } else if (argDay.equals("tue")) {
            tue.setValue(1.0);
        } else if (argDay.equals("wed")) {
            wed.setValue(1.0);
        } else if (argDay.equals("thu")) {
            thu.setValue(1.0);
        } else if (argDay.equals("fri")) {
            fri.setValue(1.0);
        } else if (argDay.equals("sat")) {
            sat.setValue(1.0);
        } else if (argDay.equals("sun")) {
            sun.setValue(1.0);
        } else {
            throw new ReadFileException("Incorrect week day name");
        }
    }

    /*public Sample(Double argX, Double argY, Double argFfmc, Double argDmc, Double argDc, Double argIsi,
                  Double argTemp, Double argRh, Double argWind, Double argRain, Double argResult) {
        super();
        X = new Argument(Argument.ArgumentType.X, argX);
        Y = new Argument(Argument.ArgumentType.Y, argY);
        ffmc = new Argument(Argument.ArgumentType.FFMC, argFfmc);
        dmc = new Argument(Argument.ArgumentType.DMC, argDmc);
        dc = new Argument(Argument.ArgumentType.DC, argDc);
        isi = new Argument(Argument.ArgumentType.ISI, argIsi);
        temp = new Argument(Argument.ArgumentType.TEMP, argTemp);
        rh = new Argument(Argument.ArgumentType.RH, argRh);
        wind = new Argument(Argument.ArgumentType.WIND, argWind);
        rain = new Argument(Argument.ArgumentType.RAIN, argRain);

        jan = new Argument(Argument.ArgumentType.JAN);
        feb = new Argument(Argument.ArgumentType.FEB);
        mar = new Argument(Argument.ArgumentType.MAR);
        apr = new Argument(Argument.ArgumentType.APR);
        may = new Argument(Argument.ArgumentType.MAY);
        jun = new Argument(Argument.ArgumentType.JUN);
        jul = new Argument(Argument.ArgumentType.JUL);
        aug = new Argument(Argument.ArgumentType.AUG);
        sep = new Argument(Argument.ArgumentType.SEP);
        oct = new Argument(Argument.ArgumentType.OCT);
        nov = new Argument(Argument.ArgumentType.NOV);
        dec = new Argument(Argument.ArgumentType.DEC);

        mon = new Argument(Argument.ArgumentType.MON);
        tue = new Argument(Argument.ArgumentType.TUE);
        wed = new Argument(Argument.ArgumentType.WED);
        thu = new Argument(Argument.ArgumentType.THU);
        fri = new Argument(Argument.ArgumentType.FRI);
        sat = new Argument(Argument.ArgumentType.SAT);
        sun = new Argument(Argument.ArgumentType.SUN);

        result = argResult;
    }*/

    public Sample() {
        super();
    }
}
