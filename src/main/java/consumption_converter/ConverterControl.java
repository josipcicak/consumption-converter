package cuie.project.consumption_converter;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Authors: @BojanCalic @JosipCicak
 * Logo contributer: Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
 * <p>
 * Dieses SimpleControl bietet eine klare Übersicht welche Geräte im Haushalt am meisten Strom im Jahr verbrauchen.
 * Unser Converter nimmt den Jahresschnitt von den Geräten und teilt diese durch die erzeugte Enerige von den Windparks.
 */

public class ConverterControl extends Region {

    // Labels for Title and text next to logos
    private Label title;
    private Label einheitenFoehn;
    private Label einheitenWaschmachine;
    private Label einheitenFernseher;
    private Label einheitenRasen;

    // Labels for icon numbers - reference to years 2014-2018
    private Label firstYearLabel;
    private Label secondYearLabel;
    private Label thirdYearLabel;
    private Label fourthYearLabel;

    // Init image url from resource folder
    Image dryer = new Image("./appliances/dryer.png");
    Image washingMachine = new Image("./appliances/wash.png");
    Image tv = new Image("./appliances/monitor.png");
    Image mover = new Image("./appliances/yard.png");

    // Adding ImageView elements
    ImageView imgView = new ImageView();
    ImageView imgView2 = new ImageView();
    ImageView imgView3 = new ImageView();
    ImageView imgView4 = new ImageView();

    // StackPane for resizing and positining
    private StackPane drawingPane;

    // Properties for getting individual years
    private final IntegerProperty firstYear = new SimpleIntegerProperty();
    private final IntegerProperty secondYear = new SimpleIntegerProperty();
    private final IntegerProperty thirdYear = new SimpleIntegerProperty();
    private final IntegerProperty fourtfhYear = new SimpleIntegerProperty();

    // For free spacing trick
    private Region spacer;
    private Region shortSpacer;

    private Button button;

    public ConverterControl() {
        initializeSelf();
        initializeParts();
        layoutParts();
        setupBindings();
    }

    private void initializeSelf() {

        // CSS init and class
        addStylesheetFiles("style.css");
        getStyleClass().add("converter-control");
    }

    private void initializeParts() {

        // Labels for main init parts and showing its layout
        title = new Label("Erzeugte Leistung für:");
        title.getStyleClass().add("title-header");

        firstYearLabel = new Label();
        firstYearLabel.getStyleClass().add("dryerIMG");
        einheitenFoehn = new Label("Einheiten     ");
        einheitenFoehn.getStyleClass().add("einheiten-foehn");

        secondYearLabel = new Label();
        secondYearLabel.getStyleClass().add("washingMachineIMG");
        einheitenWaschmachine = new Label("Einheiten     ");
        einheitenWaschmachine.getStyleClass().add("einheiten-waschmachine");

        thirdYearLabel = new Label();
        thirdYearLabel.getStyleClass().add("tvIMG");
        einheitenFernseher = new Label("Einehiten     ");
        einheitenFernseher.getStyleClass().add("einheiten-fernseher");

        fourthYearLabel = new Label();
        fourthYearLabel.getStyleClass().add("moverIMG");
        einheitenRasen = new Label("Einheiten     ");
        einheitenRasen.getStyleClass().add("einheiten-rasen");

        // init images, important! with setImage we set the image elements next to our icons
        imgView.setImage(dryer);
        imgView.setFitWidth(50);
        imgView.setFitHeight(50);

        imgView2.setImage(washingMachine);
        imgView2.setFitWidth(50);
        imgView2.setFitHeight(50);

        imgView3.setImage(tv);
        imgView3.setFitWidth(50);
        imgView3.setFitHeight(50);

        imgView4.setImage(mover);
        imgView4.setFitWidth(50);
        imgView4.setFitHeight(50);

        // init pane
        drawingPane = new StackPane();
        drawingPane.getStyleClass().add("drawing-pane");

        spacer = new Region();
        spacer.setMinWidth(40);

        shortSpacer = new Region();
        shortSpacer.setMinWidth(40);

        button = new Button();
        button.getStyleClass().add("button-info-hover");

        // Added Tooltip for more information over InfoButton
        final Tooltip tooltipButtonInfo = new Tooltip();
        tooltipButtonInfo.setText("Die Einheiten werden als erzeugte Leistung für die einzeilnen Geräte angezeigt. \n" +
                "Wir haben unsere Berechnung alle in kwH dargestellt! \n" +
                "Folgende Berrechnungsmöglichkeiten: 1MwH = 1000 kwH,\ndanach teilen wir die gesammte Summe mit den Einzelnen werten angepasst auf das Gerät");
        tooltipButtonInfo.setShowDelay(Duration.millis(10));
        tooltipButtonInfo.setShowDuration(Duration.seconds(10));
        button.setTooltip(tooltipButtonInfo);
        button.setGraphic(new ImageView("./appliances/info.png"));
    }

