package com.automationanywhere.botcommand.demo;

import com.automationanywhere.botcommand.data.Value;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DesaturateTest {

    Desaturate command = new Desaturate();

    @DataProvider(name = "cimages")
    public Object[][] dataTobeTested(){

        return new Object[][]{
                {20,"c:\\feedback.png","c:\\feedback_dt1.png", ""}
        };
    }

    @Test(dataProvider = "cimages")
    public void imageDesaturateTests(double dsatfactor,String inputImagePath, String OutputImagePath, String result){
        Value<String> d = command.action(dsatfactor,inputImagePath,OutputImagePath);
        String output = (String)d.get();
        //System.out.println("Debug:"+output);
        assertEquals(output,result);
    }
}
