package cuie.project.consumption_converter;


import java.util.Arrays;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

class SliderSkin extends SkinBase<SliderControl> {
    private static final int IMG_SIZE   = 12;
    private static final int IMG_OFFSET = 4;

    private static final String ANGLE_DOWN = "\uf107";
    private static final String ANGLE_UP   = "\uf106";

    private enum State {

        INVALID("Invalid",  "invalid.png");

        public final String    text;
        public final ImageView imageView;

        State(final String text, final String file) {
            this.text = text;
            String url = SliderSkin.class.getResource("/icons/" + file).toExternalForm();
            this.imageView = new ImageView(new Image(url,
                                                     IMG_SIZE, IMG_SIZE,
                                                     true, false));
        }
    }

    private static final String STYLE_CSS = "style.css";

    // all parts
    private TextField editableNode;
    private Label     readOnlyNode;
    private Popup     popup;
    private Pane      dropDownChooser;
    private Button    chooserButton;

    private StackPane drawingPane;

    SliderSkin(SliderControl control) {
        super(control);
        initializeSelf();
        initializeParts();
        layoutParts();
        setupEventHandlers();
        setupBindings();
    }

    private void initializeSelf() {
        getSkinnable().loadFonts("/fonts/arca-majora-3/ArcaMajora3-Bold.otf",  "/fonts/Lato/Lato-Reg.ttf", "/fonts/ds_digital/DS-DIGI.TTF", "/fonts/fontawesome-webfont.ttf");
        getSkinnable().addStylesheetFiles(STYLE_CSS);
    }

    private void initializeParts() {
        editableNode = new TextField();
        editableNode.getStyleClass().add("editable-node");

        readOnlyNode = new Label();
        readOnlyNode.getStyleClass().add("read-only-node");

        chooserButton = new Button(ANGLE_DOWN);
        chooserButton.getStyleClass().add("chooser-button");

        dropDownChooser = new DropDownChooser(getSkinnable());

        popup = new Popup();
        popup.getContent().addAll(dropDownChooser);

        drawingPane = new StackPane();
        drawingPane.getStyleClass().add("drawing-pane");
    }

    private void layoutParts() {
        StackPane.setAlignment(chooserButton, Pos.CENTER_RIGHT);
        drawingPane.getChildren().addAll(editableNode, chooserButton, readOnlyNode);

        Arrays.stream(State.values())
              .map(state -> state.imageView)
              .forEach(imageView -> {
                  imageView.setManaged(false);
                  drawingPane.getChildren().add(imageView);
              });

        StackPane.setAlignment(editableNode, Pos.CENTER_LEFT);
        StackPane.setAlignment(readOnlyNode, Pos.CENTER_LEFT);

        getChildren().add(drawingPane);
    }

    private void setupEventHandlers() {
        chooserButton.setOnAction(event -> {
            if (popup.isShowing()) {
                popup.hide();
            } else {
                popup.show(editableNode.getScene().getWindow());
            }
        });

        popup.setOnHidden(event -> chooserButton.setText(ANGLE_DOWN));

        popup.setOnShown(event -> {
            chooserButton.setText(ANGLE_UP);
            Point2D location = editableNode.localToScreen(editableNode.getWidth() - dropDownChooser.getPrefWidth() - 3,
                                                          editableNode.getHeight() -3);

            popup.setX(location.getX());
            popup.setY(location.getY());
        });

        editableNode.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    getSkinnable().increase();
                    event.consume();
                    break;
                case DOWN:
                    getSkinnable().decrease();
                    event.consume();
                    break;
                case LEFT:
                    getSkinnable().decreaseSmall();
                    event.consume();
                    break;
                case RIGHT:
                    getSkinnable().increaseSmall();
                    event.consume();
                    break;
            }
        });
    }

    private void setupBindings() {
        readOnlyNode.textProperty().bindBidirectional(getSkinnable().valueProperty(), new NumberStringConverter());
        editableNode.textProperty().bindBidirectional(getSkinnable().valueProperty(), new NumberStringConverter());

        editableNode.visibleProperty().bind(getSkinnable().readOnlyProperty().not());
        chooserButton.visibleProperty().bind(getSkinnable().readOnlyProperty().not());
        readOnlyNode.visibleProperty().bind(getSkinnable().readOnlyProperty());

        State.INVALID.imageView.visibleProperty().bind(getSkinnable().invalidProperty());

        State.INVALID.imageView.xProperty().bind(editableNode.translateXProperty().add(editableNode.layoutXProperty()).subtract(IMG_OFFSET));
        State.INVALID.imageView.yProperty().bind(editableNode.translateYProperty().add(editableNode.layoutYProperty()).subtract(IMG_OFFSET));

    }

}

