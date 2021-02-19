package kapablankaNew.PolynoMatrix;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class MatrixTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEquals(){
        Polynom p11 = new Polynom("1");
        Polynom p12 = new Polynom("1");
        Polynom p13 = new Polynom("1");
        Polynom p21 = new Polynom("1");
        Polynom p22 = new Polynom("1");
        Polynom p23 = new Polynom("1");

        Matrix first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("1");
        p12 = new Polynom("1");
        p13 = new Polynom("1");
        p21 = new Polynom("1");
        p22 = new Polynom("1");
        p23 = new Polynom("1");

        Matrix second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(first, second);

        p11 = new Polynom("p11");
        p12 = new Polynom("p12");
        p13 = new Polynom("p13");
        p21 = new Polynom("p21");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("p11");
        p12 = new Polynom("p12");
        p13 = new Polynom("p13");
        p21 = new Polynom("p21");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(first, second);

        p11 = new Polynom("p11");
        p12 = new Polynom("p21");
        p13 = new Polynom("p13");
        p21 = new Polynom("p12");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("p11");
        p12 = new Polynom("p12");
        p13 = new Polynom("p13");
        p21 = new Polynom("p21");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertNotEquals(first, second);

        p11 = new Polynom("p11");
        p12 = new Polynom("p21");
        p13 = new Polynom("p13");
        p21 = new Polynom("p12");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        first = new Matrix(2, 4, Arrays.asList(p11, p12, p13, p11,
                p21, p22, p23, p21));

        p11 = new Polynom("p11");
        p12 = new Polynom("p12");
        p13 = new Polynom("p13");
        p21 = new Polynom("p21");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertNotEquals(first, second);
    }

    @Test
    public void testCreateException() throws IllegalArgumentException {
        Polynom p = new Polynom("a");
        thrown.expect(IllegalArgumentException.class);
        Matrix m1 = new Matrix(2, 2, Arrays.asList(p, p, p));
    }

    @Test
    public void testDimensionException() throws IllegalArgumentException {
        Polynom p = new Polynom("a");
        thrown.expect(IllegalArgumentException.class);
        Matrix m1 = new Matrix(2, 0);
    }
}