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

// A dispenser that IMPROPERLY handles currency. Find the bugs and fix them.

public class Dispenser {
    
    public Teller.Result dispense(double request) {
        int dollars = (int)request;
        double remainder1 = request - dollars;
        int quarters = (int)(remainder1 / 0.25);
        double remainder2 = remainder1 - quarters * 0.25;
        int dimes = (int)(remainder2 / 0.1);
        double remainder3 = remainder2 - dimes * 0.1;
        int nickels = (int)(remainder3 / 0.05);
        double remainder4 = remainder3 - nickels * 0.05;
        int pennies = (int)(remainder4 / 0.01);
        int coins[] = {dollars, quarters, dimes, nickels, pennies};
        double total = coins[0] * 1.0 + coins[1] * 0.25 + coins[2] * 0.1 + coins[3] * 0.05 + coins[4] * 0.01;
        
        return new Teller.Result(coins, total);
    }

}
