package org.example.entity.magasin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProduitTest {



    @Test
    void testMilkProductWithAQualityUnder50AndSellinOver0(){
        Produit produit = new Produit();
        produit.setQuality(45);
        produit.setSellin(2);
        produit.setItemName("lait");
        produit.setCategory("pdt laitier");



        produit.updateProductSettings();

        Assertions.assertEquals(43, produit.getQuality());
        Assertions.assertEquals(1, produit.getSellin());

    }

    @Test
    void testMilkProductWithAQualityEqual50AndSellinOver0(){
        Produit produit1 = new Produit();
        produit1.setQuality(50);
        produit1.setSellin(2);
        produit1.setItemName("lait");
        produit1.setCategory("pdt laitier");



        produit1.updateProductSettings();

        Assertions.assertEquals(48, produit1.getQuality());
        Assertions.assertEquals(1, produit1.getSellin());

    }

    @Test
    void testBrieVieilliProductWithAQualityEqual35AndSellinOver0(){
        Produit produit2 = new Produit();
        produit2.setQuality(35);
        produit2.setSellin(10);
        produit2.setItemName("brie vieilli");
        produit2.setCategory("pdt laitier");



        produit2.updateProductSettings();

        Assertions.assertEquals(36, produit2.getQuality());
        Assertions.assertEquals(9, produit2.getSellin());

    }
  @Test
    void testOutOfDateProductWithAQualityEqual35AndSellinUnder0(){
        Produit produit3 = new Produit();
      produit3.setQuality(35);
      produit3.setSellin(0);
      produit3.setItemName("escargot");
      produit3.setCategory("dégeu");



      produit3.updateProductSettings();

        Assertions.assertEquals(33, produit3.getQuality());
        Assertions.assertEquals(-1, produit3.getSellin());

    }


}
