package csvparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import kbbg.Kbbg;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

  public static String TYPE = "text/csv";
  static String[] HEADERs = {
    "plc",
    "rl100_code",
    "rl100_langname",
    "rl100_kurzname",
    "typ_kurz",
    "typ_lang",
    "betriebszustand",
    "datum_ab",
    "datum_bis",
    "niederlassung",
    "regionalbereich",
    "letzte_aenderung",
  };

  public static boolean hasCSVFormat(MultipartFile file) {
    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Kbbg> csvToKbbgs(InputStream is) {
    try (
      BufferedReader fileReader = new BufferedReader(
        new InputStreamReader(is, "UTF-8")
      );
      CSVParser csvParser = new CSVParser(
        fileReader,
        CSVFormat.DEFAULT
          .withFirstRecordAsHeader()
          .withIgnoreHeaderCase()
          .withAllowMissingColumnNames()
          .withIgnoreEmptyLines()
          .withTrim()
      );
    ) {
      List<Kbbg> kbbgs = new ArrayList<Kbbg>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      Integer dateTo;
      Integer dateFrom;
      Integer lastChange;
      Integer establishment;
      String region;
      String opSate;
      String typLong;
      String typ;
      String rl100Code;
      String rl100Short;
      String rl100Long;
      String plc;

      for (CSVRecord csvRecord : csvRecords) {
        try {
          lastChange = Integer.parseInt(csvRecord.get("Letzte Änderung"));
        } catch (Exception e) {
          lastChange = null;
        }
        try {
          region = csvRecord.get("Regionalbereich");
        } catch (Exception e) {
          region = "";
        }
        try {
          dateTo = Integer.parseInt(csvRecord.get("Datum bis"));
        } catch (Exception e) {
          dateTo = null;
        }
        try {
          dateFrom = Integer.parseInt(csvRecord.get("Datum ab"));
        } catch (Exception e) {
          dateFrom = null;
        }
        try {
          establishment = Integer.parseInt(csvRecord.get("Niederlassung"));
        } catch (Exception e) {
          establishment = null;
        }
        try {
          opSate = csvRecord.get("Betriebszustand");
        } catch (Exception e) {
          opSate = "";
        }
        try {
          typLong = csvRecord.get("Typ Lang");
        } catch (Exception e) {
          typLong = "";
        }
        try {
          typ = csvRecord.get("Typ Kurz");
        } catch (Exception e) {
          typ = "";
        }
        try {
          rl100Code = csvRecord.get("RL100-Code");
        } catch (Exception e) {
          rl100Code = "";
        }
        try {
          rl100Long = csvRecord.get("RL100-Langname");
        } catch (Exception e) {
          rl100Long = "";
        }
        try {
          rl100Short = csvRecord.get("RL100-Kurzname");
        } catch (Exception e) {
          rl100Short = "";
        }
        try {
          plc = csvRecord.get("PLC");
        } catch (Exception e) {
          plc = "";
        }

        Kbbg kbbg = new Kbbg(
          plc,
          rl100Code,
          rl100Long,
          rl100Short,
          typ,
          typLong,
          opSate,
          dateFrom,
          dateTo,
          establishment,
          region,
          lastChange
        );

        kbbgs.add(kbbg);
      }

      return kbbgs;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}
