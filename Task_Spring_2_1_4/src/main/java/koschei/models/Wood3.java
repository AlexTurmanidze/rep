package koschei.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wood3 {

    private Box4 box;

    @Autowired
    public Wood3(Box4 box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "под деревом сундук зарыт, " + box.toString();
    }
}
