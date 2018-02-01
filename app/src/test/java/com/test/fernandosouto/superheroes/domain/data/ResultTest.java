package com.test.fernandosouto.superheroes.domain.data;

import com.test.fernandosouto.superheroes.mock.MarvelJUnitTest;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class ResultTest extends MarvelJUnitTest {

    @Test
    public void thatCanCreateAResult() {
        Integer number = new Integer(1034);
        Status status = Status.builder().build();
        Result<Integer> result = new Result<Integer>(status, number);
        assertThat(result.data()).isEqualTo(1034);
        assertThat(result.status().isOk()).isTrue();
    }

    @Test
    public void thatCanWraAResultOnAnother() {
        Integer number = new Integer(1034);
        Status status = Status.builder().build();
        Result<Integer> resultWrapper = new Result<Integer>(status, number);
        Result<Integer> result = new Result<Integer>(resultWrapper);
        assertThat(result.data()).isEqualTo(1034);
        assertThat(result.status().isOk()).isTrue();
    }
}
