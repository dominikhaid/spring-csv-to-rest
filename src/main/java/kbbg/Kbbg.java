package kbbg;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "kbbg")
public class Kbbg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "plc")
  private String plc;

  //DE13703

  @Column(name = "rl100_code")
  private String rl100Code;

  //SGRB

  @Column(name = "rl100_langname")
  private String rl100Long;

  //Gerolstein Basalt

  @Column(name = "rl100_kurzname")
  private String rl100Short;

  //Gerolst Basalt

  @Column(name = "typ_kurz")
  private String typ;

  //AWAN

  @Column(name = "typ_lang")
  private String typLong;

  //Awanst

  @Column(name = "betriebszustand")
  private String opSate;

  //Betrieb

  @Column(name = "datum_ab")
  private Integer dateFrom;

  //20081214

  @Column(name = "datum_bis")
  private Integer dateTo;

  //

  @Column(name = "niederlassung")
  private Integer establishment;

  //5

  @Column(name = "regionalbereich")
  private String region;

  //Mitte

  @Column(name = "letzte_aenderung")
  private Integer lastChange;

  //20200701

  public Kbbg() {}

  public Kbbg(
    String plc,
    String rl100Code,
    String rl100Long,
    String rl100Short,
    String typ,
    String typLong,
    String opSate,
    Integer dateFrom,
    Integer dateTo,
    Integer establishment,
    String region,
    Integer lastChange
  ) {
    this.plc = plc;
    this.rl100Code = rl100Code;
    this.rl100Long = rl100Long;
    this.rl100Short = rl100Short;
    this.typ = typ;
    this.typLong = typLong;
    this.opSate = opSate;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.establishment = establishment;
    this.region = region;
    this.lastChange = lastChange;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    return super.equals(obj);
  }
}
