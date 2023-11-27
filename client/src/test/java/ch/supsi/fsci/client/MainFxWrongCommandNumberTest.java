package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxWrongCommandNumberTest extends AbstractMainGUITest{

    private String output;

    @Test
    public void testWrongCommandNumber() {
        testCd();
        testClear();
        testHelp();
        testLs();
        testMkdir();
        testMv();
        testPwd();
        testRm();
    }
    private void testCd() {
        output = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "cd",1,0,"cd <path>")+ "\n";
        step("Cd 0 arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });

        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "cd",1,2,"cd <path>")+ "\n";
        step("Cd too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd a b");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testClear() {
        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "clear",0,1,"clear")+ "\n";
        step("Clear too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("clear uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testHelp() {
        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "help",0,1,"help")+ "\n";
        step("Help too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("help uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testLs() {
        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "ls",0,1,"ls")+ "\n";
        step("Ls too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("ls uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testMkdir() {
        output+= String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "mkdir",1,0,"mkdir <directory>")+ "\n";
        step("Mkdir 0 arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });

        output+= String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "mkdir",1,2,"mkdir <directory>")+ "\n";
        step("Mkdir too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir a b");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testMv() {
        output+= String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "mv",2,0,"mv <src> <dest>")+ "\n";
        step("Mkdir 0 arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });

        output+= String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "mv",2,3,"mv <src> <dest>")+ "\n";
        step("Mkdir too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv a b c");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testPwd() {
        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "pwd",0,1,"pwd")+ "\n";
        step("Pwd too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd uno");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }

    private void testRm() {
        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "rm",1,0,"rm <path>")+ "\n";
        step("Rm 0 arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm ");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });

        output += String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "rm",1,2,"rm <path>")+ "\n";
        step("Rm too many arguments", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm a b");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(output));
        });
    }
}
