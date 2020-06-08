package cuie.project.consumption_converter;

import javafx.beans.property.*;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

public class SliderControl extends Control {

    // Usable attribute for ConverterControl bindings
    public static final String FORMATTED_INTEGER_PATTERN = "%,d";
    private static final PseudoClass INVALID_CLASS   = PseudoClass.getPseudoClass("invalid");

    private static final String regex = "[a-z]+";

    private final IntegerProperty value = new SimpleIntegerProperty();
    private final StringProperty userFacingText = new SimpleStringProperty();

    private final BooleanProperty invalid = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            pseudoClassStateChanged(INVALID_CLASS, get());
        }
    };
    private final BooleanProperty readOnly     = new SimpleBooleanProperty();

    public SliderControl() {
        initializeSelf();
        addValueChangeListener();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SliderSkin(this);
    }

    public void increase() {
        setValue(getValue() + 1000);
    }
    public void increaseSmall() {
        setValue(getValue() - 100);
    }
    public void decreaseSmall() {
        setValue(getValue() - 100);
    }
    public void decrease() {
        setValue(getValue() - 1000);
    }

    private void initializeSelf() {
         getStyleClass().add("business-control");
         setUserFacingText(convertToString(getValue()));
    }

    // Validation
    private void addValueChangeListener() {
        userFacingText.addListener((observable, oldValue, userInput) -> {
            if ((userInput == null || userInput.isEmpty() || userInput.matches(regex))) {
                setInvalid(true);
                return;
            } else {
               setInvalid(false);
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> {
            setInvalid(false);
            setUserFacingText(convertToString(newValue.intValue()));
        });
    }

    public void loadFonts(String... font){
        for(String f : font){
            Font.loadFont(getClass().getResourceAsStream(f), 0);
        }
    }

    public void addStylesheetFiles(String... stylesheetFile){
        for(String file : stylesheetFile){
            String stylesheet = getClass().getResource(file).toExternalForm();
            getStylesheets().add(stylesheet);
        }
    }

    private String convertToString(int newValue) {
        return String.format(FORMATTED_INTEGER_PATTERN, newValue);
    }

    // alle  Getter und Setter
    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public boolean isReadOnly() {
        return readOnly.get();
    }

    public BooleanProperty readOnlyProperty() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    public boolean getInvalid() {
        return invalid.get();
    }

    public BooleanProperty invalidProperty() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid.set(invalid);
    }

    public String getUserFacingText() {
        return userFacingText.get();
    }

    public StringProperty userFacingTextProperty() {
        return userFacingText;
    }

    public void setUserFacingText(String userFacingText) {
        this.userFacingText.set(userFacingText);
    }

    public boolean isInvalid() {
        return invalid.get();
    }

}
