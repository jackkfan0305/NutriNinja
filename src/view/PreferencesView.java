package view;

import interface_adapter.generate_meal.GenerateMealController;
import interface_adapter.generate_random_meal.GenerateRandomMealController;
import interface_adapter.login.LoginController;
import interface_adapter.save_preferences.SavePreferencesController;
import interface_adapter.save_preferences.SavePreferencesState;
import interface_adapter.save_preferences.SavePreferencesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Preferences";

    JLabel username;

    final JButton savePreferences;
    final JButton generateMeal;
    final JButton feelingLucky;

    final JTextField minimumCalorieInputField = new JTextField(15);
    private final JLabel minimumCalorieErrorField = new JLabel();
    final JTextField maximumCalorieInputField = new JTextField(15);
    private final JLabel maximumCalorieErrorField = new JLabel();


    final JTextField minimumFatInputField = new JTextField(15);
    private final JLabel minimumFatErrorField = new JLabel();
    final JTextField maximumFatInputField = new JTextField(15);
    private final JLabel maximumFatErrorField = new JLabel();


    final JTextField minimumProteinInputField = new JTextField(15);
    private final JLabel minimumProteinErrorField = new JLabel();
    final JTextField maximumProteinInputField = new JTextField(15);
    private final JLabel maximumProteinErrorField = new JLabel();


    final JTextField minimumCarbInputField = new JTextField(15);
    private final JLabel minimumCarbErrorField = new JLabel();
    final JTextField maximumCarbInputField = new JTextField(15);
    private final JLabel maximumCarbErrorField = new JLabel();


    String[] healthPreferencesOptions = {"dairy-free", "fish-free", "gluten-free", "keto-friendly",
    "kosher", "low-sugar", "peanut-free", "pescatarian", "pork-free", "vegan", "vegetarian"};
    String[] dishTypeOptions = {"Deserts", "Main Course", "Salad", "Sandwiches", "Side Dish", "Soup", "Starter"};
    String[] mealTypeOptions = {"Breakfast", "Lunch", "Dinner", "Snack"};


    final JList<String> healthPreferencesInputField = new JList<>(healthPreferencesOptions);
    private final JLabel healthPreferencesErrorField = new JLabel();

    final JList<String> dishTypeInputField = new JList<>(dishTypeOptions);
    private final JLabel dishTypeErrorField = new JLabel();

    final JList<String> mealTypeInputField = new JList<>(mealTypeOptions);
    private final JLabel mealTypeErrorField = new JLabel();

    private final SavePreferencesController savePreferencesController;
    private final GenerateMealController generateMealController;
    private final GenerateRandomMealController generateRandomMealController;


    /**
     * A window with a title and a JButton.
     */
    public PreferencesView(SavePreferencesViewModel savePreferencesViewModel,
                           SavePreferencesController savePreferencesController,
                           GenerateMealController generateMealController,
                           GenerateRandomMealController generateRandomMealController) {
        savePreferencesViewModel.addPropertyChangeListener(this);
        this.savePreferencesController = savePreferencesController;
        this.generateMealController = generateMealController;
        this.generateRandomMealController = generateRandomMealController;

        JLabel title = new JLabel("Enter Preferences");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel healthPreferencesInfo = new JPanel();
        healthPreferencesInfo.add(new JLabel("Health Preferences"), healthPreferencesInputField);

        JPanel dishTypeInfo = new JPanel();
        dishTypeInfo.add(new JLabel("Dish Type"), dishTypeInputField);

        JPanel mealTypeInfo = new JPanel();
        mealTypeInfo.add(new JLabel("Meal Type"), mealTypeInputField);


        LabelTextPanel minimumCalorieInfo = new LabelTextPanel(
                new JLabel("Minimum Calories"), minimumCalorieInputField);
        LabelTextPanel maximumCalorieInfo = new LabelTextPanel(
                new JLabel("Maximum Calories"), maximumCalorieInputField);

        LabelTextPanel minimumFatInfo = new LabelTextPanel(
                new JLabel("Minimum Fat"), minimumFatInputField);
        LabelTextPanel maximumFatInfo = new LabelTextPanel(
                new JLabel("Maximum Fat"), maximumFatInputField);

        LabelTextPanel minimumProteinInfo = new LabelTextPanel(
                new JLabel("Minimum Protein"), minimumProteinInputField);
        LabelTextPanel maximumProteinInfo = new LabelTextPanel(
                new JLabel("Maximum Protein"), maximumProteinInputField);

        LabelTextPanel minimumCarbInfo = new LabelTextPanel(
                new JLabel("Minimum Carbs"), minimumCarbInputField);
        LabelTextPanel maximumCarbInfo = new LabelTextPanel(
                new JLabel("Maximum Carbs"), maximumCarbInputField);


        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        savePreferences = new JButton(SavePreferencesViewModel.SAVE_PREFERENCES_BUTTON_LABEL);
        generateMeal = new JButton((SavePreferencesViewModel.GENERATE_MEAL_BUTTON_LABEL));
        feelingLucky = new JButton((SavePreferencesViewModel.FEELING_LUCKY_BUTTON_LABEL));
        buttons.add(savePreferences);
        buttons.add(generateMeal);
        buttons.add(feelingLucky);


        savePreferences.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(savePreferences)) {
                            SavePreferencesState currentState = savePreferencesViewModel.getState();

                            savePreferencesController.execute(
                                    currentState.getNutrientRange().getCalorieRange(),
                                    currentState.getNutrientRange().getFatRange(),
                                    currentState.getNutrientRange().getProteinRange(),
                                    currentState.getNutrientRange().getCarbRange(),
                                    currentState.getHealthPreferences(),
                                    currentState.getDishType(),
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        generateMeal.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(generateMeal)) {
                            SavePreferencesState currentState = savePreferencesViewModel.getState();

                            generateMealController.execute(
                                    currentState.getHealthPreferences(),
                                    currentState.getMealType(),
                                    currentState.getDishType(),
                                    currentState.getNutrientRange().getCalorieRange().getLowerBound(),
                                    currentState.getNutrientRange().getCalorieRange().getUpperBound(),
                                    currentState.getNutrientRange().getCarbRange().getLowerBound(),
                                    currentState.getNutrientRange().getCarbRange().getUpperBound(),
                                    currentState.getNutrientRange().getProteinRange().getLowerBound(),
                                    currentState.getNutrientRange().getProteinRange().getUpperBound(),
                                    currentState.getNutrientRange().getFatRange().getLowerBound(),
                                    currentState.getNutrientRange().getFatRange().getUpperBound()

                            );
                        }
                    }
                }
        );

        feelingLucky.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(feelingLucky)) {
                            generateRandomMealController.execute();
                        }
                    }
                }
        );

        minimumCalorieInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getCalorieRange().setLowerBound(Integer.valueOf(minimumCalorieInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        maximumCalorieInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getCalorieRange().setUpperBound(Integer.valueOf(maximumCalorieInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        minimumFatInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getFatRange().setLowerBound(Integer.valueOf(minimumFatInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        maximumFatInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getFatRange().setUpperBound(Integer.valueOf(maximumFatInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        minimumProteinInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getProteinRange().setLowerBound(Integer.valueOf(minimumProteinInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        maximumProteinInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getProteinRange().setUpperBound(Integer.valueOf(maximumProteinInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        minimumCarbInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getCarbRange().setLowerBound(Integer.valueOf(minimumCarbInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        maximumCarbInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.getNutrientRange().getCarbRange().setUpperBound(Integer.valueOf(maximumCarbInputField.getText() + e.getKeyChar()));
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });




        healthPreferencesInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.setHealthPreferences(healthPreferencesInputField.getSelectedValuesList());
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dishTypeInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.setDishType(dishTypeInputField.getSelectedValuesList());
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        mealTypeInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SavePreferencesState currentState = savePreferencesViewModel.getState();
                currentState.setMealType(mealTypeInputField.getSelectedValuesList());
                savePreferencesViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);

        this.add(minimumCalorieInfo);
        this.add(minimumCalorieErrorField);
        this.add(minimumFatInfo);
        this.add(minimumFatErrorField);
        this.add(minimumProteinInfo);
        this.add(minimumProteinErrorField);
        this.add(minimumCarbInfo);
        this.add(minimumCarbErrorField);

        this.add(maximumCalorieInfo);
        this.add(maximumCalorieErrorField);
        this.add(maximumFatInfo);
        this.add(maximumFatErrorField);
        this.add(maximumProteinInfo);
        this.add(maximumProteinErrorField);
        this.add(maximumCarbInfo);
        this.add(maximumCarbErrorField);

        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SavePreferencesState state = (SavePreferencesState) evt.getNewValue();
        username.setText(state.getUsername());
    }



}