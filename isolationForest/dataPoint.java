package isolationForest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class dataPoint {
    public List<Double> data;

    public dataPoint() {
        this.data = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        dataPoint other = (dataPoint) obj;
        return Objects.equals(this.data, other.data);
    }
}
