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

// Use this driver to test the program for a broad range of inputs.

import java.text.DecimalFormat;


public class Test {
    static void test1() {
        int count = 0;
        double start = 0.01;
        double end = 100.00;
        double interval = 0.01;

        count = (int) (Math.ceil((end-start)/interval)) + 1;
        DecimalFormat df = new DecimalFormat(".##");

        String requests[] = new String[count];
        for (int i=0; i<count; i++) {
            requests[i] = df.format(start + i*interval);
        }
        Teller teller = new Teller(requests);
        teller.operate();        
    }
    
    static void test2() {
        // Sample 100 points between the start and the end, inclusively.
        int count = 100;
        double start = Integer.MAX_VALUE;
        double end = Integer.MAX_VALUE * (1+0.25+0.1+0.05+0.01);
        double interval = (end-start)/(count-1);

        DecimalFormat df = new DecimalFormat(".##");

        String requests[] = new String[count];
        for (int i=0; i<count; i++) {
            requests[i] = df.format(start + i*interval);
        }
        Teller teller = new Teller(requests);
        teller.operate();        
    }
    
    public static void main(String[] args) {
        if (args.length==1 && args[0].equals("1"))
            test1();
        else if (args.length==1 && args[0].equals("2"))
            test2();
        else { // default: run all.
            test1();
            test2();            
        }
    }
}
