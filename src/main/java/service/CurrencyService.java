package service;

import model.CurrencyModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CurrencyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;
@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository repository;
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
    public List<CurrencyModel> getAll(){

        return repository.findAll();
    }
    public void save(CurrencyModel currencyModel){

        repository.save(currencyModel);
    }
    public CurrencyModel getLast(){
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
  public CurrencyModel getElementFromDocument(Document document){
      Element element = document.getElementById("R01820");
      CurrencyModel currencyModel = new CurrencyModel();
      currencyModel.setNominal(Integer.parseInt(element.child(2).html()));
      currencyModel.setValue(Double.parseDouble(element.child(4).html().replace(",",".")));
      return currencyModel;
  }

}
