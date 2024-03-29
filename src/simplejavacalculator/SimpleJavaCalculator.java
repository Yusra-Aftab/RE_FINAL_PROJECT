/**
 * A Simple Java Calculator.
 * 
 * @author SORIA Pierre-Henry
 * @email pierrehs@hotmail.com
 * @link http://github.com/pH-7
 * @copyright Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * @created 2012-03-30
 * 
 * @modifiedby Achintha Gunasekara
 * @modweb http://www.achinthagunasekara.com
 * @modemail contact@achinthagunasekara.com
 */

 package simplejavacalculator;

 public class SimpleJavaCalculator {
     
     public static void main(String[] args) {
         try {
             UI uiCalculator = new UI();
             uiCalculator.initialize();
         } catch (Exception e) {
             System.out.println(e.getMessage());   
         }
     }
 }
 