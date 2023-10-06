package ch.supsi.fsci.client;

import ch.supsi.fsci.client.controller.CommandLineController;
import ch.supsi.fsci.client.model.CommandLineModel;
import ch.supsi.fsci.client.view.CommandLineView;
import ch.supsi.fsci.client.view.OutputAreaView;
import ch.supsi.fsci.client.controller.CommandExecutionController;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import ch.supsi.fsci.engine.Model.Persistence;
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
import java.util.Map;

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

        /**
         * TODO: c'è un errore nella lettura del file e non va bene fare
         *      preferencesData(key), siccome ritorna null,
         *      motivo sconosciuto, possibile contrasto con la ',' o spazio o endline
         */

        String[][] preferencesArray = new String[preferencesData.size()][2];
        int index = 0;
        for (Map.Entry<String, String> entry : preferencesData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            preferencesArray[index][0] = key;
            preferencesArray[index][1] = value;

            index++;
        }

        this.applicationTitle = "command interpreter for fs simulator";
        this.commandLabel = new Label("command");

        this.prefCommandSpacerWidth = Integer.parseInt(preferencesArray[0][1]);
        this.commandFieldPrefColumnCount = Integer.parseInt(preferencesArray[2][1]);
        this.prefOutputAreaRowCount = Integer.parseInt(preferencesArray[3][1]);
        this.prefInsetsSize = Integer.parseInt(preferencesArray[4][1]);


        this.commandTextField = new TextField();
        this.outputArea = new TextArea();
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
