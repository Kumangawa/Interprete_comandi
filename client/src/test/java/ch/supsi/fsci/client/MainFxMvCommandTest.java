package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxMvCommandTest  extends AbstractMainGUITest {

    @Test
    public void testMvCommand() {
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
                commandTextField.setText("mv / /" + secondaLettera);
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
        String destinationPath = "/C";
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
                commandTextField.setText("mv /"+ primaLettera + " "+ destinationPath);
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
                commandTextField.setText("cd /"+primaLettera + "/"+secondaLettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(g+h));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv /"+ primaLettera + " "+ destinationPath);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(i));
        });

        String l = String.format(Localization.getSingleton().localize("command.cd.success"), primaLettera) +"\n";
        String path = "/"+ primaLettera + "/"+ secondaLettera;
        String m = i+l+String.format(Localization.getSingleton().localize("command.mv.success"),path,destinationPath )+"\n";

        step("Test command mv, move in " + primaLettera + " and try to move " + secondaLettera + " in "+ terzaLettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd /"+primaLettera);
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
    }
}