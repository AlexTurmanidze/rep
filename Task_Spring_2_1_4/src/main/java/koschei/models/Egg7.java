package koschei.models;

public class Egg7 {
    private Needle8 needle;

    public Egg7(Needle8 needle) {
        this.needle = needle;
    }

    @Override
    public String toString() {
        return "в яйце - игла, " + needle.toString();
    }
}
