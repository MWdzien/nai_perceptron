import java.util.ArrayList;
import java.util.List;


public class Perceptron {
    Double[] weights;
    double learningRate = 0.0001;

    public Perceptron(int attributesAmount) {
        weights = new Double[attributesAmount];

        for (int i = 0; i < attributesAmount; i++) {
            weights[i] = (2*Math.random()-1);
        }
    }

    public int classify(DataEntry entry){
        double sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += entry.attributes.get(i) * weights[i];
        }

        return sum >= 0 ? 1 : 0;
    }


    public void train(List<DataEntry> dataEntries, int n){
        int trained = 0;
        for (int i = 0; i < n; i++) {
            for (DataEntry dataEntry : dataEntries) {
                int error = dataEntry.classificationID - classify(dataEntry);
                if (error != 0) trained = 0;

                for (int k = 0; k < weights.length; k++) {
                    weights[k] += error * dataEntry.attributes.get(k) * learningRate;
                }
                trained++;
            }
            if (trained >= dataEntries.size()) break;
        }

    }
}

