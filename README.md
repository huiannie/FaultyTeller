# FaultyTeller
A simple project that explores the problems that arise from the limitations of floating point numbers.

### Description
[Teller.java](Teller.java) is the main class of this project. It implements a simple teller machine that operates in the following manner:

1. ask for a dollar amount to be converted to coins;
2. dispense the amount in the denominations of *dollars, quarters, dimes, nickels, pennies*;
3. verify the amount dispensed; 
until a non-positive input is received.

[Dispenser.java](Dispenser.java) implements a dispenser that is faulty due to improper handling of the floating point values. 

[Test.java](Test.java) is a simple test driver that may be used to test the implementation results.


### Tasks
The first task in this project is to fix the bugs in [Dispenser.java](Test.java) so that it (1) dispenses the correct quantities of coins, (2) computes the correct total amount dispensed.

After fixing the errors reported by [Test.java](Test.java), investigate the upper limit on the quantity of coins that this machine may accurately dispense.

### Further investigations
1. Provide an implementation which protects this machine when the user requests an amount that exceeds the limit of this machine.
2. Is it sensible to handle financial data using strings? Investigate the practical challenges of this approach.
