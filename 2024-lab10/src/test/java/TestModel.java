import it.unimi.di.sweng.slalom.model.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

@Timeout(2)
public class TestModel {

    private final static String SKIER = "WORLEY Tessa;58.93";
    @Test
    void testReadFirstManche() {
        Model model = new Model();
        Scanner sc = new Scanner(SKIER);
        model.readFilePrimaManche(sc);

        assertThat(model).extracting("firstManche", as(MAP))
                .containsEntry("WORLEY Tessa", 58.93);
    }
}
