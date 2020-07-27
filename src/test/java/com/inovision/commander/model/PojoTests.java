package com.inovision.commander.model;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PojoTests {

    private static final Validator ACCESSOR_VALIDATOR = ValidatorBuilder.create()
            .with(new GetterTester())
            .with(new SetterTester())
            .build();

    public static void testGetterSetter(final Class<?> clazz) {
        ACCESSOR_VALIDATOR.validate(PojoClassFactory.getPojoClass(clazz));
    }

    @Test
    public void testPOJOs() {
        PojoTests.testGetterSetter(TestCase.class);
        PojoTests.testGetterSetter(TestResult.class);
        PojoTests.testGetterSetter(TestCaseDefinition.class);
        PojoTests.testGetterSetter(TestCaseRun.class);
        PojoTests.testGetterSetter(TestCaseInstance.class);
        PojoTests.testGetterSetter(Host.class);
        PojoTests.testGetterSetter(UserRole.class);
        PojoTests.testGetterSetter(TestCaseInstance.class);
        PojoTests.testGetterSetter(TestCaseInstance.class);
        PojoTests.testGetterSetter(TestCaseInstance.class);
        PojoTests.testGetterSetter(TestCaseInstance.class);
    }

    @Test
    public void testContentType() {
        ContentType c = ContentType.fromString("application/json");
        assertNotNull(c);
        assertEquals(ContentType.APPLICATION_JSON, c);
        assertEquals("text/html", ContentType.TEXT_HTML.toString());
    }
}
