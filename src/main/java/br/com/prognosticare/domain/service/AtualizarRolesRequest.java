package br.com.prognosticare.domain.service;

import java.util.List;

import br.com.prognosticare.domain.entity.role.RoleEntity;

public record AtualizarRolesRequest (List<RoleEntity> roles) {
}
