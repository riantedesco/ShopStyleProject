package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.form.InstallmentFormDto;
import com.compass.mspayment.domain.dto.form.InstallmentUpdateFormDto;

public interface InstallmentService {

    void save(InstallmentFormDto body);

    void update(Long id, InstallmentUpdateFormDto body);

    void delete(Long id);

}
