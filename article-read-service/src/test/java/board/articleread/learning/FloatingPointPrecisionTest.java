package board.articleread.learning;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FloatingPointPrecisionTest {

    @Test
    void float_변수의_덧셈은_정확히_떨어지지_않는다() {
        float num1 = 240f;
        float num2 = 187.17f;

        float sum = num1 + num2;

        System.out.println("[float] sum (float)      = " + sum);
        System.out.println("[float] sum (BigDecimal) = " + new BigDecimal(Float.toString(sum)));
    }

    @Test
    void float_변수의_연산_결과를_Math_round로_보정한다() {
        float num1 = 240f;
        float num2 = 187.17f;

        float sum = num1 + num2;

        float roundedSum = Math.round(sum * 100) / 100f;

        System.out.println("[float] sum        = " + sum);
        System.out.println("[float] roundedSum = " + roundedSum);
    }

    @Test
    void BigDecimal_타입으로_테스트하면_정확성을_보장한다() {
        BigDecimal num1 = new BigDecimal("240");
        BigDecimal num2 = new BigDecimal("187.17");

        BigDecimal sum = num1.add(num2).setScale(2, RoundingMode.HALF_UP);

        System.out.println("[BigDecimal] sum = " + sum);
    }
}
