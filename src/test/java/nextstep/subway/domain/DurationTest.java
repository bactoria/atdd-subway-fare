package nextstep.subway.domain;

import nextstep.subway.domain.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DurationTest {

    @Test
    void 소요시간을_정상적으로_생성한다() {
        assertThat(Duration.of(3)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 소요시간이_음수이거나_0인_경우_예외가_발생한다(int value) {
        assertThatThrownBy(() -> Duration.of(value)).isInstanceOf(NotPositiveNumberException.class);
    }

    @Test
    void 소요시간에_소요시간을_뺄_수_있다() {
        var five = Duration.of(5);
        var three = Duration.of(3);
        var two = Duration.of(2);

        assertThat(five.minus(three)).isEqualTo(two);
    }

    @Test
    void 소요시간에_소요시간를_더할_수_있다() {
        var five = Duration.of(5);
        var three = Duration.of(3);
        var eight = Duration.of(8);

        assertThat(five.plus(three)).isEqualTo(eight);
    }
}
