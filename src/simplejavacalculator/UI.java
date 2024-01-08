package simplejavacalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UI implements ActionListener {

    private static final int BUTTON_COUNT = 10;
    private static final Font FONT = new Font("Consolas", Font.PLAIN, 18);
    private static final Font TEXT_FONT = new Font("Consolas", Font.BOLD, 24);

    private final JFrame frame;
    private final JPanel panel;
    private final JTextArea text;
    private final JButton[] numericButtons;
    private final JButton[] operatorButtons;
    private final Calculator calc;

    public UI() throws Exception {
        frame = new JFrame("Calculator PH");
        BufferedImageCustom imageReturn = new BufferedImageCustom();
        ImageIcon image = new ImageIcon(imageReturn.imageReturn());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        text = new JTextArea(1, 30);

        numericButtons = new JButton[BUTTON_COUNT];
        operatorButtons = new JButton[]{
                new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/"),
                new JButton("="), new JButton("sqrt"), new JButton("x*x"), new JButton("1/x"),
                new JButton("Cos"), new JButton("Sin"), new JButton("Tan"), new JButton("ln"),
                new JButton("x^y"), new JButton("log10(x)"), new JButton("x%"), new JButton("abs(x)"),
                new JButton("C"), new JButton("Bin")
        };

        calc = new Calculator();
    }

    public void init() {
        createButtons();
        addComponentsToPanel();

        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new BufferedImageCustom().imageReturn());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void createButtons() {
        for (int i = 0; i < BUTTON_COUNT; i++) {
            numericButtons[i] = new JButton(String.valueOf(i));
            numericButtons[i].setFont(FONT);
            numericButtons[i].addActionListener(this);
        }

        for (JButton operatorButton : operatorButtons) {
            operatorButton.setFont(FONT);
            operatorButton.addActionListener(this);
        }
    }

    private void addComponentsToPanel() {
        panel.add(Box.createHorizontalStrut(100));
        panel.add(createPanelWithLayout(new FlowLayout(), text));

        panel.add(createPanelWithLayout(new FlowLayout(),
                numericButtons[1], numericButtons[2], numericButtons[3],
                Box.createHorizontalStrut(15), operatorButtons[0], operatorButtons[1]));

        panel.add(createPanelWithLayout(new FlowLayout(),
                numericButtons[4], numericButtons[5], numericButtons[6],
                Box.createHorizontalStrut(15), operatorButtons[2], operatorButtons[3]));

        panel.add(createPanelWithLayout(new FlowLayout(),
                numericButtons[7], numericButtons[8], numericButtons[9],
                Box.createHorizontalStrut(15), operatorButtons[4], operatorButtons[17]));

        panel.add(createPanelWithLayout(Box.createHorizontalStrut(92),
                numericButtons[0], operatorButtons[11], Box.createHorizontalStrut(210)));

        panel.add(createPanelWithLayout(operatorButtons[6], operatorButtons[5], operatorButtons[7], operatorButtons[12]));

        panel.add(createPanelWithLayout(operatorButtons[8], operatorButtons[9], operatorButtons[10]));

        panel.add(createPanelWithLayout(operatorButtons[13], operatorButtons[14], operatorButtons[15], operatorButtons[16]));
    }

    private JPanel createPanelWithLayout(LayoutManager layoutManager, Component... components) {
        JPanel subPanel = new JPanel(layoutManager);
        for (Component component : components) {
            subPanel.add(component);
        }
        return subPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        Double checkNum = null;

        for (int i = 0; i < BUTTON_COUNT; i++) {
            if (source == numericButtons[i]) {
                text.replaceSelection(String.valueOf(i));
                return;
            }
        }

        try {
            checkNum = Double.parseDouble(text.getText());
        } catch (NumberFormatException k) {
            // Handle the exception if needed
        }

        if (checkNum != null || source == operatorButtons[17]) {
            handleButtonClick(source, checkNum);
        }

        text.selectAll();
    }

    private void handleButtonClick(Object source, Double checkNum) {
        if (source == operatorButtons[0]) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.add, checkNum));
            text.replaceSelection(operatorButtons[0].getText());
        } else if (source == operatorButtons[1]) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.minus, checkNum));
            text.replaceSelection(operatorButtons[1].getText());
        } else if (source == operatorButtons[2]) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.multiply, checkNum));
            text.replaceSelection(operatorButtons[2].getText());
        } else if (source == operatorButtons[3]) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.divide, checkNum));
            text.replaceSelection(operatorButtons[3].getText());
        } else if (source == operatorButtons[11]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.ln, checkNum));
        } else if (source == operatorButtons[5]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, checkNum));
        } else if (source == operatorButtons[6]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.square, checkNum));
        } else if (source == operatorButtons[7]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.oneDividedBy, checkNum));
        } else if (source == operatorButtons[9]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.cos, checkNum));
        } else if (source == operatorButtons[10]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.sin, checkNum));
        } else if (source == operatorButtons[14]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.log, checkNum));
        } else if (source == operatorButtons[12]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.tan, checkNum));
        } else if (source == operatorButtons[13]) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, checkNum));
        } else if (source == operatorButtons[15]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.rate, checkNum));
        } else if (source == operatorButtons[16]) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.abs, checkNum));
        } else if (source == operatorButtons[17]) {
            writer(calc.calculateEqual(checkNum));
        } else if (source == operatorButtons[18]) {
            writer(calc.reset());
        } else if (source == operatorButtons[19]) {
            parsetoBinary();
        }
    }

    private void parsetoBinary() {
        try {
            text.setText(Long.toBinaryString(Long.parseLong(text.getText())));
        } catch (NumberFormatException ex) {
            System.err.println("Error while parse to binary." + ex.toString());
        }
    }

    private Double reader() {
        String str = text.getText();
        return Double.valueOf(str);
    }

    private void writer(final Double num) {
        if (Double.isNaN(num)) {
            text.setText("");
        } else {
            text.setText(Double.toString(num));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UI uiCal = new UI();
                uiCal.init();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
