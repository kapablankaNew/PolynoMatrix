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
        thrown.expect(IllegalArgumentException.class);
        Matrix m1 = new Matrix(2, 0);
    }

    @Test
    public void testTranspose() {
        Polynom a11 = new Polynom("1");
        Polynom a12 = new Polynom("2");
        Polynom a21 = new Polynom("3");
        Polynom a22 = new Polynom("4");

        Matrix source = new Matrix(2, 2, Arrays.asList(a11, a12, a21, a22));
        Matrix result = source.transpose();
        Matrix expected = new Matrix(2, 2, Arrays.asList(a11, a21, a12, a22));

        Assert.assertEquals(expected, result);

        a11 = new Polynom("p11");
        a12 = new Polynom("p12");
        Polynom a13 = new Polynom("p13");
        a21 = new Polynom("p21");
        a22 = new Polynom("p22");
        Polynom a23 = new Polynom("p23");

        source = new Matrix(2, 3, Arrays.asList(a11, a12, a13, a21, a22, a23));
        result = source.transpose();

        expected = new Matrix(3, 2, Arrays.asList(a11, a21, a12, a22, a13, a23));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAdd() {
        Polynom p11 = new Polynom("1");
        Polynom p12 = new Polynom("2");
        Polynom p13 = new Polynom("3");
        Polynom p21 = new Polynom("4");
        Polynom p22 = new Polynom("5");
        Polynom p23 = new Polynom("6");

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

        p11 = new Polynom("2");
        p12 = new Polynom("3");
        p13 = new Polynom("4");
        p21 = new Polynom("5");
        p22 = new Polynom("6");
        p23 = new Polynom("7");

        Matrix result = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(result, first.add(second));

        p11 = new Polynom("p11");
        p12 = new Polynom("p12");
        p13 = new Polynom("p13");
        p21 = new Polynom("p21");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("q11");
        p12 = new Polynom("q12");
        p13 = new Polynom("q13");
        p21 = new Polynom("q21");
        p22 = new Polynom("q22");
        p23 = new Polynom("q23");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("p11 + q11");
        p12 = new Polynom("p12 + q12");
        p13 = new Polynom("p13 + q13");
        p21 = new Polynom("p21 + q21");
        p22 = new Polynom("p22 + q22");
        p23 = new Polynom("p23 + q23");

        result = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(result, first.add(second));

        p11 = new Polynom("a + b");
        p12 = new Polynom("a - c");
        p13 = new Polynom("b + c");
        p21 = new Polynom("c - a");
        p22 = new Polynom("c + a");
        p23 = new Polynom("b - c");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("a - b");
        p12 = new Polynom("c - a");
        p13 = new Polynom("b + c");
        p21 = new Polynom("a - b");
        p22 = new Polynom("b + a");
        p23 = new Polynom("b + c");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("2*a");
        p12 = new Polynom("0");
        p13 = new Polynom("2*b + 2*c");
        p21 = new Polynom("c - b");
        p22 = new Polynom("2*a + b + c");
        p23 = new Polynom("2*b");

        result = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(result, first.add(second));

        p11 = new Polynom("1");
        p12 = new Polynom("2");
        p13 = new Polynom("3");
        p21 = new Polynom("4");
        p22 = new Polynom("5");
        p23 = new Polynom("6");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("1");
        p12 = new Polynom("1");
        p13 = new Polynom("1");
        p21 = new Polynom("1");
        p22 = new Polynom("1");
        p23 = new Polynom("1");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("0");
        p12 = new Polynom("1");
        p13 = new Polynom("2");
        p21 = new Polynom("3");
        p22 = new Polynom("4");
        p23 = new Polynom("5");

        result = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(result, first.subtract(second));

        p11 = new Polynom("p11");
        p12 = new Polynom("p12");
        p13 = new Polynom("p13");
        p21 = new Polynom("p21");
        p22 = new Polynom("p22");
        p23 = new Polynom("p23");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("q11");
        p12 = new Polynom("q12");
        p13 = new Polynom("q13");
        p21 = new Polynom("q21");
        p22 = new Polynom("q22");
        p23 = new Polynom("q23");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("p11 - q11");
        p12 = new Polynom("p12 - q12");
        p13 = new Polynom("p13 - q13");
        p21 = new Polynom("p21 - q21");
        p22 = new Polynom("p22 - q22");
        p23 = new Polynom("p23 - q23");

        result = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(result, first.subtract(second));

        p11 = new Polynom("a + b");
        p12 = new Polynom("a - c");
        p13 = new Polynom("b + c");
        p21 = new Polynom("c - a");
        p22 = new Polynom("c + a");
        p23 = new Polynom("b - c");

        first = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("a - b");
        p12 = new Polynom("c - a");
        p13 = new Polynom("b + c");
        p21 = new Polynom("a - b");
        p22 = new Polynom("b + a");
        p23 = new Polynom("b + c");

        second = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        p11 = new Polynom("2*b");
        p12 = new Polynom("2*a-2*c");
        p13 = new Polynom("0");
        p21 = new Polynom("c - 2*a + b");
        p22 = new Polynom("c - b");
        p23 = new Polynom("-2*c");

        result = new Matrix(2, 3, Arrays.asList(p11, p12, p13,
                p21, p22, p23));

        Assert.assertEquals(result, first.subtract(second));
    }

    @Test
    public void testMultiply() {
        Polynom a11 = new Polynom("1");
        Polynom a12 = new Polynom("2");
        Polynom a21 = new Polynom("3");
        Polynom a22 = new Polynom("4");

        Matrix first = new Matrix(2, 2, Arrays.asList(a11, a12, a21, a22));

        a11 = new Polynom("5");
        a12 = new Polynom("6");
        a21 = new Polynom("7");
        a22 = new Polynom("8");

        Matrix second = new Matrix(2, 2, Arrays.asList(a11, a12, a21, a22));

        a11 = new Polynom("19");
        a12 = new Polynom("22");
        a21 = new Polynom("43");
        a22 = new Polynom("50");

        Matrix result = new Matrix(2, 2, Arrays.asList(a11, a12, a21, a22));

        Assert.assertEquals(result, first.multiply(second));

        Polynom p11 = new Polynom("p11");
        Polynom p12 = new Polynom("p12");
        Polynom p21 = new Polynom("p21");
        Polynom p22 = new Polynom("p22");

        Matrix P = new Matrix(2, 2, Arrays.asList(p11, p12, p21, p22));

        a11 = new Polynom("0");
        a12 = new Polynom("0");
        a21 = new Polynom("1");
        a22 = new Polynom("0");

        Matrix A = new Matrix(2,2, Arrays.asList(a11, a12, a21, a22));

        Matrix AT = A.transpose();

        result = new Matrix(2, 2, Arrays.asList(p21, p22, a11, a12));

        Assert.assertEquals(result, AT.multiply(P));

        result = new Matrix(2, 2, Arrays.asList(p12, a11, p22, a11));

        Assert.assertEquals(result, P.multiply(A));

        Polynom b11 = new Polynom("1");
        Polynom b21 = new Polynom("0");

        Matrix B = new Matrix(2, 1, Arrays.asList(b11, b21));

        Matrix BT = B.transpose();

        Matrix test = P.multiply(B).multiply(BT).multiply(P);

        Polynom r11 = new Polynom("p11*p11");
        Polynom r12 = new Polynom("p11*p12");
        Polynom r21 = new Polynom("p11*p21");
        Polynom r22 = new Polynom("p12*p21");

        result = new Matrix(2, 2, Arrays.asList(r11, r12, r21, r22));

        Assert.assertEquals(result, test);
    }
}