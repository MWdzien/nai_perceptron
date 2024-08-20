import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    //List<DataEntry> outputs;

    public DataReader() {
        //outputs = new ArrayList<>();
    }

    public List<DataEntry> readFile(File file, String posClassName){
        List<DataEntry> inputs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null){
                List<Double> nums = new ArrayList<>();

                line = line.replaceAll(",", ".").replaceAll(" ", "");
                String[] data = line.split("\t");
                for (int i = 0; i < data.length - 1; i++){
                    nums.add(Double.parseDouble(data[i]));
                }

                DataEntry entry;
                if (data[data.length-1].equals(posClassName)){
                    entry = new DataEntry(1, nums);
                } else {
                    entry = new DataEntry(0, nums);
                }
                inputs.add(entry);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return inputs;
    }
}
