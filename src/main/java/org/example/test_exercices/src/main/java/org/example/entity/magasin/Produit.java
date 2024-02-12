package org.example.entity.magasin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    private String itemName;
     private int  sellin;
     private int quality;
     private String category;



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSellin() {
        return sellin;
    }

    public void setSellin(int sellin) {
        this.sellin = sellin;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void updateProductSettings(){

        this.sellin--;

        if ("brie vieilli".equals(this.itemName)) {
            if (this.quality < 50) {
                this.quality++;
            }
        } else {
            if (this.quality > 0) {
                this.quality--;

                if (sellin < 0) {
                    this.quality--;
                }
            }

            if ("pdt laitier".equals(this.category) && this.quality > 0) {
                this.quality--;
            }
        }

        if (this.quality < 0) {
            this.quality = 0;
        }

        if (this.quality > 50) {
            this.quality = 50;
        }
    }

    }



