package cz.vse.java.nerv01.adventura.main;

public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObservers();
}
