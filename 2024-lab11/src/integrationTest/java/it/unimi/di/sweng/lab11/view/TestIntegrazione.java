package it.unimi.di.sweng.lab11.view;


import it.unimi.di.sweng.lab11.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {

  private InputAlimentView input;
  private DisplayView display;
  private DisplayView display2;
  private CommandView[] command;

  private static final boolean HEADLESS = false;
  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(Stage primaryStage) {

    Main m = new Main();
    m.start(primaryStage);

    GridPane gp = (GridPane) primaryStage.getScene().getRoot();
    ObservableList<Node> view = gp.getChildren();

    input = (InputAlimentView) view.get(0);
    command = new CommandView[2];
    for (int i = 0; i < 2; i++) {
      command[i] = (CommandView) view.get(i + 1);
    }
    display = (DisplayView) view.get(3);
    display2 = (DisplayView) view.get(4);
  }



  @Test
  public void testSomething(FxRobot robot) {
    // FIG 1
    assertThat(command[0].get(0)).startsWith("---");
    assertThat(command[1].get(0)).startsWith("---");

    // FIG 2

    addToList("mele", "6", robot);

    assertThat(command[0].get(0)).startsWith("mele");
    assertThat(command[1].get(0)).startsWith("mele");
    assertThat(display.get(0)).startsWith("mele").endsWith("6");


    // FIG 3
    buy(1,0, robot);
    buy(0,0, robot);
    buy(0,0, robot);

    addToList("pere", "4", robot);

    buy(1,0, robot);
    buy(1,0, robot);

    assertThat(display.get(0)).startsWith("mele").endsWith("1");
    assertThat(display.get(1)).startsWith("pere").endsWith("4");
    assertThat(display2.get(0)).startsWith("mele").endsWith("5");

    // FIG.4

    //TODO proseguire nella interazione e controllare esito

  }

  // TEST UTILITY FUNCTIONS
  private void addToList(String f1, String f2, @NotNull FxRobot robot) {
    robot.doubleClickOn(input.text);
    robot.write(f1, 0);
    robot.doubleClickOn(input.num);
    robot.write(f2, 0);
    robot.clickOn(input.addButton);
  }

  private void buy(int buyer, int itemInList, @NotNull FxRobot robot) {
    robot.clickOn(command[buyer].buttons[itemInList]);
  }
}
