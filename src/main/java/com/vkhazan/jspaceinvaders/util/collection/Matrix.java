package com.vkhazan.jspaceinvaders.util.collection;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Matrix<T> {

    private Object[][] matrix;
    private int row;
    private int col;

    // getNext mothod support fields
    private int currentGetRow;
    private int currentGetCol;

    // getNext mothod support fields
    private int currentAddRow;
    private int currentAddCol;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;

        matrix = new Object[row][col];

        currentGetRow = 0;
        currentGetCol = 0;

        currentAddRow = 0;
        currentAddCol = 0;
    }

    public void add(T entity, int i, int j) {
        if (!checkIndexes(i, j)) {
            return;
        }
        matrix[i][j] = entity;
    }

    public void add(T entity) {
        if (currentAddCol + 1 > col) {
            currentAddCol = 0;
            currentAddRow++;
        }
        if (currentAddRow + 1 > row) {
            currentAddRow = 0;
        }
        matrix[currentAddRow][currentAddCol++] = entity;
        
        System.out.println(entity);
    }

    public T get(int i, int j) {
        if (!checkIndexes(i, j)) {
            return null;
        }
        return (T) matrix[i][j];
    }

    private boolean rotation = false;
    
    public T getNext() {
       // if (currentGetCol == 0 && currentGetRow == 0) {
       //     rotation = false;
       // }
        
        if (currentGetCol + 1 > col) {
            currentGetCol = 0;
            currentGetRow++;
        }
        if (currentGetRow + 1 > row) {
            currentGetRow = 0;
        //    rotation = true;
            return null;
        }
        
        Object o = matrix[currentGetRow][currentGetCol++];
        
        if (o == null) {
            
        }
        //return (T) (o != null ? o : (getNext()));
        /*
        TODO: сделать что бы возвращалось только не null
        */
        
        return (T)o;
        
    }

    public void remove(T entity) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (entity.equals(matrix[i][j])) {
                    remove(i, j);
                }
            }
        }
    }

    public void remove(int i, int j) {
        if (!checkIndexes(i, j)) {
            return;
        }
        matrix[i][j] = null;
        checkAndDeleteNullCollumns();
  
    }

    public int size() {
        return row * col;
    }

    public boolean isEmpty() {
        return row * col == 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private boolean checkIndexes(int i, int j) {
        return (i * j >= 0)
                || (i * j < size());
    }

    private void checkAndDeleteNullCollumns() {

        if (checkColumn(0)) {
            deleteFirstColumn();
        }
        if (checkColumn(col - 1)) {
            deleteLastColumn();
        }

    }

    public boolean contains(T entity) {
        return intexOf(entity) >= 0;
    }

    public int intexOf(T entity) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (entity.equals(matrix[i][j])) {
                    return i * j;
                }
            }
        }
        return -1;
    }

    private void deleteFirstColumn() {
        for (int i = 0; i < row; i++) {
            arrayCopy(matrix[i], 1, matrix[i], 0, col - 1);
            matrix[i][col - 1] = null;
        }
        
        col--;
        
        System.out.println("Size: " + row + "x" + col);
    }

    private void deleteLastColumn() {
        col--;
        System.out.println("Size: " + row + "x" + col);
    }

    private boolean checkColumn(int n) {
        for (int i = row - 1; i >= 0; i--) {
            if (matrix[i][n] != null) {
                return false;
            }
        }
        return true;
    }

    private void arrayCopy(Object[] src, int srcPos, Object[] dest, int destPos, int lenght) {
        for (int index = 0; index < lenght; index++) {
            dest[destPos + index] = src[srcPos + index];
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(matrix[i][j]);
                sb.append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
