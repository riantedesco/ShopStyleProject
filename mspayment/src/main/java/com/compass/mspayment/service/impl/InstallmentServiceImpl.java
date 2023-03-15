package com.compass.mspayment.service.impl;

import com.compass.mspayment.domain.InstallmentEntity;
import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.form.InstallmentFormDto;
import com.compass.mspayment.domain.dto.form.InstallmentUpdateFormDto;
import com.compass.mspayment.exception.InvalidAttributeException;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.repository.InstallmentRepository;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.service.InstallmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstallmentServiceImpl implements InstallmentService {

	@Autowired
	private InstallmentRepository installmentRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(InstallmentFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		InstallmentEntity installment = mapper.map(body, InstallmentEntity.class);

		if(body.getPaymentId() != null) {
			installment.setId(null);
			Optional<PaymentEntity> payment = this.paymentRepository.findById(body.getPaymentId());
			if(!payment.isPresent()) {
				throw new InvalidAttributeException("Payment " + body.getPaymentId() + " not found");
			}
			if(payment.get().getExistsInstallments().equals(false)) {
				throw new InvalidAttributeException("Payment doesn't have installments");
			}
			installment.setPayment(payment.get());
		}

		this.installmentRepository.save(installment);
	}

	@Override
	public void update(Long id, InstallmentUpdateFormDto body) {
		Optional<InstallmentEntity> installment = this.installmentRepository.findById(id);
		if (!installment.isPresent()) {
			throw new NotFoundAttributeException("Installment " + id + " not found");
		}

		installment.get().setAmount(body.getAmount());
		installment.get().setBrand(body.getBrand());

		this.installmentRepository.save(installment.get());
	}

	@Override
	public void delete(Long id) {
		Optional<InstallmentEntity> installment = this.installmentRepository.findById(id);
		if (!installment.isPresent()) {
			throw new NotFoundAttributeException("Installment " + id + " not found");
		}

		this.installmentRepository.deleteById(id);
	}

}
