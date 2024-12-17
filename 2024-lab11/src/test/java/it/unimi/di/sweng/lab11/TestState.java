package it.unimi.di.sweng.lab11;

import it.unimi.di.sweng.lab11.model.State;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

@Timeout(2)
public class TestState {

    @Test
    void testSetterGetterState() {
        State state = new State();

        state.addToGroceryList("latte", 2);
        assertThat(state.getGroceryListNoAmount().get(0)).isEqualTo("latte");
    }

    @Test
    void testCumulativeSetterState() {
        State state = new State();

        state.addToGroceryList("latte", 2);
        state.addToGroceryList("latte", 7);
        assertThat(state).extracting("groceryList", as(MAP)).containsEntry("latte", 9);
    }
}
