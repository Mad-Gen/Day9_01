package animal;

/**
 * ライオンクラス
 */
public class Lion extends Cat {
  

  /**
   * コンストラクタ
   * @param name
   */
  public Lion(String name) {
    // Lion クラスのコンストラクタから Cat クラスのコンストラクタを呼び出して名前変数の設定をする必要があります。
    super(name);
  }
}
