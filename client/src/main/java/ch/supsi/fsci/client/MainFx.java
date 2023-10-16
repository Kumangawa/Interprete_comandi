package ch.supsi.fsci.client;

import ch.supsi.fsci.client.controller.CommandLineController;
import ch.supsi.fsci.client.model.CommandLineModel;
import ch.supsi.fsci.client.view.CommandLineView;
import ch.supsi.fsci.client.view.OutputAreaView;
import ch.supsi.fsci.client.controller.CommandExecutionController;
import ch.supsi.fsci.engine.FileSystemModel;
import ch.supsi.fsci.engine.Localization;
import ch.supsi.fsci.engine.Persistence;
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

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

public class MainFx extends Application {
    private final static String separator = File.separator;
    private String applicationTitle;
    private Label commandLabel;
    private int prefCommandSpacerWidth;
    private TextField commandTextField;
    private int commandFieldPrefColumnCount;
    private TextArea outputArea;
    private int prefOutputAreaRowCount;
    private int prefInsetsSize;

    public MainFx(){
        // persistence
        Persistence persistence = new Persistence();
        persistence.initializeExplicit();
        HashMap<String, String> preferencesData = persistence.getPreference();

        this.applicationTitle = "command interpreter for fs simulator";
        this.commandLabel = new Label("command");
        this.commandTextField = new TextField();
        this.outputArea = new TextArea();

        this.prefCommandSpacerWidth = Integer.parseInt(preferencesData.get("prefCommandSpacerWidth"));
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag(preferencesData.get("language")));
        this.commandFieldPrefColumnCount = Integer.parseInt(preferencesData.get("commandFieldPrefColumnCount"));
        this.prefOutputAreaRowCount = Integer.parseInt(preferencesData.get("prefOutputAreaRowCount"));
        this.prefInsetsSize = Integer.parseInt(preferencesData.get("prefInsetsSize"));

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

        // scene
        Scene mainScene = new Scene(borderPane);

        //OutputArea
        OutputAreaView outputAreaView = new OutputAreaView(outputArea);

        // FileSystemModel
        final FileSystemModel fileSystemModel = new FileSystemModel();

        // CommandDispatcher
        final CommandExecutionController commandExecutionController = new CommandExecutionController(fileSystemModel);
        commandExecutionController.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");

        //CommandLine
        CommandLineView commandLineView = new CommandLineView();
        CommandLineModel commandLineModel = new CommandLineModel(fileSystemModel, commandExecutionController);
        CommandLineController commandLineController = new CommandLineController(commandTextField, commandLineModel,
                commandLineView, outputAreaView);
        commandLineController.initialize();

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
