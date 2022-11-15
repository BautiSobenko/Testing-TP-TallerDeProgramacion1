package com.grupo8.app.wrappers;

import com.grupo8.app.modelo.Operario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OperariosWrapper implements Serializable {
  private Set<Operario> operarios;
}
