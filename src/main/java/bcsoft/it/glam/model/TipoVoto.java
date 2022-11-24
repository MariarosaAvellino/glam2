package bcsoft.it.glam.model;

public enum TipoVoto {
    UP_VOTE(1),
    DOWN_VOTE(-1);

    private int direction;

    private TipoVoto(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}