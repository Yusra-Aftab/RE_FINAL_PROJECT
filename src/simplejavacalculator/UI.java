/**
 * A Simple Java Calculator User Interface.
 * 
 * @author SORIA Pierre-Henry
 * @email pierrehs@hotmail.com
 * @link http://github.com/pH-7
 * @copyright Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * @create 2012-03-30
 *
 * @modifiedby Achintha Gunasekara
 * @modifiedby Kydon Chantzaridis
 * @modweb http://www.achinthagunasekara.com
 * @modemail contact@achinthagunasekara.com
 * @modemail kchantza@csd.auth.gr
 */

 package simplejavacalculator;

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.IOException;
 
 public class UI implements ActionListener {
 
     private final JFrame frame;
     private final JPanel panel;
     private final JPanel[] panels;
     private final JTextArea text;
     private final JButton[] digitButtons;
     private final JButton[] operatorButtons;
     private final JButton[] specialFunctionButtons;
     private final Calculator calc;
     private final Font font;
     private final Font textFont;
 
     public UI() throws IOException {
         frame = new JFrame("Calculator PH");
         panel = new JPanel();
         panels = new JPanel[8];
         text = new JTextArea(1, 30);
         font = new Font("Consolas", Font.PLAIN, 18);
         textFont = new Font("Consolas", Font.BOLD, 24);
         digitButtons = new JButton[10];
         operatorButtons = new JButton[]{
                 new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/"),
                 new JButton("x^y"), new JButton("sqrt"), new JButton("x*x"), new JButton("1/x"),
                 new JButton("Cos"), new JButton("Sin"), new JButton("Tan"),
                 new JButton("ln"), new JButton("log10(x)"), new JButton("x%"),
                 new JButton("abs(x)"), new JButton("C"), new JButton("Bin"),
                 new JButton("!"), new JButton("(+)^2"),
                 new JButton("C(n, r)"), new JButton("P(n, r)")
         };
         specialFunctionButtons = new JButton[]{new JButton("Binary")};
         calc = new Calculator();
     }
 
     public void initialize() {
         frame.setSize(450, 450);
         frame.setLocationRelativeTo(null);
         frame.setResizable(false);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         text.setFont(textFont);
         text.setEditable(false);
 
         for (int i = 0; i < 10; i++) {
             digitButtons[i] = new JButton(String.valueOf(i));
             digitButtons[i].setFont(font);
             digitButtons[i].addActionListener(this);
         }
 
         for (JButton operatorButton : operatorButtons) {
             operatorButton.setFont(font);
             operatorButton.addActionListener(this);
         }
 
         for (JButton specialFunctionButton : specialFunctionButtons) {
             specialFunctionButton.setFont(font);
             specialFunctionButton.addActionListener(this);
         }
 
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         for (int i = 0; i < panels.length; i++) {
             panels[i] = new JPanel(new FlowLayout());
             panel.add(panels[i]);
         }
 
         panels[0].add(Box.createHorizontalStrut(100));
         panels[0].add(text);
         panels[1].add(digitButtons[1]);
         panels[1].add(digitButtons[2]);
         panels[1].add(digitButtons[3]);
         panels[1].add(Box.createHorizontalStrut(15));
         for (JButton operatorButton : operatorButtons) {
             panels[1].add(operatorButton);
         }
         panels[2].add(digitButtons[4]);
         panels[2].add(digitButtons[5]);
         panels[2].add(digitButtons[6]);
         panels[2].add(Box.createHorizontalStrut(15));
         for (JButton operatorButton : operatorButtons) {
             panels[2].add(operatorButton);
         }
         panels[3].add(digitButtons[7]);
         panels[3].add(digitButtons[8]);
         panels[3].add(digitButtons[9]);
         panels[3].add(Box.createHorizontalStrut(15));
         panels[3].add(operatorButtons[16]); // Binary button
         for (JButton operatorButton : operatorButtons) {
             panels[3].add(operatorButton);
         }
         panels[4].add(Box.createHorizontalStrut(92));
         panels[4].add(digitButtons[0]);
         panels[4].add(operatorButtons[11]); // ln button
         panels[4].add(Box.createHorizontalStrut(210));
         panels[4].add(operatorButtons[12]); // log button
         panels[4].add(operatorButtons[13]); // x% button
         panels[4].add(operatorButtons[14]); // abs(x) button
         panels[4].add(operatorButtons[17]); // Factorial button
         panels[4].add(operatorButtons[18]); // Square of Sum button
         panels[4].add(operatorButtons[19]); // Combination button
         panels[4].add(operatorButtons[20]); // Permutation button
         panels[5].add(operatorButtons[8]); // Cos button
         panels[5].add(operatorButtons[9]); // Sin button
         panels[5].add(operatorButtons[10]); // Tan button
         panels[6].add(operatorButtons[15]); // log10(x) button
         panels[6].add(operatorButtons[13]); // x% button
         panels[6].add(operatorButtons[14]); // abs(x) button
         panels[6].add(operatorButtons[16]); // Binary button
         frame.add(panel);
         frame.setVisible(true);
     }
 
     @Override
     public void actionPerformed(ActionEvent e) {
         final Object source = e.getSource();
         Double checkNum = null;
 
         for (int i = 0; i < 10; i++) {
             if (source == digitButtons[i]) {
                 text.replaceSelection(String.valueOf(i));
                 return;
             }
         }
 
         try {
             checkNum = Double.parseDouble(text.getText());
         } catch (NumberFormatException k) {
             // Handle the exception if needed
         }
 
         if (checkNum != null || source == operatorButtons[16]) {
             handleButtonClick(source);
         }
 
         text.selectAll();
     }
 
     private void handleButtonClick(Object source) {
         if (source instanceof JButton) {
             JButton clickedButton = (JButton) source;
 
             for (int i = 0; i < operatorButtons.length; i++) {
                 if (clickedButton == operatorButtons[i]) {
                     if (i == 16) {
                         parseToBinary();
                     } else {
                         handleOperatorClick(i);
                     }
                     return;
                 }
             }
 
             for (JButton specialFunctionButton : specialFunctionButtons) {
                 if (clickedButton == specialFunctionButton) {
                     parseToBinary();
                     return;
                 }
             }
         }
     }
 
     private void handleOperatorClick(int index) {
         if (index >= 0 && index < operatorButtons.length) {
             Calculator.BiOperatorModes biOperatorMode;
 
             switch (index) {
                 case 0:
                     biOperatorMode = Calculator.BiOperatorModes.add;
                     break;
                 case 1:
                     biOperatorMode = Calculator.BiOperatorModes.minus;
                     break;
                 case 2:
                     biOperatorMode = Calculator.BiOperatorModes.multiply;
                     break;
                 case 3:
                     biOperatorMode = Calculator.BiOperatorModes.divide;
                     break;
                 case 4:
                     biOperatorMode = Calculator.BiOperatorModes.xpowerofy;
                     break;
                 default:
                     throw new IllegalStateException("Unexpected value: " + index);
             }
 
             calc.calculateBi(biOperatorMode, reader());
             text.replaceSelection(operatorButtons[index].getText());
         }
     }
 
     private void parseToBinary() {
         try {
             text.setText(Long.toBinaryString(Long.parseLong(text.getText())));
         } catch (NumberFormatException ex) {
             System.err.println("Error while parsing to binary." + ex.toString());
         }
     }
 
     public Double reader() {
         Double num;
         String str;
         str = text.getText();
         num = Double.valueOf(str);
         return num;
     }
 }
 