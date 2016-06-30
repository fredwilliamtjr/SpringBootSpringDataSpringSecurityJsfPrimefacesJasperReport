package br.com.fwtj.base.util.report;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
 
public class ReportFactory {
 
 private String reportName;
 private Map<String, Object> params;
 private List<?> list;
 
 public ReportFactory(String ReportName, Map<String, Object> params, List<?>list) {
 this.reportName = ReportName;
 this.params = params;
 this.list = list;
 }
 
 public InputStream getReportStream(){
 
 InputStream input = null;
 
 try {
 ByteArrayOutputStream output = new ByteArrayOutputStream();
 JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResourceAsStream("relatorios/"+reportName));
 jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
 JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(list);
 
 JasperPrint print1 = JasperFillManager.fillReport(jasperReport, params, datasource);
 JRExporter exporter = null;
 
 exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
 
 exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
 exporter.setParameter(JRExporterParameter.JASPER_PRINT, print1);
 exporter.exportReport();
 input = new ByteArrayInputStream(output.toByteArray());
 } catch (JRException ex) {
 Logger.getLogger(ReportFactory.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return input;
 
 }
 
}