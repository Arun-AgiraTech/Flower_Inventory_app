package com.flowerinventory.management;

public class FlowerShopOwner {
    private String flowershopOwnerName;
    private String flowershopOwnerAddress;
    private long flowershopOwnerNumber;
    public static FlowerShopOwner flowerShopOwner;
    private int roseQuantity;
    private int lotusQuantity;
    private int jasmineQuantity;
    private int marigoldQuantity;
    private FlowerShopOwner(){

    }

    void setInput(String flowershopOwnerName, String flowershopOwnerAddress , long flowershopOwnerNumber){
        this.flowershopOwnerName = flowershopOwnerName;
        this.flowershopOwnerAddress = flowershopOwnerAddress;
        this.flowershopOwnerNumber = flowershopOwnerNumber;
    }

    public static FlowerShopOwner getFlowershopOwner() {
        if(flowerShopOwner==null){
            return new FlowerShopOwner();
        }
        return flowerShopOwner;
    }

    public int getRoseQuantity() {
        return roseQuantity;
    }

    public void setRoseQuantity(int roseQuantity) {
        this.roseQuantity = roseQuantity;
    }

    public int getLotusQuantity() {
        return lotusQuantity;
    }

    public void setLotusQuantity(int lotusQuantity) {
        this.lotusQuantity = lotusQuantity;
    }

    public int getJasmineQuantity() {
        return jasmineQuantity;
    }

    public void setJasmineQuantity(int jasmineQuantity) {
        this.jasmineQuantity = jasmineQuantity;
    }

    public int getMarigoldQuantity() {
        return marigoldQuantity;
    }

    public void setMarigoldQuantity(int marigoldQuantity) {
        this.marigoldQuantity = marigoldQuantity;
    }

    @Override
    public String toString() {
        return "Owner Details [ " +
                "Name : '" + flowershopOwnerName + '\'' +
                ", Address : '" + flowershopOwnerAddress + '\'' +
                ", Number : " + flowershopOwnerNumber +
                " ]";
    }
}
