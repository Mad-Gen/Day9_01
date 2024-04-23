package transportation1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 移動手段
 */
public class Transportation {
  
  public static int INTERNET_CONNECTION_SUCCESS = 0;
  public static int INTERNET_CONNECTION_FAILED = 101;
  
  private String strPlaceOfDestination = null;
  private String strPlaceOfDeparture = null;
  
  private long timeDepart = 0; // 出発時刻
  private long timeDest = 0; // 到着時刻
  private long timeEstimateTravel = 0; // 予想所要時間
  
  private double distTravel = 0.0; // 移動距離
  
  private boolean available = true; // 交通機関が使用可能か
  
  private Transportation tpPre = null;

  /**
   * コンストラクタ
   * @param p_depart
   * @param p_dest
   * @param t_depart
   * @param dist
   */
  public Transportation(String p_depart, String p_dest, long t_depart, double dist) {
    this.strPlaceOfDeparture = p_depart;
    this.strPlaceOfDestination = p_dest;
    this.timeDepart = t_depart;
    this.distTravel = dist;
  }
  
  /**
   * コンストラクタ
   * @param tpPre
   * @param p_dest
   * @param dist
   */
  public Transportation(Transportation tpPre, String p_dest, double dist) {
    this.tpPre = tpPre;
    this.strPlaceOfDestination = p_dest;
    this.distTravel = dist;
  }
  
  /**
   * 移動手段の名前を取得
   * @return
   */
  protected String getTransportationName() {
    return "移動";
  }
  
  /**
   * 出発地と時刻を設定
   * @param tp
   */
  public final void setDeparturePlaceAndTime(Transportation tp) {
    this.tpPre = tp;
  }
  
  /**
   * 出発時刻のセット
   * @param time
   */
  public final void setDepartureTime(long time) {
    this.timeDepart = time;
  }
  
  /**
   * 出発時刻の取得
   */
  public final long getDepatureTime() {
    return timeDepart;
  }
  
  /**
   * 出発地点をセット
   * @param str
   */
  public final void setDeparturePlace(String str) {
    this.strPlaceOfDeparture = str;
  }
  
  /**
   * 出発地点を取得
   * @param name
   * @return
   */
  public final String getDeparturePlace() {
    return strPlaceOfDeparture;
  }
  
  /**
   * 目的地点名を取得
   * @return
   */
  public final String getDestination() {
    return strPlaceOfDestination;
  }
  
  /**
   * 到着時刻のセット
   * @param time
   */
  public final void setDestinationTime(long time) {
    this.timeDest = time;
  }
  
  /**
   * 出発時刻の取得
   * @return
   */
  public final long getDestinationTime() {
    return timeDest;
  }
  
  /**
   * 移動の予測時間をセット
   * @param timeEstimateTravel
   */
  public final void setTimeEstimateTravel(long timeEstimateTravel) {
    this.timeEstimateTravel = timeEstimateTravel;
  }
  
  /**
   * 移動時間の予測を取得
   * @return
   */
  public final long getEstimateTravelTime() {
    return timeEstimateTravel;
  }

  /**
   * 移動距離を取得
   */
  public final double getTravelDistance() {
    return distTravel;
  }
  
  /**
   * その交通機関で使用可能かどうか
   * @return
   */
  public final boolean isAvailable() {
    return available;
  }
  
  /**
   * その交通機関が使用可能か
   * @param value
   */
  protected final void setAvailable(boolean value) {
    this.available = value;
  }
  
  // ****************************************************
  //　これより上はセッターゲッターです。
  // 継承に関連するメソッドはありません。
  // ****************************************************
  
  /**
   * 情報を最新に更新します。
   * @return
   */
  public final boolean updateInfomation() {
    //
    //　ひとつ前の交通手段の目的地と到着時間を、このインスタンスの出発地と設定する。
    if(tpPre != null) {
      this.setDepartureTime(tpPre.getDestinationTime());
      this.setDeparturePlace(tpPre.getDestination());
    }
    
    // 交通機関の情報をインターネットで調査します。
    if(checkTransportationCondition() != INTERNET_CONNECTION_SUCCESS) {
      return false; 
    }
    
    // 予測所要時間を計算
    this.estimateTravelingTime();
    
    return true;
  }
  
  /**
   * 交通機関の状況を確認します。
   * @return
   */
  protected int checkTransportationCondition() {
    int ret = INTERNET_CONNECTION_SUCCESS;
    
    // 各交通機関の情報をインターネットから取得します解析します。
    System.out.println("ネットで交通機関の情報取得します。");
    
    // 交通機関が使用可能か確認する処理。 
    this.setAvailable(true); // 通常はいつでも使えることにします。
    
    return ret;
  }
  
  /**
   * 予測所要時間を計算
   * @return
   */
  protected void estimateTravelingTime() {
    final double speed = 40.0; // 40km
    this.setTimeEstimateTravel((long)((distTravel / speed) * (3600 * 1000))); // 平均時速40.0kmで移動した場合の移動時間を基本としています。
    this.setDestinationTime(getDepatureTime() + timeEstimateTravel);
  }
  
  /**
   * 情報を出力します。
   */
  public void showInformation() {
    double e_time = getEstimateTravelTime() / (60 * 1000); // 分換算
    
    SimpleDateFormat fomatter = new SimpleDateFormat("HH:mm");
    String strDepartTime = fomatter.format(new Date(getDepatureTime()));
    String strDestTime = fomatter.format(new Date(getDestinationTime()));
    
    System.out.println("*****************************");
    System.out.println(getTransportationName());
    System.out.println("出発 : " + getDeparturePlace());
    System.out.println("到着 : " + getDestination());
    
    if(isAvailable()) {
      System.out.println("出発予想時刻 : " + strDepartTime);
      System.out.println("到着予想時刻 : " + strDestTime);
      System.out.println("移動距離 : " + getTravelDistance() + "km");
      System.out.println("所要時間 : " + e_time + "分"); 
    }else{
      System.out.println("現在利用できません。");
    }
  }
}
