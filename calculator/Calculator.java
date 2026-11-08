import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JLabel display = new JLabel("0", SwingConstants.RIGHT);
    double stored = 0;
    String op = "";
    boolean startNew = true;

    public Calculator() {
        super(getCalculatorTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(120, 180, 255));
        setLayout(new BorderLayout());

        display.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 16, 10, 16));
        add(display, BorderLayout.NORTH);

        JPanel keys = new JPanel(new GridLayout(5, 4, 8, 8));

        keys.setBackground(new Color(85, 85, 85));
        keys.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

        String[] labels = {
                "C", "±", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "−",
                "1", "2", "3", "+",
                "0", ".", "⌫", "="
        };

        for (int i = 0; i < labels.length; i++) {
            keys.add(makeButton(labels[i]));
        }
        add(keys, BorderLayout.CENTER);

        setSize(320, 460);
        setLocationRelativeTo(null);
    }

    // Picks the window title from the system language
    static String getCalculatorTitle() {
        String lang = java.util.Locale.getDefault().getLanguage();

        if (lang.equals("tr")) {
            return "Hesap Makinesi";
        } else if (lang.equals("de")) {
            return "Taschenrechner";
        } else if (lang.equals("fr")) {
            return "Calculatrice";
        } else if (lang.equals("es")) {
            return "Calculadora";
        } else if (lang.equals("it")) {
            return "Calcolatrice";
        } else if (lang.equals("ru")) {
            return "Калькулятор";
        } else {
            return "Calculator";
        }
    }

    JButton makeButton(String label) {
        JButton b = new JButton(label);
        b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setForeground(Color.WHITE);
        b.addActionListener(this);

        if (label.equals("=")) {
            b.setBackground(new Color(255, 215, 0));
        } else if (label.equals("+") || label.equals("−") || label.equals("×") || label.equals("÷")) {
            b.setBackground(new Color(59, 63, 78));
        } else {
            b.setBackground(new Color(42, 45, 55));
        }
        return b;
    }

    public void actionPerformed(ActionEvent e) {
        press(e.getActionCommand());
    }

    void press(String k) {
        String text = display.getText();
        if (text.equals("Error")) {
            text = "0";
            display.setText("0");
        }

        if (k.equals("C")) {
            display.setText("0");
            stored = 0;
            op = "";
            startNew = true;

        } else if (k.equals("⌫")) {
            if (text.length() > 1 && startNew == false) {
                display.setText(text.substring(0, text.length() - 1));
            } else {
                display.setText("0");
            }

        } else if (k.equals("±")) {
            showNum(Double.parseDouble(text) * -1);

        } else if (k.equals("%")) {
            showNum(Double.parseDouble(text) / 100);

        } else if (k.equals(".")) {
            if (startNew) {
                display.setText("0.");
                startNew = false;
            } else if (text.contains(".") == false) {
                display.setText(text + ".");
            }

        } else if (k.equals("+") || k.equals("−") || k.equals("×") || k.equals("÷")) {
            if (startNew && op.equals("") == false) {
                op = k;
            } else {
                calculate();
                op = k;
                startNew = true;
            }

        } else if (k.equals("=")) {
            calculate();
            op = "";
            startNew = true;

        } else {

            if (startNew || text.equals("0")) {
                display.setText(k);
            } else {
                display.setText(text + k);
            }
            startNew = false;
        }
    }

    void calculate() {
        double current = Double.parseDouble(display.getText());

        if (op.equals("")) {
            stored = current;
        } else if (op.equals("+")) {
            stored = stored + current;
            showNum(stored);
        } else if (op.equals("−")) {
            stored = stored - current;
            showNum(stored);
        } else if (op.equals("×")) {
            stored = stored * current;
            showNum(stored);
        } else if (op.equals("÷")) {
            if (current == 0) {
                display.setText("Error");
                stored = 0;
                op = "";
            } else {
                stored = stored / current;
                showNum(stored);
            }
        }
    }

    void showNum(double value) {
        String s = String.valueOf(value);
        if (s.endsWith(".0")) {
            s = s.substring(0, s.length() - 2);
        }
        display.setText(s);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setVisible(true);
    }
}
