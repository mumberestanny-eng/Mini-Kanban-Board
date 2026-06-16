import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MiniKanbanBoard extends JFrame {

    private JPanel completeTaskPanel, revertTaskPanel;
    private JButton createTask, complete, revert;
    private JTextField createField;
    private Color backGround = new Color(82, 92, 61, 255);

    private ArrayList<Task> todoList = new ArrayList<>();
    private ArrayList<Task> doneList = new ArrayList<>();

    private boolean isDone = false;
    private boolean toDone = false;



    public MiniKanbanBoard() {
        setTitle("Mini-KanBan Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        setSize(680, 400);
        getContentPane().setBackground(backGround);
        setLocationRelativeTo(null);
        setResizable(false);

        Color panelBorderColor = new Color(130, 120, 110);
        JPanel tPanel, cPanel, bPanel;

        tPanel = new JPanel();
        tPanel.setBackground(backGround);
        tPanel.setLayout(new BorderLayout(3,3));
           tPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
             tPanel.setBorder(BorderFactory.createTitledBorder(new RoundedBorder(panelBorderColor, 2, 15), "Add Task to do"));



        // 1. Text Field Styling
        createField = new JTextField(39);
          createField.setBackground(Color.BLACK);
            createField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
              createField.setForeground(Color.WHITE);
                createField.setBorder(BorderFactory.createCompoundBorder(
                      new RoundedBorder(panelBorderColor, 1, 10),
                            BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        createTask = new RoundedButton("Create Task", 12);
          createTask.setFont(new Font("Arial", Font.PLAIN, 15));
           createTask.setBackground(Color.CYAN);
              createTask.setForeground(Color.BLACK);
                 createTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = createField.getText();
                if(!text.isEmpty()){
                    Task task = new Task(text);
                    todoList.add(task);

                    createField.setText("");
                }
                printOnScreen();
            }
        });

        tPanel.add(createField, BorderLayout.WEST);
        tPanel.add(createTask, BorderLayout.EAST);

        cPanel = new JPanel();
           cPanel.setBackground(backGround);
            cPanel.setForeground(backGround);
              cPanel.setLayout(new GridLayout(1,2,2,2));
                 cPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
                    cPanel.setBorder(BorderFactory.createTitledBorder(
                            new RoundedBorder(panelBorderColor, 2, 15), "Task Management"));
        completeTaskPanel = new JPanel();
          completeTaskPanel.setBackground(Color.BLACK);
            completeTaskPanel.setLayout(new BoxLayout(completeTaskPanel, BoxLayout.Y_AXIS));

        JScrollPane completeScroll = new JScrollPane(completeTaskPanel);
           completeScroll.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        // 3. Upgrading Column Scroll Borders
              completeScroll.setBorder(BorderFactory.createTitledBorder(
                new RoundedBorder(panelBorderColor, 2, 15), "To Do (todoList)"));

        revertTaskPanel = new JPanel();
          revertTaskPanel.setBackground(new Color(85, 74, 55));
           revertTaskPanel.setForeground(Color.BLACK);
            revertTaskPanel.setLayout(new BoxLayout(revertTaskPanel, BoxLayout.Y_AXIS));


        JScrollPane revertScroll = new JScrollPane(revertTaskPanel);
           revertScroll.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
             revertScroll.setBorder(BorderFactory.createTitledBorder(
                new RoundedBorder(panelBorderColor, 2, 15), "Done (doneList)"));

       cPanel.add(completeScroll);
       cPanel.add(revertScroll);

       bPanel = new JPanel();
         bPanel.setBackground(backGround);
           bPanel.setLayout(new GridLayout(1,2,2,2));
             bPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
               bPanel.setBorder(BorderFactory.createTitledBorder(
                       new RoundedBorder(panelBorderColor, 2, 15), "Set the Command"));

       complete = new RoundedButton("Complete Task →", 20);
          complete.setFont(new Font("Arial", Font.PLAIN, 15));
            complete.setBackground(new Color(60, 179, 113)); // Medium Sea Green for accomplishment!
              complete.setForeground(Color.WHITE);

       revert = new RoundedButton("← Reverted to Do", 20);
          revert.setFont(new Font("Arial", Font.PLAIN, 15));
            revert.setBackground(new Color(186, 45, 45)); // Crimson Red for rolling back

       bPanel.add(complete);
       bPanel.add(revert);

       complete.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
              isDone = true;
              swappTo();
           }
       });
       revert.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
               toDone = true;
               swappTo();
           }
       });

        add(tPanel, BorderLayout.NORTH);
        add(cPanel, BorderLayout.CENTER);
        add(bPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new  MiniKanbanBoard().setVisible(true));
    }

    public void printOnScreen(){
        completeTaskPanel.removeAll();
        revertTaskPanel.removeAll();

        for(int i = 0; i <= todoList.size() - 1; i++){

            Task task = todoList.get(i);
            JLabel todoLabel = new JLabel((i+1)+". "+task.getTaskName());
            todoLabel.setForeground(Color.WHITE);
            todoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

            completeTaskPanel.add(todoLabel);
        }
        for (int j = 0; j < doneList.size(); j++) {

            Task task = doneList.get(j);
            JLabel doneLabel = new JLabel("Already done: "+(j+1)+". "+task.getTaskName());
            doneLabel.setForeground(Color.BLACK);
            doneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

            revertTaskPanel.add(doneLabel);
        }
        completeTaskPanel.revalidate();
        completeTaskPanel.repaint();
        revertTaskPanel.revalidate();
        revertTaskPanel.repaint();
    }
    public void swappTo(){
        if(isDone && !todoList.isEmpty()){

            Task swapTask = todoList.remove(0);
            doneList.add(0, swapTask);

            printOnScreen();
            isDone = false;
        }
        if (toDone && !doneList.isEmpty()){

            Task swapTask = doneList.remove(0);
            todoList.add(0,  swapTask);

            printOnScreen();
            toDone = false;
        }
    }

}
