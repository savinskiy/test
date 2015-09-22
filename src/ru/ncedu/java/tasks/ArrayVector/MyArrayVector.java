/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.arrayVector;

import java.util.Arrays;

/**
 *
 * @author DimaZ
 */
public class MyArrayVector implements ArrayVector {

    double[] arrVector;

    public MyArrayVector() {
        arrVector = new double[1];
        arrVector[0] = 0;
    }

    public MyArrayVector(double[] arrVector) {
        this.arrVector = arrVector.clone();
    }

    @Override
    public void set(double... elements) {
        arrVector = new double[elements.length];
        System.arraycopy(elements, 0, arrVector, 0, elements.length);
    }

    @Override
    public double[] get() {
        return arrVector;
    }

    @Override
    public ArrayVector clone() {
        double[] newArr = arrVector.clone();
        return new MyArrayVector(newArr);
    }

    @Override
    public int getSize() {
        return arrVector.length;
    }

    @Override
    public void set(int index, double value) {
        if (index >= 0) {
            if (index < arrVector.length) {
                arrVector[index] = value;
            } else {
                double[] temp = new double[index + 1];
                Arrays.fill(temp, 0);

                temp[index] = value;
                System.arraycopy(arrVector, 0, temp, 0, arrVector.length);
                arrVector = temp;
            }
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        return arrVector[index];
    }

    @Override
    public double getMax() {
        double max = Double.MIN_VALUE;

        for (int i = 0; i < arrVector.length; i++) {
            max = Math.max(max, arrVector[i]);
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < arrVector.length; i++) {
            min = Math.min(min, arrVector[i]);
        }
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(arrVector);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < arrVector.length; i++) {
            arrVector[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        int length = Math.min(arrVector.length, anotherVector.getSize());

        for (int i = 0; i < length; i++) {
            arrVector[i] += anotherVector.get(i);
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double result = 0;
        int length = Math.min(arrVector.length, anotherVector.getSize());

        for (int i = 0; i < length; i++) {
            result += arrVector[i] * anotherVector.get(i);
        }
        return result;
    }

    @Override
    public double getNorm() {
        return Math.sqrt(scalarMult(this));
    }
}
