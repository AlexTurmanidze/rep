package koschei.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Rabbit5 {

    private Duck6 duck;
    
    @Autowired
    public Rabbit5(Duck6 duck) {
        this.duck = duck;
    }

    @Override
    public String toString() {
        return "в зайце - утка, " + duck.toString();
    }
}
