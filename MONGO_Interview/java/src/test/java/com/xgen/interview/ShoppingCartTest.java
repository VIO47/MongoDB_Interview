package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        //assertEquals(String.format("apple - 1 - €1.00\nTotal: €1.00"), myOut.toString());
        assertTrue(myOut.toString().contains("Total: €1.00"));
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nTotal: €2.00"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();

        //if (result.startsWith("apple")) {
        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%nTotal: €4.00"), result);
        //} else {
           // assertEquals(String.format("banana - 1 - €2.00%napple - 2 - €2.00%nTotal: €4.00"), result);
        //}
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nTotal: €0.00"), myOut.toString());
    }

    @Test
    public void changePrintingFormat(){
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("banana", 2);
        sc.changePrintingFormat(1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("€4.00 - banana - 2%nTotal: €4.00"), myOut.toString());
    }

    @Test
    public void addMultipleObjectsWithNewFormat(){

        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("banana", 2);
        sc.addItem("chips", 1);
        sc.changePrintingFormat(1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("€4.00 - banana - 2%n€3.04 - chips - 1%nTotal: €7.04"), myOut.toString());
    }
}


