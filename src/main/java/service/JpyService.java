package service;

import model.JPYModel;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JPYRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;
@Service
public class JpyService {

    @Autowired
    JPYRepository repository;
    public void fillRepositoryEveryHour() {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    repository.save(getElementFromDocument(getDocumentFromUrl(new URL("http://www.cbr.ru/scripts/XML_daily.asp"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }, 0, 1, TimeUnit.HOURS);
    }
    public List<JPYModel> getAll(){

        return repository.findAll();
    }
    public void save(JPYModel jpyModel){

        repository.save(jpyModel);
    }
    public JPYModel getLast(){
        return repository.findTopByOrderByTimeReceiptDesc();
    }
    public Document getDocumentFromUrl(URL url) throws IOException {
            InputStreamReader isr = new InputStreamReader(url.openStream(),"windows-1251");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                sb.append(br.readLine()).append("\n");
            }
            br.close();
        return Jsoup.parse(sb.toString(), "", Parser.xmlParser());
  }
  public JPYModel getElementFromDocument(Document document){
      Element element = document.getElementById("R01820");
      JPYModel jpyModel = new JPYModel();
      jpyModel.setNominal(Integer.parseInt(element.child(2).html()));
      jpyModel.setValue(Double.parseDouble(element.child(4).html().replace(",",".")));
      return jpyModel;
  }

}
