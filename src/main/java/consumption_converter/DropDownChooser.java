package cuie.project.consumption_converter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

class DropDownChooser extends VBox {

    // Styling of Dropdown-Chooser
    private static final String STYLE_CSS = "dropDownChooser.css";

    // Slider + Control
    private SliderControl sliderControl;
    private Slider mwhSlider;

    // Size of Dropdown-Chooser
    private static final double ARTBOARD_WIDTH = 150;
    private static final double ARTBOARD_HEIGHT = 50;

    // Pane + HBox
    private Pane drawingPane;
    private HBox stackContainer;

    // Buttons to control Mwh value in the input field
    private Button decreaseButton;
    private Button increaseButton;
    private TextField inputField;

    // Sign inside the increase and decrease buttons
    private static final String PLUS = "+";
    private static final String MINUS = "-";

    // Property of value inside the input field
    private final DoubleProperty SomeValue = new SimpleDoubleProperty();

    // Constructor
    DropDownChooser(SliderControl sliderControl) {
        this.sliderControl = sliderControl;
        initializeSelf();
        initializeParts();
        layoutParts();
        setupBindings();
        initializeDrawingPane();
        setupValueChangeHandler();
        setupEventHandler();
        setupBindings();
    }

    private void initializeSelf() {
        getStyleClass().add("drop-down-chooser");
        String stylesheet = getClass().getResource(STYLE_CSS).toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeParts() {

        // Initialization of slider
        mwhSlider = new Slider(0, 100000, 0);

        // Initialization of input field
        inputField = new TextField();
        inputField.getStyleClass().add("inputField");
        inputField.setAlignment(Pos.CENTER);

        // Initialization of decrease button
        decreaseButton = new Button(MINUS);
        decreaseButton.getStyleClass().add("decreaseButton");
        decreaseButton.prefWidthProperty().bind(inputField.heightProperty());
        decreaseButton.minWidthProperty().bind(inputField.heightProperty());

        // Initialization of increase button
        increaseButton = new Button(PLUS);
        increaseButton.getStyleClass().add("increaseButton");
        increaseButton.prefWidthProperty().bind(inputField.heightProperty());
        increaseButton.minWidthProperty().bind(inputField.heightProperty());

        // Initialization of HBox
        stackContainer = new HBox();
        stackContainer.setPrefWidth(ARTBOARD_WIDTH);
        stackContainer.setPrefHeight(ARTBOARD_HEIGHT);
        stackContainer.getStyleClass().add("stackContainer");
    }

    private void initializeDrawingPane() {
        drawingPane = new Pane();
        drawingPane.getStyleClass().add("drawingPane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        stackContainer.getChildren().addAll(decreaseButton, inputField, increaseButton);
        stackContainer.setMargin(inputField, new Insets(5, 0, 0, 0));
        stackContainer.setMargin(decreaseButton, new Insets(5, 0, 0, 0));
        stackContainer.setMargin(increaseButton, new Insets(5, 0, 0, 0));

        getChildren().add(new VBox(mwhSlider, stackContainer));
    }

    private void setupEventHandler() {
        // Enable arrow keys for dec/inc
        inputField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    decrement();
                    keyEvent.consume();
                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    increment();
                    keyEvent.consume();
                }
            }
        });
        increaseButton.setFocusTraversable(false);
        increaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                increment();
                ae.consume();
            }
        });
        decreaseButton.setFocusTraversable(false);
        decreaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                decrement();
                ae.consume();
            }
        });
    }

    private void setupValueChangeHandler() {
        someValueProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    private void setupBindings() {
        mwhSlider.valueProperty().bindBidirectional(sliderControl.valueProperty());
        inputField.textProperty().bindBidirectional(SomeValue, new NumberStringConverter());
        mwhSlider.valueProperty().bindBidirectional(SomeValue);
    }

    // Incrementation of value
    private void increment() {
        setSomeValue(getSomeValue() + 10000);
    }

    // Decrementation of value
    private void decrement() {
        setSomeValue(getSomeValue() - 10000);
    }

    // Getter and Setter

    public double getSomeValue() {
        return SomeValue.get();
    }

    public DoubleProperty someValueProperty() {
        return SomeValue;
    }

    public void setSomeValue(double someValue) {
        this.SomeValue.set(someValue);
    }
}
