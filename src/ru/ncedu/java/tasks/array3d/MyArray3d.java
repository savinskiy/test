/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.array3d;

import java.io.PrintStream;

/**
 *
 * @author DimaZ
 */
public class MyArray3d implements Array3d<String> {

    String[][][] arrVector3d;
    final int MAX_Y = 10;
    final int MAX_X = 10;

    public MyArray3d() {
        arrVector3d = new String[MAX_Y][][];
        for (int i = 0; i < MAX_Y; i++) {
            arrVector3d[i] = new String[i + 1][];
            for (int j = 0; j < i + 1; j++) {
                arrVector3d[i][j] = new String[(i + 1) * (j + 1)];
                for (int k = 0; k < (i + 1) * (j + 1); k++) {
                    arrVector3d[i][j][k] = j + "," + i + "," + k;
                }
            }
        }
    }

    @Override
    public String get(int x, int y, int z) throws IndexOutOfBoundsException {
        return arrVector3d[y][x][z];
    }

    @Override
    public String set(int x, int y, int z, String value) throws IndexOutOfBoundsException {
        String result = arrVector3d[y][x][z];
        arrVector3d[y][x][z] = value;
        return result;
    }

    @Override
    public void printArray(PrintStream ps) {
        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < i + 1; j++) {
                for (int k = 0; k < (i + 1) * (j + 1); k++) {
                    ps.printf("[%d][%d][%d]:%s", j, i, k, get(j, i, k));
                }
            }
        }
    }
}
