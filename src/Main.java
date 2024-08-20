import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String posClassName = "Iris-setosa";
        DataReader dr = new DataReader();
        String trainingFile = "iris_training 3.txt";
        String testFile = "iris_test 3.txt";
        int n = 1000;

        List<DataEntry> trainingInputs = dr.readFile(new File(trainingFile), posClassName);

        Perceptron perceptron = new Perceptron(trainingInputs.get(0).attributes.size());
        perceptron.train(trainingInputs, n);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Aby sprawdzić poprawność klasyfikacji w pliku iris_test.txt wprowadź 1");
            System.out.println("Aby sprawdzić własny wektor wprowadź 2");

            String response = scanner.nextLine();

            switch (response) {
                case "1":
                    List<DataEntry> testInputs = dr.readFile(new File(testFile), posClassName);

                    int correct = 0;
                    for (int i = 0; i < testInputs.size(); i++) {
                        if (perceptron.classify(testInputs.get(i)) == testInputs.get(i).classificationID) {
                            correct++;
                        }
                    }

                    double acc = ((double) correct / testInputs.size() * 100.0);
                    System.out.println("W pliku " + testFile + " liczba poprawnie zaklasyfikowanych wpisów to: " +  + correct + " tj. " + acc + "%");
                    break;
                case "2":
                    System.out.println("Podaj dane");
                    String s = scanner.nextLine();
                    List<Double> list = new ArrayList<>();
                    s = s.replaceAll(" ", "").replaceAll(",", ".");
                    String[] nums = s.split("\t");
                    for (String num : nums) {
                        list.add(Double.parseDouble(num));
                    }
                    DataEntry entry = nums[nums.length - 1].equals(posClassName) ? new DataEntry(1, list) : new DataEntry(0, list);

                    if (perceptron.classify(entry) == 1) {
                        System.out.println("Podany wektor wskazuje na " + posClassName);
                    } else System.out.println("Podany wektor wskazuje na nie-" + posClassName);
            }
        }

    }
}