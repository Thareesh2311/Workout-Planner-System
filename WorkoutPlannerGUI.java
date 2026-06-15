import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class Workout {

    private String name;
    private int duration;
    private int calories;

    public Workout(String name, int duration, int calories) {
        this.name = name;
        this.duration = duration;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Workout: " + name +
                " | Duration: " + duration + " mins" +
                " | Calories: " + calories;
    }
}

public class WorkoutPlannerGUI extends JFrame implements ActionListener {

    JTextField nameField;
    JTextField durationField;
    JTextField caloriesField;

    JButton addButton;
    JButton viewButton;
    JButton searchButton;
    JButton totalButton;
    JButton clearButton;

    JTextArea outputArea;

    ArrayList<Workout> workouts = new ArrayList<>();

    public WorkoutPlannerGUI() {

        setTitle("Workout Planner System");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading =
                new JLabel("WORKOUT PLANNER SYSTEM");
        heading.setBounds(220, 20, 300, 30);
        add(heading);

        JLabel nameLabel =
                new JLabel("Workout Name:");
        nameLabel.setBounds(50, 80, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 80, 200, 25);
        add(nameField);

        JLabel durationLabel =
                new JLabel("Duration:");
        durationLabel.setBounds(50, 130, 120, 25);
        add(durationLabel);

        durationField = new JTextField();
        durationField.setBounds(180, 130, 200, 25);
        add(durationField);

        JLabel caloriesLabel =
                new JLabel("Calories:");
        caloriesLabel.setBounds(50, 180, 120, 25);
        add(caloriesLabel);

        caloriesField = new JTextField();
        caloriesField.setBounds(180, 180, 200, 25);
        add(caloriesField);

        addButton = new JButton("Add Workout");
        addButton.setBounds(450, 80, 180, 35);
        addButton.addActionListener(this);
        add(addButton);

        viewButton = new JButton("View Workouts");
        viewButton.setBounds(450, 130, 180, 35);
        viewButton.addActionListener(this);
        add(viewButton);

        searchButton = new JButton("Search Workout");
        searchButton.setBounds(450, 180, 180, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        totalButton = new JButton("Total Duration");
        totalButton.setBounds(450, 230, 180, 35);
        totalButton.addActionListener(this);
        add(totalButton);

        clearButton = new JButton("Clear All");
        clearButton.setBounds(450, 280, 180, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 260, 350, 250);
        outputArea.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(outputArea);

        scroll.setBounds(50, 260, 350, 250);

        add(scroll);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

            try {

                String name =
                        nameField.getText();

                int duration =
                        Integer.parseInt(
                                durationField.getText());

                int calories =
                        Integer.parseInt(
                                caloriesField.getText());

                workouts.add(
                        new Workout(
                                name,
                                duration,
                                calories));

                outputArea.setText(
                        "Workout Added Successfully!");

                nameField.setText("");
                durationField.setText("");
                caloriesField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Valid Details!");
            }
        }
        else if (e.getSource() == viewButton) {

            if (workouts.isEmpty()) {

                outputArea.setText(
                        "No Workouts Found!");
                return;
            }

            String data =
                    "===== WORKOUTS =====\n\n";

            for (Workout w : workouts) {

                data += w + "\n";
            }

            outputArea.setText(data);
        }
        else if (e.getSource() == searchButton) {

            String search =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Workout Name");

            boolean found = false;

            for (Workout w : workouts) {

                if (w.getName()
                        .equalsIgnoreCase(search)) {

                    outputArea.setText(
                            "Workout Found\n\n" + w);

                    found = true;
                    break;
                }
            }

            if (!found) {

                outputArea.setText(
                        "Workout Not Found!");
            }
        }
        else if (e.getSource() == totalButton) {

            int total = 0;

            for (Workout w : workouts) {

                total += w.getDuration();
            }

            outputArea.setText(
                    "Total Workout Duration = "
                            + total + " mins");
        }
        else if (e.getSource() == clearButton) {

            workouts.clear();

            outputArea.setText(
                    "All Workouts Cleared!");
        }
    }

    public static void main(String[] args) {

        new WorkoutPlannerGUI();
    }
}