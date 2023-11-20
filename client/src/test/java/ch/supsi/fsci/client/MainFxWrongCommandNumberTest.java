package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxWrongCommandNumberTest extends AbstractMainGUITest{

    @Test
    public void testWrongCommandNumber() {
        String cd = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "cd",1,0,"cd <path>")+ "\n";
        step("Test wrong command number cd", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd));
        });

        String clear = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "clear",0,1,"clear")+ "\n";
        step("Test wrong command number clear", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("clear uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear));
        });

        String help = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "help",0,1,"help")+ "\n";
        step("Test wrong command number clear", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("help uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear+help));
        });

        String ls = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "ls",0,1,"ls")+ "\n";
        step("Test wrong command number ls", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("ls uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear+help+ls));
        });

        String mkdir = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "mkdir",1,0,"mkdir <directory>")+ "\n";
        step("Test wrong command number mkdir", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear+help+ls+mkdir));
        });

        String mv = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "mv",2,0,"mv <src> <dest>")+ "\n";
        step("Test wrong command number mv", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear+help+ls+mkdir+mv));
        });

        String pwd = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "pwd",0,1,"pwd")+ "\n";
        step("Test wrong command number pwd", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear+help+ls+mkdir+mv+pwd));
        });

        String rm = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "rm",1,0,"rm <path>")+ "\n";
        step("Test wrong command number rm", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(cd+clear+help+ls+mkdir+mv+pwd+
                    rm));
        });
    }
}
