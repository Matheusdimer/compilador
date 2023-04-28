package com.unesc.compilador.analisadorlexico.base;

import java.util.Arrays;

public class PointerController {

    private int pointer = -1;
    private int checkpoint;
    private int row = 1;
    private char[] string;

    public void setString(String string) {
        this.string = string.toCharArray();
    }

    public boolean hasNext() {
        return string.length - 1 > pointer;
    }

    public char getNext() {
        pointer++;
        char character = string[pointer];

        if (character == '\n') {
            row++;
        }

        return character;
    }

    public void back() {
        pointer--;
    }

    public void restoreTo(int pointer) {
        this.pointer = pointer;
    }

    public int getRow() {
        return row;
    }

    public void saveCheckpoint() {
        checkpoint = pointer;
    }

    public void restoreCheckpoin() {
        pointer = checkpoint;
    }

    @Override
    public String toString() {
        return "PointerController{" +
                "pointer=" + pointer +
                ", checkpoint=" + checkpoint +
                ", row=" + row +
                ", string=" + Arrays.toString(string) +
                '}';
    }
}
