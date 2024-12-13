import it.unimi.di.sweng.slalom.model.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Timeout(2)
public class TestState {

    @Test
    void testGetterSetter(){
        State state = new State();

        state.addSecondTimeSkier("roberto", 45.32);
        assertThat(state.getSecondManche().get("roberto")).isCloseTo(45.32, within(.01));
    }

    @Test
    void testGetterSetterTotal(){
        State state = new State();

        state.addFirstTimeSkier("roberto", 45.32);
        state.addSecondTimeSkier("roberto", 74.65);
        assertThat(state.getTotalTimes().get("roberto")).isCloseTo(119.97, within(.01));
    }
}
