/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.base.view;

import br.com.fwtj.base.model.Pessoa;
import br.com.fwtj.base.util.report.ReportFactory;
import br.com.fwtj.base.service.PessoaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller // Equivalente à @ManagedBean
@Scope("request") // Equivalente à @RequestScoped
public class PessoaImprimirBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    PessoaService pessoaService;

    public StreamedContent getSampleReportPDF() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", "Relatório de Pessoas");
        
        List<Pessoa> list = new ArrayList<Pessoa>();
        list = pessoaService.todasOrdenadaPorNome();

        ReportFactory rf = new ReportFactory("relatorioDePessoas.jasper", params, list);
        return new DefaultStreamedContent(rf.getReportStream(), "", "RelatorioDePessoas.pdf");
    }
}
