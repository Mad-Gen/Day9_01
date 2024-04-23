package animal;

/**
 * ネコ
 * 基本クラス
 */
// この Cat クラスを 抽象クラスにしてください。
public class Cat {

  public enum SEX{
    MALE, FEMALE, UNDEFINED
  }

  //
  private String name = null;
  private SEX sex = SEX.UNDEFINED;
  
  /**
   * コンストラクタ
   * @param _name
   */
  public Cat(String _name) {
    this.name = _name;
  }
  
  /**
   * 名前を取得
   * @return
   */
  public String getName() {
    return name;
  }
  
  /**
   * 性別をセット
   * @param _sex
   */
  public void setSex(SEX _sex) {
    this.sex = _sex;
  }
  
  /**
   * 性別を取得
   * @return
   */
  public SEX getSex() {
    return sex;
  }
  
  /**
   * ネコを行動させる
   */
  public final void action() {
    double random = Math.random();
    if(random > 0.5) {
      this.sleep();
    }else{
      this.play();
    }
  }
  
  /**
   * 寝る
   */
  protected void sleep() {
    // このメソッドを抽象メソッドにしてください。
    // 子クラスではこのメソッドをオーバーライドし、何かメッセージを出力してみましょう。
    //  例：)System.out.println("○○が寝ています...");
  }
  
  /**
   * 遊ぶ
   */
  protected void play() {
    // このメソッドを抽象メソッドにしてください。
    // 子クラスではこのメソッドをオーバーライドし、何かメッセージを出力してみましょう。
    //  System.out.println("○○が遊んでいます...");
  }
}
