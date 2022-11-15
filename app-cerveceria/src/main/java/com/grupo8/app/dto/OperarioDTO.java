package com.grupo8.app.dto;

import com.grupo8.app.modelo.Operario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OperarioDTO {
    private String nombreCompleto;
    private String username;
    private String password;
    private Boolean activo;

    public static OperarioDTO of(Operario operario) {
        OperarioDTO operarioDTO = new OperarioDTO();
        operarioDTO.setNombreCompleto(operario.getNombreCompleto());
        operarioDTO.setUsername(operario.getUsername());
        operarioDTO.setPassword(operario.getPassword());
        operarioDTO.setActivo(operario.getActivo());
        return operarioDTO;
    }

    @Override
    public String toString() {
        return "{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", username='" + username + '\'' +
                ", activo=" + activo
                + '}';
    }
}
