package com.example.projectbakery;

import org.junit.Assert;
import org.junit.Test;


public class UnitTests {

    @Test
    public void inventoryItemTest() {
        InventoryItem testDough = new InventoryItem("sourdough", "dough", 10);
        Assert.assertEquals("sourdough", testDough.getName());
        Assert.assertEquals("dough", testDough.getCategory());
        Assert.assertEquals(10, testDough.getAmount());

        InventoryItem testLiquid = new InventoryItem("frosting", "liquid", 20);
        Assert.assertEquals("frosting", testLiquid.getName());
        Assert.assertEquals("liquid", testLiquid.getCategory());
        Assert.assertEquals(20, testLiquid.getAmount());
    }

    @Test
    public void JSONwriter() {
        //TODO: Test JSON writing
    }

    @Test
    public void JSONreader() {
        //TODO: Test JSON reading
    }

    @Test
    public void buildList() {
        //TODO: Test bulid list
    }
}