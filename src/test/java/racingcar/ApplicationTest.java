package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 비어있는_이름_입력시_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> new CarList(""))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 자동차_이름_공백_예외_처리() {
        String carName = "a b c";
        assertSimpleTest(() ->
                assertThatThrownBy(() -> new CarList(carName))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 레이스_횟수_입력_예외_처리() {
        String carName = "abc,def"; // there is no exception
        String raceTime = "5a"; // expect to occur exception
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(carName, raceTime))
                        .isInstanceOf(IllegalArgumentException.class)
        );

    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
