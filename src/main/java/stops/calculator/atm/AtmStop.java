package stops.calculator.atm;

import stops.calculator.geocode.GeoPoint;

import java.util.List;

public class AtmStop {
    String code;
    String description;
    List<String> availableLines;
    GeoPoint position;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAvailableLines() {
        return availableLines;
    }

    public void setAvailableLines(List<String> availableLines) {
        this.availableLines = availableLines;
    }

    public GeoPoint getPosition() {
        return position;
    }

    public void setPosition(GeoPoint position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "AtmStop{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", availableLines=" + availableLines +
                ", position=" + position +
                '}';
    }
}
