package kapablankaNew.PolynoMatrix;

import org.junit.Assert;
import org.junit.Test;

public class PolynomTest {
    @Test
    public void testSimplify() {
        Polynom p1 = new Polynom("2*a + a");
        Polynom p2 = new Polynom("3*a");
        p1.simplify();
        Assert.assertEquals(p2, p1);

        p1 = new Polynom("3*c + 2 + 4*c - 2");
        p2 = new Polynom("7*c");
        p1.simplify();
        Assert.assertEquals(p2, p1);

        p1 = new Polynom("3*a - 2*c + 2*c - 3*a");
        p2 = new Polynom("0");
        p1.simplify();
        Assert.assertEquals(p2, p1);
    }

    @Test
    public void testEquals() {
        Polynom p1 = new Polynom("2*a - 3*b");
        Polynom p2 = new Polynom("-3*b + 2*a");
        Assert.assertTrue(p1.equals(p2));

        p1 = new Polynom("2*p1*p2");
        p2 = new Polynom("2*p2*p1");
        Assert.assertTrue(p1.equals(p2));

        p1 = new Polynom("2");
        p2 = new Polynom("3 - 1");
        Assert.assertTrue(p1.equals(p2));

        p1 = new Polynom("2*a + 4*b - a");
        p2 = new Polynom("5*b + a - b");
        Assert.assertTrue(p1.equals(p2));
    }

    @Test
    public void testAdd() {
        Polynom p1 = new Polynom("a + b");
        Polynom p2 = new Polynom("3*a + 2*b");
        Polynom result = new Polynom("4*a + 3*b");
        Assert.assertEquals(p1.add(p2), result);

        p1 = new Polynom("2*a + 3*b");
        p2 = new Polynom("3*b + 2*c");
        result = new Polynom("2*c + 2*a + 6*b");
        Assert.assertEquals(p1.add(p2), result);

        p1 = new Polynom("2*a - 3*b");
        p2 = new Polynom("3*b + 2*c");
        result = new Polynom("2*c + 2*a");
        Assert.assertEquals(p1.add(p2), result);

        p1 = new Polynom("1");
        p2 = new Polynom("3*b - 2");
        result = new Polynom("3*b - 1");
        Assert.assertEquals(p1.add(p2), result);
    }

    @Test
    public void testSubtract() {
        Polynom p1 = new Polynom("3*a + 2*b");
        Polynom p2 = new Polynom("a + b");
        Polynom result = new Polynom("2*a + b");
        Assert.assertEquals(p1.subtract(p2), result);

        p1 = new Polynom("2*a + 3*b");
        p2 = new Polynom("3*b + 2*c");
        result = new Polynom("2*a - 2*c");
        Assert.assertEquals(p1.subtract(p2), result);

        p1 = new Polynom("2*a - 3*b");
        p2 = new Polynom("3*b + 2*c");
        result = new Polynom("-2*c -6*b + 2*a");
        Assert.assertEquals(p1.subtract(p2), result);

        p1 = new Polynom("1");
        p2 = new Polynom("3*b - 2");
        result = new Polynom("-3*b + 3");
        Assert.assertEquals(p1.subtract(p2), result);
    }

    @Test
    public void testMultiply (){
        Polynom p1 = new Polynom("a");
        Polynom p2 = new Polynom("2");
        Polynom result = new Polynom("2*a");
        Assert.assertEquals(p1.multiply(p2), result);

        p1 = new Polynom("a");
        p2 = new Polynom("0.1 + b");
        result = new Polynom("0.1*a + a*b");
        Assert.assertEquals(result, p1.multiply(p2));

        p1 = new Polynom("2*a + 3*c");
        p2 = new Polynom("5");
        result = new Polynom("10*a + 15*c");
        Assert.assertEquals(p1.multiply(p2), result);

        p1 = new Polynom("0.2 + 0.4*c");
        p2 = new Polynom("0.5 + 0.5*b");
        result = new Polynom("0.1 + 0.2*c + 0.1*b + 0.2*b*c");
        Assert.assertEquals(result, p1.multiply(p2));

        p1 = new Polynom("a + b");
        p2 = new Polynom("a + b");
        result = new Polynom("a*a + 2*a*b + b*b");
        Assert.assertEquals(p1.multiply(p2), result);

        p1 = new Polynom("a - b");
        p2 = new Polynom("a - b");
        result = new Polynom("a*a - 2*a*b + b*b");
        Assert.assertEquals(p1.multiply(p2), result);

        p1 = new Polynom("a - b");
        p2 = new Polynom("a + b");
        result = new Polynom("a*a - b*b");
        Assert.assertEquals(p1.multiply(p2), result);

        p1 = new Polynom("2*a + 4*b - c");
        p2 = new Polynom("a - 2*b + 3*c");
        result = new Polynom("2*a*a - 8*b*b - 3*c*c + 5*a*c + 14*b*c");
        Assert.assertEquals(p1.multiply(p2), result);
    }
}