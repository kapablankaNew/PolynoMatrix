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
            for (int j = 0; j < columns; j++) {
                builder.append(cells.get(i).get(j).toString());
                builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
