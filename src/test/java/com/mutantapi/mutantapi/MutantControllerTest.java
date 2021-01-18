package com.mutantapi.mutantapi;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import com.mutantapi.mutantapi.controller.MutantController;
import com.mutantapi.mutantapi.model.Mutant;
import com.mutantapi.mutantapi.repository.MutantRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import io.netty.util.concurrent.BlockingOperationException;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@SpringBootTest
public class MutantControllerTest {

    @InjectMocks
    private MutantController mutantController;

    @Mock
    private MutantRepository mutantrepositoryMock;

    private final Mutant mutant = new Mutant();

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }

    @BeforeEach
    public void setUp() {

        BDDMockito.when(mutantrepositoryMock.findAll()).thenReturn(Flux.just(mutant));
        BDDMockito.when(mutantrepositoryMock.save(mutant)).thenReturn(Mono.just(mutant));
    }

    @Test
    public void blockHoundWorks() {
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(1);
                return "";
            });
            Schedulers.parallel().schedule(task);
            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("should fail");

        } catch (Exception e) {
            Assertions.assertThat(e.getCause() instanceof BlockingOperationError);
        }

    }

    @Test
    void ListMutantsTest() {
        Mutant mutant = new Mutant();

        StepVerifier.create(mutantController.ListMutants()).expectSubscription().expectNext(mutant).verifyComplete();
    }

    @Test
    void ValidateISMutantTest() {

    }
}
