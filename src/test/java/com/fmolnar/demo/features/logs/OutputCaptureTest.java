package com.fmolnar.demo.features.logs;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(OutputCaptureExtension.class)
class OutputCaptureTest {

    @Test
    void shouldCaptNormalOutput(CapturedOutput output) {
        System.out.println("ok pas de problème");
        assertThat(output).contains("ok pas de problème");
    }

    @Test
    void shouldCaptErrorOutput(CapturedOutput output) {
        System.err.println("Houston we have a problem");
        assertThat(output).contains("Houston we have a problem");
    }


}
