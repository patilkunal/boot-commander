package com.inovision.commander.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class HostTest {

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Host.class)
		.suppress(Warning.SURROGATE_KEY)
		.withPrefabValues(List.class, Arrays.asList(new Category(), new Category()), Arrays.asList(new Category())).verify();;
	}
	
	@Test
	public void testGetterSetter() {
		PojoClass pojo = PojoClassFactory.getPojoClass(Host.class);
		Validator validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();
		validator.validate(pojo);
	}
}
