package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    HashMap<String, Integer> contents = new HashMap<>();
    Pricer pricer;
    //Use a numeric printing code that will express the format
    //0 will be the default printing with the price being last on the line
    //1 will have the price shown forst
    //Scalability can be done by adding another numeric code and new case to the if-else staments inside printReceipt()
    int printingCode = 0;
    //Use a sorted map for the sequence of products to keep order of scanning
    SortedMap<String, Integer> cart = new TreeMap<>();

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            cart.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            cart.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();
        //float variable that will keep the total
        float total = 0.0f;
        //Create iterator that will traverse the tree and compute the same as before the price
        Iterator it = cart.keySet().iterator();

        while(it.hasNext()){
            String product = (String) it.next();
            int amount = (int) cart.get(product);
            int price = pricer.getPrice(product) * amount;
            Float priceFloat = new Float(new Float(price) / 100);
            total  += priceFloat;
            String priceString = String.format("€%.2f", priceFloat);

            if(printingCode == 0){
                System.out.println(product + " - " + amount + " - " + priceString);
            }
            else if (printingCode == 1){
                System.out.println(priceString + " - " + product + " - " + amount);
            }
            //If any other printing formats apprear, add new "if" case
        }

        String totalFormatted = String.format("€%.2f", total);
        System.out.print("Total: " + totalFormatted);

        /**for (int i = 0; i < Array.getLength(keys) ; i++) {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat = new Float(new Float(price) / 100);
            total  += priceFloat;
            String priceString = String.format("€%.2f", priceFloat);

            System.out.println(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
        }

         **/
    }

    /**
     * Method for changing the printing format to a custom one
     * Used only for testing
     * @param code desired printing format represented as a code
     */
    public void changePrintingFormat(Integer code){
        printingCode = code;
    }
}
