package org.willprice.connect4.model;

public class ColouredDisc {
    private final String id;

    public ColouredDisc(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || ! (o instanceof ColouredDisc)) {
            return false;
        }
        ColouredDisc other = (ColouredDisc) o;
        return id.equals(other.getId());
    }
}
