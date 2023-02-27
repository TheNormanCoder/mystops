package stops.calculator.atm.reader;

import java.util.List;

public class LineDescriptor {
    String code=null;
    String description=null;
    List<String> availableLines=null;

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
}
