package com.grupo8.app.wrappers;

import com.grupo8.app.modelo.CierreComanda;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CierreComandaWrapper implements Serializable {
  private List<CierreComanda> cierreComandas;
}
