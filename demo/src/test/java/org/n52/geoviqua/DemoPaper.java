
package org.n52.geoviqua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.geoviqua.gmd19157.DQDataQualityType;
import org.geoviqua.qualityInformationModel.x40.GVQDataQualityType;
import org.geoviqua.qualityInformationModel.x40.GVQDiscoveredIssueType;
import org.geoviqua.qualityInformationModel.x40.GVQMetadataDocument;
import org.geoviqua.qualityInformationModel.x40.GVQMetadataType;

public class DemoPaper {

    public static class MetadataQuality {

        public String scope;

        public String issue;

        public String solution;

    }

    public static class MetadataRecord {

        public String id;

        public String language;

        public String standard;

        public String standardVersion;

        public MetadataQuality quality;

        public Calendar date;
    }

    public static class Database {

        public MetadataRecord getRecord(String id) {
            MetadataRecord mr = new MetadataRecord();
            mr.id = "c0dc2fd0-88fd-11da-a88f-000d939bc5d8";
            mr.language = "eng";
            mr.date = Calendar.getInstance();
            mr.date.set(2011, 1, 17, 0, 0, 42);// "2011-02-17T00:00:42";
            mr.standard = "GeoViQua-QIM";
            mr.standardVersion = "4.0";
            mr.quality = new MetadataQuality();
            mr.quality.issue = "There is no provenance for this dataset.";
            mr.quality.solution = "Contact the provider directly for informal inquiries.";

            return mr;
        }
    }

    public static class CatalogClient {

        public CatalogClient(String url) {
            //
        }

        public boolean store(XmlObject record) {
            return true;
        }

    }

    private static XmlOptions options;

    private static ArrayList< ? > validationErrors = new ArrayList<Object>();

    static {
        options = new XmlOptions();
        options.setSavePrettyPrint();
        options.setSaveAggressiveNamespaces();

        HashMap<String, String> suggestedPrefixes = new HashMap<>();
        suggestedPrefixes.put("http://www.geoviqua.org/QualityInformationModel/4.0", "gvq");
        options.setSaveSuggestedPrefixes(suggestedPrefixes);

        options.setErrorListener(validationErrors);
    }

    public static void main(String[] args) {

        // retrieve a metadata record
        Database db = new Database();
        MetadataRecord record = db.getRecord("myInternalId");

        // create GVQ document
        GVQMetadataDocument doc = GVQMetadataDocument.Factory.newInstance();
        GVQMetadataType gvqMetadata = doc.addNewGVQMetadata();
        gvqMetadata.addNewLanguage().setCharacterString(record.language);
        gvqMetadata.addNewMetadataStandardName().setCharacterString(record.standard);
        gvqMetadata.addNewMetadataStandardVersion().setCharacterString(record.standardVersion);
        gvqMetadata.addNewDateStamp().setDate(record.date);
        DQDataQualityType quality = gvqMetadata.addNewDataQualityInfo2().addNewDQDataQuality();
        GVQDataQualityType gvqQuality = (GVQDataQualityType) quality.substitute(new QName("http://www.geoviqua.org/QualityInformationModel/4.0",
                                                                                          "GVQ_DataQuality"),
                                                                                GVQDataQualityType.type);
        GVQDiscoveredIssueType issue = gvqQuality.addNewDiscoveredIssue().addNewGVQDiscoveredIssue();
        issue.addNewKnownProblem().setCharacterString(record.quality.issue);
        issue.addNewWorkAround().setCharacterString(record.quality.solution);

        // validate schema conformity
        boolean isValid = doc.validate();
        if ( !isValid)
            System.out.println(Arrays.toString(validationErrors.toArray()));

        // print out as XML
        System.out.println(doc.xmlText(options));

        // store in catalog
        new CatalogClient("http://catalog.url/csw").store(doc);
    }

}
