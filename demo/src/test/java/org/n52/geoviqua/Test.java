
package org.n52.geoviqua;

import org.geoviqua.gmd19157.DQElementPropertyType;
import org.geoviqua.qualityInformationModel.x40.GVQDataQualityDocument;
import org.geoviqua.qualityInformationModel.x40.GVQDataQualityType;


public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {

        GVQDataQualityDocument dqd = GVQDataQualityDocument.Factory.newInstance();
        GVQDataQualityType gdq = dqd.addNewGVQDataQuality();
        DQElementPropertyType report = gdq.addNewReport();
        report.setTitle("Report Title");
        report.setRole("Rollller");
        report.addNewAbstractDQElement();

        System.out.println(dqd.xmlText());

        System.out.println(report.xmlText());
    }

}
