package mainboard;

import mainboard.boardengine.Task;
import mainboard.boardengine.TaskEngine;
import mainboard.boardengine.boardstyling.RoundedBorder;
import mainboard.boardengine.boardstyling.RoundedButton;
import mainboard.boardengine.boardstyling.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class MiniKanbanBoard extends JFrame {

    private RoundedPanel completeTaskPanel, revertTaskPanel;

    private JTextField inputTextField;

    private final Color DEFAULT_BG_COLOR = new Color(244, 246, 248);

    private final Font DEFAULT_BUTTON_FONT = new Font("Segoe UI", Font.PLAIN, 20);

    private final Color borderColor = new Color(130, 120, 110);

    private final TaskEngine  taskEngine = new TaskEngine();

    public MiniKanbanBoard() {

        setTitle("Mini-KanBan Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int MAX_WIDTH = 780;
        int MAX_HEIGHT = 500;

        setLayout(new BorderLayout(10,10));
        setSize(MAX_WIDTH, MAX_HEIGHT);

        getContentPane().setBackground(DEFAULT_BG_COLOR);
        setLocationRelativeTo(null);
        setResizable(false);

        add(createTopContainer(), BorderLayout.NORTH);
        add(createMidContainer(), BorderLayout.CENTER);
        add(createBottomContainer(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private RoundedButton createCompleteButton() {
        RoundedButton completeButton = new RoundedButton("Complete Task", 20);

        completeButton.setFont(DEFAULT_BUTTON_FONT);
        Color completeButtonColor = new Color(74, 139, 190);
        completeButton.setBackground(completeButtonColor);
        completeButton.setForeground(Color.WHITE);

        completeButton.addActionListener(e -> {
           taskEngine.complete();
           printOnPane();
        });

        return completeButton;
    }

    private RoundedButton createRevertButton() {
        RoundedButton revertButton = new RoundedButton("Reverted to Do", 20);

        revertButton.setFont(DEFAULT_BUTTON_FONT);
        Color revertButtonColor = new Color(226, 116, 53);
        revertButton.setBackground(revertButtonColor);
        revertButton.setForeground(Color.WHITE);

        revertButton.addActionListener(e -> {
           taskEngine.incomplete();
           printOnPane();
        });

        return revertButton;
    }

    private RoundedButton createTaskButton() {

        RoundedButton createTask = new RoundedButton("Create Task", 15);

        Color createButtonColor = new Color(26, 128, 229);
        createTask.setFont(DEFAULT_BUTTON_FONT);
        createTask.setBackground(createButtonColor);
        createTask.setForeground(Color.WHITE);

        createTask.addActionListener(e -> textHandler());

        return createTask;
    }

    private JScrollPane completeTaskScrollPane() {

        completeTaskPanel = new RoundedPanel(19, 2);
        completeTaskPanel.setBackground(new Color(43, 45, 49));
        completeTaskPanel.setLayout(new BoxLayout(completeTaskPanel, BoxLayout.Y_AXIS));

        JScrollPane completeScroll = new JScrollPane(completeTaskPanel);
        completeScroll.setBackground(Color.WHITE);
        completeScroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return completeScroll;
    }

    private JScrollPane revertTaskScrollPane() {
        revertTaskPanel = new RoundedPanel(19, 0);

        Color revertPanelBackground = new Color(43, 45, 49);
        revertTaskPanel.setBackground(revertPanelBackground);
        revertTaskPanel.setForeground(Color.BLACK);
        revertTaskPanel.setLayout(new BoxLayout(revertTaskPanel, BoxLayout.Y_AXIS));

        JScrollPane revertScroll = new JScrollPane(revertTaskPanel);
        revertScroll.setBackground(Color.WHITE);
        revertScroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return revertScroll;
    }

    private JTextField styleInputTextField() {
        inputTextField = new JTextField("Enter a new task description...");

        Font inputFieldFont = new Font("SansSerif", Font.PLAIN, 19);
        inputTextField.setFont(inputFieldFont);

        Color placeholderColor = new Color(107, 114, 128);
        Color activeTextColor = new Color(33, 35, 73);
        inputTextField.setForeground(placeholderColor);

        inputTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputTextField.getText().equals("Enter a new task description...")) {
                    inputTextField.setText("");
                    inputTextField.setForeground(activeTextColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputTextField.getText().trim().isEmpty()) {
                    inputTextField.setText("Enter a new task description...");
                    inputTextField.setForeground(placeholderColor);
                }
            }
        });

        inputTextField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(borderColor, 1, 15),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        inputTextField.setFocusable(true);
        SwingUtilities.invokeLater(() -> {
            if (inputTextField.getParent() != null) {
                inputTextField.getParent().requestFocusInWindow();
            }
        });

        return inputTextField;
    }

    private JPanel createTopContainer() {

        JPanel topContainer = new JPanel();

        topContainer.setLayout(new BorderLayout(3,3));
        topContainer.setBackground(DEFAULT_BG_COLOR);
        topContainer.setBorder(BorderFactory.createEmptyBorder(10,10, 10,10));

        topContainer.add(styleInputTextField(), BorderLayout.CENTER);
        topContainer.add(createTaskButton(), BorderLayout.EAST);

        return topContainer;
    }

    private JPanel createMidContainer() {
        JPanel midContainer = new JPanel();

        midContainer.setLayout(new GridLayout(1,2));
        midContainer.setBackground(DEFAULT_BG_COLOR);
        midContainer.setBorder(BorderFactory.createEmptyBorder());

        midContainer.add(completeTaskScrollPane());
        midContainer.add(revertTaskScrollPane());

        return midContainer;
    }

    private JPanel createBottomContainer() {

        JPanel bottomContainer = new JPanel();
        bottomContainer.setBackground(DEFAULT_BG_COLOR);
        bottomContainer.setLayout(new GridLayout(1,2,2,2));
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        bottomContainer.add(createCompleteButton());
        bottomContainer.add(createRevertButton());

        return bottomContainer;
    }

    private void textHandler() {
        String inputText = inputTextField.getText();

        if (!inputText.isEmpty()) {
            taskEngine.createTask(inputText.trim());
            printOnPane();
            inputTextField.setText("");
        }

    }

    public void printOnPane(){

        completeTaskPanel.removeAll();
        revertTaskPanel.removeAll();

        int index = 0;
        Font displayFont = new Font("Segoe UI", Font.PLAIN, 17);

        for (Task task : taskEngine.getCompleteTask()){
            index++;

            JLabel todoLabel = new JLabel(index +". "+task.taskName());
            todoLabel.setForeground(Color.WHITE);
            todoLabel.setFont(displayFont);

            completeTaskPanel.add(todoLabel);
        }

        int index2 = 0;
        for (Task incompleteTask : taskEngine.getIncompleteTask()) {
            index2++;
            JLabel doneLabel = new JLabel("Already done: "+ index2 +". "+ incompleteTask.taskName());
            doneLabel.setForeground(Color.LIGHT_GRAY);
            doneLabel.setFont(displayFont);

            revertTaskPanel.add(doneLabel);
        }

        completeTaskPanel.revalidate();
        completeTaskPanel.repaint();
        revertTaskPanel.revalidate();
        revertTaskPanel.repaint();
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(MiniKanbanBoard::new); }

}
