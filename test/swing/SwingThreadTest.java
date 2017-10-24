package swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yan96in on 2017/10/24.
 */
public class SwingThreadTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new SwingThreadFrame();
        });
    }
}

class SwingThreadFrame extends JFrame{
    public SwingThreadFrame() {
        final JComboBox<Integer> combo=new JComboBox<>();
        combo.insertItemAt(Integer.MAX_VALUE,0);
        combo.setPrototypeDisplayValue(combo.getItemAt(0));
        combo.setSelectedIndex(0);

        JPanel panel=new JPanel();

        JButton goodButton=new JButton("Good");
    }
}
