package app;

import animal.Cat;
import animal.Lion;
import transportation1.Train;
import transportation1.Transportation;
import transportation1.Walk;
import transportation2.Recommend;

/**
 * Main.java
 */
public class Main {

  /**
   * エントリーポイント
   * @param args
   */
  public static void main(String[] args) {
    
    // ■ Java のポリモーフィズムを使ってコーディングした例です。
    //   Point1 : Transportation 型の変数に Train, Walk クラスのインスタンスが代入されています。
    //   Point2 : for ループの中では、クラスごとにif文で処理が分岐しているのではなく
    //          : updateInformation/showInfomation だけが呼ばれています。
    //          : このメソッドの処理はオーバーライドによって子クラスごとに書き換えられています。

    // 現在時刻を取得
    long now = System.currentTimeMillis();
    
    // 交通手段のクラスをインスタンス化
    Transportation t1 = new Train("大阪駅", "東京駅", now, 400.0); // 大阪から東京まで電車に乗ります。
    Transportation t2 = new Train(t1, "舞浜駅", 12.0); // 東京から舞浜まで電車に乗ります。
    Transportation t3 = new Walk(t2, "東京ディズニーランド", 0.8); // 舞浜からディズニーランドまで歩きます。
    
    // 配列にセット
    // 配列にセットしたことで繰り返し構文で、処理できるようになります!
    Transportation[] transWays = { t1, t2, t3 };
    
    // 情報を更新
    for(Transportation t : transWays) {
      // ポリモーフィズムの考え方を利用することによって、交通手段ごとにどのように情報収集・更新するかが異なっていても
      // たった一つのメソッドを呼び出すだけで良くなります。ifによる条件分岐やキャストを書かなくてもよくなりました!
      t.updateInfomation(); 
    }
    
    // 最新の情報を出力
    for(Transportation t : transWays) {
      t.showInformation();
    }
    
    
    // *****************************************
    // ★演習
    // Mini  : Eclipseで上記のコードを実行しどのようなコードになっているか、各グループで予想を立ててみましょう。
    //       : 各グループでどのメソッドがオーバーライドされているかやどんな実装になっているかなどを簡単にまとめてSlackにポストしていただけますか？
    // Full  : Walk.java, Train.java を参考に Plane.java, Taxi.java, Bycicle.java を作成してみましょう。
    //       : こちらもグループごとにSlackへポストしてください。
    //
    // EX    : 取り組んでおられるEXがあれば、残りの時間で作業なさってください。
    // *****************************************
    
    
    
    // ■　抽象クラス
    // これまで「親クラス」は、通常のクラスとして定義してきました。

    // Cat, Lion クラスの場合は以下のようにインスタンス化、ポリモーフィズムを実現します。
    Cat cat1 = new Cat("ネコさん");     // 親クラスである Cat クラスはこのようにインスタンス化が可能です。
    Cat cat2 = new Lion("ライオンさん"); // 子クラスである Lion クラスもこのようにインスタンス化が可能です。
    
    
    // では上の Transportation クラスへインスタンスを代入している箇所をご覧ください。 
    // new Transportation() がまったく呼ばれていません。
    //
    // 「電車、タクシー、飛行機」などの抽象化を突き詰めた結果、それらは「交通手段」という目に見えない「枠組み(考え方)」になりました。
    // Transportation は「クラスの動作の枠組み（考え方）」として存在するだけになり、インスタンス化されることはありません。
    
    
    // Javaではこのようなクラスを「抽象クラス」として定義します。
    // ※Transportationクラスは transpotation1,2 パッケージどちらにも存在しています。
    //   異なるパッケージには同じ名前のクラスを作成できますが、きちんと呼び分ける必要があります。
    
    // 抽象クラスとして定義すると、インスタンス化することはできません!!
    //transportation2.Transportation absTp1 = new transportation2.Transportation("東京", "盛岡", now, 700.0); // エラー!!
    transportation2.Transportation absTp2 = new transportation2.Train("東京", "盛岡", now, 700.0); // こちらはOK!
    
    
    // 抽象クラスには、未実装のメソッドがあるため必ず子クラスで処理を実装する必要があります。
    //absTp1.getTransportationName() // 未実装!
    //absTp1.checkTransportationCondition(); // 未実装!
    
    
    
    // *****************************************
    // ★演習
    // Mini  : transportation2 パッケージのクラスを参考に transportation1 パッケージの Transportation.java　を抽象クラス化してください。
    //       : 抽象クラス化した Transportation.java を子クラスで継承します。
    //       : App.java を新しく App パッケージに作成し、エントリーポイントを書きます。その中で Transportationの継承クラスを呼び出して実行してみましょう。
    //       
    // Full  : transportation1 パッケージのクラスを自由に機能拡張し、最新の経路状況が確認できるアプリのコンセプトモデルを作ってみましょう。
    //       : 可能であれば実際に何らかのWEBAPIを呼び出して情報を取得し、コンソールに出力することにも挑戦することもできます。
    //       : 野崎さんがSlackに投稿した課題を参考にできるかもしれませんね。
    //       : https://w1710911706-rxp487806.slack.com/archives/C06TG2VSHJ4/p1713426458046659
    //
    // EX    : 取り組んでおられるEXがあれば、残りの時間で作業なさってください。
    // *****************************************
    
    
    // ■　インターフェース
    // 私たちがよく使うインターフェースの一つに、「USB」や「コンセント」があります。
    //
    // 「USB」は「コンセント」などの特徴は...
    //   「差し込み口」と「プラグ」の形がピッタリあっていないと使えない
    // ことです。
    // 
    // 例えばUSB-MicroB と USB-C はインターフェースが異なるので刺さりません。
    // 日本のコンセントは、外国のコンセントに刺さらない場合もあります。
    
    // Walk クラスは　Recommend インターフェースを実装しています。
    // インターフェースには、メソッド名と引数だけが定義されています。
    // インターフェースを実装したクラスは、そのメソッドを必ず実装(やることはオーバーライドと同じです)する必要があります。
    transportation2.Walk absTp3 = new transportation2.Walk(absTp2, "盛岡", 600.0); 
    
    // Javaの「インターフェース」を使うと、「インターフェースの型」を差し込み口、代入するインスタンスを「プラグ」としてつかって以下のようにできます。
    // 図をご覧ください
    Recommend recommend = absTp3;
    recommend.showRecommend();
    
    //
    System.out.println();
    if(absTp3 instanceof Recommend) {
      System.out.println("absTp3 は Recommend インターフェースを実装しています。");
    }else {
      System.out.println("absTp3 は Recommend インターフェースを実装していません");
    }
    
    
    // メリット：
    // ● Javaは多重継承（2つの親クラスを指定できない）できませんが、インターフェースを使うとほぼ同じことができます。
    // ● インターフェースを使うとさらに「抽象化」を進め、他のクラスとの依存性が低いプログラムを作ることができます。
    
    
    //　「抽象クラス」と「インターフェース」の違い
    //
    // ▶ インターフェースは指定されたメソッドが必ず存在していることを保証しますが、どんな順番で実行されるかを定義することはできません。
    //    クラスの「輪郭」だけが決まっているイメージです。
    // ▶ 抽象クラスは、あるメソッドでまとめて他のメソッドを呼び出すプログラムを作成でき、実行順など処理の枠組みも決めることができます。
    
    
    
    // *****************************************
    // ★演習
    // Mini : transportation1 パッケージにCarbonFootprintインターフェースを作成し、CO2の排出量を返すメソッドを定義してください(単位と量は適当でよいです)。
    //      : 二酸化炭素が問題になっている移動方法を選んで、インターフェースを実装してみましょう。
    // Full : Main.javaの47行目のループに、移動時の二酸化炭素の排出量を計算する処理を追加してください。
    //      : 移動経路の出力が終わったら、二酸化炭素の総排出力を出力するようにしてみてください。
    //
    // EX   : 取り組んでおられるEXがあれば、残りの時間で作業なさってください。
    //      : 本日も「小さな進捗、新たな目標の設定」なんでもよいので、Slackで進捗の共有をお願いいたします!
    // *****************************************
  }
}
