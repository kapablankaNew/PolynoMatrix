package kapablankaNew.PolynoMatrix;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Matrix {
    @Getter
    final int rows;
    @Getter
    final int columns;
    @Getter
    List<List<Polynom>> cells;

    public Matrix(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Incorrect number of rows!");
        }
        if (columns <= 0) {
            throw new IllegalArgumentException("Incorrect number of columns!");
        }
        this.rows = rows;
        this.columns = columns;
        cells = new ArrayList<>();
        for (int i = 0; i < rows; i++){
            cells.add(null);
        }
        for (int i = 0; i < rows; i++) {
            List<Polynom> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(null);
            }
            cells.set(i, row);
        }
    }

    public Matrix(int rows, int columns, List<Polynom> elements) {
        if (elements.size() != rows*columns || elements.size() == 0) {
            throw new IllegalArgumentException("Incorrect number of elements!");
        }
        this.rows = rows;
        this.columns = columns;
        ListIterator<Polynom> iterator = elements.listIterator();
        cells = new ArrayList<>();
        for (int i = 0; i < rows; i++){
            cells.add(null);
        }
        for (int i = 0; i < rows; i++) {
            List<Polynom> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(iterator.next());
            }
            cells.set(i, row);
        }
    }

    public Matrix add(Matrix second) {
        if (getRows() != second.getRows() || getColumns() != second.getColumns()) {
            throw new IllegalArgumentException("The dimensions og the matrix do not match!");
        }
        Matrix result = new Matrix(getRows(), getColumns());

        for (int i = 0; i < getRows(); i++) {
            for(int j = 0; j < getColumns(); j++) {
                Polynom cell = getCells().get(i).get(j).add(second.getCells().get(i).get(j));
                cell.simplify();
                result.getCells().get(i).set(j, cell);
            }
        }
        return result;
    }

    public Matrix subtract(Matrix second){
        if (getRows() != second.getRows() || getColumns() != second.getColumns()) {
            throw new IllegalArgumentException("The dimensions og the matrix do not match!");
        }
        Matrix result = new Matrix(getRows(), getColumns());

        for (int i = 0; i < getRows(); i++) {
            for(int j = 0; j < getColumns(); j++) {
                Polynom cell = getCells().get(i).get(j).subtract(second.getCells().get(i).get(j));
                cell.simplify();
                result.getCells().get(i).set(j, cell);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix second) {
        if (getColumns() != second.getRows()) {
            throw new IllegalArgumentException("Matrix dimension aren't compatible!");
        }
        Matrix result = new Matrix(getRows(), second.getColumns());

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Polynom cell = new Polynom();
                for (int r = 0; r < getColumns(); r++) {
                    Polynom addend = getCells().get(i).get(r).multiply(second.getCells().get(r).get(j));
                    cell = cell.add(addend);
                }
                result.getCells().get(i).set(j, cell);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(getColumns(), getRows());
        for (int i = 0; i < getColumns(); i++) {
            for (int j = 0; j < getRows(); j++) {
                result.getCells().get(i).set(j, getCells().get(j).get(i));
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        if (getRows() != matrix.getRows() || getColumns() != matrix.getColumns()) {
            return false;
        }
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (!getCells().get(i).get(j).equals(matrix.getCells().get(i).get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, cells);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            builder.append("|");
            for (int j = 0; j < columns; j++) {
                builder.append(cells.get(i).get(j).toString());
                builder.append("|");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
