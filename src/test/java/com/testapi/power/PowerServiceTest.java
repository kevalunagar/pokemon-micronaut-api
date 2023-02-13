package com.testapi.power;

import com.testapi.exception.InvalidDataException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class PowerServiceTest {

  PowerRepository powerRepository;
  PowerService powerService;

  @BeforeEach
  void setUp() {
    powerRepository = Mockito.mock(PowerRepository.class);
    powerService = new PowerService(powerRepository);
  }

  @Test
  void testGet() {
    Power power = new Power(4, "Grass");
    Mockito.when(powerRepository.findById(4)).thenReturn(Optional.of(power));
    Power expectedPower = powerService.get(4);
    Mockito.verify(powerRepository).findById(4);
    Assertions.assertThat(expectedPower).isEqualTo(power);
  }

  @Test
  void testGetException() {
    Mockito.when(powerRepository.findById(2)).thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> powerService.get(2))
        .isInstanceOf(InvalidDataException.class);
  }
}
