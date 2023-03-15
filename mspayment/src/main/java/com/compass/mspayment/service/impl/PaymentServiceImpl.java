package com.compass.mspayment.service.impl;

import com.compass.mspayment.domain.InstallmentEntity;
import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.dto.InstallmentDto;
import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;
import com.compass.mspayment.domain.dto.form.PaymentUpdateFormDto;
import com.compass.mspayment.exception.InvalidAttributeException;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.repository.InstallmentRepository;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private InstallmentRepository installmentRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(PaymentFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		PaymentEntity payment = mapper.map(body, PaymentEntity.class);
		this.paymentRepository.save(payment);
	}

	@Override
	public List<PaymentDto> list() {
		List<PaymentDto> payments = this.paymentRepository.findAll().stream().map(p -> mapper.map(p, PaymentDto.class))
				.collect(Collectors.toList());

		List<PaymentDto> paymentsResponse = new ArrayList<>();

		for (PaymentDto paymentDto: payments) {
			List<InstallmentDto> installments = this.installmentRepository.findByPaymentId(paymentDto.getId())
					.stream().map(i -> mapper.map(i, InstallmentDto.class))
					.collect(Collectors.toList());
			if (!installments.isEmpty()) {
				paymentDto.setInstallments(installments);
			}
			paymentsResponse.add(paymentDto);
		}

		return paymentsResponse;
	}

	@Override
	public void update(Long id, PaymentUpdateFormDto body) {
		Optional<PaymentEntity> payment = this.paymentRepository.findById(id);
		if (!payment.isPresent()) {
			throw new NotFoundAttributeException("Payment " + id + " not found");
		}

		payment.get().setType(body.getType());

		if (body.getExistsInstallments().equals(false)) {
			List<InstallmentEntity> installments = this.installmentRepository.findByPaymentId(payment.get().getId());
			if (!installments.isEmpty()) {
				throw new InvalidAttributeException("It's not allowed to change existsIntallments to false because" +
						" there's installments registered in this payment");
			}
		}

		payment.get().setExistsInstallments(body.getExistsInstallments());
		payment.get().setActive(body.getActive());

		this.paymentRepository.save(payment.get());
	}

	@Override
	public void delete(Long id) {
		Optional<PaymentEntity> payment = this.paymentRepository.findById(id);
		if (!payment.isPresent()) {
			throw new NotFoundAttributeException("Payment " + id + " not found");
		}

		this.paymentRepository.deleteById(id);
	}

}
