package com.test.fernandosouto.superheroes.domain.data;

import com.test.fernandosouto.superheroes.mock.MarvelJUnitTest;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class StatusTest extends MarvelJUnitTest {

    @Test
    public void thatCanCreateAOkStatus(){
        Status status =  Status.builder().build();
        assertThat(status.isOk()).isTrue();
    }

    @Test
    public void thatCanHandleANotFoundException(){
        Status status =  Status.builder().error(new Exception("Could not found resource")).build();
        assertThat(status.dataStatus()).isEqualTo(Status.STATUS_NOT_FOUND_ERROR);
        assertThat(status.isNotFoundException()).isTrue();
        assertThat(status.getExceptionMessage().toString()).contains("found");
    }


    @Test
    public void thatCanSetCancelStatus(){
        Status status =  Status.builder().cancel().build();
        assertThat(status.errorStatus()).isEqualTo(Status.STATUS_CANCELED);
        assertThat(status.isCanceled()).isTrue();
    }



}
