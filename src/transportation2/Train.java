package transportation2;

/**
 * 電車
 */
public class Train extends Transportation{
  
  private long timeDelay = 0;
  private boolean isStationCrowded = false;
  
  /**
   * コンストラクタ
   * @param p_depart
   * @param p_dest
   * @param t_depart
   * @param dist
   */
  public Train(String p_depart, String p_dest, long t_depart, double dist) {
    super(p_depart, p_dest, t_depart, dist);
  }

  /**
   * コンストラクタ
   * @param tpPre
   * @param p_dest
   * @param dist
   */
  public Train(Transportation tpPre, String p_dest, double dist) {
    super(tpPre, p_dest, dist);
  }
  
  /**
   * 移動手段の名前を取得
   * @return
   */
  protected String getTransportationName() {
    return "電車";
  }
  
  /**
   * 交通機関の状況を確認します。
   * @return
   */
  protected int checkTransportationCondition() {
    int ret = INTERNET_CONNECTION_SUCCESS;
    
    // 各交通機関の情報をインターネットから取得します解析します。
    System.out.println("JRから運行情報を取得します。");
    
    // 交通機関が使用可能か確認する処理。 
    boolean available = (Math.random() > 0.4); // 実際のWEBAPIをたたくわけにいかないのでランダムで対応します。
    this.setAvailable(available); // 
    
    // 遅延情報があれば「遅延しています」等の情報を出してもよいかもしれません。
    this.timeDelay = 0; // 0の時は遅延はありません。
    
    // 駅の混雑情報なども取得できるかもしれません。
    this.isStationCrowded = (Math.random() > 0.4);
    
    return ret;
  }
  
  /**
   * 予測所要時間を計算
   * @return
   */
  protected void estimateTravelingTime() {
    final double speed = 80.0; // 平均時速80.0kmで移動した場合の移動時間を基本としています。
    final double distTrabel = this.getTravelDistance(); 
    final long estimate = (long)((distTrabel / speed) * (3600 * 1000));
    
    this.setTimeEstimateTravel(estimate); 
    this.setDestinationTime(getDepatureTime() + estimate);
  }
  
  /**
   * 情報を出力します。
   */
  public void showInformation() {
    // 親クラスの定型処理を呼び出します。
    super.showInformation();
    
    // 子クラスで追加の情報を出力することができます。
    System.out.println("---------------");
    if(timeDelay > 0) {
      System.out.println("遅延しています。");
    }
    if(isStationCrowded) {
      System.out.println("駅が混雑しています。");
    }
  }
}
