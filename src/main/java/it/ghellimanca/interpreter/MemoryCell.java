package it.ghellimanca.interpreter;

public class MemoryCell {
    private Integer data;
    private boolean isFull; //TODO capire se utile o basta inizializzare a default in qualche modo l'intero

    public MemoryCell() {
        this.data = null;
        this.isFull = false;
    }

    public MemoryCell(Integer data, boolean isFull) {
        this.data = data;
        this.isFull = isFull;
    }
}
