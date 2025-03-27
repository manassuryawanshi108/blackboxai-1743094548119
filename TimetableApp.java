import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimetableApp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JButton addButton, removeButton;

    public TimetableApp() {
        frame = new JFrame("Editable Timetable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create table model with days and time slots
        String[] columns = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Only time column is not editable
            }
        };

        // Add time slots (8am to 5pm)
        for (int hour = 8; hour <= 17; hour++) {
            model.addRow(new Object[]{String.format("%02d:00", hour), "", "", "", "", ""});
        }

        table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);

        // Add buttons panel
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Row");
        removeButton = new JButton("Remove Row");
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{"", "", "", "", "", ""});
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    model.removeRow(table.getSelectedRow());
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimetableApp().show();
            }
        });
    }
}