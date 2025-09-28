package com.cuenta.services;

import com.cuenta.entity.CuentaEntity;
import com.cuenta.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;


    @Override
    public void actualizarSaldo(String cuentaId, Double monto) {
        CuentaEntity cuentaEntity = cuentaRepository.findById(Long.parseLong(cuentaId)).orElseThrow();
        cuentaEntity.setMonto(cuentaEntity.getMonto() - monto);
        cuentaRepository.save(cuentaEntity);
    }
}
