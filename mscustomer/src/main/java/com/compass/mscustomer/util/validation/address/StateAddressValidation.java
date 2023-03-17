package com.compass.mscustomer.util.validation.address;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StateAddressValidation {

	private String ac = "Acre";
	private String al = "Alagoas";
	private String ap = "Amapá";
	private String am = "Amazonas";
	private String ba = "Bahia";
	private String ce = "Ceará";
	private String df = "Distrito Federal";
	private String es = "Espírito Santo";
	private String go = "Goiás";
	private String ma = "Maranhão";
	private String mt = "Mato Grosso";
	private String ms = "Mato Grosso do Sul";
	private String mg = "Minas Gerais";
	private String pa = "Pará";
	private String pb = "Paraíba";
	private String pr = "Paraná";
	private String pe = "Pernambuco";
	private String pi = "Piauí";
	private String rj = "Rio de Janeiro";
	private String rn = "Rio Grande do Norte";
	private String rs = "Rio Grande do Sul";
	private String ro = "Rondônia";
	private String rr = "Roraima";
	private String sc = "Santa Catarina";
	private String sp = "São Paulo";
	private String se = "Sergipe";
	private String to = "Tocantins";

	List<String> stateList = Arrays.asList(ac, al, ap, am, ba, ce, df, es, go, ma, mt, ms, mg, pa, pb, pr, pe, pi, rj, rn, rs, ro, rr, sc, sp, se, to);

	public void validateState (AddressEntity address) {
		String state = address.getState();
		if (!stateList.contains(state)) {
			throw new InvalidAttributeException("State  " + state + " is invalid");
		}
	}

}
