import java.util.List;

public class DataEntry {
    int classificationID;
    List<Double> attributes;

    public DataEntry(int classificationID, List<Double> attributes) {
        this.classificationID = classificationID;
        this.attributes = attributes;
    }
}
