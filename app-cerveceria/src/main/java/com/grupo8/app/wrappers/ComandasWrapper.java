package com.grupo8.app.wrappers;

import com.grupo8.app.modelo.Comanda;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ComandasWrapper implements Serializable {
  private List<Comanda> comandas;
}
