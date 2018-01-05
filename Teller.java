/*
 * Copyright (c) 2015-2017 Annie Hui @ NVCC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// Usage: Either run it in commandline with inputs as arguments,
//        or run in console and enter inputs interactively.

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Teller {
    String inputs = null;
    
    private Teller() {}
    public Teller(String args[]) {
        // If user provides arguments in commandline, convert them to a string of inputs.
        String data = "";
        for (String s : args) {
            data += s + " ";
        }
        if (data.trim().length()>0) inputs = data.trim();
    }
    
    double getInput(Scanner in) {
        // If no input is provided from commandline, then as for input interactively.
        // Else, make sure inputs are not exhausted. 
        if (inputs==null)
            System.out.print("Enter an amount (truncated to 2 decimal places):  ");
        else if (!in.hasNext()) 
            return 0;
        
        // As long as there is a possibility for more inputs, continue reading.
        double value = in.nextDouble();
        DecimalFormat df = new DecimalFormat(".##");
        df.setRoundingMode(RoundingMode.DOWN);
        // Truncate digits after 2 decimal places.
        // Use the truncated Result, instead of the input request, as the request. 
        double request = Double.parseDouble(df.format(value));
        System.out.println("You have requested $" + request);
        return request;
    }

    void report(int coins[], double actual, double request) {
        // Report results.
        System.out.print("Dispensing:");
        System.out.print("  " + coins[0] + " dollars");
        System.out.print("  " + coins[1] + " quarters");
        System.out.print("  " + coins[2] + " dimes");
        System.out.print("  " + coins[3] + " nickels");
        System.out.print("  " + coins[4] + " pennies");
        System.out.println("\nTotal dispensed = $" + actual);

        // Cross verify using a simple algorithm to make sure the dispensed amount
        // matches the reported total.
        boolean acceptable = verify(coins, actual);
        if (!acceptable) {
            System.out.println("ALERT!!! Coins dispensed mismatch total!");
        }

        // We expect the total amount dispensed to be exactly equal to the request
        if (actual==request && acceptable)
            System.out.println("Correct.");
        else 
            System.out.println("Incorrect.");
    }
    
    boolean verify(int coins[], double actualReported) {
        double tolerance = 0.0001; // Set tolerance at 1/100 of a cent.
        double sum = coins[0] * 1.0 + coins[1] * 0.25 + coins[2] * 0.1 + coins[3] * 0.05 + coins[4] * 0.01;
        if (Math.abs(actualReported-sum)<tolerance) return true;
        else return false;
    }
    
    
    public void operate() {
        Scanner in;
        // If inputs are provides as command line arguments, use them. 
        // Else, use stdio.
        if (inputs!=null) 
            in = new Scanner(inputs);
        else
            in = new Scanner(System.in);
        
        Dispenser dispenser = new Dispenser();
        
        double request = getInput(in);
        while (request>0.0) {
            Result result = dispenser.dispense(request);
            report(result.coins, result.total, request);
            request = getInput(in);
        }
    }
    
    public static class Result {
        int coins[];
        double total;
        private Result() {}
        public Result(int coins[], double total) {
            this.coins = coins;
            this.total = total;
        }
    }
    
    public static void main(String[] args) {
        Teller teller = new Teller(args);
        teller.operate();
    }
}
 