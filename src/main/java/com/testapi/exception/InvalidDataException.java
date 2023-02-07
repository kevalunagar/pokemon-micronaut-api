package com.testapi.exception;

public class InvalidDataException extends PokemonException {
  public InvalidDataException(String message) {
    super(message);
  }
}
