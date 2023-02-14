package com.testapi.power;

import com.testapi.exception.InvalidDataException;
import jakarta.inject.Singleton;

@Singleton
public class PowerService {
  private final PowerRepository powerRepository;

  public PowerService(PowerRepository powerRepository) {
    this.powerRepository = powerRepository;
  }

  public Power get(Integer id) {
    return powerRepository
        .findById(id)
        .orElseThrow(() -> new InvalidDataException("No Power exist with this " + id));
  }
}
