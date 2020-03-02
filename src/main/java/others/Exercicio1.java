package others;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercicio1 {

    public class CEP {
        private String tipoCEP;
        private String cep;
        private String metodo;

        public CEP(String tipoCEP, String cep, String metodo){
            this.tipoCEP = tipoCEP.toLowerCase();
            this.cep = cep;
            this.metodo = metodo;
        }
    }
    public class Endereco{
        private int resultado;
        private String resultadoTxt;
        private String retorno;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String uf;
        private CEP cep;

        public Endereco(int resultado, String resultadoTxt, String retorno, String logradouro, String bairro, String cidade, String uf, CEP cep) {
            this.resultado = resultado;
            this.resultadoTxt = resultadoTxt;
            this.retorno = retorno;
            this.logradouro = logradouro;
            this.bairro = bairro;
            this.cidade = cidade;
            this.uf = uf;
            this.cep = cep;
        }
    }
    public String getJsonXml(String cep, String formato) throws MalformedURLException,IOException {
        Reader in = fazerRequisicao(cep);
        String resultadoRequisicao = ((BufferedReader) in).lines().collect(Collectors.joining());
        Document doc = Jsoup.parse(resultadoRequisicao);
        Elements paragraphs = doc.select(".caixacampobranco span.respostadestaque");
        Endereco endereco = null;
        if(paragraphs.size() > 2){
            endereco = new Endereco (1,"Sucesso cep completo",paragraphs.get(0),paragraphs.get(1),null,null,null,null);
        }
        if(paragraphs.size() < 2){
            endereco = new Endereco (2,"Sucesso cep unico",paragraphs.get(0),paragraphs.get(1),null,null,null,null);
        }
        else {

        }
    }
    public BufferedReader fazerRequisicao(String cep) throws MalformedURLException,IOException{
        URL url = new URL ("http://m.correios.com.br/movel/buscaCEPConfirma.do");
        CEP cepRequisicao = new CEP('',cep,'buscaCEP');
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(cepRequisicao, LinkedHashMap.class);
        StringBuilder postData = new StringBuilder();
        appendPostData(postData, map);
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        return new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

    }
    private void appendPostData(StringBuilder postData, Map<String, Object> map) throws UnsupportedEncodingException {
        for (Map.Entry<String,Object> param : map.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
    }

}
