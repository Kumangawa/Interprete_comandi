package ch.supsi.fsci.client;

import ch.supsi.fsci.client.model.CommandExecutionModel;
import ch.supsi.fsci.client.controller.CommandExecutionController;
import ch.supsi.fsci.engine.Controller.PreferencesController;
import ch.supsi.fsci.engine.FileSystemModel;
import ch.supsi.fsci.engine.Localization;
import ch.supsi.fsci.engine.Model.PreferencesModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Locale;

public class MainFx extends Application {
    private String applicationTitle;
    private Label commandLabel;
    private int prefCommandSpacerWidth;
    private TextField commandTextField;
    private int commandFieldPrefColumnCount;
    private TextArea outputArea;
    private int prefOutputAreaRowCount;
    private int prefInsetsSize;

    public MainFx(){
        PreferencesController preferencesController = new PreferencesController();
        PreferencesModel preferencesModel = preferencesController.loadPreferences();

        this.applicationTitle = "command interpreter for fs simulator";
        this.commandLabel = new Label("command");
        this.commandLabel.setId("commandLabel");
        this.commandTextField = new TextField();
        this.commandTextField.setId("commandTextField");
        this.outputArea = new TextArea();
        this.outputArea.setId("outputArea");

        this.prefCommandSpacerWidth = 11;
        this.prefInsetsSize = 7;
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag(preferencesModel.getPreference("language")));
        this.commandFieldPrefColumnCount = Integer.parseInt(preferencesModel.getPreference("commandFieldPrefColumnCount"));
        this.prefOutputAreaRowCount = Integer.parseInt(preferencesModel.getPreference("prefOutputAreaRowCount"));

    }

    @Override
    public void start(Stage stage){
        // spacer
        Region spacer = new Region();
        spacer.setPrefWidth(prefCommandSpacerWidth);

        // command textfield
        commandTextField.setPrefColumnCount(commandFieldPrefColumnCount);

        // horizontal box to hold the command line
        HBox commandLinePane = new HBox();
        commandLinePane.setAlignment(Pos.BASELINE_LEFT);
        commandLinePane.setPadding(new Insets(prefInsetsSize));
        commandLinePane.getChildren().add(commandLabel);
        commandLinePane.getChildren().add(spacer);
        commandLinePane.getChildren().add(commandTextField);

        // output area
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(prefOutputAreaRowCount);

        // scroll pane to hold the output area
        ScrollPane outputAreaPane = new ScrollPane();
        outputAreaPane.setFitToHeight(true);
        outputAreaPane.setFitToWidth(true);
        outputAreaPane.setPadding(new Insets(prefInsetsSize));
        outputAreaPane.setContent(outputArea);

        // border pane to hold the command line and the output area
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(commandLinePane);
        borderPane.setCenter(outputAreaPane);
        borderPane.setId("borderPane");

        // scene
        Scene mainScene = new Scene(borderPane);

        // FileSystemModel
        final FileSystemModel fileSystemModel = new FileSystemModel();

        // CommandDispatcher
        final CommandExecutionModel commandExecutionModel = new CommandExecutionModel(fileSystemModel);
        commandExecutionModel.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");
        final CommandExecutionController commandExecutionController = new CommandExecutionController(commandTextField, outputArea, commandExecutionModel);
        commandExecutionController.initialize();

        // put the scene onto the primary stage
        stage.setTitle(applicationTitle);
        stage.setResizable(true);
        stage.setScene(mainScene);

        // on close
        stage.setOnCloseRequest(e -> {
            // send a command to the ApplicationExitController
            // to handle to exit process...
        });

        // show the primary stage
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
