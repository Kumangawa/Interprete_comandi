package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxMvCommandTest  extends AbstractMainGUITest {

    @Test
    public void testMvCommand() {
        FileSystemModel fileSystemModel = new FileSystemModel();
        String separator = fileSystemModel.getSeparator();
        String primaLettera = "A";
        String a = String.format(Localization.getSingleton().localize("command.mkdir"),primaLettera)+"\n";
        String secondaLettera = "B";
        String b = String.format(Localization.getSingleton().localize("command.mkdir"),secondaLettera)+"\n";
        String c = String.format(Localization.getSingleton().localize("command.mv.root")) +"\n";
        String d = a+b+c+String.format(Localization.getSingleton().localize("command.mv.success"),secondaLettera ,primaLettera) +"\n";

        step("Test command mv, move root", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+primaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+secondaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + b));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv " + separator + " " + separator+ secondaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c));
        });

        step("Test command mv, move the directory " + secondaLettera + " in " + primaLettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv "+ secondaLettera + " "+ primaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(d));
        });

        String terzaLettera = "C";
        String destinationPath = separator + "C";
        String e = String.format(Localization.getSingleton().localize("command.mkdir"),terzaLettera)+"\n";
        String f = String.format(Localization.getSingleton().localize("command.cd.success"), primaLettera) +"\n";
        String g = d+e+f+Localization.getSingleton().localize("command.mv.failed.current") + "\n";

        step("Test command mv, move in " + primaLettera + " and try to move " + primaLettera + " in "+ terzaLettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+terzaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(d+e));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd "+primaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(d+e+f));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv "+ separator + primaLettera + " "+ destinationPath);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(g));
        });

        String h = String.format(Localization.getSingleton().localize("command.cd.success"), secondaLettera) +"\n";
        String i = g+h+Localization.getSingleton().localize("command.mv.failed.descendant") + "\n";

        step("Test command mv, move in " + secondaLettera + " and try to move " + primaLettera + " in "+ terzaLettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd "+ separator+ primaLettera+ separator+ secondaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(g+h));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv "+ separator+ primaLettera+ " "+ destinationPath);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(i));
        });

        String l = String.format(Localization.getSingleton().localize("command.cd.success"), primaLettera) +"\n";
        String path = separator+ primaLettera + separator+ secondaLettera;
        String m = i+l+String.format(Localization.getSingleton().localize("command.mv.success"),path,destinationPath )+"\n";

        step("Test command mv, move in " + primaLettera + " and try to move " + secondaLettera + " in "+ terzaLettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd "+separator+primaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(i+l));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv " + path + " " + destinationPath);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(m));
        });

        String q = String.format(Localization.getSingleton().localize("command.cd.success"), separator) +"\n";
        String quartaLettera = "E";
        String r = String.format(Localization.getSingleton().localize("command.mkdir"),quartaLettera)+"\n";
        String pathToSecondaLettera = separator+ quartaLettera + separator+ secondaLettera;
        String s = String.format(Localization.getSingleton().localize("command.mkdir"),secondaLettera)+"\n";
        String t = m+q+r+s+Localization.getSingleton().localize("command.mv.failed.samename")+"\n";


        step("Test command mv, move in " + separator + " and try to move " + pathToSecondaLettera + " in "+ terzaLettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd "+separator);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(m+q));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir " + quartaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(m+q+r));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir " + pathToSecondaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(m+q+r+s));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv " + pathToSecondaLettera + " " + destinationPath);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(t));
        });
    }
}