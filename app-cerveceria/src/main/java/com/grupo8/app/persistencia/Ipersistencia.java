package com.grupo8.app.persistencia;

import java.io.IOException;

public interface Ipersistencia<E> {
  void abrirInput(String nombre) throws IOException;

  void abrirOutput(String nombre) throws IOException;

  void cerrarOutput() throws IOException;

  void cerrarInput() throws IOException;

  void escribir(E objeto) throws IOException;

  E lee() throws IOException, ClassNotFoundException;
}