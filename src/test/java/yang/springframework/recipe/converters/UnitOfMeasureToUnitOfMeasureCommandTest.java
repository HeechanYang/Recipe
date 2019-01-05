package yang.springframework.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import yang.springframework.recipe.commands.UnitOfMeasureCommand;
import yang.springframework.recipe.models.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject()  throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void converter() throws Exception{
        // given
        UnitOfMeasure command = new UnitOfMeasure();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand uom = converter.convert(command);

        // then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}
