import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MyTest {


    @Test
    public void test() {

        String spu = "642849,237970,648526,537126,582306,670587,633653,655006,407890,653423,57654,555525,478456,106535,641450,88159,600363,603952,624115,453388,658095,658303,663598,665236,666651,667974,641848,634607,269141,637587,403056,642987,451197,625684";
        String sku = "478456,207753,654767,645611,651789,582306,642987,670470,407890,57654,416735,642849,653423,641666,385728,641848,648526,631599,625684,624115,642922,453388,600363,652043,629985,655006,623594,642666,585340,657512,658095,658303,663598,666651,667306";

        String[] spuTenantIds = spu.split(",");

        String[] skuTenantIds = sku.split(",");


        Set<String> spus = Sets.newHashSet(Arrays.asList(spuTenantIds));
        Set<String> skus = Sets.newHashSet(Arrays.asList(skuTenantIds));
        System.out.println(skus);


        System.out.println("商品产品都有值的,共:" + Sets.intersection(spus, skus).size() + "\n" + Sets.intersection(spus, skus));

        spus.addAll(skus);
        System.out.println("全部企业,共:" + spus.size() + "\n" + spus);
    }

    @Test
    public void test01() {
        String corpId = "449094";
        int i = Math.abs(corpId.hashCode() % 100);
        System.out.println(i);
    }

    @Test
    public void test001() {
        List<String> list = Lists.newArrayListWithCapacity(10);

        System.out.println(list);
    }


}
