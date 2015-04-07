package com.stanislav.hamara.expensesmanager;

/**
 * Created by stan on 07/04/15.
 */
public class Expense {
    private String category;
    private String subcategory;
    private String description;
    private String currency;
    private int whole_currency;
    private int small_currency;
    private boolean receipt_retianed;

    public Expense(String c, String sc, String desc, String curr, int w, int s, boolean r){
        category = c;
        subcategory = sc;
        description = desc;
        currency = curr;
        whole_currency = w;
        small_currency = s;
        receipt_retianed = r;

    };

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public int getWhole_currency() {
        return whole_currency;
    }

    public int getSmall_currency() {
        return small_currency;
    }

    public boolean isReceipt_retianed() {
        return receipt_retianed;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", whole_currency=" + whole_currency +
                ", small_currency=" + small_currency +
                ", receipt_retianed=" + receipt_retianed +
                '}';
    }
}