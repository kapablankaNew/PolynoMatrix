package kapablankaNew.PolynoMatrix;

import junit.framework.TestCase;
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
        Assert.assertEquals(p1, p2);

        p1 = new Polynom("2*p1*p2");
        p2 = new Polynom("2*p2*p1");

        Assert.assertEquals(p1, p2);

        p1 = new Polynom("2");
        p2 = new Polynom("3 - 1");
        Assert.assertEquals(p1, p2);

        p1 = new Polynom("2*a + 4*b - a");
        p2 = new Polynom("5*b + a - b");
        Assert.assertEquals(p1, p2);
    }
}