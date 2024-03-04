package com.flowerinventory.management;

import javax.xml.soap.Detail;
import java.util.Scanner;

public class FlowerInventoryManagement {

    private static FlowerInventoryManagement flowerInventoryManagement = new FlowerInventoryManagement();

    static {
        System.out.println("Welcome to flowerShop");
        flowerInventoryManagement.showStocks();
        flowerInventoryManagement.takeOwnerInput();

    }

    private final int inventorySpace = 1000;
    Scanner scanner = new Scanner(System.in);
    FlowerShopOwner flowerShopOwner = FlowerShopOwner.getFlowershopOwner();
    private int roseQuantity = 100;
    private int lotusQuantity = 100;
    private int jasmineQuantity = 100;
    private int marigoldQuantity = 100;
    private int inventorySpaceLeft;


    private FlowerInventoryManagement() {

    }

    public static FlowerInventoryManagement getFlowerInventoryManagement() {
        if (flowerInventoryManagement == null) {
            return new FlowerInventoryManagement();
        }
        return flowerInventoryManagement;
    }

    private void takeOwnerInput() {
        try {
            System.out.println("Please enter your name : ");
            String flowershopOwnerName = scanner.nextLine();
            boolean flag = false;
            while (!flag) {
                if (flowershopOwnerName.equals("") || flowershopOwnerName.trim().equals("")) {
                    System.out.println("Dont give blank names!");
                    flowershopOwnerName = scanner.nextLine();
                } else {
                    flag = true;
                }
            }
            System.out.println("Please enter your address : ");
            String flowershopOwnerAddress = scanner.nextLine();
            flag = false;
            while (!flag) {
                if (flowershopOwnerAddress.equals("") || flowershopOwnerAddress.trim().equals("")) {
                    System.out.println("Dont give blank Address!");
                    flowershopOwnerAddress = scanner.nextLine();
                } else {
                    flag = true;
                }
            }
            System.out.println("Please enter your number : ");
            long flowershopOwnerNumber = scanner.nextLong();
            flag = false;
            while (!flag) {
                if (flowershopOwnerNumber < 1000000000L || flowershopOwnerNumber > 9999999999L) {
                    System.out.println("Enter 10-Digit number only");
                    flowershopOwnerNumber = scanner.nextLong();
                } else {
                    flag = true;
                }
            }
            flowerShopOwner.setInput(flowershopOwnerName, flowershopOwnerAddress, flowershopOwnerNumber);
            System.out.println("Registration successfull ");
            System.out.println("Details Recieved : " + flowerShopOwner);
        } catch (Exception e) {
            System.out.println("Error try again..");
            scanner.nextLine();
            takeOwnerInput();
        }
        takeFlowersinput();
    }

    private void takeFlowersinput() {
        try {
            showStocks();
            System.out.println("please give the amount of flower items in Kgs : ");
            System.out.println("Enter the amount for Rose : ");
            flowerShopOwner.setRoseQuantity(scanner.nextInt());
            System.out.println("Enter the amount for Lotus : ");
            flowerShopOwner.setLotusQuantity(scanner.nextInt());
            System.out.println("Enter the amount for Jasmine : ");
            flowerShopOwner.setJasmineQuantity(scanner.nextInt());
            System.out.println("Enter the amount for Marigold : ");
            flowerShopOwner.setMarigoldQuantity(scanner.nextInt());
            System.out.println("Do you want to Buy or Sell the Flowers\n type \'sell\' for buying or \n\t \'stock\' to stock");
            String ans = scanner.next().toLowerCase().trim();
            switch (ans) {
                case "sell":
                    sellFlowers();
                    showStocks();
                    continueProgram();
                    break;
                case "stock":
                    stockFlowers();
                    showStocks();
                    continueProgram();
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Error try again");
            takeFlowersinput();
        }
    }

    private void sellFlowers() {
        if (checkStocks() == 2) {
            System.out.println("Only Stocking ... Storage Empty");
        }
        if (roseQuantity >= flowerShopOwner.getRoseQuantity() &&
                lotusQuantity >= flowerShopOwner.getLotusQuantity() &&
                jasmineQuantity >= flowerShopOwner.getJasmineQuantity() &&
                marigoldQuantity >= flowerShopOwner.getMarigoldQuantity()) {
            roseQuantity -= flowerShopOwner.getJasmineQuantity();
            lotusQuantity -= flowerShopOwner.getLotusQuantity();
            jasmineQuantity -= flowerShopOwner.getJasmineQuantity();
            marigoldQuantity -= flowerShopOwner.getMarigoldQuantity();
            if (checkStocks() == 0) System.out.println("Items sold Successfully");
        } else {
            System.out.println("Not sufficient flowers");
        }
    }

    private void stockFlowers() {
        if (checkStocks() == 1) {
            System.out.println("Only Selling ... Storage Full");
        }
        if ((flowerShopOwner.getRoseQuantity()+roseQuantity+ flowerShopOwner.
                getLotusQuantity()+lotusQuantity + flowerShopOwner.
                getJasmineQuantity()+jasmineQuantity + flowerShopOwner.
                getMarigoldQuantity()+marigoldQuantity) <= inventorySpace) {
            roseQuantity += flowerShopOwner.getRoseQuantity();
            lotusQuantity += flowerShopOwner.getLotusQuantity();
            jasmineQuantity += flowerShopOwner.getJasmineQuantity();
            marigoldQuantity += flowerShopOwner.getMarigoldQuantity();
            if (checkStocks() == 0) System.out.println("Items Stocked Successfully");
        } else{
            System.out.println("Storage Full Open for selling");
        }
    }

    private void continueProgram() {
        try {
            System.out.println("Do you want to continue y/n ?");
            char ans = Character.toLowerCase(scanner.next(".").charAt(0));
            scanner.nextLine();
            switch (ans) {
                case 'y':
                    takeOwnerInput();
                    break;
                case 'n':
                    System.out.println("..Thanks for Visiting..");
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Error Try again...");
            continueProgram();
        }
    }

    private int calculateInventory() {
        inventorySpaceLeft = inventorySpace - (roseQuantity + lotusQuantity +
                jasmineQuantity + marigoldQuantity);
        return inventorySpaceLeft;
    }

    private int checkStocks() {
        calculateInventory();
        if (inventorySpaceLeft <= 0) {
            return 1;
        }
        if (inventorySpaceLeft > inventorySpace) {
            return 2;
        }
        return 0;
    }

    private void stockStatus() {
        if (checkStocks() == 1) {
            System.out.println("Storage full");
        }
        if (checkStocks() == 2) {
            System.out.println("Storage Empty");
        }
    }

    private void showStocks() {
        stockStatus();
            System.out.println("The Total stock remaining : " + flowerInventoryManagement +
                    "\n" + "Treasure Space left : " + inventorySpaceLeft);
        }

    @Override
    public String toString() {
        return " Stock [ " +
                "rose = " + roseQuantity +
                "kg, lotus = " + lotusQuantity +
                "kg, jasmine = " + jasmineQuantity +
                "kg, marigold = " + marigoldQuantity +
                "kg ]";
    }
}
