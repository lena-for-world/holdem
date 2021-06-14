package cardgame.holdem;

import cardgame.holdem.tools.Pair;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class TestVector {

    @Test
    @DisplayName("Vector Test")
    void testVector() {
        Vector<Pair> vec = new Vector<>();
        Pair pair = new Pair(1, 2);
        vec.add(pair);
        Integer temp = vec.get(0).first();

        Vector<String> a = new Vector<>();
        a.add("Ssibal");

        Assertions.assertThat(temp).isSameAs(1);
        Assertions.assertThat(a.get(0)).isSameAs("Ssibal");
    }

}
