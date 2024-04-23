package transportation1;

/**
 * 歩き
 */
public class Walk extends Transportation{

  /**
   * コンストラクタ
   * @param p_depart
   * @param p_dest
   * @param t_depart
   * @param dist
   */
  public Walk(String p_depart, String p_dest, long t_depart, double dist) {
    super(p_depart, p_dest, t_depart, dist);
  }

  /**
   * コンストラクタ
   * @param tpPre
   * @param p_dest
   * @param dist
   */
  public Walk(Transportation tpPre, String p_dest, double dist) {
    super(tpPre, p_dest, dist);
  }
  
  /**
   * 移動手段の名前を取得
   * @return
   */
  protected String getTransportationName() {
    return "徒歩";
  }
  
  /**
   * 交通機関の状況を確認します。
   * @return
   */
  protected int checkTransportationCondition() {
    int ret = INTERNET_CONNECTION_SUCCESS;
    
    // 各交通機関の情報をインターネットから取得します解析します。
    System.out.println("天気予報の情報取得します。");
    
    // 交通機関が使用可能か確認する処理。 
    this.setAvailable(true); // 台風でも来ない限り、歩くのは自由ですね。
    
    return ret;
  }
  
  /**
   * 予測所要時間を計算
   * @return
   */
  protected void estimateTravelingTime() {
    final double speed = 5.0; // 平均時速5.0kmで移動した場合の移動時間を基本としています。
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
    
    //
    System.out.println("---------------");
    System.out.println("お天気は 🌤 です。");
  }
}
