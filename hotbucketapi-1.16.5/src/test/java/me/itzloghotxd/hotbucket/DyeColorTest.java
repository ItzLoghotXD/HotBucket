package me.itzloghotxd.hotbucket;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import me.itzloghotxd.hotbucket.material.Colorable;
import me.itzloghotxd.hotbucket.material.Dye;
import me.itzloghotxd.hotbucket.material.Wool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DyeColorTest {

    @Parameters(name = "{index}: {0}")
    public static List<Object[]> data() {
        List<Object[]> list = new ArrayList<Object[]>();
        for (DyeColor dye : DyeColor.values()) {
            list.add(new Object[] {dye});
        }
        return list;
    }

    @Parameter public DyeColor dye;

    @Test
    @SuppressWarnings("deprecation")
    public void getByData() {
        byte data = dye.getWoolData();

        DyeColor byData = DyeColor.getByWoolData(data);
        assertThat(byData, is(dye));
    }

    @Test
    public void getByWoolData() {
        byte data = dye.getWoolData();

        DyeColor byData = DyeColor.getByWoolData(data);
        assertThat(byData, is(dye));
    }

    @Test
    public void getByDyeData() {
        byte data = dye.getDyeData();

        DyeColor byData = DyeColor.getByDyeData(data);
        assertThat(byData, is(dye));
    }

    @Test
    public void getDyeDyeColor() {
        testColorable(new Dye(Material.LEGACY_INK_SACK, dye.getDyeData()));
        testColorable(new Dye(dye));
    }

    @Test
    public void getWoolDyeColor() {
        testColorable(new Wool(Material.LEGACY_WOOL, dye.getWoolData()));
    }

    private void testColorable(final Colorable colorable) {
        assertThat(colorable.getColor(), is(this.dye));
    }
}
