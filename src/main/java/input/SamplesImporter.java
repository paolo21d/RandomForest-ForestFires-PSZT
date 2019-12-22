package input;

import lombok.Data;
import model.Sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class SamplesImporter {

    private List<Sample> samples = new ArrayList<>();

    public List<Sample> readSamplesFromFile(String path) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(path));

        String line = reader.readLine();
        List<String> parts;
        while (line != null) {
            parts = Arrays.asList(line.split(";"));

            Double X = Double.valueOf(parts.get(0));
            Double Y = Double.valueOf(parts.get(1));
            String month = parts.get(2);
            String day = parts.get(3);
            Double ffmc = Double.valueOf(parts.get(4));
            Double dmc = Double.valueOf(parts.get(5));
            Double dc = Double.valueOf(parts.get(6));
            Double isi = Double.valueOf(parts.get(7));
            Double temp = Double.valueOf(parts.get(8));
            Double rh = Double.valueOf(parts.get(9));
            Double wind = Double.valueOf(parts.get(10));
            Double rain = Double.valueOf(parts.get(11));
            Double result = Double.valueOf(parts.get(12));

            Sample sample = new Sample(X, Y, month, day, ffmc, dmc, dc, isi, temp, rh, wind, rain, result);
            samples.add(sample);

            line = reader.readLine();
        }
        return samples;
    }

}