    private void layoutParts() {

        // Adding all parts for UI, to keep our work structured we use VBox for the hole UI and HBox for individual elements
        HBox firstIconRow = new HBox(new VBox(firstYearLabel, einheitenFoehn), imgView);
        firstIconRow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        firstIconRow.getStyleClass().add("first-icon-row");
        firstIconRow.setMinHeight(50);
        firstIconRow.setMaxHeight(100);

        HBox secondIconRow = new HBox(new VBox(secondYearLabel, einheitenWaschmachine), imgView2);
        secondIconRow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        secondIconRow.getStyleClass().add("second-icon-row");
        secondIconRow.setMinHeight(50);
        secondIconRow.setMaxHeight(100);

        HBox thirdIconRow = new HBox(new VBox(thirdYearLabel, einheitenFernseher), imgView3);
        thirdIconRow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        thirdIconRow.getStyleClass().add("third-icon-row");
        thirdIconRow.setMinHeight(50);
        thirdIconRow.setMaxHeight(100);

        HBox fourthIconRow = new HBox(new VBox(fourthYearLabel, einheitenRasen), imgView4);
        fourthIconRow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fourthIconRow.getStyleClass().add("fourth-icon-row");
        fourthIconRow.setMinHeight(50);
        fourthIconRow.setMaxHeight(100);

        drawingPane.getChildren().addAll(new VBox(30, title, new HBox(firstIconRow, shortSpacer, secondIconRow, button), new HBox(thirdIconRow, spacer, fourthIconRow)));
        getChildren().add(drawingPane);

        drawingPane.setMinSize(0,0);
    }

    private void setupBindings() {
        firstYearLabel.textProperty().bind(firstYearProperty().add(secondYearProperty().add(thirdYearProperty().add(fourtfhYearProperty()))).multiply(1000).divide(486).asString(SliderControl.FORMATTED_INTEGER_PATTERN));
        secondYearLabel.textProperty().bind(firstYearProperty().add(secondYearProperty().add(thirdYearProperty().add(fourtfhYearProperty()))).multiply(1000).divide(108).asString(SliderControl.FORMATTED_INTEGER_PATTERN));
        thirdYearLabel.textProperty().bind(firstYearProperty().add(secondYearProperty().add(thirdYearProperty().add(fourtfhYearProperty()))).multiply(1000).divide(200).asString(SliderControl.FORMATTED_INTEGER_PATTERN));
        fourthYearLabel.textProperty().bind(firstYearProperty().add(secondYearProperty().add(thirdYearProperty().add(fourtfhYearProperty()))).multiply(1000).divide(104).asString(SliderControl.FORMATTED_INTEGER_PATTERN));
    }

    // function for adding stylesheet files to class as resource
    private void addStylesheetFiles(String... stylesheetFile) {
        for (String file : stylesheetFile) {
            String stylesheet = getClass().getResource(file).toExternalForm();
            getStylesheets().add(stylesheet);
        }
    }

    // All getters and setter
    public int getFirstYear() {
        return firstYear.get();
    }

    public IntegerProperty firstYearProperty() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear.set(firstYear);
    }

    public int getSecondYear() {
        return secondYear.get();
    }

    public IntegerProperty secondYearProperty() {
        return secondYear;
    }

    public void setSecondYear(int secondYear) {
        this.secondYear.set(secondYear);
    }

    public int getThirdYear() {
        return thirdYear.get();
    }

    public IntegerProperty thirdYearProperty() {
        return thirdYear;
    }

    public void setThirdYear(int thirdYear) {
        this.thirdYear.set(thirdYear);
    }

    public int getFourtfhYear() {
        return fourtfhYear.get();
    }

    public IntegerProperty fourtfhYearProperty() {
        return fourtfhYear;
    }

    public void setFourtfhYear(int fourtfhYear) {
        this.fourtfhYear.set(fourtfhYear);
    }
}
