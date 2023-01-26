package com.compass.mscustomer.util.validation.update;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AddressUpdateValidation {

	public void validateState (AddressEntity address) {
		String ac = "Acre";
		String al = "Alagoas";
        String ap = "Amapá";
        String am = "Amazonas";
        String ba = "Bahia";
        String ce = "Ceará";
        String df = "Distrito Federal";
        String es = "Espírito Santo";
        String go = "Goiás";
        String ma = "Maranhão";
        String mt = "Mato Grosso";
        String ms = "Mato Grosso do Sul";
        String mg = "Minas Gerais";
        String pa = "Pará";
        String pb = "Paraíba";
        String pr = "Paraná";
        String pe = "Pernambuco";
        String pi = "Piauí";
        String rj = "Rio de Janeiro";
        String rn = "Rio Grande do Norte";
        String rs = "Rio Grande do Sul";
        String ro = "Rondônia";
        String rr = "Roraima";
        String sc = "Santa Catarina";
        String sp = "São Paulo";
        String se = "Sergipe";
        String to = "Tocantins";
        List<String> list = Arrays.asList(ac, al, ap, am, ba, ce, df, es, go, ma, mt, ms, mg, pa, pb, pr, pe, pi, rj, rn, rs, ro, rr, sc, sp, se ,to);
		if (!list.contains(address.getState())) {
			throw new InvalidAttributeException("Invalid state");
		}
	}

}
