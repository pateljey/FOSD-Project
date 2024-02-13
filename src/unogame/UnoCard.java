/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unogame;

/**
 *
 * @author Jeyur Patel
 */
public class UnoCard {
    private UnoColor color;
    private UnoValue value;

    public UnoCard(UnoColor color, UnoValue value) {
        this.color = color;
        this.value = value;
    }

    public UnoColor getColor() {
        return color;
    }

    public UnoValue getValue() {
        return value;
    }

    public boolean matches(UnoCard other) {
        return this.color == other.color || this.value == other.value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}