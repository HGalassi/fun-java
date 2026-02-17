package fun.usecases;

public enum EntityType {

    USER,
    WALLET,
    PAYMENT,
    ADDRESS,
    HISTORY;

    public String prefix() {
        return this.name();
    }
}

