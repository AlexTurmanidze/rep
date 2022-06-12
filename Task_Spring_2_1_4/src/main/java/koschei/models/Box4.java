package koschei.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Box4 {
    
    private Rabbit5 rabbit;
    
    @Autowired
    public void setRabbit(Rabbit5 rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public String toString() {
        return "в сундуке - заяц, " + rabbit.toString();
    }
}
